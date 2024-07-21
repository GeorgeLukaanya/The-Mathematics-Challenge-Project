package Quiz;

public class ReportGeneration {
    public static void generateReport(String username, String schoolRegNo, long timeTaken, int marks) {
        System.out.println("\nReport");
        System.out.println("=======");
        System.out.println("Username: " + username);
        System.out.println("School Registration Number: " + schoolRegNo);
        System.out.println("Time Taken: " + timeTaken + " ms");
        System.out.println("Marks: " + marks);
    }
}
