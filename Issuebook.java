import java.sql.*;
import java.util.Scanner;

public class IssueBook {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false); // Start transaction
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Student ID: ");
            int sid = sc.nextInt();
            System.out.print("Enter Book ID: ");
            int bid = sc.nextInt();

            String checkBook = "SELECT available FROM books WHERE book_id = ?";
            PreparedStatement ps1 = con.prepareStatement(checkBook);
            ps1.setInt(1, bid);
            ResultSet rs = ps1.executeQuery();

            if (rs.next() && rs.getBoolean("available")) {
                String insertTrans = "INSERT INTO transactions (student_id, book_id, issue_date) VALUES (?, ?, CURDATE())";
                PreparedStatement ps2 = con.prepareStatement(insertTrans);
                ps2.setInt(1, sid);
                ps2.setInt(2, bid);
                ps2.executeUpdate();

                String updateBook = "UPDATE books SET available = FALSE WHERE book_id = ?";
                PreparedStatement ps3 = con.prepareStatement(updateBook);
                ps3.setInt(1, bid);
                ps3.executeUpdate();

                con.commit();
                System.out.println("✅ Book issued successfully!");
            } else {
                System.out.println("❌ Book not available or does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
