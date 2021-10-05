package pr.miem.vlad.io;

import pr.miem.vlad.deserializations.StreetDeserializer;
import pr.miem.vlad.entities.*;

import java.io.IOException;
import java.nio.file.*;

public class InputFromFile {
    private final String inputFileName;
    private final StreetDeserializer streetDeserializer = new StreetDeserializer();

    public InputFromFile(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public Street parseFile() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(inputFileName)));
        data = data.replaceAll("\\s+","");
        return streetDeserializer.fromJsonToObj(data);
    }
}
