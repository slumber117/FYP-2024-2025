import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class DataTest {
    private File temp;

    @BeforeEach
    public void set_it_up() throws IOException{
        temp = File.createTempFile("testFile", ".txt");
    }
    @AfterEach
    public void delete(){
        if(temp != null && temp.exists()){
            temp.delete();
        }
    }
    @Test
    public void openFile() throws IOException {
        temp = File.createTempFile("testFile", ".txt");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(temp))){
            writer.write("Works!");

        }

        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(temp))){
            String line;
            while((line = reader.readLine()) != null){
                content.append(line);
            }
        }

        assertEquals("Works!", content.toString());

    }

    // This test ensures a file writes and reads by using a temporary file.
    @Test
    public void saveFile() throws IOException {
        String saved = "random test";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(temp))) {
            writer.write(saved);
            System.out.println("It writes");
        }

        StringBuilder stuff = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(temp))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stuff.append(line);
            }
            System.out.println("It reads the file");
        }

        assertEquals(saved, stuff.toString());
    }
}
