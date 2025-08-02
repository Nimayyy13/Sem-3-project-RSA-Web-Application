import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pricing {
    private int id;
    private String serviceType;
    private String fuelType;
    private BigDecimal basePrice;
    private BigDecimal pricePerUnit;
    private String unitType;
    private int areaId;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Default constructor
    public Pricing() {}
    
    // Constructor with required fields
    public Pricing(String serviceType, BigDecimal basePrice, int areaId) {
        this.serviceType = serviceType;
        this.basePrice = basePrice;
        this.areaId = areaId;
        this.isActive = true;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getServiceType() {
        return serviceType;
    }
    
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
    public BigDecimal getBasePrice() {
        return basePrice;
    }
    
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
    
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }
    
    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    
    public String getUnitType() {
        return unitType;
    }
    
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
    
    public int getAreaId() {
        return areaId;
    }
    
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
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
        return "Pricing{" +
                "id=" + id +
                ", serviceType='" + serviceType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", basePrice=" + basePrice +
                ", pricePerUnit=" + pricePerUnit +
                ", unitType='" + unitType + '\'' +
                ", areaId=" + areaId +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
} 