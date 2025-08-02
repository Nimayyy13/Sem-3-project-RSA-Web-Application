# 📁 RSA Project Structure

## 🏗️ **Organized Folder Structure**

```
RSA/
├── 📁 frontend/                    # Frontend Application
│   ├── 📄 index.html              # Main HTML page
│   ├── 📁 assets/                 # Static assets
│   │   ├── 📄 styles.css         # CSS styles
│   │   └── 📄 script.js          # JavaScript functionality
│   └── 📄 start_server.py        # Frontend server
│
├── 📁 backend/                     # Backend Application
│   ├── 📄 server.py               # Flask API server
│   ├── 📄 setup.py                # Setup and installation
│   ├── 📄 requirements.txt        # Python dependencies
│   └── 📁 api/                    # API endpoints (future)
│
├── 📁 database/                    # Database Management
│   ├── 📄 database.sql            # Database schema
│   ├── 📄 database.properties     # Database configuration
│   └── 📁 scripts/                # Database scripts (future)
│
├── 📁 docs/                        # Documentation
│   ├── 📄 DATABASE_SETUP.md       # Database setup guide
│   └── 📄 DATABASE_CONNECTION_GUIDE.md # External access guide
│
├── 📄 run.py                       # Main application launcher
├── 📄 README.md                    # Project documentation
├── 📄 .gitignore                   # Git ignore rules
└── 📄 PROJECT_STRUCTURE.md         # This file
```

## 🎯 **Component Overview**

### **Frontend (`/frontend/`)**
- **Purpose**: User interface and client-side functionality
- **Technology**: HTML5, CSS3, JavaScript
- **Server**: Python HTTP server for development
- **Files**:
  - `index.html` - Main application page
  - `assets/styles.css` - Responsive styling
  - `assets/script.js` - Interactive functionality
  - `start_server.py` - Development server

### **Backend (`/backend/`)**
- **Purpose**: API server and business logic
- **Technology**: Python, Flask, MySQL
- **Server**: Flask development server
- **Files**:
  - `server.py` - Main API server
  - `setup.py` - Installation and setup
  - `requirements.txt` - Python dependencies
  - `api/` - Future API endpoint organization

### **Database (`/database/`)**
- **Purpose**: Database schema and configuration
- **Technology**: MySQL
- **Files**:
  - `database.sql` - Complete database schema
  - `database.properties` - Connection configuration
  - `scripts/` - Future database maintenance scripts

### **Documentation (`/docs/`)**
- **Purpose**: Project documentation and guides
- **Files**:
  - `DATABASE_SETUP.md` - Database installation guide
  - `DATABASE_CONNECTION_GUIDE.md` - External access guide

## 🚀 **How to Run the Application**

### **Option 1: Using the Main Launcher**
```bash
python run.py
```
Then choose:
- **1** - Start Backend Server (API)
- **2** - Start Frontend Server (Web Interface)
- **3** - Start Both Servers
- **4** - Setup Database

### **Option 2: Manual Start**

**Start Backend:**
```bash
cd backend
python server.py
```

**Start Frontend:**
```bash
cd frontend
python start_server.py
```

### **Option 3: Direct Access**
- **Frontend**: http://localhost:8000
- **Backend API**: http://localhost:5000

## 🔧 **Development Workflow**

### **Frontend Development**
1. **Edit files** in `/frontend/` directory
2. **CSS/JS changes** go in `/frontend/assets/`
3. **Test changes** by refreshing browser
4. **Server auto-reloads** on file changes

### **Backend Development**
1. **Edit API** in `/backend/server.py`
2. **Add dependencies** to `/backend/requirements.txt`
3. **Test API** using curl or Postman
4. **Server auto-reloads** on file changes

### **Database Development**
1. **Schema changes** in `/database/database.sql`
2. **Configuration** in `/database/database.properties`
3. **Test queries** using MySQL Workbench
4. **Backup data** before major changes

## 📊 **File Dependencies**

### **Frontend Dependencies**
```
frontend/index.html
├── frontend/assets/styles.css
├── frontend/assets/script.js
└── CDN: Font Awesome
```

### **Backend Dependencies**
```
backend/server.py
├── backend/requirements.txt
├── database/database.properties
└── frontend/ (for serving static files)
```

### **Database Dependencies**
```
database/database.sql
├── MySQL Server
└── database/database.properties
```

## 🔄 **API Endpoints**

### **Available Endpoints**
- `GET /api/requests` - Get all requests
- `GET /api/mechanics` - Get available mechanics
- `GET /api/fuel-vehicles` - Get fuel vehicles
- `GET /api/pricing` - Get pricing information
- `POST /api/save-request` - Save new requests

### **Testing API**
```bash
# Test mechanics endpoint
curl http://localhost:5000/api/mechanics

# Test fuel vehicles endpoint
curl http://localhost:5000/api/fuel-vehicles

# Test requests endpoint
curl http://localhost:5000/api/requests
```

## 🗄️ **Database Tables**

### **Core Tables**
- `users` - User management
- `fuel_requests` - Fuel delivery requests
- `breakdown_requests` - Breakdown assistance
- `mechanics` - Available mechanics
- `fuel_vehicles` - Fuel delivery vehicles
- `contact_messages` - Contact form submissions
- `service_areas` - Service coverage areas
- `pricing` - Service pricing

## 🔒 **Security Considerations**

### **File Permissions**
- Database credentials in `/database/`
- API keys in environment variables
- Sensitive files in `.gitignore`

### **Access Control**
- Database user with limited privileges
- API endpoints with CORS configuration
- Input validation on all forms

## 📈 **Future Enhancements**

### **Planned Structure**
```
RSA/
├── frontend/
│   ├── assets/
│   ├── components/     # React/Vue components
│   └── pages/         # Page components
├── backend/
│   ├── api/
│   ├── models/        # Data models
│   ├── services/      # Business logic
│   └── utils/         # Utilities
├── database/
│   ├── scripts/
│   ├── migrations/    # Database migrations
│   └── seeds/         # Sample data
└── docs/
    ├── api/           # API documentation
    ├── deployment/    # Deployment guides
    └── user/          # User guides
```

## 🎯 **Benefits of This Structure**

### **✅ Organization**
- Clear separation of concerns
- Easy to navigate and understand
- Scalable for future development

### **✅ Development**
- Independent frontend/backend development
- Easy testing and debugging
- Clear file dependencies

### **✅ Deployment**
- Separate deployment strategies
- Easy containerization
- Clear configuration management

### **✅ Maintenance**
- Easy to update individual components
- Clear documentation structure
- Simple backup and restore

---

**This organized structure makes the RSA project professional, maintainable, and ready for production deployment!** 🚗⚡ 