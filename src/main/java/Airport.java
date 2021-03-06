import airport.areas.EntranceHall;
import airport.areas.SecuredArea;
import people.Passenger;
import ticket.BoardingTicket;
import planes.Plane.BookingClass;
import ticket.Booking;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public class Airport {

    private final EntranceHall entranceHall;
    private final SecuredArea securedArea;

    public Airport(EntranceHall entranceHall, SecuredArea securedArea) {
        this.entranceHall = checkNotNull(entranceHall);
        this.securedArea = checkNotNull(securedArea);
    }

    public void runUserStoryFromBookingToBoarding(Passenger passenger, String flight, Booking booking) {
        Optional<BoardingTicket> boardingTicket = entranceHall
                .bookTicket(flight, booking)
                .flatMap(entranceHall::checkIn);

        passenger.setBoardingTicket(boardingTicket.get());

        entranceHall.dropLuggage(passenger);

        securedArea.enter(passenger)
            .boardPlane(passenger.getId(), boardingTicket.get());
    }

}
