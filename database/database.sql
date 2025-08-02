-- RSA Database Schema
-- Create the database
CREATE DATABASE IF NOT EXISTS rsa_db;
USE rsa_db;

-- Create users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password_hash VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create fuel delivery requests table
CREATE TABLE fuel_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    location TEXT NOT NULL,
    fuel_type ENUM('petrol', 'diesel', 'premium') NOT NULL,
    amount_liters INT NOT NULL,
    vehicle_details TEXT NOT NULL,
    emergency_contact VARCHAR(20),
    status ENUM('pending', 'confirmed', 'in_progress', 'completed', 'cancelled') DEFAULT 'pending',
    estimated_delivery_time TIMESTAMP,
    actual_delivery_time TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Create breakdown assistance requests table
CREATE TABLE breakdown_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    location TEXT NOT NULL,
    issue_type ENUM('flat-tire', 'battery', 'engine', 'lockout', 'towing', 'other') NOT NULL,
    vehicle_details TEXT NOT NULL,
    problem_description TEXT,
    emergency_contact VARCHAR(20),
    status ENUM('pending', 'assigned', 'in_progress', 'completed', 'cancelled') DEFAULT 'pending',
    assigned_mechanic_id INT,
    estimated_arrival_time TIMESTAMP,
    actual_arrival_time TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Create mechanics table
CREATE TABLE mechanics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    specialization ENUM('general', 'electrical', 'engine', 'tire', 'battery', 'locksmith') NOT NULL,
    status ENUM('available', 'busy', 'offline') DEFAULT 'available',
    current_location_lat DECIMAL(10, 8),
    current_location_lng DECIMAL(11, 8),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create fuel delivery vehicles table
CREATE TABLE fuel_vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_number VARCHAR(20) UNIQUE NOT NULL,
    fuel_capacity_liters INT NOT NULL,
    current_fuel_level_liters INT DEFAULT 0,
    fuel_types_available JSON,
    driver_name VARCHAR(100),
    driver_phone VARCHAR(20),
    status ENUM('available', 'busy', 'maintenance', 'offline') DEFAULT 'available',
    current_location_lat DECIMAL(10, 8),
    current_location_lng DECIMAL(11, 8),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create contact messages table
CREATE TABLE contact_messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    message TEXT NOT NULL,
    status ENUM('new', 'read', 'replied', 'closed') DEFAULT 'new',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create service areas table
CREATE TABLE service_areas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    area_name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) DEFAULT 'USA',
    coverage_radius_km DECIMAL(5,2) DEFAULT 50.00,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create pricing table
CREATE TABLE pricing (
    id INT AUTO_INCREMENT PRIMARY KEY,
    service_type ENUM('fuel_delivery', 'breakdown_assistance', 'towing') NOT NULL,
    fuel_type ENUM('petrol', 'diesel', 'premium') NULL,
    base_price DECIMAL(10,2) NOT NULL,
    price_per_unit DECIMAL(10,2),
    unit_type ENUM('liter', 'hour', 'km') NULL,
    area_id INT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (area_id) REFERENCES service_areas(id) ON DELETE SET NULL
);

-- Insert sample data

-- Sample service areas
INSERT INTO service_areas (area_name, city, state, coverage_radius_km) VALUES
('Downtown Area', 'New York', 'NY', 30.00),
('Suburban Area', 'Los Angeles', 'CA', 45.00),
('Highway Coverage', 'Chicago', 'IL', 60.00);

-- Sample pricing
INSERT INTO pricing (service_type, fuel_type, base_price, price_per_unit, unit_type, area_id) VALUES
('fuel_delivery', 'petrol', 15.00, 1.20, 'liter', 1),
('fuel_delivery', 'diesel', 15.00, 1.35, 'liter', 1),
('fuel_delivery', 'premium', 15.00, 1.50, 'liter', 1),
('breakdown_assistance', NULL, 50.00, 25.00, 'hour', 1),
('towing', NULL, 75.00, 2.50, 'km', 1);

-- Sample mechanics
INSERT INTO mechanics (name, phone, email, specialization, status) VALUES
('John Smith', '+1-555-0101', 'john.smith@rsa.com', 'general', 'available'),
('Mike Johnson', '+1-555-0102', 'mike.johnson@rsa.com', 'electrical', 'available'),
('Sarah Wilson', '+1-555-0103', 'sarah.wilson@rsa.com', 'engine', 'available'),
('David Brown', '+1-555-0104', 'david.brown@rsa.com', 'tire', 'available'),
('Lisa Davis', '+1-555-0105', 'lisa.davis@rsa.com', 'battery', 'available');

-- Sample fuel vehicles
INSERT INTO fuel_vehicles (vehicle_number, fuel_capacity_liters, current_fuel_level_liters, fuel_types_available, driver_name, driver_phone, status) VALUES
('FD-001', 1000, 800, '["petrol", "diesel"]', 'Tom Anderson', '+1-555-0201', 'available'),
('FD-002', 1200, 900, '["petrol", "premium"]', 'Jerry White', '+1-555-0202', 'available'),
('FD-003', 800, 600, '["diesel"]', 'Mary Johnson', '+1-555-0203', 'available');

-- Create indexes for better performance
CREATE INDEX idx_fuel_requests_status ON fuel_requests(status);
CREATE INDEX idx_fuel_requests_created_at ON fuel_requests(created_at);
CREATE INDEX idx_breakdown_requests_status ON breakdown_requests(status);
CREATE INDEX idx_breakdown_requests_created_at ON breakdown_requests(created_at);
CREATE INDEX idx_mechanics_status ON mechanics(status);
CREATE INDEX idx_fuel_vehicles_status ON fuel_vehicles(status);
CREATE INDEX idx_contact_messages_status ON contact_messages(status);

-- Create views for common queries
CREATE VIEW active_requests AS
SELECT 
    'fuel' as request_type,
    id,
    name,
    phone,
    location,
    status,
    created_at
FROM fuel_requests 
WHERE status IN ('pending', 'confirmed', 'in_progress')
UNION ALL
SELECT 
    'breakdown' as request_type,
    id,
    name,
    phone,
    location,
    status,
    created_at
FROM breakdown_requests 
WHERE status IN ('pending', 'assigned', 'in_progress');

-- Create stored procedure for getting available mechanics
DELIMITER //
CREATE PROCEDURE GetAvailableMechanics(IN issue_type VARCHAR(20))
BEGIN
    SELECT id, name, phone, specialization, current_location_lat, current_location_lng
    FROM mechanics 
    WHERE status = 'available' 
    AND (specialization = issue_type OR specialization = 'general')
    ORDER BY RAND()
    LIMIT 5;
END //
DELIMITER ;

-- Create stored procedure for getting available fuel vehicles
DELIMITER //
CREATE PROCEDURE GetAvailableFuelVehicles(IN fuel_type VARCHAR(20))
BEGIN
    SELECT id, vehicle_number, fuel_capacity_liters, current_fuel_level_liters, 
           driver_name, driver_phone, current_location_lat, current_location_lng
    FROM fuel_vehicles 
    WHERE status = 'available' 
    AND JSON_CONTAINS(fuel_types_available, CONCAT('"', fuel_type, '"'))
    ORDER BY RAND()
    LIMIT 3;
END //
DELIMITER ; 