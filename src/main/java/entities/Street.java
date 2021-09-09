package entities;

import java.util.ArrayList;

public class Street {
    public Street(String streetName) {
        this.streetName = streetName;
    }
    private ArrayList<Home> homes = new ArrayList<Home>();


    public void addHome(Home home) {
        homes.add(home);
    }
    private String streetName;

}
