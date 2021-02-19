package ticket;

import planes.seats.Reservation;
import planes.Plane.BookingClass;

public class Ticket {

    private final String flightNumber;
    private final Reservation reservation;
    private BookingClass bookingClass; // Gets set Later.

    public Ticket(String flightNumber, Reservation reservation) {
        this.flightNumber = flightNumber;
        this.reservation = reservation;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public BookingClass getBookingClass() {
        return bookingClass;
    }

    public Ticket withBookingClass(BookingClass bookingClass) {
        this.bookingClass = bookingClass;
        return this;
    }

    @Override
    public String toString() {
        return "Ticket{" + "flightNumber='" + flightNumber + '\'' + ", reservation=" + reservation + ", bookingClass="
                + bookingClass + '}';
    }
}
