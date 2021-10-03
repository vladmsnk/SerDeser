package pr.miem.vlad.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Street {

    private final String streetName;
    private final ArrayList<Home> homes;

    public Street(String streetName, ArrayList<Home> homes) {
        this.streetName = streetName;
        this.homes = homes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return Objects.equals(streetName, street.streetName) && Objects.equals(homes, street.homes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, homes);
    }

    public String getStreetName() {
        return streetName;
    }

    public ArrayList<Home> getHomes() {
        return new ArrayList<>(homes);
    }

    public static class Builder {
        private String streetName;
        private ArrayList<Home> homes;

        public Builder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder withHomes(ArrayList<Home> homes) {
            this.homes = homes;
            return this;
        }

        public Street build() {
            return new Street(streetName, homes);
        }
    }
}
