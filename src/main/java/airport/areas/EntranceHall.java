package airport.areas;

import airport.areas.zone.DropOffZone;
import com.google.common.base.Strings;
import com.google.common.collect.ObjectArrays;
import luggage.Luggage;
import people.Passenger;
import planes.Plane;
import planes.Plane.BookingClass;
import ticket.BoardingTicket;
import ticket.Ticket;

import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class EntranceHall extends DropOffZone {

    private final Map<String, Plane> flights;

    public EntranceHall(Map<String, Plane> flights) {
        this.flights = checkNotNull(flights);
    }

    public Optional<Ticket> bookTicket(String flightNumber, String passengerId, BookingClass bookingClass, int rowId,
            char seatInRow) {
        checkArgument(!Strings.isNullOrEmpty(flightNumber), "FlightNumber may not be Null or empty");
        checkArgument(!Strings.isNullOrEmpty(passengerId), "PassengerId may not be null or empty");
        checkArgument(rowId > 0, "RowIndex may not be negative or 0");

        return Optional.ofNullable(flights.get(flightNumber))
                .flatMap(plane -> plane.book(passengerId, bookingClass, rowId, seatInRow));
    }

    public Optional<BoardingTicket> checkIn(Ticket ticket) {
        checkNotNull(ticket, "Ticket may not be null or empty");

        Plane flight = Optional.ofNullable(flights.get(ticket.getFlightNumber())).orElseThrow(() -> new IllegalArgumentException("Flight does not exist."));
        return flight.checkIn(ticket);
    }

    public void dropLuggage(Passenger passenger) {
        checkNotNull(passenger, "Passenger may not be null");

        Plane flight = Optional.ofNullable(flights.get(passenger.getBookedFlight())).orElseThrow(() -> new IllegalArgumentException("Flight does not exist."));

        Luggage[] luggages = dropOff(passenger);
        flight.luggageSpace = ObjectArrays.concat(flight.luggageSpace, luggages, Luggage.class);

    }

    @Override
    public void close() {
        // We don't need this in entrance hall
    }

    @Override
    public void open() {
        // We don't need this in entrance hall
    }

    @Override
    public boolean isOpen() {
        // Always true
        return true;
    }
}
