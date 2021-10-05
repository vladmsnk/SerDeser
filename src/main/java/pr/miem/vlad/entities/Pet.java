package pr.miem.vlad.entities;

import pr.miem.vlad.restrictions.Animal;

import java.util.Objects;

public class Pet {
    private final String name;
    private final Animal animal;

    private Pet(String name, Animal animal) {
        this.name = name;
        this.animal = animal;
    }

    public String getName() {
        return name;
    }

    public String getAnimal() {
        return animal.name();
    }

    public static class Builder {
        private String name;
        private Animal animal;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAnimal(Animal animal) {
            this.animal = animal;
            return this;
        }

        public Pet build() {
            return new Pet(name, animal);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(name, pet.name) && animal == pet.animal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, animal);
    }
}
