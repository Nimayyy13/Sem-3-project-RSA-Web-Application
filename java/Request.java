import java.time.LocalDateTime;

public class Request {
    private String requestType;
    private int id;
    private String name;
    private String phone;
    private String location;
    private String status;
    private LocalDateTime createdAt;
    
    // Default constructor
    public Request() {}
    
    // Constructor with required fields
    public Request(String requestType, int id, String name, String phone, String location, String status) {
        this.requestType = requestType;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.status = status;
    }
    
    // Getters and Setters
    public String getRequestType() {
        return requestType;
    }
    
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    
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
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Request{" +
                "requestType='" + requestType + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
} 