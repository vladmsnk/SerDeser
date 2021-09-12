package entities;

import java.util.ArrayList;

public class Room {
    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.peopleCount = 0;
    }

    private int roomNumber;
    private int peopleCount;
    private ArrayList<Person> residents = new ArrayList<>();

    public void assignResident(Person person) {
        this.peopleCount++;
        this.residents.add(person);
    }

    public String getPeopleCount() {
        return String.valueOf(peopleCount);
    }

    public String getRoomNumber() {
        return String.valueOf(roomNumber);
    }

    public ArrayList<Person> getResidents() {
        return new ArrayList<>(residents);
    }

}
