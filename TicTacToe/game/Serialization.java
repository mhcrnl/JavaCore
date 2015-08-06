package game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialization {
    private ObjectOutputStream writer;

    public Serialization(Object[] items) throws FileNotFoundException, IOException {
        writer = null;
        try {
            writer = new ObjectOutputStream(new FileOutputStream("src\\saves\\data.bin"));
            for (int i = 0; i < items.length; i++) {
                writer.writeObject(items[i]);
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
