package pr.miem.vlad.entities;

import java.util.ArrayList;
import java.util.Objects;

public class House {

    private final int houseNumber;
    private final ArrayList<Apartment> apartments;

    private House(int houseNumber, ArrayList<Apartment> apartments) {
        this.houseNumber = houseNumber;
        this.apartments = apartments;
    }

    public String getHouseNumber() {
        return String.valueOf(houseNumber);
    }
    
    public ArrayList<Apartment> getApartments() {
        return new ArrayList<>(apartments);
    }

    public static class Builder {
        private int houseNumber;
        private ArrayList<Apartment> apartments;

        public Builder withHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder withApartment(ArrayList<Apartment> apartments) {
            this.apartments = apartments;
            return this;
        }

        public House build() {
            return new House(houseNumber, apartments);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return houseNumber == house.houseNumber && Objects.equals(apartments, house.apartments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseNumber, apartments);
    }
}
