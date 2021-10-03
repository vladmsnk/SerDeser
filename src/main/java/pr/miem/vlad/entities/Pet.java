package pr.miem.vlad.entities;

import pr.miem.vlad.restrictions.AnimalType;

import java.util.Objects;

public class Pet {
    private final String petName;
    private final AnimalType animalType;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(petName, pet.petName) && animalType == pet.animalType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(petName, animalType);
    }

    public Pet(String petName, AnimalType animalType) {
        this.petName = petName;
        this.animalType = animalType;
    }

    public String getPetName() {
        return petName;
    }

    public String getAnimalType() {
        return animalType.name();
    }

    public static class Builder {
        private String petName;
        private AnimalType animalType;

        public Builder withPetName(String petName) {
            this.petName = petName;
            return this;
        }

        public Builder withAnimalType(AnimalType animalType) {
            this.animalType = animalType;
            return this;
        }

        public Pet build() {
            return new Pet(petName, animalType);
        }

    }
}
