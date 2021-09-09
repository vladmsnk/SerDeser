package entities;

import java.util.ArrayList;

public class Room {
    Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.peopleCount = 0;
    }
    public void assignResident(Person person) {
        this.peopleCount++;
        this.residents.add(person);
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    private int roomNumber;
    private int peopleCount;
    private ArrayList<Person> residents = new ArrayList<Person>();
}
