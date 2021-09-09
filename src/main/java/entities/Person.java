package entities;


import java.util.ArrayList;

public class Person {
    Person(String personName, String personLastName, int moneyCount) {
        this.personName = personName;
        this.personLastName = personLastName;
        this.moneyCount = moneyCount;
        this.petCount = 0;
    }
    public void setMoney(int moneyCount) {
        this.moneyCount = moneyCount;
    }

    public void assignPet(Pet pet) {
        this.petCount++;
        this.personsPet.add(pet);
    }

    public int getPetCount() {
        return petCount;
    }

    private String personName;
    private String personLastName;
    private int moneyCount;
    private int petCount;
    private ArrayList<Pet> personsPet = new ArrayList<Pet>();
}
