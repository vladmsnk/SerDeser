package entities;

import java.util.ArrayList;

public class Home {
    public Home(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void addRoom(Room room) {
        roomes.add(room);
    }
    public int getHomeNumber() {
        return homeNumber;
    }
    private int homeNumber;
    private ArrayList<Room> roomes = new ArrayList<Room>();
}
