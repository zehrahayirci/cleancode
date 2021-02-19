import airport.areas.EntranceHall;
import airport.areas.SecuredArea;
import com.google.common.collect.ImmutableMap;
import luggage.Luggage;
import people.Passenger;
import planes.Plane;
import planes.Plane.BookingClass;


public class Runner {
    private static final String flightNumber = "AH-3356";


    public static void main(String[] args) {
        Airport airport = createDefaultAirport();
        Luggage[] luggages = {new Luggage()};
        Passenger passenger = new Passenger("eh-324", luggages);

        airport.runUserStoryFromBookingToBoarding(passenger, flightNumber,
                BookingClass.Economy, 65, 'C');
    }

    private static Airport createDefaultAirport() {

        final Plane plane = new Plane("eh-324",flightNumber, BookingClass.Economy, 65, 'C', 1, 6, 'A', 'C',
                7, 20, 'A', 'D', 21, 99, 'A', 'F', new Luggage[20]);

        EntranceHall entranceHall = new EntranceHall(ImmutableMap.of(flightNumber, plane));
        SecuredArea securedArea = new SecuredArea(ImmutableMap.of(flightNumber, plane));

        return new Airport(entranceHall, securedArea);
    }
}
