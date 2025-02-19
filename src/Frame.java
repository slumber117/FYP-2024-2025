import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Frame extends JFrame {
    private String title;
    private JMenuBar menuBar;
    private PanelManager manager;

    public Frame(String title) {
        this.title = title;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exportAsPDF = new JMenuItem("Export file as PDF");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = manager.getCurrentTextPane().getText();
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
        exportAsPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsPDF();
            }
        });

        menu.add(save);
        menu.add(open);
        menu.add(exit);
        menu.add(exportAsPDF);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        this.manager = new PanelManager(title);
        this.add(manager);

        this.setVisible(true);
    }
// PDF saver
    private void saveAsPDF() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save as PDF");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }

            Document document = new Document();
            try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                PdfWriter.getInstance(document, fos);
                document.open();


                String content = manager.getCurrentTextPane().getText();
                if(!content.isBlank()){
                    document.add(new Paragraph(content));
                } else {
                    JOptionPane.showMessageDialog(this, "No content to save");
                    return;
                }

                document.close();
                JOptionPane.showMessageDialog(this, "PDF saved successfully: " + fileToSave.getAbsolutePath());
            } catch (DocumentException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving PDF: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}