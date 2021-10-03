package pr.miem.vlad.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Room {
    private final int roomNumber;
    private final ArrayList<Person> residents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber && Objects.equals(residents, room.residents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, residents);
    }

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
