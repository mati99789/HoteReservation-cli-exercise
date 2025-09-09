import java.util.Scanner;

public class Main {
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
                case 1:
                    System.out.println("Main");
                    break;
                case 2:
                    System.out.println("Admin");
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }
        }
    }

    private static void displayMenu() {
        String greeting = """
                ==Hotel==
                1) Main
                2) Admin
                3) Exit
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