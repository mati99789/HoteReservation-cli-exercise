package interfaces.cli;

import common.Result;
import reeservation.domain.Reservation;
import reeservation.domain.dto.FreeRoom;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private final ReservationResource reservationResource;
    public MainMenu(ReservationResource reservationResource) {
        this.reservationResource = reservationResource;
    }

    public void run(Scanner scanner) {
        while (true) {
            System.out.println("""
                    == MAIN MENU ==
                    1) Find free rooms
                    2) Create reservation
                    3) Cancel reservation
                    0) Back
                    """);

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(input);
            }catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again. \n");
                continue;
            }

            switch (choice) {
                case 1 -> handleFind(scanner);
                case 2 -> handleCreate(scanner);
                case 3 ->  handleCancel(scanner);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid input. Please try again. \n");
            }
        }
    }

    public void handleCancel(Scanner scanner) {
        System.out.print("Reservation ID (UUID) : ");
        String reservationID = scanner.nextLine().trim();

        var res = this.reservationResource.cancel(reservationID);

        if(!res.isOk()) {
            System.out.println("Error: " + res.getError() + "\n");
            return;
        }

        System.out.println("Reservation has been cancelled.");

    }
    public void handleFind(Scanner scanner) {
        System.out.print("Check-in (yyyy-MM-dd): "); String from = scanner.nextLine().trim();
        System.out.print("Check-out (yyyy-MM-dd): "); String to   = scanner.nextLine().trim();

        Result<List<FreeRoom>> res = reservationResource.findFreeRooms(from, to);
        if (!res.isOk()) { System.out.println("Error: " + res.getError() + "\n"); return; }

        List<FreeRoom> rooms = res.getData();
        if (rooms.isEmpty()) { System.out.println("No free rooms.\n"); return; }

        System.out.println("== FREE ROOMS ==");
        rooms.forEach(r -> System.out.println("%s | %s | %s".formatted(r.roomNumber(), r.roomType(), r.price())));
        System.out.println();

    }
    public void handleCreate(Scanner scanner) {
        System.out.print("Email: "); String email = scanner.nextLine().trim();
        System.out.print("Room number: "); String room = scanner.nextLine().trim();
        System.out.print("Check-in (yyyy-MM-dd): "); String from = scanner.nextLine().trim();
        System.out.print("Check-out (yyyy-MM-dd): "); String to   = scanner.nextLine().trim();

        Result<Reservation> res = reservationResource.createReservation(email, room, from, to);
        if (!res.isOk()) { System.out.println("Error: " + res.getError() + "\n"); return; }

        Reservation r = res.getData();
        System.out.println("Created reservation: " + r.getUuid() + "\n");
    }
}
