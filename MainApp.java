import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static LostItemDAO lostDAO;
    private static FoundItemDAO foundDAO;
    private static Scanner scanner;

    public static void main(String[] args) throws SQLException {
        lostDAO = new LostItemDAO();
        foundDAO = new FoundItemDAO();
        scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Lost and Found System ---");
            System.out.println("1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. View Lost Items");
            System.out.println("4. View Found Items");
            System.out.println("5. Match Lost & Found Items");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    reportLostItem();
                    break;
                case 2:
                    reportFoundItem();
                    break;
                case 3:
                    viewLostItems();
                    break;
                case 4:
                    viewFoundItems();
                    break;
                case 5:
                    matchLostAndFound();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    private static void reportLostItem() throws SQLException {
        System.out.print("Enter your name: ");
        String personName = scanner.nextLine();
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter location lost: ");
        String locationLost = scanner.nextLine();
        System.out.print("Enter date lost (yyyy-MM-dd): ");
        Date dateLost = Date.valueOf(scanner.nextLine());
        String status = "Unresolved";

        LostItem lostItem = new LostItem(personName, itemName, description, locationLost, dateLost, status);
        lostDAO.insertLostItem(lostItem);

        System.out.println("Lost item reported successfully.");
    }

    private static void reportFoundItem() throws SQLException {
        System.out.print("Enter your name: ");
        String finderName = scanner.nextLine();
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter location found: ");
        String locationFound = scanner.nextLine();
        System.out.print("Enter date found (yyyy-MM-dd): ");
        Date dateFound = Date.valueOf(scanner.nextLine());
        String status = "Unmatched";

        FoundItem foundItem = new FoundItem(finderName, itemName, description, locationFound, dateFound, status);
        foundDAO.insertFoundItem(foundItem);

        System.out.println("Found item reported successfully.");
    }

    private static void viewLostItems() throws SQLException {
        List<LostItem> lostItems = lostDAO.getAllLostItems();
        System.out.println("\n--- Lost Items ---");
        for (LostItem item : lostItems) {
            System.out.println(item);
        }
    }

    private static void viewFoundItems() throws SQLException {
        List<FoundItem> foundItems = foundDAO.getAllFoundItems();
        System.out.println("\n--- Found Items ---");
        for (FoundItem item : foundItems) {
            System.out.println(item);
        }
    }

    private static void matchLostAndFound() throws SQLException {
        List<LostItem> lostItems = lostDAO.getAllLostItems();
        List<FoundItem> foundItems = foundDAO.getAllFoundItems();

        boolean matchFound = false;

        for (LostItem lost : lostItems) {
            for (FoundItem found : foundItems) {
                if (lost.getItemName().equalsIgnoreCase(found.getItemName())
                        && lost.getDescription().equalsIgnoreCase(found.getDescription())
                        && lost.getLocationLost().equalsIgnoreCase(found.getLocationFound())
                        && lost.getStatus().equals("Unresolved")
                        && found.getStatus().equals("Unmatched")) {

                    System.out.println("\nMatch found:");
                    System.out.println("Lost: " + lost);
                    System.out.println("Found: " + found);

                    // Update statuses and matched IDs in DB
                    lostDAO.updateStatus(lost.getId(), "Resolved");
                    foundDAO.updateStatusAndMatch(found.getId(), "Matched", lost.getId());

                    matchFound = true;
                }
            }
        }

        if (!matchFound) {
            System.out.println("No matches found.");
        }
    }
}
