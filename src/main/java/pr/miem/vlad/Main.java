package pr.miem.vlad;

import pr.miem.vlad.io.InputFromFile;
import pr.miem.vlad.entities.*;
import pr.miem.vlad.restrictions.Animal;
import pr.miem.vlad.serializations.*;

import java.io.IOException;
import java.util.ArrayList;
import pr.miem.vlad.io.App;

public class Main {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.runApp();
    }
}