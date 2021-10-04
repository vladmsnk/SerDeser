package pr.miem.vlad.entities;

import pr.miem.vlad.restrictions.Animal;

import java.util.Objects;

public class Pet {
    private final String petName;
    private final Animal animal;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(petName, pet.petName) && animal == pet.animal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(petName, animal);
    }

    public Pet(String petName, Animal animal) {
        this.petName = petName;
        this.animal = animal;
    }

    public String getPetName() {
        return petName;
    }

    public String getAnimalType() {
        return animal.name();
    }

    public static class Builder {
        private String petName;
        private Animal animal;

        public Builder withPetName(String petName) {
            this.petName = petName;
            return this;
        }

        public Builder withAnimalType(Animal animal) {
            this.animal = animal;
            return this;
        }

        public Pet build() {
            return new Pet(petName, animal);
        }

    }
}
