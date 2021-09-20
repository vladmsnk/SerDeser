package entities;

import java.util.ArrayList;

public class Room {
    private final int roomNumber;
    private final ArrayList<Person> residents;

    public Room(int roomNumber, ArrayList<Person> residents) {
        this.roomNumber = roomNumber;
        this.residents = residents;
    }
    public int getPeopleCount() {
        return residents.size();
    }

    public String getRoomNumber() {
        return String.valueOf(roomNumber);
    }

    public ArrayList<Person> getResidents() {
        return new ArrayList<>(residents);
    }

    public static class Builder {
        private int roomNumber;
        private ArrayList<Person> residents;


        public Builder withRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder withResidents(ArrayList<Person> residents) {
            this.residents = residents;
            return this;
        }

        public Room build() {
            return new Room(roomNumber, residents);
        }
    }
}
