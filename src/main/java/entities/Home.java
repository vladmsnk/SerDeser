package entities;

import java.util.ArrayList;

public class Home {
    public Home(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void addRoom(Room room) {
        roomes.add(room);
    }

    public String getHomeNumber() {
        return String.valueOf(homeNumber);
    }

    public ArrayList<Room> getRoomes() {
        return new ArrayList<>(roomes);
    }

    private int homeNumber;
    private ArrayList<Room> roomes = new ArrayList<>();
}
