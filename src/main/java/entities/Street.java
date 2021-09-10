package entities;

import java.util.ArrayList;

public class Street {
    public Street(String streetName) {
        this.streetName = streetName;
    }
    public void addHome(Home home) {
        homes.add(home);
    }
    public String getStreetName() {
        return streetName;
    }

    public ArrayList<Home> getHomes() {
        return new ArrayList<>(homes);
    }

    private ArrayList<Home> homes = new ArrayList<>();
    private String streetName;

}
