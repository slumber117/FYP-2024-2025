import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Frame extends JFrame {
    private String title;
    private JMenuBar menuBar;
    private PanelManager manager;

    public Frame(String title){
        this.title = title;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = manager.getCurrentTextArea().getText();
                Data.saveFile(Frame.this, content);
            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Data.openFile(Frame.this, manager);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        menu.add(save);
        menu.add(open);
        menu.add(exit);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        this.manager = new PanelManager(title);
        this.add(manager);

        this.setVisible(true);

    }

    private void openFile(ActionEvent e){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open a file");

        int ret = chooser.showOpenDialog(this);
        if(ret == JFileChooser.APPROVE_OPTION){
            File selected = chooser.getSelectedFile();
            try(BufferedReader reader = new BufferedReader(new FileReader(selected))){
                StringBuilder content = new StringBuilder();
                String line;
                while((line = reader.readLine())!= null){
                    content.append(line);
                }
                JTextArea txt = manager.getCurrentTextArea();
                if(txt != null){
                    txt.setText(content.toString());
                }
            } catch (IOException x){
                JOptionPane.showMessageDialog(this, "Error opening the file: " + x.getMessage());
            }
        }
    }
    private void savingTheFile(ActionEvent ok){
        JFileChooser FileList = new JFileChooser();
        FileList.setDialogTitle("Save any file");

        int returnTheValue = FileList.showSaveDialog(this);
        if(returnTheValue == JFileChooser.APPROVE_OPTION){
            File theSelectedFile = FileList.getSelectedFile();
            if(theSelectedFile != null){
                String path = theSelectedFile.getAbsolutePath();

                // Now check if the file exists
                if(theSelectedFile.exists()){
                    int reply = JOptionPane.showConfirmDialog(this,
                            "You already have a file named like this, would you like to replace it?",
                            "Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(reply != JOptionPane.YES_OPTION){
                        return; // User chose no
                    }
                }

                try(BufferedWriter typewriter = new BufferedWriter(new FileWriter(theSelectedFile))){
                    JTextArea txt = manager.getCurrentTextArea();
                    String words = txt.getText();
                    typewriter.write(words);
                    JOptionPane.showMessageDialog(this, "The file was saved!" + path);
                } catch (IOException e){
                    JOptionPane.showMessageDialog(this, "Error with saving this file" + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "No file has been selected, please select a file");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Save was cancelled");
        }
    }

    @Override
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
}
