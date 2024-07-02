import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Define database URL, username, and password
        String url = "jdbc:mysql://localhost:3306Mathematics_Challenge/";
        String username = "root";
        String password = "";
        String RegistrationNumber = "2300700696";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            //Establish the connection
            con = DriverManager.getConnection(url, username, password);

            //Query to check if the registration number exists.
            String query = "SELECT COUNT(*) FROM School WHERE SchoolRegNo = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, RegistrationNumber );

            //Execute the query
            rs = pst.executeQuery();

            //Check if number exists
            if(rs.next()){
                int count = rs.getInt(1);
                if(count > 0){
                    System.out.println(RegistrationNumber + " exists.");
                }else{
                    System.out.println("Doesn't exist.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(rs != null) rs.close();
                if(pst != null) rs.close();
                if(con != null) rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }
}
