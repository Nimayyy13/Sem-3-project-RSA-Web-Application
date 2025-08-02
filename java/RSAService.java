import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RSAService {
    
    /**
     * Save fuel delivery request to database
     * @param request Fuel delivery request data
     * @return true if saved successfully, false otherwise
     */
    public static boolean saveFuelRequest(FuelRequest request) {
        String sql = "INSERT INTO fuel_requests (name, phone, location, fuel_type, amount_liters, " +
                    "vehicle_details, emergency_contact, status, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, 'pending', NOW())";
        
        try (PreparedStatement pstmt = DatabaseConnection.prepareStatement(sql,
                request.getName(),
                request.getPhone(),
                request.getLocation(),
                request.getFuelType(),
                request.getAmountLiters(),
                request.getVehicleDetails(),
                request.getEmergencyContact())) {
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error saving fuel request: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Save breakdown assistance request to database
     * @param request Breakdown assistance request data
     * @return true if saved successfully, false otherwise
     */
    public static boolean saveBreakdownRequest(BreakdownRequest request) {
        String sql = "INSERT INTO breakdown_requests (name, phone, location, issue_type, " +
                    "vehicle_details, problem_description, emergency_contact, status, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, 'pending', NOW())";
        
        try (PreparedStatement pstmt = DatabaseConnection.prepareStatement(sql,
                request.getName(),
                request.getPhone(),
                request.getLocation(),
                request.getIssueType(),
                request.getVehicleDetails(),
                request.getProblemDescription(),
                request.getEmergencyContact())) {
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error saving breakdown request: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Save contact message to database
     * @param message Contact message data
     * @return true if saved successfully, false otherwise
     */
    public static boolean saveContactMessage(ContactMessage message) {
        String sql = "INSERT INTO contact_messages (name, email, phone, message, status, created_at) " +
                    "VALUES (?, ?, ?, ?, 'new', NOW())";
        
        try (PreparedStatement pstmt = DatabaseConnection.prepareStatement(sql,
                message.getName(),
                message.getEmail(),
                message.getPhone(),
                message.getMessage())) {
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error saving contact message: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get available mechanics for a specific issue type
     * @param issueType Type of breakdown issue
     * @return List of available mechanics
     */
    public static List<Mechanic> getAvailableMechanics(String issueType) {
        List<Mechanic> mechanics = new ArrayList<>();
        String sql = "CALL GetAvailableMechanics(?)";
        
        try (PreparedStatement pstmt = DatabaseConnection.prepareStatement(sql, issueType);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Mechanic mechanic = new Mechanic();
                mechanic.setId(rs.getInt("id"));
                mechanic.setName(rs.getString("name"));
                mechanic.setPhone(rs.getString("phone"));
                mechanic.setSpecialization(rs.getString("specialization"));
                mechanic.setCurrentLocationLat(rs.getBigDecimal("current_location_lat"));
                mechanic.setCurrentLocationLng(rs.getBigDecimal("current_location_lng"));
                mechanics.add(mechanic);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting available mechanics: " + e.getMessage());
        }
        
        return mechanics;
    }
    
    /**
     * Get available fuel delivery vehicles
     * @param fuelType Type of fuel needed
     * @return List of available fuel vehicles
     */
    public static List<FuelVehicle> getAvailableFuelVehicles(String fuelType) {
        List<FuelVehicle> vehicles = new ArrayList<>();
        String sql = "CALL GetAvailableFuelVehicles(?)";
        
        try (PreparedStatement pstmt = DatabaseConnection.prepareStatement(sql, fuelType);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                FuelVehicle vehicle = new FuelVehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setVehicleNumber(rs.getString("vehicle_number"));
                vehicle.setFuelCapacityLiters(rs.getInt("fuel_capacity_liters"));
                vehicle.setCurrentFuelLevelLiters(rs.getInt("current_fuel_level_liters"));
                vehicle.setDriverName(rs.getString("driver_name"));
                vehicle.setDriverPhone(rs.getString("driver_phone"));
                vehicle.setCurrentLocationLat(rs.getBigDecimal("current_location_lat"));
                vehicle.setCurrentLocationLng(rs.getBigDecimal("current_location_lng"));
                vehicles.add(vehicle);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting available fuel vehicles: " + e.getMessage());
        }
        
        return vehicles;
    }
    
    /**
     * Get pricing information for a service
     * @param serviceType Type of service
     * @param fuelType Type of fuel (for fuel delivery)
     * @param areaId Service area ID
     * @return Pricing information
     */
    public static Pricing getPricing(String serviceType, String fuelType, int areaId) {
        String sql = "SELECT * FROM pricing WHERE service_type = ? AND fuel_type = ? AND area_id = ? AND is_active = 1";
        
        try (PreparedStatement pstmt = DatabaseConnection.prepareStatement(sql, serviceType, fuelType, areaId);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                Pricing pricing = new Pricing();
                pricing.setId(rs.getInt("id"));
                pricing.setServiceType(rs.getString("service_type"));
                pricing.setFuelType(rs.getString("fuel_type"));
                pricing.setBasePrice(rs.getBigDecimal("base_price"));
                pricing.setPricePerUnit(rs.getBigDecimal("price_per_unit"));
                pricing.setUnitType(rs.getString("unit_type"));
                pricing.setAreaId(rs.getInt("area_id"));
                return pricing;
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting pricing: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Update request status
     * @param requestType Type of request (fuel or breakdown)
     * @param requestId Request ID
     * @param status New status
     * @return true if updated successfully, false otherwise
     */
    public static boolean updateRequestStatus(String requestType, int requestId, String status) {
        String tableName = requestType.equals("fuel") ? "fuel_requests" : "breakdown_requests";
        String sql = "UPDATE " + tableName + " SET status = ?, updated_at = NOW() WHERE id = ?";
        
        try (PreparedStatement pstmt = DatabaseConnection.prepareStatement(sql, status, requestId)) {
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating request status: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get active requests
     * @return List of active requests
     */
    public static List<Request> getActiveRequests() {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM active_requests ORDER BY created_at DESC";
        
        try (ResultSet rs = DatabaseConnection.executeQuery(sql)) {
            while (rs.next()) {
                Request request = new Request();
                request.setRequestType(rs.getString("request_type"));
                request.setId(rs.getInt("id"));
                request.setName(rs.getString("name"));
                request.setPhone(rs.getString("phone"));
                request.setLocation(rs.getString("location"));
                request.setStatus(rs.getString("status"));
                request.setCreatedAt(rs.getTimestamp("created_at"));
                requests.add(request);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting active requests: " + e.getMessage());
        }
        
        return requests;
    }
    
    /**
     * Calculate estimated delivery time for fuel delivery
     * @param location Customer location
     * @param fuelType Type of fuel
     * @return Estimated delivery time
     */
    public static LocalDateTime calculateEstimatedDeliveryTime(String location, String fuelType) {
        // This is a simplified calculation
        // In a real application, you would use GPS coordinates and traffic data
        LocalDateTime now = LocalDateTime.now();
        return now.plusMinutes(30); // 30 minutes estimated delivery time
    }
    
    /**
     * Calculate estimated arrival time for breakdown assistance
     * @param location Customer location
     * @param issueType Type of issue
     * @return Estimated arrival time
     */
    public static LocalDateTime calculateEstimatedArrivalTime(String location, String issueType) {
        // This is a simplified calculation
        // In a real application, you would use GPS coordinates and traffic data
        LocalDateTime now = LocalDateTime.now();
        return now.plusMinutes(45); // 45 minutes estimated arrival time
    }
    
    /**
     * Send SMS notification (placeholder for real SMS service)
     * @param phoneNumber Phone number to send SMS to
     * @param message SMS message
     * @return true if SMS sent successfully, false otherwise
     */
    public static boolean sendSMS(String phoneNumber, String message) {
        // This is a placeholder for a real SMS service
        // In a real application, you would integrate with services like Twilio
        System.out.println("SMS to " + phoneNumber + ": " + message);
        return true;
    }
    
    /**
     * Send email notification (placeholder for real email service)
     * @param email Email address to send to
     * @param subject Email subject
     * @param message Email message
     * @return true if email sent successfully, false otherwise
     */
    public static boolean sendEmail(String email, String subject, String message) {
        // This is a placeholder for a real email service
        // In a real application, you would integrate with services like SendGrid
        System.out.println("Email to " + email + " - Subject: " + subject + " - Message: " + message);
        return true;
    }
} 