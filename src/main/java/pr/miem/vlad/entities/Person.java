package pr.miem.vlad.entities;


import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private final String personName;
    private final String personLastName;
    private final int money;
    private final ArrayList<Pet> personsPet;

    public Person(String personName, String personLastName, int money, ArrayList<Pet> personsPet) {
        this.personName = personName;
        this.personLastName = personLastName;
        this.personsPet = personsPet;
        this.money = money;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public String getMoney() {
        return String.valueOf(money);
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

        public Builder withMoney(int moneyCount) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return money == person.money && Objects.equals(personName, person.personName) && Objects.equals(personLastName, person.personLastName) && Objects.equals(personsPet, person.personsPet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName, personLastName, money, personsPet);
    }
}
