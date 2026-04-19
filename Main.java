import service.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TicketService ticketService = new TicketService();
        ExhibitService exhibitService = new ExhibitService();
        VisitorService visitorService = new VisitorService();
        AdminService adminService = new AdminService();

        while (true) {
            System.out.println("\n============================================");
            System.out.println("      WELCOME TO THE NATIONAL MUSEUM");
            System.out.println("============================================");
            System.out.println("1. Book Ticket");
            System.out.println("2. View Exhibit");
            System.out.println("3. Monitor Visitor Entry");
            System.out.println("4. Administrator Mode");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    ticketService.bookTicket(name);
                    break;

                case 2:
                    System.out.print("Enter your Ticket ID: ");
                    String inputTicket = sc.nextLine();

                    if (ticketService.validateTicket(inputTicket)) {
                        boolean backToMenu = exhibitService.virtualGallery(sc);
                        if (backToMenu) {
                            System.out.print("Enter your name: ");
                            String visitor = sc.nextLine();
                            visitorService.recordEntry(visitor, inputTicket);
                        }
                    } else {
                        System.out.println("Invalid ticket.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- VISITOR ENTRY RECORDS ---");
                    visitorService.viewEntries();
                    break;

                case 4:
                    System.out.println("\n--- ADMIN LOGIN ---");
                    System.out.print("Username: ");
                    String user = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();

                    if (adminService.login(user, pass)) {
                        System.out.println("Login successful!");

                        while (true) {
                            System.out.println("\n--- ADMIN MENU ---");
                            System.out.println("1. Add New Admin");
                            System.out.println("2. Update Admin Record");
                            System.out.println("3. Delete Admin Record");
                            System.out.println("4. Monitor & Manage Bookings");
                            System.out.println("5. View All Admin Accounts");
                            System.out.println("6. View Visitor Entry Logs");
                            System.out.println("0. Back to Main Menu");
                            System.out.print("Enter choice: ");
                            int adminChoice = sc.nextInt();
                            sc.nextLine();

                            switch (adminChoice) {
                                case 1:
                                    System.out.print("Username: ");
                                    String newUser = sc.nextLine();
                                    System.out.print("Password: ");
                                    String newPass = sc.nextLine();
                                    adminService.addAdmin(newUser, newPass);
                                    break;
                                case 2:
                                    System.out.print("Admin ID: ");
                                    int upId = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("New Username: ");
                                    String upUser = sc.nextLine();
                                    System.out.print("New Password: ");
                                    String upPass = sc.nextLine();
                                    adminService.updateAdmin(upId, upUser, upPass);
                                    break;
                                case 3:
                                    System.out.print("Admin ID: ");
                                    int delId = sc.nextInt();
                                    sc.nextLine();
                                    adminService.deleteAdmin(delId);
                                    break;
                                case 4:
                                    adminService.manageBookings(sc);
                                    break;
                                case 5:
                                    adminService.viewAdmins();
                                    break;
                                case 6:
                                    visitorService.viewEntries();
                                    break;
                                case 0:
                                    break;
                            }
                            if (adminChoice == 0)
                                break;
                        }
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}