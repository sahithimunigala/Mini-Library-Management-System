import java.util.Scanner;

public class Mainmenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== MINI LIBRARY MANAGEMENT SYSTEM =====");
System.out.println("1. Issue Book");
System.out.println("2. Return Book");
System.out.println("3. View Total Fine Collected");
System.out.println("4.AddBook");
System.out.println("5. Exit");
System.out.print("Enter your choice: ");
choice = sc.nextInt();

switch (choice) {
    case 1:
        IssueBook.main(null);
        break;
    case 2:
        ReturnBook.main(null);
        break;
    case 3:
        FineReport.main(null);
        break;
    case 4:
        AddBook.main(null);
        break;
    case 5:
        System.out.println("Exiting the System. Thankyou!");
        break;
    default:
        System.out.println("Invalid choice! Please try again.");
}

        } while (choice != 3);

        sc.close();
    }
}
