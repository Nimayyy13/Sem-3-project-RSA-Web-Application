import java.time.LocalDateTime;

public class BreakdownRequest {
    private int id;
    private Integer userId;
    private String name;
    private String phone;
    private String location;
    private String issueType;
    private String vehicleDetails;
    private String problemDescription;
    private String emergencyContact;
    private String status;
    private Integer assignedMechanicId;
    private LocalDateTime estimatedArrivalTime;
    private LocalDateTime actualArrivalTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Default constructor
    public BreakdownRequest() {}
    
    // Constructor with required fields
    public BreakdownRequest(String name, String phone, String location, String issueType,
                          String vehicleDetails, String problemDescription, String emergencyContact) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.issueType = issueType;
        this.vehicleDetails = vehicleDetails;
        this.problemDescription = problemDescription;
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
    
    public String getIssueType() {
        return issueType;
    }
    
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }
    
    public String getVehicleDetails() {
        return vehicleDetails;
    }
    
    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
    
    public String getProblemDescription() {
        return problemDescription;
    }
    
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
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
    
    public Integer getAssignedMechanicId() {
        return assignedMechanicId;
    }
    
    public void setAssignedMechanicId(Integer assignedMechanicId) {
        this.assignedMechanicId = assignedMechanicId;
    }
    
    public LocalDateTime getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }
    
    public void setEstimatedArrivalTime(LocalDateTime estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }
    
    public LocalDateTime getActualArrivalTime() {
        return actualArrivalTime;
    }
    
    public void setActualArrivalTime(LocalDateTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
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
        return "BreakdownRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", issueType='" + issueType + '\'' +
                ", vehicleDetails='" + vehicleDetails + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", status='" + status + '\'' +
                ", assignedMechanicId=" + assignedMechanicId +
                ", estimatedArrivalTime=" + estimatedArrivalTime +
                ", actualArrivalTime=" + actualArrivalTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
} 