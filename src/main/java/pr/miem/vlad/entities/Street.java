package pr.miem.vlad.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Street {

    private final String streetName;
    private final ArrayList<House> houses;

    private Street(String streetName, ArrayList<House> houses) {
        this.streetName = streetName;
        this.houses = houses;
    }

    public String getStreetName() {
        return streetName;
    }

    public ArrayList<House> getHouses() {
        return new ArrayList<>(houses);
    }

    public static class Builder {
        private String streetName;
        private ArrayList<House> houses;

        public Builder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder withHouses(ArrayList<House> houses) {
            this.houses = houses;
            return this;
        }

        public Street build() {
            return new Street(streetName, houses);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return Objects.equals(streetName, street.streetName) && Objects.equals(houses, street.houses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, houses);
    }
}
