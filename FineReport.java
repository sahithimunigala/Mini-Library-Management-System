import java.sql.*;

public class FineReport {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT SUM(fine) AS total_fine FROM transactions";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                int totalFine = rs.getInt("total_fine");
                System.out.println("\n📊 Total Fine Collected: ₹" + totalFine);
            } else {
                System.out.println("No fines recorded yet.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
