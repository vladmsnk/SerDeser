package pr.miem.vlad.io;

import pr.miem.vlad.deserializations.StreetDeserializer;
import pr.miem.vlad.entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

public class InputFromFile {
    private final StreetDeserializer streetDeserializer = new StreetDeserializer();

    public Street parseFile(String inputFileName) throws IOException {
        String data;
        if (new File(inputFileName).isFile()) {
            data = new String(Files.readAllBytes(Paths.get(inputFileName)));
            data = data.replaceAll("\\s+", "");
        } else {
            throw new FileNotFoundException("File not found!");
        }
        return streetDeserializer.fromJsonToObj(data);

    }
}
