package pr.miem.vlad.entities;


import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private final String name;
    private final String lastName;
    private final int money;
    private final ArrayList<Pet> pets;

    private Person(String name, String lastName, int money, ArrayList<Pet> pets) {
        this.name = name;
        this.lastName = lastName;
        this.pets = pets;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMoney() {
        return String.valueOf(money);
    }

    public  ArrayList<Pet> getPets() {
        return new ArrayList<>(pets);
    }

    public static class Builder {
        private String name;
        private String lastName;
        private int money;
        private ArrayList<Pet> pets;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withMoney(int money) {
            this.money = money;
            return this;
        }

        public Builder withPets(ArrayList<Pet> pets) {
            this.pets = pets;
            return this;
        }

        public Person build() {
            return new Person(name, lastName, money, pets);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return money == person.money && Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName) && Objects.equals(pets, person.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, money, pets);
    }
}
