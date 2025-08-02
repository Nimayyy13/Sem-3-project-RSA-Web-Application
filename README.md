# 🚗 Road Side Assistance (RSA) Web Application

A comprehensive web-based technology platform for emergency fuel delivery and vehicle breakdown assistance services.

![RSA Application](https://img.shields.io/badge/Status-Active-brightgreen)
![Python](https://img.shields.io/badge/Python-3.7+-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-orange)
![Flask](https://img.shields.io/badge/Flask-2.3+-red)

## 🌟 Features

### Core Services
- **🚨 Emergency Fuel Delivery**: 24/7 fuel delivery to your location within 30 minutes
- **🔧 Breakdown Assistance**: Professional mechanics for vehicle repairs and towing services
- **📞 24/7 Support**: Round-the-clock customer support for all emergencies

### Key Features
- ✅ Modern, responsive web interface
- ✅ Real-time request tracking
- ✅ GPS location support
- ✅ Multiple fuel types (Petrol, Diesel, Premium)
- ✅ Professional mechanic assignment
- ✅ Emergency contact system
- ✅ Contact form with database storage
- ✅ Mobile-friendly design
- ✅ RESTful API endpoints
- ✅ MySQL database integration

## 🛠️ Tech Stack

### Frontend
- **HTML5**: Semantic markup and structure
- **CSS3**: Modern styling with gradients, animations, and responsive design
- **JavaScript**: Interactive functionality, form handling, and AJAX requests
- **Font Awesome**: Icons for better user experience

### Backend
- **Python**: Core application logic
- **Flask**: Web framework for API development
- **MySQL**: Relational database management
- **Flask-CORS**: Cross-origin resource sharing

### Database
- **MySQL**: Primary database with comprehensive schema
- **Stored Procedures**: Optimized queries for better performance
- **Indexes**: Fast data retrieval
- **Views**: Simplified data access

## 📁 Project Structure

```
RSA/
├── index.html              # Main application page
├── styles.css              # CSS styles and responsive design
├── script.js               # JavaScript functionality
├── server.py               # Flask backend server
├── start_server.py         # Frontend server
├── setup.py                # Setup and installation script
├── database.sql            # MySQL database schema
├── database.properties     # Database configuration
├── requirements.txt        # Python dependencies
├── .gitignore             # Git ignore rules
├── README.md              # Project documentation
├── DATABASE_SETUP.md      # Database setup guide
└── DATABASE_CONNECTION_GUIDE.md # External database access
```

## 🚀 Quick Start

### Prerequisites
- Python 3.7 or higher
- MySQL Server 5.7 or higher
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/rsa-web-application.git
   cd rsa-web-application
   ```

2. **Install Python dependencies**
   ```bash
   pip install -r requirements.txt
   ```

3. **Set up MySQL database**
   ```bash
   # Create database and user
   mysql -u root -p -e "
   CREATE DATABASE IF NOT EXISTS rsa_db;
   CREATE USER IF NOT EXISTS 'rsa_user'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON rsa_db.* TO 'rsa_user'@'localhost';
   FLUSH PRIVILEGES;
   "
   
   # Import database schema
   mysql -u rsa_user -p rsa_db < database.sql
   ```

4. **Configure database connection**
   - Edit `database.properties` with your MySQL credentials
   - Update `server.py` with your database configuration

5. **Run the application**
   ```bash
   # Start backend server (Terminal 1)
   python server.py
   
   # Start frontend server (Terminal 2)
   python start_server.py
   ```

6. **Access the application**
   - Frontend: http://localhost:8000
   - Backend API: http://localhost:5000

## 📊 Database Schema

### Core Tables
- **users**: User account management
- **fuel_requests**: Fuel delivery requests
- **breakdown_requests**: Breakdown assistance requests
- **mechanics**: Available mechanics and their specializations
- **fuel_vehicles**: Fuel delivery vehicles
- **contact_messages**: Contact form submissions
- **service_areas**: Geographic service coverage
- **pricing**: Service pricing information

### Key Features
- **Foreign Key Relationships**: Maintains data integrity
- **Indexes**: Optimized for fast queries
- **Stored Procedures**: Efficient data retrieval
- **Views**: Simplified data access patterns

## 🔧 API Endpoints

### Backend API (http://localhost:5000)
- `GET /api/requests` - Get all requests
- `GET /api/mechanics` - Get available mechanics
- `GET /api/fuel-vehicles` - Get available fuel vehicles
- `GET /api/pricing` - Get pricing information
- `POST /api/save-request` - Save new requests

### Example Usage
```bash
# Get all mechanics
curl http://localhost:5000/api/mechanics

# Get all fuel vehicles
curl http://localhost:5000/api/fuel-vehicles

# Get all requests
curl http://localhost:5000/api/requests
```

## 🎯 Usage

### Fuel Delivery Request
1. Click "Request Fuel" button
2. Fill in the form with:
   - Full name and phone number
   - Current location (GPS coordinates or address)
   - Fuel type and amount
   - Vehicle details
   - Emergency contact (optional)
3. Submit the request
4. Receive confirmation and estimated delivery time

### Breakdown Assistance Request
1. Click "Breakdown Help" button
2. Fill in the form with:
   - Full name and phone number
   - Current location
   - Issue type (flat tire, battery, engine, etc.)
   - Vehicle details
   - Problem description
   - Emergency contact (optional)
3. Submit the request
4. Receive confirmation and estimated arrival time

### Contact Support
1. Navigate to the Contact section
2. Fill in the contact form
3. Submit your message
4. Receive confirmation

## 🔧 Configuration

### Database Configuration
Edit `database.properties` to match your MySQL setup:
```properties
db.url=jdbc:mysql://localhost:3306/rsa_db?useSSL=false&serverTimezone=UTC
db.username=your_username
db.password=your_password
```

### Environment Variables
Create a `.env` file for sensitive configuration:
```env
DB_HOST=localhost
DB_USER=rsa_user
DB_PASSWORD=your_password
DB_NAME=rsa_db
FLASK_ENV=development
```

## 🚨 Emergency Features

### 24/7 Hotline
- Emergency number: 1-800-RSA-HELP
- Available round the clock
- Direct connection to support team

### Real-time Tracking
- GPS location tracking
- Estimated arrival times
- Status updates via SMS/email

### Emergency Contacts
- Automatic notification system
- Backup contact information
- Emergency escalation procedures

## 📱 Mobile Responsiveness

The application is fully responsive and works on:
- Desktop computers
- Tablets
- Mobile phones
- All modern browsers

## 🔒 Security Features

- **Input Validation**: All form inputs are validated
- **SQL Injection Prevention**: Prepared statements used
- **XSS Protection**: Input sanitization
- **Secure Connections**: HTTPS ready
- **CORS Configuration**: Proper cross-origin handling

## 🚀 Deployment

### Local Development
1. Clone the repository
2. Set up MySQL database
3. Configure database properties
4. Start local web server
5. Access via localhost

### Production Deployment
1. Set up production MySQL server
2. Configure web server (Apache/Nginx)
3. Update database configuration
4. Deploy to production server
5. Set up SSL certificates

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For technical support or questions:
- Email: support@rsa.com
- Phone: 1-800-RSA-HELP
- Emergency: Available 24/7

## 🎉 Acknowledgments

- Font Awesome for icons
- MySQL for database management
- Flask community for web framework
- Modern web standards for responsive design

## 📈 Project Status

- ✅ Core functionality implemented
- ✅ Database integration complete
- ✅ API endpoints functional
- ✅ Frontend responsive design
- ✅ Mobile compatibility
- ✅ Security measures in place

---

**Road Side Assistance (RSA)** - Your trusted partner for emergency roadside services and fuel delivery. 🚗⚡

---

<div align="center">
  <p>Made with ❤️ for emergency roadside assistance</p>
  <p>⭐ Star this repository if you find it helpful!</p>
</div> 