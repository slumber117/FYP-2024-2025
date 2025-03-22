import static org.junit.Assert.*;
import javax.swing.*;
import javax.swing.text.*;
import org.junit.Before;
import org.junit.Test;

public class PanelManagerTest {
    private PanelManager panel; // Replace with your actual class name
    private JTextPane textPane;
    private String title;
    private JTabbedPane tab;
    private JComboBox<Integer> fontSize;
    private JButton addTab;

    @Before
    public void setUp() {

        textPane = new JTextPane();
        title = "Test Title";
        tab = new JTabbedPane();
        fontSize = new JComboBox<>(new Integer[]{12, 14, 16});
        addTab = new JButton("Add Tab");

        panel = new PanelManager(title);
        panel.setup_Add();
    }

    @Test
    public void testMakeSelectedTextBold() {
        textPane.setText("This is a test text.");
        textPane.select(10, 14);
        panel.makeSelectedTextBold();

        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        AttributeSet attrs = doc.getCharacterElement(start).getAttributes();
        boolean isBold = StyleConstants.isBold(attrs);

        assertTrue("The selected text should be bold.", isBold);
    }

    @Test
    public void testAddTab() {

        fontSize.setSelectedItem(14);

        addTab.doClick();

        assertEquals(1, tab.getTabCount());

        assertEquals("Page: 1", tab.getTitleAt(0));
    }

}