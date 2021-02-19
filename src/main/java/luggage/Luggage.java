package luggage;

import java.util.Optional;

public class Luggage {
    private String luggageId; // receives id when dropped of at counter

    public Optional<String> getLuggageId() {
        return Optional.ofNullable(luggageId);
    }

    public void setLuggageId(String luggageId) {
        this.luggageId = luggageId;
    }

    @Override
    public String toString() {
        return "Luggage{" +
                "luggageId='" + luggageId + '\'' +
                '}';
    }
}
