package airport.areas;

import people.Passenger;
import planes.Plane;
import ticket.BoardingTicket;

import java.util.HashMap;
import java.util.Map;

public class SecuredArea {

    private final Map<String, Plane> flights;
    private final Map<String, Passenger> passengerMap;

    public SecuredArea(Map<String, Plane> flights) {
        this.passengerMap = new HashMap<>();
        this.flights = flights;
    }

    public SecuredArea enter(Passenger passenger) {
        passengerMap.put(passenger.getId(), passenger);

        return this;
    }

    public SecuredArea boardPlane(String passengerId, BoardingTicket boardingTicket) {
        Passenger passenger = passengerMap.remove(passengerId);
        String flightNumber = boardingTicket.getTicket().getFlightNumber();
        Plane plane = flights.get(flightNumber);

        plane.board(passenger, boardingTicket);

        return this;
    }

}
