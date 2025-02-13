import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class PanelManager extends JPanel {
    // Private attributes
    private String title;
    private JTabbedPane tab;
    private JButton addTabButton;
    private JButton prevButton;
    private JButton nextButton;
    private ButtonSettings redBtn, blueBtn, greenBtn, yellowBtn;
    private JPanel ColourPnl, VocabularyPnl;
    private JTextPane txtArea, vocab;
    private JComboBox<Integer> dropMenu;
    private JComboBox<String> fontTypeMenu;

    public PanelManager(String title) {
        this.title = title;
        this.setBackground(new Color(255, 255, 192));
        this.setLayout(new BorderLayout());

        // Settings for the drop-down menu for font size
        Integer[] FontSize = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 24, 26, 30};
        dropMenu = new JComboBox<>(FontSize);
        dropMenu.setSelectedIndex(15);

        // Create the font type combo box
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontTypeMenu = new JComboBox<>(fontNames);
        fontTypeMenu.setSelectedItem("Arial"); // Set default font

        // Makes drop down menu work.
        fontTypeMenu.addActionListener(e -> {
            String selectedFont = (String) fontTypeMenu.getSelectedItem();
            updateFontType(selectedFont);
        });

        // Settings for JTextPane.
        txtArea = new JTextPane();
        txtArea.setContentType("text/plain");
        txtArea.setFont(new Font("Arial", Font.PLAIN, 16));
        txtArea.setBackground(new Color(255, 255, 192));

        // Makes drop down menu work again.
        dropMenu.addActionListener(e -> {
            Integer selectedSize = (Integer) dropMenu.getSelectedItem();
            updateFontSize(selectedSize);
        });

        ColourPnl = new JPanel(new GridLayout(2, 2));
        ColourPnl.setBackground(new Color(255, 255, 192));

        // colour for the colour buttons.
        redBtn = new ButtonSettings("Red", 15, Color.RED, Color.LIGHT_GRAY, 2);
        blueBtn = new ButtonSettings("Blue", 15, Color.BLUE, Color.LIGHT_GRAY, 2);
        greenBtn = new ButtonSettings("Green", 15, Color.GREEN, Color.LIGHT_GRAY, 2);
        yellowBtn = new ButtonSettings("Yellow", 15, new Color(220, 200, 0), Color.LIGHT_GRAY, 2); // Light yellow

        // Button size settings with implemention below it.
        Dimension btnSize = new Dimension(150, 25);
        redBtn.setPreferredSize(btnSize);
        greenBtn.setPreferredSize(btnSize);
        blueBtn.setPreferredSize(btnSize);
        yellowBtn.setPreferredSize(btnSize);


        ColourPnl.add(redBtn);
        ColourPnl.add(blueBtn);
        ColourPnl.add(greenBtn);
        ColourPnl.add(yellowBtn);

        // Vocabulary Panel settings.
        VocabularyPnl = new JPanel(new BorderLayout());
        vocab = new JTextPane();
        vocab.setText("Vocabulary Aid");
        vocab.setBackground(new Color(255, 255, 192));
        VocabularyPnl.add(vocab, BorderLayout.CENTER);
        VocabularyPnl.setBackground(new Color(255, 255, 192));

        // A place for toggles to go
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.fill = GridBagConstraints.HORIZONTAL;
        gbcButton.insets = new Insets(5, 5, 5, 5);


        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        buttonPanel.add(dropMenu, gbcButton);

        // Font family positioning.
        gbcButton.gridx = 1; // Second column
        buttonPanel.add(fontTypeMenu, gbcButton);

        // Toggle settings for embolding.
        ButtonSettings boldToggleBtn = new ButtonSettings("Bold", 15, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 2);
        boldToggleBtn.addActionListener(e -> {
            if (boldToggleBtn.isSelected()) {
                makeSelectedTextBold();
            } else {
                makeSelectedTextNormal();
            }
        });
        gbcButton.gridx = 2;
        buttonPanel.add(boldToggleBtn, gbcButton);

        // Toggle settings for colour.
        ButtonSettings tog = new ButtonSettings("Colour Toggle", 15, new Color(255, 255, 192), new Color(73, 129, 253), 2);
        tog.addActionListener(e -> changeColour(tog));
        tog.setPreferredSize(new Dimension(125, 25));
        gbcButton.gridx = 3;
        buttonPanel.add(tog, gbcButton);

        // position stuff.
        gbcButton.gridx = 4;
        buttonPanel.add(ColourPnl, gbcButton);
        buttonPanel.setBackground(new Color(255, 255, 192));

        JPanel UtilityPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 10);
        UtilityPanel.add(buttonPanel, gbc);


        gbc.gridx = 1;
        UtilityPanel.add(VocabularyPnl, gbc);


        UtilityPanel.setBackground(new Color(255, 255, 192));


        this.add(UtilityPanel, BorderLayout.NORTH);

        // Tabbed pane settings.
        tab = new JTabbedPane(JTabbedPane.TOP);
        JScrollPane scroll = new JScrollPane(txtArea);
        tab.addTab("Page 1", scroll);
        tab.setBackground(new Color(255, 255, 192));
        this.add(tab, BorderLayout.CENTER);

        // Button navigation.
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        addTabButton = new JButton("Add Tab");

        // Previous and Next button settings.
        prevButton.addActionListener(e -> {
            int selectedIndex = tab.getSelectedIndex();
            if (selectedIndex > 0) {
                tab.setSelectedIndex(selectedIndex - 1);
            }
        });

        nextButton.addActionListener(e -> {
            int selectedIndex = tab.getSelectedIndex();
            if (selectedIndex < tab.getTabCount() - 1) {
                tab.setSelectedIndex(selectedIndex + 1);
            }
        });

        // Navigation panel settings.
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        navPanel.add(prevButton);
        navPanel.add(addTabButton);
        navPanel.add(nextButton);

        // Add the navigation panel to the bottom of the main panel
        this.add(navPanel, BorderLayout.SOUTH);

        // Colour button method calls so, yellow had to be manually configured so to speak in RGB format for more customisability regarding research.
        redBtn.addActionListener(e -> changeTextColour(Color.RED));
        blueBtn.addActionListener(e -> changeTextColour(Color.BLUE));
        greenBtn.addActionListener(e -> changeTextColour(Color.GREEN));
        yellowBtn.addActionListener(e -> changeTextColour(new Color(220, 200, 0)));


        setupColours();
        setup_Add();
        changeTextColour(Color.BLACK);
        txtArea.setCaretColor(new Color(255, 255, 192));
    }

    private void updateFontSize(int size) {
        // Update the font size of the currently selected JTextPane
        JTextPane currentTextPane = getCurrentTextPane();
        if (currentTextPane != null) {
            currentTextPane.setFont(new Font(currentTextPane.getFont().getFontName(), Font.PLAIN, size));
        }
    }

    private void updateFontType(String fontName) {
        // Update the font type of the currently selected JTextPane
        JTextPane currentTextPane = getCurrentTextPane();
        if (currentTextPane != null) {
            currentTextPane.setFont(new Font(fontName, Font.PLAIN, currentTextPane.getFont().getSize()));
        }
    }

    private void changeColour(JToggleButton tog) {
        JTextPane currentTextPane = getCurrentTextPane();
        if (currentTextPane != null) {
            if (tog.isSelected()) {
                currentTextPane.setBackground(new Color(73, 129, 253)); // Light blue
            } else {
                currentTextPane.setBackground(new Color(255, 255, 192)); // Default background
            }
        }
    }

    private void changeTextColour(Color colour) {
        Component selected = tab.getSelectedComponent();
        if (selected instanceof JScrollPane) {
            JViewport view = ((JScrollPane) selected).getViewport();
            if (view.getView() instanceof JTextPane) {
                JTextPane current = (JTextPane) view.getView();
                current.setForeground(colour);
            }
        }
    }

    private void makeSelectedTextBold() {
        JTextPane currentTextPane = getCurrentTextPane();
        if (currentTextPane != null) {
            StyledDocument doc = currentTextPane.getStyledDocument();
            int start = currentTextPane.getSelectionStart();
            int end = currentTextPane.getSelectionEnd();

            if (start != end) {
                Style style = currentTextPane.addStyle("BoldStyle", null);
                StyleConstants.setBold(style, true);
                doc.setCharacterAttributes(start, end - start, style, false);
            }
        }
    }

    private void makeSelectedTextNormal() {
        JTextPane currentTextPane = getCurrentTextPane();
        if (currentTextPane != null) {
            StyledDocument doc = currentTextPane.getStyledDocument();
            int start = currentTextPane.getSelectionStart();
            int end = currentTextPane.getSelectionEnd();

            if (start != end) {
                Style style = currentTextPane.addStyle("NormalStyle", null);
                StyleConstants.setBold(style, false); // Set bold to false
                doc.setCharacterAttributes(start, end - start, style, false);
            }
        }
    }

    private void setupColours() {
        redBtn.setBackground(new Color(255, 0, 0));
        redBtn.setForeground(Color.WHITE);

        greenBtn.setBackground(new Color(0, 255, 0));
        greenBtn.setForeground(Color.WHITE);

        blueBtn.setBackground(new Color(0, 0, 255));
        blueBtn.setForeground(Color.WHITE);

        yellowBtn.setBackground(new Color(220, 200, 0));
        yellowBtn.setForeground(Color.WHITE);
    }

    public JTextPane getCurrentTextPane() {
        Component selected = tab.getSelectedComponent();
        if (selected instanceof JScrollPane) {
            JViewport view = ((JScrollPane) selected).getViewport();
            if (view.getView() instanceof JTextPane) {
                return (JTextPane) view.getView();
            }
        }
        return null;
    }

    private void setup_Add() {
        addTabButton.addActionListener(e -> {
            Integer selectedSize = (Integer) dropMenu.getSelectedItem();
            JTextPane txt = new JTextPane();
            txt.setText("New Page: " + (tab.getTabCount() + 1));
            txt.setFont(new Font("Arial", Font.PLAIN, selectedSize));
            txt.setBackground(new Color(255, 255, 192));
            txt.setCaretColor(Color.GREEN);
            JScrollPane scroller = new JScrollPane(txt);

            tab.addTab("Page: " + (tab.getTabCount() + 1), scroller);
        });
    }
}