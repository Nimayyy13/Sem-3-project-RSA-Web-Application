#!/usr/bin/env python3
"""
RSA Application Setup Script
Installs dependencies and provides setup instructions
"""

import subprocess
import sys
import os

def install_requirements():
    """Install Python requirements"""
    print("📦 Installing Python dependencies...")
    try:
        subprocess.check_call([sys.executable, "-m", "pip", "install", "-r", "requirements.txt"])
        print("✅ Python dependencies installed successfully")
        return True
    except subprocess.CalledProcessError as e:
        print(f"❌ Error installing dependencies: {e}")
        return False

def check_mysql():
    """Check if MySQL is running"""
    print("🔍 Checking MySQL connection...")
    try:
        import mysql.connector
        connection = mysql.connector.connect(
            host='localhost',
            user='rsa_user',
            password='sanskruti14',
            database='rsa_db'
        )
        connection.close()
        print("✅ MySQL connection successful")
        return True
    except Exception as e:
        print(f"❌ MySQL connection failed: {e}")
        print("\n📋 Please set up MySQL database:")
        print("1. Install MySQL Server")
        print("2. Create database and user:")
        print("   CREATE DATABASE rsa_db;")
        print("   CREATE USER 'rsa_user'@'localhost' IDENTIFIED BY 'sanskruti14';")
        print("   GRANT ALL PRIVILEGES ON rsa_db.* TO 'rsa_user'@'localhost';")
        print("   FLUSH PRIVILEGES;")
        return False

def main():
    print("==================================================")
    print("🛣️  RSA Application Setup")
    print("==================================================")
    
    # Install Python dependencies
    if not install_requirements():
        print("❌ Setup failed. Please check the errors above.")
        return
    
    # Check MySQL connection
    if not check_mysql():
        print("\n⚠️  Please set up MySQL before running the application")
        print("After setting up MySQL, run this script again.")
        return
    
    print("\n✅ Setup completed successfully!")
    print("\n🚀 To run the application:")
    print("1. Start the backend server: python server.py")
    print("2. Start the frontend server: python start_server.py")
    print("3. Open your browser to: http://localhost:8000")
    print("\n📱 The backend API will be available at: http://localhost:5000")

if __name__ == "__main__":
    main() 