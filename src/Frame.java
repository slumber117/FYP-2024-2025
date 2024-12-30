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
        JMenuItem open = new JMenuItem("Exit");
        JMenuItem save = new JMenuItem("Exit");

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
                openFile(e);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(open);
        menu.add(save);
        menu.add(exit);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);


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
}
