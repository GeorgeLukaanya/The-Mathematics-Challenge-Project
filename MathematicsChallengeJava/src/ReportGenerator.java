import java.sql.*;
import java.util.*;
import java.io.*;

public class ReportGenerator {

    public static void main(String[] args) throws ClassNotFoundException {
        List<Users> users = fetchUsersFromDatabase();

        if (users == null) {
            System.out.println("Failed to fetch users from the database.");
            return;
        }

        generateIndividualReports(users);
        generateSchoolRankingReport(users);
        generateAnalyticsReport(users);
    }


    public static List<Users> fetchUsersFromDatabase() throws ClassNotFoundException {
        List<Users> users = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/math-challengez", "username", "");//credentials
            stmt = conn.createStatement();

            // Fetch users
            String sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");
                String email_verified_at = rs.getString("email_verified_at");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String postal = rs.getString("postal");
                String about = rs.getString("about");
                String remember_token = rs.getString("remember_token");
                String created_at = rs.getString("created_at");
                String updated_at = rs.getString("updated_at"); 

                users.add(new Users(id, username, firstName, lastName, email, usertype, email_verified_at, password, address, city, country, postal, about, remember_token, created_at, updated_at));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    public static void generateIndividualReports(List<Users> users) {
        for (Users user : users) {
            try {
                FileWriter writer = new FileWriter(user.getUsername() + "_report.txt");
                writer.write("users Report\n");
                writer.write("=================\n");
                writer.write("Name: " + user.getFirstname() + " " + user.getLastname() + "\n");
                writer.write("Email: " + user.getEmail() + "\n");
                writer.close();
                System.out.println("Report generated for " + user.getUsername());
            } catch (IOException e) {
                System.out.println("An error occurred while generating report for " + user.getUsername());
                e.printStackTrace();
            }
        }
    }

    public static void generateSchoolRankingReport(List<Users> users) {
        Map<String, List<Users>> schoolusers = new HashMap<>();

        for (Users user : users) {
            schoolusers.computeIfAbsent(user.getSchoolRegistrationNumber(), k -> new ArrayList<>()).add((Users) users);
        }

        try {
            FileWriter writer = new FileWriter("school_ranking_report.txt");
            writer.write("School Ranking Report\n");
            writer.write("=====================\n");

            schoolusers.entrySet().stream()
                    .sorted((e1, e2) -> Double.compare(getAverageScore(e2.getValue()), getAverageScore(e1.getValue())))
                    .forEach(entry -> {
                        try {
                            writer.write("School: " + entry.getKey() + "\n");
                            writer.write("Average Score: " + getAverageScore(entry.getValue()) + "\n\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            writer.close();
            System.out.println("School ranking report generated.");
        } catch (IOException e) {
            System.out.println("An error occurred while generating school ranking report.");
            e.printStackTrace();
        }
    }

    private static double getAverageScore(List<Users> value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAverageScore'");
    }


    public static void generateAnalyticsReport(List<Users> users) {
        System.out.println("Analytics Report");
        System.out.println("================");
        System.out.println("Total users: " + users.size());
    }

}

class Users {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String usertype;
    private String email_verified_at;
    private String password;
    private String address;
    private String city;
    private String country;
    private String postal;
    private String about;
    private String remember_token;
    private String created_at;
    private String updated_at; 

    public Users(Long id, String username, String firstname, String lastname, String email, String usertype, String email_verified_at, String password, String address, String city, String country, String postal, String about, String remember_token, String created_at, String updated_at){
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.usertype = usertype;
        this.email_verified_at = email_verified_at;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postal = postal;
        this.about = about;
        this.remember_token = remember_token;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getSchoolRegistrationNumber() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSchoolRegistrationNumber'");
    }

    // Getters and Setters
    public Long getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getEmail(){
        return email;
    }
    public String getUsertype(){
        return usertype;
    }
    public String getEmail_verified_at(){
        return email_verified_at;
    }
    public String getPassword(){
        return password;
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }
    public String getCountry(){
        return country;
    }
    public String getPostal(){
        return postal;
    }
    public String getAbout(){
        return about;
    }
    public String getRemember_token(){
        return remember_token;
    }
    public String getCreated_at(){
        return created_at;
    }
    public String getUpdated_at(){
        return updated_at;
    }


    public void setId() {
        this.id = id;
    }
    public void setUsername() {
        this.username = username;
    }
    public void setFirstName(){
        this.firstname = firstname;
    }
    public void setLastName(){
        this.lastname = lastname;
    }
    public void setEmail(){
        this.email = email;
    }
    public void setUsertype(){
        this.usertype =usertype;
    }
    public void setEmail_verified_at(){
        this.email_verified_at = email_verified_at;
    }
    public void setPassword(){
        this.password = password;
    }
    public void setAddress(){
        this.address = address;
    }
    public void setCity(){
        this.city = city;
    }
    public void setCountry(){
        this.country = country;
    }
    public void setPostal(){
        this.postal = postal;
    }
    public void setAbout(){
        this.about = about;
    }
    public void setRemember_token(){
        this.remember_token = remember_token;
    }
    public void setCreated_at(){
        this.created_at = created_at;
    }
    public void setUpdated_at(){
        this.updated_at = updated_at;
    }
}
