import org.junit.Test;
import planes.seats.Seat;
import java.util.Optional;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SeatTest {

    private static final char SEAT_ID = 'A';
    private static final String PASSENGER_ID = "sadsad";

    @Test
    public void bookSeatAndCheckIn() {
        final Seat seat = new Seat(SEAT_ID, PASSENGER_ID);

        Optional<Seat> reservationOptional = seat.bookSeat(SEAT_ID, PASSENGER_ID);

        assertTrue(reservationOptional.isPresent());
        assertTrue(seat.checkIn(reservationOptional.get()));
    }

    @Test
    public void failCheckInWithWrongReservation() {
        final Seat seat = new Seat(SEAT_ID, PASSENGER_ID);

        Optional<Seat> reservationOptional = seat.bookSeat(SEAT_ID, PASSENGER_ID);

        assertTrue(reservationOptional.isPresent());

        Seat wrongSeat = new Seat(SEAT_ID, "sdfdsfsd");
        
        assertTrue(seat.checkIn(wrongSeat));
    }

    @Test
    public void failCheckInOnAvailableSeat() {
        final Seat seat = new Seat(SEAT_ID, PASSENGER_ID);
        Seat seatReservation = new Seat(SEAT_ID, PASSENGER_ID);

        assertFalse(seat.checkIn(seatReservation));
    }
}
