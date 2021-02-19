package planes.seats;

import com.google.common.base.Strings;
import people.Passenger;

import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Seat {

    private final char seatId;
    private final String passengerId;
    private boolean checkedIn = false;
    private Seat seatReservation;
    private Passenger occupant;

    public Seat(char seatId, String passengerId) {
        this.seatId = seatId;
        this.passengerId = passengerId;
    }

    public Optional<Seat> bookSeat(final char seatId, final String passengerId) {
        checkArgument(!Strings.isNullOrEmpty(passengerId), "Passenger Id may not be null or empty");
        if (isAvailable()) {
            this.seatReservation = new Seat(seatId, passengerId);
            return Optional.of(seatReservation);
        }
        return Optional.empty();
    }

    public boolean checkIn(Seat seatReservation) {
        checkNotNull(seatReservation, "Reservation may not be null or empty");

        this.checkedIn = !isAvailable() && seatReservation.equals(this.seatReservation);

        return this.checkedIn;
    }

    public void board(Passenger passenger, Seat seatReservation) {
        checkNotNull(seatReservation, "Reservation may not be null or empty");
        checkNotNull(passenger);

        if (this.checkedIn) {
            occupant = passenger;
            System.out.println("Passenger" + passenger + " sat down");
        }
    }

    private boolean isAvailable() {
        return seatReservation == null;
    }

    public char getSeatId() {
        return seatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return checkedIn == seat.checkedIn && seatId == seat.seatId && Objects.equals(seatReservation, seat.seatReservation) && Objects.equals(occupant, seat.occupant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatId, checkedIn, seatReservation, occupant);
    }

}
