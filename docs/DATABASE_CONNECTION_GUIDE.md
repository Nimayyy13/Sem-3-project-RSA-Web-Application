# RSA Database External Connection Guide

## 🔗 **MySQL Workbench Connection**

### **Connection Details:**
```
Connection Name: RSA Database
Hostname: localhost
Port: 3306
Username: rsa_user
Password: sanskruti14
Database: rsa_db
```

### **Steps to Connect:**

1. **Open MySQL Workbench**
2. **Click the "+" icon** next to "MySQL Connections"
3. **Fill in the connection details above**
4. **Click "Test Connection"** to verify
5. **Click "OK"** to save and connect

## 📊 **Database Structure**

### **Tables Available:**
- `users` - User account management
- `fuel_requests` - Emergency fuel delivery requests
- `breakdown_requests` - Vehicle breakdown assistance requests
- `mechanics` - Available mechanics and their specializations
- `fuel_vehicles` - Fuel delivery vehicles
- `contact_messages` - Contact form submissions
- `service_areas` - Geographic service coverage
- `pricing` - Service pricing information

### **Views Available:**
- `active_requests` - Shows all pending and in-progress requests

### **Stored Procedures:**
- `GetAvailableMechanics(issue_type)` - Get available mechanics for specific issues
- `GetAvailableFuelVehicles(fuel_type)` - Get available fuel vehicles for specific fuel types

## 🔍 **Useful Queries**

### **Check Recent Requests:**
```sql
-- Recent fuel requests
SELECT id, name, phone, fuel_type, amount_liters, status, created_at 
FROM fuel_requests 
ORDER BY created_at DESC 
LIMIT 10;

-- Recent breakdown requests
SELECT id, name, phone, issue_type, status, created_at 
FROM breakdown_requests 
ORDER BY created_at DESC 
LIMIT 10;
```

### **Check Available Resources:**
```sql
-- Available mechanics
SELECT name, phone, specialization, status 
FROM mechanics 
WHERE status = 'available';

-- Available fuel vehicles
SELECT vehicle_number, fuel_capacity_liters, driver_name, status 
FROM fuel_vehicles 
WHERE status = 'available';
```

### **Check Contact Messages:**
```sql
-- Recent contact messages
SELECT id, name, email, phone, status, created_at 
FROM contact_messages 
ORDER BY created_at DESC 
LIMIT 10;
```

## 🛠️ **Command Line Access**

### **Connect via Command Line:**
```bash
mysql -u rsa_user -p rsa_db
# Enter password: sanskruti14
```

### **Quick Commands:**
```bash
# Check database status
mysql -u rsa_user -p rsa_db -e "SHOW TABLES;"

# Count records in each table
mysql -u rsa_user -p rsa_db -e "
SELECT 'users' as table_name, COUNT(*) as count FROM users
UNION ALL
SELECT 'fuel_requests', COUNT(*) FROM fuel_requests
UNION ALL
SELECT 'breakdown_requests', COUNT(*) FROM breakdown_requests
UNION ALL
SELECT 'mechanics', COUNT(*) FROM mechanics
UNION ALL
SELECT 'fuel_vehicles', COUNT(*) FROM fuel_vehicles
UNION ALL
SELECT 'contact_messages', COUNT(*) FROM contact_messages;
"
```

## 📱 **API Endpoints for External Access**

Your RSA application provides these API endpoints:

### **Backend API (http://localhost:5000):**
- `GET /api/requests` - Get all requests
- `GET /api/mechanics` - Get available mechanics
- `GET /api/fuel-vehicles` - Get available fuel vehicles
- `GET /api/pricing` - Get pricing information
- `POST /api/save-request` - Save new requests

### **Test API Endpoints:**
```bash
# Get all mechanics
curl http://localhost:5000/api/mechanics

# Get all fuel vehicles
curl http://localhost:5000/api/fuel-vehicles

# Get all requests
curl http://localhost:5000/api/requests
```

## 🔧 **Database Management**

### **Backup Database:**
```bash
mysqldump -u rsa_user -p rsa_db > rsa_backup.sql
```

### **Restore Database:**
```bash
mysql -u rsa_user -p rsa_db < rsa_backup.sql
```

### **Reset Database:**
```bash
mysql -u rsa_user -p rsa_db -e "DROP DATABASE rsa_db; CREATE DATABASE rsa_db;"
mysql -u rsa_user -p rsa_db < database.sql
```

## 🚨 **Troubleshooting**

### **Connection Issues:**
1. **Check MySQL service is running:**
   ```bash
   net start mysql
   ```

2. **Verify user permissions:**
   ```sql
   SHOW GRANTS FOR 'rsa_user'@'localhost';
   ```

3. **Check database exists:**
   ```sql
   SHOW DATABASES;
   USE rsa_db;
   SHOW TABLES;
   ```

### **Common Error Solutions:**
- **Access denied:** Check username/password
- **Connection refused:** Check if MySQL service is running
- **Database doesn't exist:** Run the database.sql import

## 📊 **Monitoring Queries**

### **Real-time Monitoring:**
```sql
-- Active requests count
SELECT 
    (SELECT COUNT(*) FROM fuel_requests WHERE status IN ('pending', 'confirmed', 'in_progress')) as active_fuel_requests,
    (SELECT COUNT(*) FROM breakdown_requests WHERE status IN ('pending', 'assigned', 'in_progress')) as active_breakdown_requests,
    (SELECT COUNT(*) FROM mechanics WHERE status = 'available') as available_mechanics,
    (SELECT COUNT(*) FROM fuel_vehicles WHERE status = 'available') as available_vehicles;
```

### **Performance Monitoring:**
```sql
-- Recent activity
SELECT 'fuel_requests' as table_name, COUNT(*) as recent_count 
FROM fuel_requests 
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 24 HOUR)
UNION ALL
SELECT 'breakdown_requests', COUNT(*) 
FROM breakdown_requests 
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 24 HOUR)
UNION ALL
SELECT 'contact_messages', COUNT(*) 
FROM contact_messages 
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 24 HOUR);
```

---

**Your RSA database is now accessible externally!** 🎉

- **MySQL Workbench:** Use the connection details above
- **Command Line:** Use `mysql -u rsa_user -p rsa_db`
- **API Access:** Use the endpoints at `http://localhost:5000`
- **Web Interface:** Access at `http://localhost:8000` 