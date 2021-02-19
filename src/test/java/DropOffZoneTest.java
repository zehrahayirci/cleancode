import luggage.Luggage;
import org.junit.Test;
import airport.areas.zone.AutoDropOffZone;
import airport.areas.zone.DropOffZone;
import people.Passenger;
import planes.seats.Reservation;
import ticket.BoardingTicket;
import ticket.Ticket;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DropOffZoneTest {

    private Passenger createPassenger() {
        Luggage l1 = new Luggage();
        Luggage l2 = new Luggage();
        Luggage[] luggageList = { l1, l2 };

        return new Passenger("some-passengerid", luggageList);
    }

    @Test
    public void dropZoneGivesLuggageIdsIfCheckedIn() {
        Passenger p = createPassenger();
        BoardingTicket boardingTicket = new BoardingTicket(new Ticket("AH-345", mock(Reservation.class)), true);
        p.setBoardingTicket(boardingTicket);

        DropOffZone autoZone = new AutoDropOffZone();
        Luggage[] luggage = autoZone.dropOff(p);

        for (Luggage l : luggage) {
            assertTrue(l.getLuggageId().isPresent());
        }
        assertEquals(0, p.getLuggages().length);
    }

    @Test
    public void dropZoneDoesntGiveIdWithoutCheckin() {
        Passenger p = createPassenger();
        BoardingTicket boardingTicket = new BoardingTicket(new Ticket("AH-345", mock(Reservation.class)), false);
        p.setBoardingTicket(boardingTicket);

        DropOffZone autoZone = new AutoDropOffZone();
        Luggage[] luggage = autoZone.dropOff(p);

        assertEquals(0, luggage.length);
    }
}
