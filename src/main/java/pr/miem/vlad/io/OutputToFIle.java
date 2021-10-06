package pr.miem.vlad.io;

import pr.miem.vlad.serializations.StreetSerializer;

import pr.miem.vlad.entities.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputToFIle {
    private final StreetSerializer streetSerializer = new StreetSerializer();

    public void writeToFile(Street street, String outputFileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        writer.write(streetSerializer.objToJson(street));
        writer.close();
    }

}
