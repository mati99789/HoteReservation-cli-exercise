import interfaces.bootstrap.AppContext;
import interfaces.cli.AdminMenu;
import interfaces.cli.AdminResource;
import interfaces.cli.ReservationResource;
import interfaces.cli.MainMenu;

import java.util.Scanner;

public class Main {

    private static AppContext appContext = new AppContext();
    private static ReservationResource reservationResource = appContext.getReservationResource();
    private static AdminResource adminResources = appContext.getAdminResource();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String userInput = scanner.nextLine();

            if (!isValidInput(userInput)) {
                System.out.println("Invalid input, try again.");
                continue;
            }


            int choice = Integer.parseInt(userInput);

            switch (choice) {
                case 1 -> new MainMenu(reservationResource).run(scanner);
                case 2 ->
                    System.out.println("See all reservations");
                case 3 ->
                        System.out.println("Create Account");
                case 4 ->
                    new AdminMenu(adminResources).run(scanner);
                default ->
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void displayMenu() {
        String greeting = """
                ---------------------------------------
                1) Find and reserve a room
                2) See my reservations
                3) Create an Account
                4) Admin
                3) Exit
                ---------------------------------------
                """;
        System.out.println(greeting);
        System.out.print("Choose an option: ");
    }

    private static boolean isValidInput(String input) {
        try {
            int chooseNumber = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}