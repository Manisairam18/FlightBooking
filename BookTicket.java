package FlightTicketReservation;

import java.util.*;
import java.text.SimpleDateFormat;

public class BookTicket {

    public static void book(Flight currentFlight, int tickets, int passengerID) {
        String passengerDetail = "Passenger ID: " + passengerID + " | Tickets Booked: " 
                                + tickets + " | Total Cost: $" + currentFlight.price * tickets;
        currentFlight.addPassengerDetails(passengerDetail, tickets, passengerID);
        currentFlight.flightSummary();
        currentFlight.printDetails();
    }

    public static void cancel(Flight currentFlight, int passengerID) {
        currentFlight.cancelTicket(passengerID);
        currentFlight.flightSummary();
        currentFlight.printDetails();
    }

    public static void print(Flight f) {
        f.printDetails();
    }

    public static void main(String[] args) {
        List<Flight> flights = createFlights();
        int passengerID = 1;
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = getUserChoice(sc);

            switch (choice) {
                case 1:
                    bookFlight(sc, flights, passengerID);
                    passengerID++;
                    break;
                case 2:
                    cancelBooking(sc, flights);
                    break;
                case 3:
                    printAllFlights(flights);
                    break;
                case 4:
                    System.out.println("\n👋 Exiting the system. Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("\n❌ Invalid choice. Please try again.");
            }
        }
    }

    private static List<Flight> createFlights() {
        List<Flight> flights = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            flights.add(new Flight("Flight A", sdf.parse("01/08/2024 09:00")));
            flights.add(new Flight("Flight B", sdf.parse("01/08/2024 14:00")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }

    private static void printMenu() {
        System.out.println("\n=== Flight Ticket Reservation System ===");
        System.out.println("1. Book a Ticket");
        System.out.println("2. Cancel a Booking");
        System.out.println("3. Print Flight Details");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("❌ Invalid input. Please enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static void bookFlight(Scanner sc, List<Flight> flights, int passengerID) {
        System.out.print("Enter Flight ID: ");
        int fid = getValidFlightID(sc, flights);

        Flight currentFlight = flights.get(fid - 1);
        currentFlight.flightSummary();
        System.out.print("Enter number of tickets: ");
        int t = getValidTicketCount(sc, currentFlight);

        book(currentFlight, t, passengerID);
    }

    private static void cancelBooking(Scanner sc, List<Flight> flights) {
        System.out.print("Enter Flight ID: ");
        int fid = getValidFlightID(sc, flights);

        System.out.print("Enter Passenger ID to cancel booking: ");
        while (!sc.hasNextInt()) {
            System.out.print("❌ Invalid input. Please enter a number: ");
            sc.next();
        }
        int passengerID = sc.nextInt();

        Flight currentFlight = flights.get(fid - 1);
        cancel(currentFlight, passengerID);
    }

    private static int getValidFlightID(Scanner sc, List<Flight> flights) {
        int fid;
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.print("❌ Invalid input. Please enter a number: ");
                sc.next();
            }
            fid = sc.nextInt();
            if (fid > 0 && fid <= flights.size()) {
                break;
            }
            System.out.print("❌ Invalid Flight ID. Please enter a valid Flight ID: ");
        }
        return fid;
    }

    private static int getValidTicketCount(Scanner sc, Flight currentFlight) {
        int t;
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.print("❌ Invalid input. Please enter a number: ");
                sc.next();
            }
            t = sc.nextInt();
            if (t > 0 && t <= currentFlight.tickets) {
                break;
            }
            System.out.print("❌ Not enough tickets available. Enter a valid number of tickets: ");
        }
        return t;
    }

    private static void printAllFlights(List<Flight> flights) {
        for (Flight f : flights) {
            if (f.passengerDetails.isEmpty()) {
                System.out.println("\nNo passenger details for Flight " + f.flightID);
            } else {
                print(f);
            }
        }
    }
}

