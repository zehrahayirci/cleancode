package planes;

import luggage.Luggage;
import people.Passenger;
import planes.seats.Reservation;
import planes.seats.Row;
import ticket.BoardingTicket;
import ticket.Booking;
import ticket.Ticket;

import java.util.Optional;

public class Plane {

    public enum BookingClass {
        FirstClass, Business, Economy
    }

    private final BookingClass bookingClass;
    private final String passengerId;
    private final char seatInRow;
    private final int rowId;

    private final Row[] firstClassrows;
    private final Row[] businessClassrows;
    private final Row[] economyClassrows;


    public Luggage[] luggageSpace;

    private final String flightNumber;

    public Plane(String passengerId, String flightNumber, BookingClass bookingClass, int rowId, char seatInRow,
         ClassRows firstClassSection, ClassRows businessClassSection, ClassRows economyClassSection,  Luggage[] luggageSpace) {
        this.flightNumber = flightNumber;
        this.passengerId = passengerId;
        this.bookingClass = bookingClass;
        this.rowId = rowId;
        this.seatInRow = seatInRow;

        this.firstClassrows = create(firstClassSection, passengerId);
        this.businessClassrows = create(businessClassSection, passengerId);
        this.economyClassrows = create(economyClassSection, passengerId);
        this.luggageSpace = luggageSpace;
    }

    public static Row[] create(ClassRows ClassSection, String passengerId) {
        Row[] rows = new Row[ClassSection.getLastRow() + 1 - ClassSection.getFirstRow()];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = Row.create(i + 1, ClassSection.getLeftSeat(), ClassSection.getRightSeat(), passengerId);
        }

        return rows;
    }


    public Optional<Ticket> book(Booking booking) {

        if(passengerId.equals("")) {
            throw new IllegalArgumentException("Booking Details may not be null or empty");
        }
        if (rowId < 0) {
            throw new IllegalArgumentException();
        }
        if (seatInRow < 'A') {
            throw new IllegalArgumentException();
        }


        Row[] rows = getRowsFromBookingClass(booking.getBookingClass());
        return book(rows, booking.getPassengerId(), booking.getRowId(), booking.getSeat()).map(ticket -> ticket.withBookingClass(bookingClass));
    }

    private Optional<Ticket> book(Row[] rows, String passengerId, int rowId, char seatInRow) {
        Row row = rows[rowId - 1];
        Optional<Reservation> reservationOptional = row.book(seatInRow, passengerId);

        return reservationOptional.map(reservation1 -> new Ticket(flightNumber, reservation1));
    }

    public Optional<BoardingTicket> checkIn(Ticket ticket) {
        if(ticket == null) {
            throw new NullPointerException();
        }

        Row[] rows = getRowsFromBookingClass(ticket.getBookingClass());
        return checkIn(rows, ticket);
    }

    public void board(Passenger passenger, BoardingTicket boardingTicket) {
        if(passenger == null) {
            throw new NullPointerException();
        }
        if(boardingTicket == null) {
            throw new NullPointerException();
        }
        if(!boardingTicket.isCheckedIn()) {
            throw new IllegalArgumentException("Passenger needs to be checked in");
        }

        Row[] rows = getRowsFromBookingClass(boardingTicket.getTicket().getBookingClass());

        Row row = rows[boardingTicket.getTicket().getReservation().getRowId() - 1];
        row.board(passenger, boardingTicket.getTicket().getReservation());
    }

    private Optional<BoardingTicket> checkIn(Row[] rows, Ticket ticket) {
        Row row = rows[ticket.getReservation().getRowId() - 1];
        boolean checkedIn = row.checkIn(ticket.getReservation());

        if (checkedIn) {
            return Optional.of(new BoardingTicket(ticket, true));
        } else {
            return Optional.empty();
        }
    }

    private Row[] getRowsFromBookingClass(BookingClass bookingClass) {
        switch (bookingClass) {
            case Economy:
                return economyClassrows;
            case Business:
                return businessClassrows;
            case FirstClass:
                return firstClassrows;
            default:
                throw new IllegalArgumentException("BookingClass does not exist on this flight");
        }
    }

}
