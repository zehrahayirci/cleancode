package ticket;

import planes.Plane;

public class Booking {
    private final String passengerId;
    private final int rowId;
    private final char seat;
    private final Plane.BookingClass bookingClass;
    private final boolean isPremiumFood;

    public Booking(String passengerId, int rowId, char seat, Plane.BookingClass bookingClass, boolean isPremiumFood) {
        this.passengerId = passengerId;
        this.rowId = rowId;
        this.seat = seat;
        this.bookingClass = bookingClass;
        this.isPremiumFood = isPremiumFood;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public int getRowId() {
        return rowId;
    }

    public char getSeat() {
        return seat;
    }

    public Plane.BookingClass getBookingClass() {
        return bookingClass;
    }

    public boolean isPremiumFood() {
        return isPremiumFood;
    }
}
