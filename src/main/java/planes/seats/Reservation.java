package planes.seats;

import java.util.Objects;

public class Reservation {

    private final int rowId;
    private final Seat seatReservation;

    public Reservation(int rowId, Seat seatReservation) {
        this.rowId = rowId;
        this.seatReservation = seatReservation;
    }

    public int getRowId() {
        return rowId;
    }

    public Seat getSeatReservation() {
        return seatReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Reservation that = (Reservation) o;
        return rowId == that.rowId && seatReservation.equals(that.seatReservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowId, seatReservation);
    }

    @Override
    public String toString() {
        return "Reservation{" + "rowId=" + rowId + ", seatReservation=" + seatReservation + '}';
    }
}
