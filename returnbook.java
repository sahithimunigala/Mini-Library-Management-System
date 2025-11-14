import java.sql.*;
import java.util.Scanner;

public class ReturnBook {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection()) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Transaction ID: ");
            int tid = sc.nextInt();

            String query = "SELECT book_id, DATEDIFF(CURDATE(), issue_date) AS days FROM transactions WHERE id = ? AND return_date IS NULL";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, tid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                int days = rs.getInt("days");
                int fine = (days > 7) ? (days - 7) * 5 : 0;

                String updateTrans = "UPDATE transactions SET return_date = CURDATE(), fine = ? WHERE id = ?";
                PreparedStatement ps2 = con.prepareStatement(updateTrans);
                ps2.setInt(1, fine);
                ps2.setInt(2, tid);
                ps2.executeUpdate();

                String updateBook = "UPDATE books SET available = TRUE WHERE book_id = ?";
                PreparedStatement ps3 = con.prepareStatement(updateBook);
                ps3.setInt(1, bookId);
                ps3.executeUpdate();

                System.out.println("✅ Book returned successfully! Fine: ₹" + fine);
            } else {
                System.out.println("❌ Invalid transaction ID or book already returned.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
