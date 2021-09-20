package inputoutput;

import deserializations.StreetDeserializer;
import serializations.StreetSerializer;
import entities.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

public class InputFromFile {
    private final String inputFileName;
    private final StreetDeserializer streetDeserializer = new StreetDeserializer();

    public InputFromFile(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public Street parseFile() throws IOException {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(inputFileName)));
        return streetDeserializer.FromJsonToObj(data);
    }


}
