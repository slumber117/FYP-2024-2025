import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

// I have extended the JPanel class in order to be able to use all of it's methods.
public class PanelManager extends JPanel{
    //private attributes.
        private String title;
        private JTabbedPane tab;
        private JButton btn, redBtn, blueBtn, greenBtn;
        private JPanel ColourPnl, VocabularyPnl;
        private JTextArea txtArea, vocab;


        // Constructor with preset conditions.
        public PanelManager(String title){
            this.title = title;
            // According to my research, default colour will be light yellow.
            this.setBackground(new Color(255, 255, 192));
            this.setLayout(new BorderLayout());

            ColourPnl = new JPanel(new CardLayout());
            tab = new JTabbedPane(JTabbedPane.TOP);
            btn = new JButton("Add tab");
            ColourPnl = new JPanel();
            ColourPnl.setLayout(new GridLayout(2, 2));


            // Creating the buttons
            redBtn = new JButton();
            blueBtn = new JButton();
            greenBtn = new JButton();


            // Adding the buttons to the Colour Panel (ColourPnl)
            ColourPnl.add(redBtn);
            ColourPnl.add(greenBtn);
            ColourPnl.add(blueBtn);
            VocabularyPnl = new JPanel();

            btn.setBackground(new Color(255, 255, 192));

            txtArea = new JTextArea();
            txtArea.setBackground(new Color(255, 255, 192));
            // Vocabulary Helper being set up
            vocab = new JTextArea("Vocabulary Aid");
            VocabularyPnl.add(vocab);
            VocabularyPnl.setBackground(new Color(255, 255, 192));
            vocab.setBackground(new Color(255, 255, 192));


            JScrollPane scroll = new JScrollPane(txtArea);
            scroll.setBackground(new Color(255, 255, 192));
            tab.addTab("Page 1", scroll);
            tab.setBackground(new Color(255, 255, 192));

//            this.add(ColourPnl);
            ColourPnl.setBackground(new Color(255, 255, 192));
            this.add(tab, BorderLayout.CENTER);
            this.add(btn, BorderLayout.PAGE_END);
            this.add(VocabularyPnl, BorderLayout.NORTH);

            ColourPnl.setLocation(50, 100);

            setupColours();
            setup_Add();

            changeTextColour(Color.BLACK);

            txtArea.setCaretColor(new Color(255, 255, 192));
        }


        /*
        private void setup_Colour(){
            redBtn.addActionListener(e -> changeTextColour(Color.RED));
            greenBtn.addActionListener(e -> changeTextColour(Color.GREEN));
            blueBtn.addActionListener(e -> changeTextColour(Color.BLUE));
            setupColours();
        }
        */
        private void changeTextColour(Color color){
            Component selected = tab.getSelectedComponent();
            if(selected instanceof JScrollPane){
                JViewport view = ((JScrollPane) selected).getViewport();
                if(view.getView() instanceof JTextArea){
                    JTextArea current = (JTextArea) view.getView();
                    current.setForeground(color);
                }
            }
        }
        private void setupColours(){
            redBtn.setBackground(new Color(255, 0, 0));
            greenBtn.setBackground(new Color(0, 255, 0));
            blueBtn.setBackground(new Color(0, 0, 255));
        }
        public JTextArea getCurrentTextArea(){
            Component selected = tab.getSelectedComponent();
            if(selected instanceof JScrollPane){
                JViewport view = ((JScrollPane) selected).getViewport();
                if (view.getView() instanceof JTextArea){
                    return (JTextArea) view.getView();
                }
            }
            return null;
        }

        public JTextArea getTxtArea(){
            if(tab.getTabCount() > 0){
                Component first = tab.getComponentAt(0);
                if(first instanceof JScrollPane){
                    JViewport view = ((JScrollPane)first).getViewport();
                    if(view.getView() instanceof JTextArea){
                        return (JTextArea)view.getView();
                    }
                }
            }
            return null;
        }
        private void setup_Add(){
            btn.addActionListener(e -> {
                JTextArea txt = new JTextArea("New Page: " + (tab.getTabCount() + 1), 10, 10);
                txt.setBackground(new Color(255, 234, 0));
                txt.setCaretColor(Color.GREEN);
                JScrollPane scroller = new JScrollPane(txt);

                tab.addTab("Page: " + (tab.getTabCount() +1), scroller);

                txt.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        changeTextColour(txt.getForeground());
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        changeTextColour(txt.getForeground());
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        changeTextColour(txt.getForeground());
                    }
                });
            });
        }

    }



