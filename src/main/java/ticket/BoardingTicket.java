package ticket;

import java.util.Objects;

public class BoardingTicket {

    private final Ticket ticket;
    private final boolean checkedIn;

    public BoardingTicket(Ticket ticket, boolean checkedIn) {
        this.ticket = ticket;
        this.checkedIn = checkedIn;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardingTicket that = (BoardingTicket) o;
        return checkedIn == that.checkedIn && ticket.equals(that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket, checkedIn);
    }

    @Override
    public String toString() {
        return "BoardingTicket{" +
                "ticket=" + ticket +
                ", checkedIn=" + checkedIn +
                '}';
    }
}
