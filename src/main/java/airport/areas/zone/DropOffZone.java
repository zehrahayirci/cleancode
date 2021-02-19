package airport.areas.zone;

import luggage.Luggage;
import people.Passenger;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class DropOffZone {

    public Luggage[] dropOff(Passenger passenger) {
        checkNotNull(passenger);

        if(passenger.isCheckedIn()) {
            Luggage[] luggages = passenger.dropLuggage();

            for (Luggage l: luggages) {
                l.setLuggageId(passenger.getId());
            }

            return luggages;
        }

        return new Luggage[0];
    }

    public abstract void close();

    public abstract void open();

    public abstract boolean isOpen();

}
