package entities;

import java.util.ArrayList;

public class Home {

    private final int homeNumber;
    private final ArrayList<Room> rooms;

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
