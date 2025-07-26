package LoginDao;

import java.sql.*;

public class LoginDao {

    // Change these according to your setup
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employeemanagementsystem";
    private static final String JDBC_USERNAME = "root"; // your MySQL username
    private static final String JDBC_PASSWORD = "Ashna"; // your MySQL password

    public boolean validateUser(String username, String password) {
        boolean isValid = false;

        String query = "SELECT * FROM login WHERE username = ? AND password = ?";

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // Prepare statement
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username.trim());
            stmt.setString(2, password.trim());

            // Print to debug what is being searched
            System.out.println("Attempting login for: " + username + " / " + password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                isValid = true;
                System.out.println("User found: login successful.");
            } else {
                System.out.println("No matching user found.");
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error during login validation:");
            e.printStackTrace();
        }

        return isValid;
    }
}
