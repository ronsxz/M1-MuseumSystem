package service;

import db.DBConnection;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class ExhibitService {

    public boolean virtualGallery(Scanner sc) {

        String sql = "SELECT * FROM exhibits";

        try (Connection conn = DBConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            ArrayList<Integer> ids = new ArrayList<>();
            ArrayList<String> titles = new ArrayList<>();
            ArrayList<String> descs = new ArrayList<>();
            ArrayList<String> images = new ArrayList<>();

            while (rs.next()) {
                ids.add(rs.getInt("id"));
                titles.add(rs.getString("title"));
                descs.add(rs.getString("description"));
                images.add(rs.getString("image"));
            }

            while (true) {
                System.out.println("\n===== EXHIBIT LIST =====");
                for (int i = 0; i < titles.size(); i++) {
                    System.out.println((i + 1) + ". " + titles.get(i));
                }
                System.out.print("\nSelect exhibit (or Q): ");
                String input = sc.nextLine().trim();

                if (input.equalsIgnoreCase("Q")) {
                    return true; // 🔥 signal to Main
                }
                int index;

                try {
                    index = Integer.parseInt(input) - 1;
                    if (index < 0 || index >= titles.size()) {
                        System.out.println("Invalid selection.");
                        continue;
                    }

                    while (true) {
                        System.out.println("\n===== EXHIBIT INFORMATION =====");
                        System.out.println("ID: " + ids.get(index));
                        System.out.println("Title: " + titles.get(index));
                        System.out.println("Description: " + descs.get(index));
                        System.out.println("Image: " + images.get(index));
                        System.out.print("\nChoose again or Q: ");
                        String action = sc.nextLine().trim();

                        if (action.equalsIgnoreCase("Q")) {
                            return true;
                        }
                        try {
                            int newIndex = Integer.parseInt(action) - 1;

                            if (newIndex >= 0 && newIndex < titles.size()) {
                                index = newIndex;
                            } else {
                                System.out.println("Invalid number.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid number.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
