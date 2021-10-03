package pr.miem.vlad.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Home {

    private final int homeNumber;
    private final ArrayList<Room> rooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Home home = (Home) o;
        return homeNumber == home.homeNumber && Objects.equals(rooms, home.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeNumber, rooms);
    }

    public Home(int homeNumber, ArrayList<Room> rooms) {
        this.homeNumber = homeNumber;
        this.rooms = rooms;
    }

    public String getHomeNumber() {
        return String.valueOf(homeNumber);
    }
    public ArrayList<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    public static class Builder {
        private int homeNumber;
        private ArrayList<Room> rooms;

        public Builder withHomeNumber(int homeNumber) {
            this.homeNumber = homeNumber;
            return this;
        }

        public Builder withRooms(ArrayList<Room> rooms) {
            this.rooms = rooms;
            return this;
        }

        public Home build() {
            return new Home(homeNumber, rooms);
        }
    }


}
