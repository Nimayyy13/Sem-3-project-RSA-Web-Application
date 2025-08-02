import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Mechanic {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String specialization;
    private String status;
    private BigDecimal currentLocationLat;
    private BigDecimal currentLocationLng;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Default constructor
    public Mechanic() {}
    
    // Constructor with required fields
    public Mechanic(String name, String phone, String specialization) {
        this.name = name;
        this.phone = phone;
        this.specialization = specialization;
        this.status = "available";
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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
        return "Mechanic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", specialization='" + specialization + '\'' +
                ", status='" + status + '\'' +
                ", currentLocationLat=" + currentLocationLat +
                ", currentLocationLng=" + currentLocationLng +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
} 