package FlightTicketReservation;

import java.util.*;

public class Flight {
    static int id = 0;
    int flightID;
    String flightName;
    Date flightDate;
    int tickets;
    int price;
    ArrayList<String> passengerDetails;
    ArrayList<Integer> passengerIDs;
    ArrayList<Integer> bookedTicketsPerPassenger;
    ArrayList<Integer> passengerCost;

    public Flight(String flightName, Date flightDate) {
        this.flightName = flightName;
        this.flightDate = flightDate;
        tickets = 50;
        price = 5000;
        id++;
        flightID = id;
        passengerDetails = new ArrayList<>();
        passengerIDs = new ArrayList<>();
        bookedTicketsPerPassenger = new ArrayList<>();
        passengerCost = new ArrayList<>();
    }

    public void addPassengerDetails(String passengerDetail, int numberOfTickets, int passengerID) {
        passengerDetails.add(passengerDetail);
        passengerIDs.add(passengerID);
        passengerCost.add(price * numberOfTickets);
        price += 200 * numberOfTickets;
        tickets -= numberOfTickets;
        bookedTicketsPerPassenger.add(numberOfTickets);
        System.out.println("\n✅ Booking Successful!");
    }

    public void cancelTicket(int passengerID) {
        int indexToRemove = passengerIDs.indexOf(passengerID);
        if (indexToRemove < 0) {
            System.out.println("\n⚠️ Passenger ID not found!");
            return;
        }
        int ticketsToCancel = bookedTicketsPerPassenger.get(indexToRemove);
        tickets += ticketsToCancel;
        price -= 200 * ticketsToCancel;
        System.out.println("\n💵 Refund Amount after cancellation: " + passengerCost.get(indexToRemove));
        bookedTicketsPerPassenger.remove(indexToRemove);
        passengerIDs.remove(Integer.valueOf(passengerID));
        passengerDetails.remove(indexToRemove);
        passengerCost.remove(indexToRemove);
        System.out.println("❌ Cancellation Successful!");
    }

    public void flightSummary() {
        System.out.println("\n🛫 Flight ID: " + flightID + " | Name: " + flightName + " | Date: " + flightDate);
        System.out.println("Remaining Tickets: " + tickets);
        System.out.println("Current Ticket Price: " + price);
    }

    public void printDetails() {
        System.out.println("\n🛫 Flight ID: " + flightID + " | Name: " + flightName + " | Date: " + flightDate);
        for (String detail : passengerDetails) {
            System.out.println(detail);
        }
    }
}

