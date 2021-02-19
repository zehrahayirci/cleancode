import org.junit.Before;
import org.junit.Test;
import planes.seats.Seat;
import planes.seats.Reservation;
import planes.seats.Row;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RowTest {

    private static final String PASSENGER_ID = "p123";
    private static final char SEAT_ID = 'C';
    private static final Reservation RESERVATION = new Reservation(43, new Seat(SEAT_ID, PASSENGER_ID));

    private Row row;

    @Before
    public void beforeTest() {
        row = Row.create(43, 'A', 'E', PASSENGER_ID);
    }

    @Test
    public void bookSeatInRow() {
        Optional<Reservation> reservation = row.book(SEAT_ID, PASSENGER_ID);

        assertTrue(reservation.isPresent());
        assertEquals(RESERVATION, reservation.get());
    }

    @Test
    public void checkInReservedSeat() {
        Optional<Reservation> reservation = row.book(SEAT_ID, PASSENGER_ID);

        assertTrue(reservation.isPresent());
        assertTrue(row.checkIn(reservation.get()));
    }

    @Test
    public void createRow() {
        Seat[] seats = new Seat[2];
        seats[0] = new Seat('A', PASSENGER_ID);
        seats[1] = new Seat('B', PASSENGER_ID);

        Row expectedRow = new Row(43, 'A', 'B', seats);
        Row createdRow = Row.create(43, 'A', 'B', PASSENGER_ID);

        assertEquals(expectedRow, createdRow);
    }
}
