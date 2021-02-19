package planes.seats;

import com.google.common.base.Strings;
import people.Passenger;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Row {

    private final int rowId;
    private final char rightSeat;
    private final char leftSeat;

    final Seat[] seats;

    public Row(int rowId, char leftSeat, char rightSeat, Seat[] seats) {
        this.rowId = rowId;

        this.rightSeat = rightSeat;
        this.leftSeat = leftSeat;

        this.seats = seats;
    }

    public Optional<Reservation> book(char seatInRow, String passengerId) {
        checkArgument(seatInRow > leftSeat && seatInRow < rightSeat, "seatInRow may not out of range");
        checkArgument(!Strings.isNullOrEmpty(passengerId), "PassengerId may not be null or empty");

        final char seatIndex = getSeatIndex(seatInRow);
        final Seat seat = getSeat(seatIndex);

        return seat.bookSeat(seatInRow, passengerId).map(seatReservation -> new Reservation(rowId, seatReservation));
    }

    public boolean checkIn(Reservation reservation) {
        
        checkNotNull(reservation, "Reservation may not be null");

        final char seatIndex = getSeatIndex(reservation.getSeatReservation().getSeatId());
        final Seat seat = getSeat(seatIndex);

        return seat.checkIn(reservation.getSeatReservation());
    }

    public void board(Passenger passenger, Reservation reservation) {
        checkNotNull(reservation, "Reservation may not be null");
        final char seatIndex = getSeatIndex(reservation.getSeatReservation().getSeatId());
        final Seat seat = getSeat(seatIndex);

        seat.board(passenger, reservation.getSeatReservation());
    }

    private char getSeatIndex(char seatInRow) {
        return (char) (seatInRow - this.leftSeat);
    }

    private Seat getSeat(char seatIndex) {
        if (seatIndex < seats.length) {
            return seats[seatIndex];
        } else {
            throw new IndexOutOfBoundsException("SeatIndex not in range");
        }
    }

    public static Row create(int rowId, char leftSeat, char rightSeat, String passengerId) {
        Seat[] seats = new Seat[rightSeat - leftSeat + 1];

        for (int i = 0; i < seats.length; i++) {
            char seatIndex = (char) (leftSeat + i);
            seats[i] = new Seat(seatIndex, passengerId);
        }

        return new Row(rowId, leftSeat, rightSeat, seats);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return leftSeat == row.leftSeat && rowId == row.rowId && Arrays.equals(seats, row.seats);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rowId, leftSeat);
        result = 31 * result + Arrays.hashCode(seats);
        return result;
    }

    @Override
    public String toString() {
        return "Row{" +
                "rowId='" + rowId + '\'' +
                ", leftSeat=" + leftSeat +
                ", seats=" + Arrays.toString(seats) +
                '}';
    }
}
