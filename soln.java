import java.util.ArrayList;
import java.util.List;

class Passenger {
    String name;
    int seatsBooked;

    Passenger(String name, int seatsBooked) {
        this.name = name;
        this.seatsBooked = seatsBooked;
    }
}

class Flight {
    String flightName;
    int totalSeats = 50;
    int availableSeats = 50;
    int basePrice = 5000;
    int currentPrice = basePrice;
    List<Passenger> passengers = new ArrayList<>();

    Flight(String flightName) {
        this.flightName = flightName;
    }

    public boolean bookTicket(String passengerName, int seatsRequired) {
        if (seatsRequired > availableSeats) {
            System.out.println("Not enough seats available.");
            return false;
        }
        availableSeats -= seatsRequired;
        passengers.add(new Passenger(passengerName, seatsRequired));
        currentPrice += 200;
        return true;
    }

    public boolean cancelTicket(String passengerName) {
        Passenger passengerToRemove = null;
        for (Passenger passenger : passengers) {
            if (passenger.name.equals(passengerName)) {
                availableSeats += passenger.seatsBooked;
                currentPrice -= 200;
                passengerToRemove = passenger;
                break;
            }
        }
        if (passengerToRemove != null) {
            passengers.remove(passengerToRemove);
            return true;
        }
        System.out.println("Passenger not found.");
        return false;
    }

    public void printFlightDetails() {
        System.out.println("Flight Name: " + flightName);
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Available Seats: " + availableSeats);
        System.out.println("Current Ticket Price: " + currentPrice);
        System.out.println("Passengers: ");
        for (Passenger passenger : passengers) {
            System.out.println("Name: " + passenger.name + ", Seats Booked: " + passenger.seatsBooked);
        }
    }
}

public class FlightReservationSystem {
    public static void main(String[] args) {
        Flight flight = new Flight("AI-202");

        flight.bookTicket("John Doe", 2);
        flight.bookTicket("Jane Doe", 3);
        
        flight.printFlightDetails();

        flight.cancelTicket("John Doe");
        
        flight.printFlightDetails();
    }
}
