# 🚗 Road Side Assistance (RSA) - Student Project

A web-based technology platform for emergency fuel delivery and vehicle breakdown assistance services. **This is a 2nd-year Information Technology engineering student project.**

## 🎓 Project Overview

This project demonstrates basic web development skills including HTML, CSS, JavaScript, and Python. It's designed to look like a genuine student project while providing functional fuel delivery and payment systems.

## 🌟 Features

### Core Services
- 🚨 **Emergency Fuel Delivery**: 24/7 fuel delivery to your location within 30-45 minutes
- 🔧 **Breakdown Assistance**: Basic vehicle breakdown assistance and towing services
- 📞 **24/7 Support**: Round-the-clock customer support for emergencies

### Key Features
- ✅ **Student-friendly web interface** - Simple, clean design suitable for academic presentation
- ✅ **Real-time fuel pricing** - Current market rates with automatic calculation
- ✅ **Multiple payment options** - Online payment and Cash on Delivery (COD)
- ✅ **Multiple fuel types** - Petrol, Diesel, and CNG with different delivery fees
- ✅ **Contact form** - Basic contact system for support
- ✅ **Mobile-friendly design** - Responsive layout for all devices
- ✅ **Local storage** - Data persistence for demo purposes

## 🛠️ Tech Stack

### Frontend
- **HTML5**: Semantic markup and structure
- **CSS3**: Simple styling with responsive design
- **JavaScript**: Basic interactive functionality and form handling
- **Font Awesome**: Icons for better user experience

### Backend
- **Python**: Simple HTTP server for static file serving
- **Local Storage**: Client-side data persistence

## 📁 Project Structure

```
RSA/
├── frontend/
│   ├── index.html              # Main application page
│   ├── assets/
│   │   ├── styles.css          # CSS styles and responsive design
│   │   └── script.js           # JavaScript functionality
│   └── start_server.py         # Python HTTP server
├── java/                       # Java source files (backend logic)
│   ├── RSAService.java
│   ├── FuelRequest.java
│   ├── BreakdownRequest.java
│   ├── ContactMessage.java
│   ├── Mechanic.java
│   ├── FuelVehicle.java
│   ├── Pricing.java
│   ├── Request.java
│   ├── DatabaseConnection.java
│   └── RSATest.java
├── scripts/
│   └── run.py                  # Main application runner
├── backend/                    # Backend API files
├── database/                   # Database configuration
├── docs/                       # Documentation
└── README.md                   # Project documentation
```

## 🚀 Quick Start

### Prerequisites
- Python 3.7 or higher
- Modern web browser
- Basic understanding of web development

### Installation

1. **Clone or download the project**
   ```bash
   # If using git
   git clone <repository-url>
   cd RSA
   ```

2. **Navigate to frontend directory**
   ```bash
   cd frontend
   ```

3. **Start the server**
   ```bash
   python start_server.py
   ```

4. **Access the application**
   - Open your browser
   - Navigate to: `http://localhost:8000`

## ⛽ Fuel Pricing System

### Current Market Prices (per liter)
| Fuel Type | Price (₹) | Delivery Fee (₹) |
|-----------|-----------|------------------|
| Petrol    | 96.72     | 50               |
| Diesel    | 89.62     | 50               |
| CNG       | 73.59     | 75               |

### Features
- **Real-time calculation** - Updates total as you select fuel type and amount
- **Delivery fee calculation** - Different fees for different fuel types
- **Price display** - Clear pricing table on the website

## 💳 Payment System

### Payment Options
1. **Online Payment (UPI/Card)**
   - Credit/Debit card processing
   - Card number formatting and validation
   - Secure payment modal

2. **Cash on Delivery (COD)**
   - Pay when fuel is delivered
   - No upfront payment required
   - Immediate order processing

### Payment Flow
1. User fills fuel order form
2. Selects payment method
3. If online payment: Opens payment modal with order summary
4. If COD: Processes order directly
5. Shows success message with payment confirmation

