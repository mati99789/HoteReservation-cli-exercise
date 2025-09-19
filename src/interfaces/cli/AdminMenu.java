package interfaces.cli;

import java.util.Scanner;

public class AdminMenu {
    private final AdminResource adminResource;
    public AdminMenu(AdminResource adminResource) {
        this.adminResource = adminResource;
    }

    public void run(Scanner scanner){
        while (true) {
            System.out.println("""
                    ----------------------------------
                    1. See all Customers
                    2. See all Rooms
                    3. See all Reservations
                    4. Add a Room
                    5. Add Test Data
                    6. Back to Main Menu
                    ----------------------------------
                    """);
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again. \n");
                continue;
            }

            switch (choice) {
                case 1 -> handleAllCustomer(scanner);
                case 2 -> handleAllRooms(scanner);
                case 3 -> handleAllReservations(scanner);
                case 4 -> handleAddRoom(scanner);
                case 5 -> System.out.println("In progress");
                case 6 -> {
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void handleAllReservations(Scanner scanner) {
    }

    private void handleAllRooms(Scanner scanner) {
        var res = this.adminResource.findRooms();
        if(res.isOk()) {
            res.getData().forEach(System.out::println);
        }else  {
            System.out.println("Error: " + res.getError());
        }
    }

    private void handleAddRoom(Scanner scanner) {
        
    }

    private void handleAllCustomer(Scanner scanner) {
        var res = this.adminResource.findCustomers();
        if(res.isOk()) {
            res.getData().forEach(System.out::println);
        }else   {
            System.out.println("Error: " + res.getError());
        }
    }

}
