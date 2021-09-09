package entities;

public class Pet {
    public Pet(String petName, String petType) {
        this.petName = petName;
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetType() {
        return petType;
    }

    private String petName;
    private String petType;
}
