import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Data extends Component {
    public static void openFile(JFrame parent, PanelManager manager) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open a file");

        int ret = chooser.showOpenDialog(parent);
        if(ret == JFileChooser.APPROVE_OPTION){
            File selected = chooser.getSelectedFile();
            System.out.println(selected);
            try(BufferedReader reader = new BufferedReader(new FileReader(selected))){
                StringBuilder content = new StringBuilder();
                String line;
                while((line = reader.readLine())!= null){
                    System.out.println(line);
                    content.append(line);
                }
                JTextPane txt = manager.getCurrentTextPane();
                if(txt != null){
                    txt.setText(content.toString());
                    System.out.println(txt);
                }
            } catch (IOException x){
                JOptionPane.showMessageDialog(parent, "Error opening the file: " + x.getMessage());
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