## 🎯 Usage Guide

### Fuel Delivery Request
1. Click "Order Fuel Now" button
2. Fill in the form with:
   - Full name and phone number
   - Current location (address or landmark)
   - Fuel type and amount needed
   - Vehicle details (optional)
3. Select payment method:
   - **Online Payment**: Complete card details
   - **Cash on Delivery**: Pay when delivered
4. Submit the request
5. Receive confirmation and estimated delivery time

### Breakdown Assistance Request
1. Click "Request Help" button
2. Fill in the form with:
   - Full name and phone number
   - Current location
   - Issue description
   - Vehicle details (optional)
3. Submit the request
4. Receive confirmation and estimated arrival time

### Contact Support
1. Navigate to the Contact section
2. Fill in the contact form
3. Submit your message
4. Receive confirmation

## 🎓 Student Project Features

### Design Philosophy
- **Simple and clean** - Suitable for academic presentation
- **Basic functionality** - Demonstrates 2nd-year programming skills
- **Professional appearance** - Looks like a real project
- **Educational value** - Shows understanding of web development concepts

### Code Quality
- **Well-commented** - Clear explanations for educational purposes
- **Modular structure** - Organized code for easy understanding
- **Basic validation** - Form validation and error handling
- **Responsive design** - Works on all device sizes

## 📱 Mobile Responsiveness

The application is fully responsive and works on:
- Desktop computers
- Tablets
- Mobile phones
- All modern browsers

## 🔧 Configuration

### Local Development
- No complex setup required
- Simple Python HTTP server
- Static file serving
- Local storage for data persistence

### Customization
- Edit `frontend/assets/styles.css` for styling changes
- Modify `frontend/assets/script.js` for functionality
- Update `frontend/index.html` for content changes

## 🚨 Emergency Features

### 24/7 Hotline
- Emergency number: 1800-RSA-HELP
- Available round the clock
- Direct connection to support team

### Emergency Contacts
- Displayed prominently on the website
- Quick access to support information
- Clear emergency procedures

## 🔒 Security Features

### Basic Security
- **Input Validation**: All form inputs are validated
- **XSS Protection**: Basic input sanitization
- **Form Security**: CSRF protection ready
- **Data Privacy**: Local storage only (no server-side data)

## 🚀 Deployment

### Local Development
1. Clone/download the project
2. Navigate to frontend directory
3. Start local web server
4. Access via localhost:8000

### File Structure
- All files are static and can be served from any web server
- No database setup required
- No complex backend configuration needed

## 🤝 Contributing

This is a student project, but suggestions are welcome:
1. Fork the repository (if applicable)
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit your improvements

## 📄 License

This project is created for educational purposes as part of a 2nd-year Information Technology engineering course.

## 📞 Support

For questions about this student project:
- **Email**: support@rsaproject.com
- **Phone**: 1800-RSA-HELP
- **Emergency**: Available 24/7

## 🎉 Acknowledgments

- **Font Awesome** for icons
- **Google Fonts** for typography
- **Modern web standards** for responsive design
- **Python community** for HTTP server functionality

## 📈 Project Status

✅ **Core functionality implemented**
✅ **Fuel pricing system complete**
✅ **Payment functionality working**
✅ **Frontend responsive design**
✅ **Mobile compatibility**
✅ **Student-level code quality**
✅ **Academic presentation ready**

---

## 🎓 Student Information

**Project**: Road Side Assistance (RSA) Web Application  
**Course**: 2nd Year Information Technology  
**Branch**: Information Technology  
**Institution**: Engineering College  
**Purpose**: Academic project demonstrating web development skills

---

**Road Side Assistance (RSA) - A student project for emergency roadside services and fuel delivery.** 🚗⚡

Made with ❤️ for academic learning and demonstration

⭐ This project demonstrates basic web development skills suitable for 2nd-year engineering students! 
