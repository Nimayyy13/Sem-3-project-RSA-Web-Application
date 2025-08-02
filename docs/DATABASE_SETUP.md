# RSA Application Database Setup Guide

## 🗄️ Database Setup Instructions

### Prerequisites
- MySQL Server 5.7 or higher
- Python 3.7 or higher
- pip (Python package installer)

### Step 1: Install MySQL Server

#### Windows:
1. Download MySQL Server from: https://dev.mysql.com/downloads/mysql/
2. Run the installer and follow the setup wizard
3. Remember the root password you set during installation

#### macOS:
```bash
brew install mysql
brew services start mysql
```

#### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
sudo systemctl enable mysql
```

### Step 2: Create Database and User

1. **Connect to MySQL as root:**
   ```bash
   mysql -u root -p
   ```
   Enter your root password when prompted.

2. **Create the database and user:**
   ```sql
   CREATE DATABASE rsa_db;
   CREATE USER 'rsa_user'@'localhost' IDENTIFIED BY 'sanskruti14';
   GRANT ALL PRIVILEGES ON rsa_db.* TO 'rsa_user'@'localhost';
   FLUSH PRIVILEGES;
   EXIT;
   ```

### Step 3: Install Python Dependencies

1. **Install required Python packages:**
   ```bash
   pip install -r requirements.txt
   ```

   Or run the setup script:
   ```bash
   python setup.py
   ```

### Step 4: Run the Application

1. **Start the backend server (in one terminal):**
   ```bash
   python server.py
   ```
   This will:
   - Initialize the database tables
   - Insert sample data
   - Start the Flask API server on port 5000

2. **Start the frontend server (in another terminal):**
   ```bash
   python start_server.py
   ```
   This will serve the web application on port 8000

3. **Access the application:**
   - Frontend: http://localhost:8000
   - Backend API: http://localhost:5000

## 📊 Database Schema

The application creates the following tables:

### `users`
- User registration and authentication
- Stores user profiles and contact information

### `fuel_requests`
- Emergency fuel delivery requests
- Tracks request status and delivery times

### `breakdown_requests`
- Vehicle breakdown assistance requests
- Stores issue descriptions and mechanic assignments

### `contact_messages`
- Contact form submissions
- Customer inquiries and support messages

### `mechanics`
- Available mechanics and their specializations
- Location tracking for service assignment

### `fuel_vehicles`
- Fuel delivery vehicles
- Capacity and location tracking

### `service_areas`
- Geographic service coverage areas
- Coverage radius and availability

### `pricing`
- Service pricing information
- Base prices and per-unit rates

## 🔧 API Endpoints

### Save Requests
- `POST /api/save-request` - Save form submissions to database

### Retrieve Data
- `GET /api/requests` - Get all requests (admin)
- `GET /api/mechanics` - Get available mechanics
- `GET /api/fuel-vehicles` - Get available fuel vehicles
- `GET /api/pricing` - Get pricing information

## 🚨 Troubleshooting

### Database Connection Issues
1. **Check MySQL service:**
   ```bash
   # Windows
   net start mysql
   
   # macOS
   brew services start mysql
   
   # Linux
   sudo systemctl start mysql
   ```

2. **Verify credentials:**
   ```bash
   mysql -u rsa_user -p rsa_db
   ```
   Enter password: `sanskruti14`

3. **Check database exists:**
   ```sql
   SHOW DATABASES;
   USE rsa_db;
   SHOW TABLES;
   ```

### Python Dependencies Issues
1. **Update pip:**
   ```bash
   python -m pip install --upgrade pip
   ```

2. **Install dependencies manually:**
   ```bash
   pip install Flask==2.3.3
   pip install Flask-CORS==4.0.0
   pip install mysql-connector-python==8.1.0
   ```

### Port Issues
- If port 5000 is in use, modify `server.py` line 350:
  ```python
  app.run(host='0.0.0.0', port=5001, debug=True)  # Change port
  ```
- Update `script.js` line 200:
  ```javascript
  const response = await fetch('http://localhost:5001/api/save-request', {
  ```

## 📱 Testing the Application

1. **Fill out forms:**
   - Emergency Fuel Delivery
   - Breakdown Assistance
   - Contact Form

2. **Check database:**
   ```sql
   USE rsa_db;
   SELECT * FROM fuel_requests;
   SELECT * FROM breakdown_requests;
   SELECT * FROM contact_messages;
   ```

3. **View API responses:**
   - Open browser developer tools
   - Check Network tab for API calls
   - Verify successful database saves

## 🔒 Security Notes

- Change default passwords in production
- Use environment variables for database credentials
- Enable SSL for database connections
- Implement proper authentication for admin endpoints

## 📞 Support

If you encounter issues:
1. Check the console output for error messages
2. Verify MySQL service is running
3. Confirm database credentials are correct
4. Ensure all Python dependencies are installed 