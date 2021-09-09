package entities;


import java.util.ArrayList;
import java.util.List;

public class Person {
    public Person(String personName, String personLastName, int moneyCount) {
        this.personName = personName;
        this.personLastName = personLastName;
        this.moneyCount = moneyCount;
        this.petCount = 0;
    }
    public String getPersonName() {
        return personName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setMoney(int moneyCount) {
        this.moneyCount = moneyCount;
    }

    public void assignPet(Pet pet) {
        this.petCount++;
        this.personsPet.add(pet);
    }

    public String getPetCount() {
        return String.valueOf(petCount);
    }
    public String getMoneyCount() {
        return String.valueOf(moneyCount);
    }
    public  ArrayList<Pet> getPersonsPet() {
        return new ArrayList<>(personsPet);
    }

    private String personName;
    private String personLastName;
    private int moneyCount;
    private int petCount;
    private ArrayList<Pet> personsPet = new ArrayList<Pet>();
}
