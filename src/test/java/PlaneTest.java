import luggage.Luggage;
import planes.Plane;
import planes.Plane.BookingClass;
import org.junit.Test;
import ticket.Booking;
import ticket.Ticket;
import java.util.Optional;
import static org.junit.Assert.assertTrue;

import planes.ClassRows;
public class PlaneTest {

    private static final String PASSENGER_ID = "C87343";
    private static final String FLIGHT_NUMBER = "A00C3";
    private static final ClassRows FIRST_CLASS = new ClassRows(1, 6, 'A', 'C');
    private static final ClassRows BUSINESS_CLASS = new ClassRows(7, 20, 'A', 'D');
    private static final ClassRows ECONOMY_CLASS = new ClassRows(21, 99, 'A', 'F');

    @Test
    public void bookFirstClassTest() {

        Plane plane = new Plane(PASSENGER_ID, FLIGHT_NUMBER, BookingClass.FirstClass,  3, 'B', FIRST_CLASS, BUSINESS_CLASS,
                ECONOMY_CLASS, new Luggage[0]);

        Booking booking = new Booking(PASSENGER_ID, 3, 'B', BookingClass.FirstClass, true);

        Optional<Ticket> ticketOptional = plane.book(booking);
        assertTrue(ticketOptional.isPresent());
    }

    @Test
    public void bookBusinessClassTest() {
        Plane plane = new Plane(PASSENGER_ID, FLIGHT_NUMBER, BookingClass.Business, 10, 'C', FIRST_CLASS, BUSINESS_CLASS,
                ECONOMY_CLASS, new Luggage[0]);


        Booking booking = new Booking(PASSENGER_ID, 10, 'C', BookingClass.Business, true);
        Optional<Ticket> ticketOptional = plane.book(booking);
        assertTrue(ticketOptional.isPresent());
    }


    @Test
    public void bookEconomyClassTest() {
        Plane plane = new Plane(PASSENGER_ID, FLIGHT_NUMBER, BookingClass.Economy, 65, 'C', FIRST_CLASS, BUSINESS_CLASS,
                ECONOMY_CLASS, new Luggage[0]);

        Booking booking = new Booking(PASSENGER_ID, 65, 'C', BookingClass.Economy, true);
        Optional<Ticket> ticketOptional = plane.book(booking);
        assertTrue(ticketOptional.isPresent());
    }
}
