package pr.miem.vlad.entities;

import pr.miem.vlad.restrictions.AnimalType;

public class Pet {
    private final String petName;
    private final AnimalType animalType;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        Pet other = (Pet) o;
        boolean petNameEquals = this.petName.equals(other.petName);
        boolean animalTypeEquals = (this.animalType == other.animalType);
        return (petNameEquals && animalTypeEquals);
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
