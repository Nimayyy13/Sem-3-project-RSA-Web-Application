import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FuelVehicle {
    private int id;
    private String vehicleNumber;
    private int fuelCapacityLiters;
    private int currentFuelLevelLiters;
    private String fuelTypesAvailable;
    private String driverName;
    private String driverPhone;
    private String status;
    private BigDecimal currentLocationLat;
    private BigDecimal currentLocationLng;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Default constructor
    public FuelVehicle() {}
    
    // Constructor with required fields
    public FuelVehicle(String vehicleNumber, int fuelCapacityLiters, String driverName, String driverPhone) {
        this.vehicleNumber = vehicleNumber;
        this.fuelCapacityLiters = fuelCapacityLiters;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.status = "available";
        this.currentFuelLevelLiters = fuelCapacityLiters;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    
    public int getFuelCapacityLiters() {
        return fuelCapacityLiters;
    }
    
    public void setFuelCapacityLiters(int fuelCapacityLiters) {
        this.fuelCapacityLiters = fuelCapacityLiters;
    }
    
    public int getCurrentFuelLevelLiters() {
        return currentFuelLevelLiters;
    }
    
    public void setCurrentFuelLevelLiters(int currentFuelLevelLiters) {
        this.currentFuelLevelLiters = currentFuelLevelLiters;
    }
    
    public String getFuelTypesAvailable() {
        return fuelTypesAvailable;
    }
    
    public void setFuelTypesAvailable(String fuelTypesAvailable) {
        this.fuelTypesAvailable = fuelTypesAvailable;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public String getDriverPhone() {
        return driverPhone;
    }
    
    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public BigDecimal getCurrentLocationLat() {
        return currentLocationLat;
    }
    
    public void setCurrentLocationLat(BigDecimal currentLocationLat) {
        this.currentLocationLat = currentLocationLat;
    }
    
    public BigDecimal getCurrentLocationLng() {
        return currentLocationLng;
    }
    
    public void setCurrentLocationLng(BigDecimal currentLocationLng) {
        this.currentLocationLng = currentLocationLng;
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
        return "FuelVehicle{" +
                "id=" + id +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", fuelCapacityLiters=" + fuelCapacityLiters +
                ", currentFuelLevelLiters=" + currentFuelLevelLiters +
                ", fuelTypesAvailable='" + fuelTypesAvailable + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                ", status='" + status + '\'' +
                ", currentLocationLat=" + currentLocationLat +
                ", currentLocationLng=" + currentLocationLng +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
} 