package people;

import luggage.Luggage;
import ticket.BoardingTicket;

public class Passenger {

    public void setBoardingTicket(BoardingTicket boardingTicket) {
        this.boardingTicket = boardingTicket;
    }

    private BoardingTicket boardingTicket;

    public String getId() {
        return id;
    }

    private final String id;

    public Luggage[] getLuggages() {
        return luggages;
    }

    private Luggage[] luggages;

    public String getBookedFlight() {
        return boardingTicket.getTicket().getFlightNumber();
    }

    public Passenger(String id, Luggage[] luggages) {
        this.id = id;
        this.luggages = luggages;
    }

    public Luggage[] dropLuggage() {
        Luggage[] tmp = luggages;
        luggages = new Luggage[0];
        return tmp;
    }

    public boolean isCheckedIn() {
        return boardingTicket != null && boardingTicket.isCheckedIn();
    }

    @Override
    public String toString() {
        return "Passenger{" + "id='" + id + '\'' + ", luggage size=" + luggages.length + '\'' + '}';
    }
}