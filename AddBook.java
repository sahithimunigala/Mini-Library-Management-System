import java.sql.*;
import java.util.Scanner;

public class AddBook {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection()) {
            Scanner sc = new Scanner(System.in);

            System.out.println("===== ADD NEW BOOK =====");
            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author Name: ");
            String author = sc.nextLine();

            String insertBook = "INSERT INTO books (title, author, available) VALUES (?, ?, TRUE)";
            PreparedStatement ps = con.prepareStatement(insertBook);

            ps.setString(1, title);
            ps.setString(2, author);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Book added successfully!");
            } else {
                System.out.println("❌ Failed to add book.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
