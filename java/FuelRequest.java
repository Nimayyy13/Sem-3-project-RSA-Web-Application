import java.time.LocalDateTime;

public class FuelRequest {
    private int id;
    private Integer userId;
    private String name;
    private String phone;
    private String location;
    private String fuelType;
    private int amountLiters;
    private String vehicleDetails;
    private String emergencyContact;
    private String status;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime actualDeliveryTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Default constructor
    public FuelRequest() {}
    
    // Constructor with required fields
    public FuelRequest(String name, String phone, String location, String fuelType, 
                      int amountLiters, String vehicleDetails, String emergencyContact) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.fuelType = fuelType;
        this.amountLiters = amountLiters;
        this.vehicleDetails = vehicleDetails;
        this.emergencyContact = emergencyContact;
        this.status = "pending";
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
    public int getAmountLiters() {
        return amountLiters;
    }
    
    public void setAmountLiters(int amountLiters) {
        this.amountLiters = amountLiters;
    }
    
    public String getVehicleDetails() {
        return vehicleDetails;
    }
    
    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
    
    public String getEmergencyContact() {
        return emergencyContact;
    }
    
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }
    
    public void setEstimatedDeliveryTime(LocalDateTime estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }
    
    public LocalDateTime getActualDeliveryTime() {
        return actualDeliveryTime;
    }
    
    public void setActualDeliveryTime(LocalDateTime actualDeliveryTime) {
        this.actualDeliveryTime = actualDeliveryTime;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        return "FuelRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", amountLiters=" + amountLiters +
                ", vehicleDetails='" + vehicleDetails + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", status='" + status + '\'' +
                ", estimatedDeliveryTime=" + estimatedDeliveryTime +
                ", actualDeliveryTime=" + actualDeliveryTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
} 