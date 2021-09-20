package entities;

import restrictions.Animals;

public class Pet {
    private final String petName;
    private final Animals animalType;

    public Pet(String petName, Animals animalType) {
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
        private Animals animalType;

        public Builder withPetName(String petName) {
            this.petName = petName;
            return this;
        }

        public Builder withAnimalType(Animals animalType) {
            this.animalType = animalType;
            return this;
        }

        public Pet build() {
            return new Pet(petName, animalType);
        }
    }

}
