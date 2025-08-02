#!/usr/bin/env python3
"""
RSA Application Backend Server
Handles form submissions and database operations
"""

from flask import Flask, request, jsonify, send_from_directory
from flask_cors import CORS
import mysql.connector
import json
import os
from datetime import datetime
import logging

# Configure logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

# Database configuration
DB_CONFIG = {
    'host': 'localhost',
    'user': 'rsa_user',
    'password': 'sanskruti14',
    'database': 'rsa_db',
    'port': 3306
}

def get_db_connection():
    """Create and return database connection"""
    try:
        connection = mysql.connector.connect(**DB_CONFIG)
        return connection
    except mysql.connector.Error as err:
        logger.error(f"Database connection error: {err}")
        return None

def init_database():
    """Initialize database tables if they don't exist"""
    connection = get_db_connection()
    if not connection:
        logger.error("Could not connect to database")
        return False
    
    try:
        cursor = connection.cursor()
        
        # Create tables if they don't exist
        tables = {
            'users': '''
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    phone VARCHAR(20) NOT NULL,
                    password_hash VARCHAR(255),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            ''',
            'fuel_requests': '''
                CREATE TABLE IF NOT EXISTS fuel_requests (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT,
                    name VARCHAR(100) NOT NULL,
                    phone VARCHAR(20) NOT NULL,
                    location VARCHAR(255) NOT NULL,
                    fuel_type ENUM('petrol', 'diesel', 'cng') NOT NULL,
                    amount_liters INT NOT NULL,
                    vehicle_details TEXT,
                    emergency_contact VARCHAR(20),
                    status ENUM('pending', 'assigned', 'in_progress', 'completed', 'cancelled') DEFAULT 'pending',
                    estimated_delivery_time TIMESTAMP NULL,
                    actual_delivery_time TIMESTAMP NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
                )
            ''',
            'breakdown_requests': '''
                CREATE TABLE IF NOT EXISTS breakdown_requests (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT,
                    name VARCHAR(100) NOT NULL,
                    phone VARCHAR(20) NOT NULL,
                    location VARCHAR(255) NOT NULL,
                    issue_description TEXT NOT NULL,
                    vehicle_details TEXT,
                    emergency_contact VARCHAR(20),
                    status ENUM('pending', 'assigned', 'in_progress', 'completed', 'cancelled') DEFAULT 'pending',
                    estimated_arrival_time TIMESTAMP NULL,
                    actual_arrival_time TIMESTAMP NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
                )
            ''',
            'contact_messages': '''
                CREATE TABLE IF NOT EXISTS contact_messages (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL,
                    phone VARCHAR(20) NOT NULL,
                    message TEXT NOT NULL,
                    status ENUM('unread', 'read', 'replied') DEFAULT 'unread',
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            ''',
            'mechanics': '''
                CREATE TABLE IF NOT EXISTS mechanics (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    phone VARCHAR(20) NOT NULL,
                    specialization VARCHAR(100),
                    location VARCHAR(255),
                    is_available BOOLEAN DEFAULT TRUE,
                    current_latitude DECIMAL(10, 8),
                    current_longitude DECIMAL(11, 8),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            ''',
            'fuel_vehicles': '''
                CREATE TABLE IF NOT EXISTS fuel_vehicles (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    vehicle_number VARCHAR(20) UNIQUE NOT NULL,
                    fuel_type ENUM('petrol', 'diesel', 'cng') NOT NULL,
                    capacity_liters INT NOT NULL,
                    current_latitude DECIMAL(10, 8),
                    current_longitude DECIMAL(11, 8),
                    is_available BOOLEAN DEFAULT TRUE,
                    driver_name VARCHAR(100),
                    driver_phone VARCHAR(20),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            ''',
            'service_areas': '''
                CREATE TABLE IF NOT EXISTS service_areas (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    area_name VARCHAR(100) NOT NULL,
                    city VARCHAR(100) NOT NULL,
                    state VARCHAR(100) NOT NULL,
                    coverage_radius_km DECIMAL(5, 2),
                    is_active BOOLEAN DEFAULT TRUE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            ''',
            'pricing': '''
                CREATE TABLE IF NOT EXISTS pricing (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    service_type ENUM('fuel_delivery', 'breakdown_assistance', 'towing') NOT NULL,
                    fuel_type ENUM('petrol', 'diesel', 'cng') NULL,
                    base_price DECIMAL(10, 2) NOT NULL,
                    per_liter_price DECIMAL(8, 2) NULL,
                    per_km_price DECIMAL(8, 2) NULL,
                    is_active BOOLEAN DEFAULT TRUE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            '''
        }
        
        for table_name, create_sql in tables.items():
            cursor.execute(create_sql)
            logger.info(f"Table {table_name} created/verified")
        
        # Insert sample data if tables are empty
        cursor.execute("SELECT COUNT(*) FROM mechanics")
        if cursor.fetchone()[0] == 0:
            sample_mechanics = [
                ('John Smith', '+1234567890', 'Engine Repair', 'Downtown', True, 40.7128, -74.0060),
                ('Mike Johnson', '+1234567891', 'Electrical Systems', 'Uptown', True, 40.7589, -73.9851),
                ('David Wilson', '+1234567892', 'Transmission', 'Midtown', True, 40.7505, -73.9934)
            ]
            for mechanic in sample_mechanics:
                cursor.execute('''
                    INSERT INTO mechanics (name, phone, specialization, location, is_available, current_latitude, current_longitude)
                    VALUES (%s, %s, %s, %s, %s, %s, %s)
                ''', mechanic)
        
        cursor.execute("SELECT COUNT(*) FROM fuel_vehicles")
        if cursor.fetchone()[0] == 0:
            sample_vehicles = [
                ('FV001', 'petrol', 200, 40.7128, -74.0060, True, 'Tom Driver', '+1234567893'),
                ('FV002', 'diesel', 300, 40.7589, -73.9851, True, 'Jerry Driver', '+1234567894'),
                ('FV003', 'cng', 150, 40.7505, -73.9934, True, 'Sam Driver', '+1234567895')
            ]
            for vehicle in sample_vehicles:
                cursor.execute('''
                    INSERT INTO fuel_vehicles (vehicle_number, fuel_type, capacity_liters, current_latitude, current_longitude, is_available, driver_name, driver_phone)
                    VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
                ''', vehicle)
        
        cursor.execute("SELECT COUNT(*) FROM pricing")
        if cursor.fetchone()[0] == 0:
            sample_pricing = [
                ('fuel_delivery', 'petrol', 25.00, 2.50, None),
                ('fuel_delivery', 'diesel', 25.00, 2.20, None),
                ('fuel_delivery', 'cng', 25.00, 1.80, None),
                ('breakdown_assistance', None, 50.00, None, 2.00),
                ('towing', None, 75.00, None, 3.50)
            ]
            for price in sample_pricing:
                cursor.execute('''
                    INSERT INTO pricing (service_type, fuel_type, base_price, per_liter_price, per_km_price)
                    VALUES (%s, %s, %s, %s, %s)
                ''', price)
        
        connection.commit()
        logger.info("Database initialized successfully")
        return True
        
    except mysql.connector.Error as err:
        logger.error(f"Database initialization error: {err}")
        return False
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/')
def serve_index():
    """Serve the main HTML file"""
    return send_from_directory('../frontend', 'index.html')

