package inputoutput;

import serializations.StreetSerializer;

import entities.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputToFIle {
    private final String ouputFileName;
    private final StreetSerializer streetSerializer = new StreetSerializer();
    private final Street street;
    public OutputToFIle(String ouputFileName, Street street) {
        this.ouputFileName = ouputFileName;
        this.street = street;
    }
    public  void output() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ouputFileName));
        writer.write(streetSerializer.objToJson(street));
        writer.close();
    }

}
