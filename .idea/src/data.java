import javax.swing.*;
import java.io.*;

public class data{
    public static String openFile(JFrame father){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open a file");
        StringBuilder content = new StringBuilder();

        int ret = fileChooser.showOpenDialog(father);
        if (ret = fileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();

            try(BufferedReader reading = new BufferedReader(new FileReader(file));){
                String line;

                while ((line = reading.readLine()) != null){
                    content.append(line).append("\n");
                }

            }catch (IOException e){
                JOptionPane.showMessageDialog(father, "Error opening file: " + e.getMessage());
            }
        }
        return content.toString();
    }
    public static void saveFile(JFrame father, String data){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save a file");

        int ret = fileChooser.showSaveDialog(father);
        if(ret == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try(BufferedWriter wrote = new BufferedWriter(new FileWriter(file))){
                wrote.write(data);
            } catch (IOException e){
                JOptionPane.showMessageDialog(father, "Error occured while saving the file: " + e.getMEssage());
            }
        }
    }
}