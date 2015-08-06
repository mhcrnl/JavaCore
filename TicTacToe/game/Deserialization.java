package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialization {
    private ObjectInputStream reader;
    private char[][] currentPos;
    private String currentTime;
    private int timer;

    public Deserialization() throws FileNotFoundException, IOException {
        reader = null;
        currentPos = new char[Layout.getGridSize()][Layout.getGridSize()];
        try {
            reader = new ObjectInputStream(new FileInputStream("src\\saves\\data.bin"));

            try {
                currentTime = (String) reader.readObject();
                timer = (int) reader.readObject();
                for (int i = 0; i < Layout.getGridSize(); i++) {
                    for (int j = 0; j < Layout.getGridSize(); j++) {
                        currentPos[i][j] = (char) reader.readObject();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } finally {

            if (reader != null) {
                reader.close();
            }
        }

    }
}
