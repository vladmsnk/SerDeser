package entities;


import java.util.ArrayList;
import java.util.List;

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
