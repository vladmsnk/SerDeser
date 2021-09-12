package entities;

public class Pet {
    public Pet(String petName, String petType) {
        this.petName = petName;
        this.petType = petType;
    }

    private String petName;
    private String petType;

    public String getPetName() {
        return petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

}