@app.route('/<path:filename>')
def serve_static(filename):
    """Serve static files (CSS, JS)"""
    return send_from_directory('../frontend', filename)

@app.route('/api/save-request', methods=['POST'])
def save_request():
    """Handle form submissions and save to database"""
    try:
        data = request.get_json()
        request_type = data.get('type')
        
        connection = get_db_connection()
        if not connection:
            return jsonify({'error': 'Database connection failed'}), 500
        
        cursor = connection.cursor()
        
        if request_type == 'fuel-delivery':
            # Save fuel delivery request
            sql = '''
                INSERT INTO fuel_requests (name, phone, location, fuel_type, amount_liters, vehicle_details, emergency_contact)
                VALUES (%s, %s, %s, %s, %s, %s, %s)
            '''
            values = (
                data.get('name'),
                data.get('phone'),
                data.get('location'),
                data.get('fuelType'),
                int(data.get('amount')),
                data.get('vehicle'),
                data.get('emergency')
            )
            
        elif request_type == 'breakdown-assistance':
            # Save breakdown request
            sql = '''
                INSERT INTO breakdown_requests (name, phone, location, issue_description, vehicle_details, emergency_contact)
                VALUES (%s, %s, %s, %s, %s, %s)
            '''
            values = (
                data.get('name'),
                data.get('phone'),
                data.get('location'),
                data.get('issue'),
                data.get('vehicle'),
                data.get('emergency')
            )
            
        elif request_type == 'contact-message':
            # Save contact message
            sql = '''
                INSERT INTO contact_messages (name, email, phone, message)
                VALUES (%s, %s, %s, %s)
            '''
            values = (
                data.get('name'),
                data.get('email'),
                data.get('phone'),
                data.get('message')
            )
            
        else:
            return jsonify({'error': 'Invalid request type'}), 400
        
        cursor.execute(sql, values)
        connection.commit()
        
        # Get the inserted ID
        request_id = cursor.lastrowid
        
        logger.info(f"Saved {request_type} request with ID: {request_id}")
        
        return jsonify({
            'success': True,
            'message': f'{request_type.replace("-", " ").title()} request saved successfully',
            'request_id': request_id
        })
        
    except mysql.connector.Error as err:
        logger.error(f"Database error: {err}")
        return jsonify({'error': 'Database error occurred'}), 500
    except Exception as err:
        logger.error(f"Unexpected error: {err}")
        return jsonify({'error': 'An unexpected error occurred'}), 500
    finally:
        if 'connection' in locals() and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/requests', methods=['GET'])
