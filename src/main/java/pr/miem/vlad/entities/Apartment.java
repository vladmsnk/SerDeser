package pr.miem.vlad.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Apartment {
    private final int apartmentNumber;
    private final ArrayList<Person> residents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return apartmentNumber == apartment.apartmentNumber && Objects.equals(residents, apartment.residents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentNumber, residents);
    }

    public Apartment(int apartmentNumber, ArrayList<Person> residents) {
        this.apartmentNumber = apartmentNumber;
        this.residents = residents;
    }

    public String getApartmentNumber() {
        return String.valueOf(apartmentNumber);
    }

    public ArrayList<Person> getResidents() {
        return new ArrayList<>(residents);
    }

    public static class Builder {
        private int apartmentNumber;
        private ArrayList<Person> residents;


        public Builder withApartmentNumber(int apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public Builder withResidents(ArrayList<Person> residents) {
            this.residents = residents;
            return this;
        }

        public Apartment build() {
            return new Apartment(apartmentNumber, residents);
        }
    }
}
