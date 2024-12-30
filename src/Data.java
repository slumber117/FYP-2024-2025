import javax.swing.*;
import java.io.*;

public class Data {
    public static void openFile(JFrame parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open a file");
        StringBuilder content = new StringBuilder();

        int ret = chooser.showOpenDialog(parent);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selected))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent, "Error opening file: " + e.getMessage());
            }
        }

    }
    public static void saveFile(JFrame parent, String content) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save the file");
        int ret = chooser.showSaveDialog(parent);
        if(ret == JFileChooser.APPROVE_OPTION){
            File selected = chooser.getSelectedFile();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(selected))){
                writer.write(content);
            } catch (IOException e){
                JOptionPane.showMessageDialog(parent, "Error saving the file: " + e.getMessage());
            }
        }
    }
}

