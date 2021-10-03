package pr.miem.vlad.entities;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private final String personName;
    private final String personLastName;
    private final int moneyCount;
    private final ArrayList<Pet> personsPet;

    public Person(String personName, String personLastName, int moneyCount, ArrayList<Pet> personsPet) {
        this.personName = personName;
        this.personLastName = personLastName;
        this.personsPet = personsPet;
        this.moneyCount = moneyCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return moneyCount == person.moneyCount && Objects.equals(personName, person.personName) && Objects.equals(personLastName, person.personLastName) && Objects.equals(personsPet, person.personsPet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName, personLastName, moneyCount, personsPet);
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonLastName() {

        return personLastName;
    }

    public int getPetCount() {
        return personsPet.size();
    }
    public String getMoneyCount() {
        return String.valueOf(moneyCount);
    }

    public  ArrayList<Pet> getPersonsPet() {
        return new ArrayList<>(personsPet);
    }

    public static class Builder {
        private String personName;
        private String personLastName;
        private int moneyCount;
        private ArrayList<Pet> personsPets;

        public Builder withPersonName(String personName) {
            this.personName = personName;
            return this;
        }

        public Builder withPersonLastName(String personLastName) {
            this.personLastName = personLastName;
            return this;
        }

        public Builder withMoneyCount(int moneyCount) {
            this.moneyCount = moneyCount;
            return this;
        }

        public Builder withPets(ArrayList<Pet> personsPets) {
            this.personsPets = personsPets;
            return this;
        }

        public Person build() {
            return new Person(personName, personLastName, moneyCount, personsPets);
        }

    }
}
