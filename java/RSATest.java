public class RSATest {
    
    public static void main(String[] args) {
        System.out.println("=== RSA Application Test ===");
        
        // Test database connection
        testDatabaseConnection();
        
        // Test fuel request creation
        testFuelRequest();
        
        // Test breakdown request creation
        testBreakdownRequest();
        
        // Test contact message creation
        testContactMessage();
        
        System.out.println("=== Test Complete ===");
    }
    
    private static void testDatabaseConnection() {
        System.out.println("Testing database connection...");
        try {
            boolean connected = DatabaseConnection.testConnection();
            if (connected) {
                System.out.println("✅ Database connection successful");
            } else {
                System.out.println("❌ Database connection failed");
            }
        } catch (Exception e) {
            System.out.println("❌ Database connection error: " + e.getMessage());
        }
    }
    
    private static void testFuelRequest() {
        System.out.println("Testing fuel request creation...");
        try {
            FuelRequest request = new FuelRequest(
                "John Doe",
                "+1-555-0123",
                "123 Main St, City, State",
                "petrol",
                20,
                "Toyota Camry 2020, White",
                "+1-555-0124"
            );
            
            boolean saved = RSAService.saveFuelRequest(request);
            if (saved) {
                System.out.println("✅ Fuel request saved successfully");
            } else {
                System.out.println("❌ Fuel request save failed");
            }
        } catch (Exception e) {
            System.out.println("❌ Fuel request error: " + e.getMessage());
        }
    }
    
    private static void testBreakdownRequest() {
        System.out.println("Testing breakdown request creation...");
        try {
            BreakdownRequest request = new BreakdownRequest(
                "Jane Smith",
                "+1-555-0456",
                "456 Oak Ave, City, State",
                "flat-tire",
                "Honda Civic 2019, Blue",
                "Front left tire is completely flat",
                "+1-555-0457"
            );
            
            boolean saved = RSAService.saveBreakdownRequest(request);
            if (saved) {
                System.out.println("✅ Breakdown request saved successfully");
            } else {
                System.out.println("❌ Breakdown request save failed");
            }
        } catch (Exception e) {
            System.out.println("❌ Breakdown request error: " + e.getMessage());
        }
    }
    
    private static void testContactMessage() {
        System.out.println("Testing contact message creation...");
        try {
            ContactMessage message = new ContactMessage(
                "Bob Johnson",
                "bob.johnson@email.com",
                "+1-555-0789",
                "I would like to inquire about your services for my company fleet."
            );
            
            boolean saved = RSAService.saveContactMessage(message);
            if (saved) {
                System.out.println("✅ Contact message saved successfully");
            } else {
                System.out.println("❌ Contact message save failed");
            }
        } catch (Exception e) {
            System.out.println("❌ Contact message error: " + e.getMessage());
        }
    }
} 