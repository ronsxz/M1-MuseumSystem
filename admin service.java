package service;
import db.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class AdminService {

    public boolean login(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username=? AND password=?";

        try (Connection conn = DBConnection.connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return false;
    }

    public void addAdmin(String username, String password) {
        String sql = "INSERT INTO admins(username, password) VALUES(?,?)";

        try (Connection conn = DBConnection.connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            System.out.println("Admin added successfully!");
        } catch (Exception e) {
            System.out.println("Admin record operation failed.");
        }
    }

    public void updateAdmin(int id, String username, String password) {

        String sql = "UPDATE admins SET username=?, password=? WHERE id=?";

        try (Connection conn = DBConnection.connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Admin updated successfully!");
        } catch (Exception e) {
            System.out.println("Admin record operation failed.");
        }
    }

    public void deleteAdmin(int id) {

        String sql = "DELETE FROM admins WHERE id=?";

        try (Connection conn = DBConnection.connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Admin deleted successfully!");

        } catch (Exception e) {
            System.out.println("Admin record operation failed.");
        }
    }

    public void viewAdmins() {

        String sql = "SELECT * FROM admins";

        try (Connection conn = DBConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- ADMIN ACCOUNTS ---");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("username"));
            }

        } catch (Exception e) {
            System.out.println("Error loading admins.");
        }
    }

    public void manageBookings(Scanner sc) {
        String sql = "SELECT * FROM tickets";

        try (Connection conn = DBConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- BOOKINGS ---");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("ticket_code") + " | " +
                                rs.getString("visitor_name") + " | " +
                                rs.getString("status"));
            }
            System.out.print("\nEnter Booking ID to manage (0 to exit): ");
            int id = sc.nextInt();
            sc.nextLine();
            if (id == 0)
                return;
            System.out.println("1. Confirm Booking");
            System.out.println("2. Cancel Booking");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                updateBookingStatus(id, "CONFIRMED");
            } else if (choice == 2) {
                updateBookingStatus(id, "CANCELLED");
            } else {
                System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Unable to retrieve booking information.");
        }
    }

    public void updateBookingStatus(int id, String status) {
        String sql = "UPDATE tickets SET status=? WHERE id=?";
        try (Connection conn = DBConnection.connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Booking updated to " + status);
        } catch (Exception e) {
            System.out.println("Booking update failed.");
        }
    }
}