def get_requests():
    """Get all requests (for admin purposes)"""
    try:
        connection = get_db_connection()
        if not connection:
            return jsonify({'error': 'Database connection failed'}), 500
        
        cursor = connection.cursor(dictionary=True)
        
        # Get fuel requests
        cursor.execute('''
            SELECT * FROM fuel_requests 
            ORDER BY created_at DESC 
            LIMIT 50
        ''')
        fuel_requests = cursor.fetchall()
        
        # Get breakdown requests
        cursor.execute('''
            SELECT * FROM breakdown_requests 
            ORDER BY created_at DESC 
            LIMIT 50
        ''')
        breakdown_requests = cursor.fetchall()
        
        # Get contact messages
        cursor.execute('''
            SELECT * FROM contact_messages 
            ORDER BY created_at DESC 
            LIMIT 50
        ''')
        contact_messages = cursor.fetchall()
        
        return jsonify({
            'fuel_requests': fuel_requests,
            'breakdown_requests': breakdown_requests,
            'contact_messages': contact_messages
        })
        
    except mysql.connector.Error as err:
        logger.error(f"Database error: {err}")
        return jsonify({'error': 'Database error occurred'}), 500
    finally:
        if 'connection' in locals() and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/mechanics', methods=['GET'])
def get_mechanics():
    """Get available mechanics"""
    try:
        connection = get_db_connection()
        if not connection:
            return jsonify({'error': 'Database connection failed'}), 500
        
        cursor = connection.cursor(dictionary=True)
        cursor.execute('SELECT * FROM mechanics WHERE is_available = TRUE')
        mechanics = cursor.fetchall()
        
        return jsonify({'mechanics': mechanics})
        
    except mysql.connector.Error as err:
        logger.error(f"Database error: {err}")
        return jsonify({'error': 'Database error occurred'}), 500
    finally:
        if 'connection' in locals() and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/fuel-vehicles', methods=['GET'])
def get_fuel_vehicles():
    """Get available fuel vehicles"""
    try:
        connection = get_db_connection()
        if not connection:
            return jsonify({'error': 'Database connection failed'}), 500
        
        cursor = connection.cursor(dictionary=True)
        cursor.execute('SELECT * FROM fuel_vehicles WHERE is_available = TRUE')
        vehicles = cursor.fetchall()
        
        return jsonify({'vehicles': vehicles})
        
    except mysql.connector.Error as err:
        logger.error(f"Database error: {err}")
        return jsonify({'error': 'Database error occurred'}), 500
    finally:
        if 'connection' in locals() and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/pricing', methods=['GET'])
def get_pricing():
    """Get pricing information"""
    try:
        connection = get_db_connection()
        if not connection:
            return jsonify({'error': 'Database connection failed'}), 500
        
        cursor = connection.cursor(dictionary=True)
        cursor.execute('SELECT * FROM pricing WHERE is_active = TRUE')
        pricing = cursor.fetchall()
        
        return jsonify({'pricing': pricing})
        
    except mysql.connector.Error as err:
        logger.error(f"Database error: {err}")
        return jsonify({'error': 'Database error occurred'}), 500
    finally:
        if 'connection' in locals() and connection.is_connected():
            cursor.close()
            connection.close()

if __name__ == '__main__':
    print("==================================================")
    print("🛣️  RSA Application Backend Server")
    print("==================================================")
    
    # Initialize database
    print("🔧 Initializing database...")
    if init_database():
        print("✅ Database initialized successfully")
    else:
        print("❌ Database initialization failed")
        print("Please make sure MySQL is running and the database is set up correctly")
        exit(1)
    
    print("🚀 Starting RSA Backend Server...")
    print("📱 Backend API available at: http://localhost:5000")
    print("🌐 Frontend available at: http://localhost:8000")
    print("⏹️  Press Ctrl+C to stop the server")
    print("--------------------------------------------------")
    
    app.run(host='0.0.0.0', port=5000, debug=True) 