import javax.swing.*;
import java.awt.*;

public class ButtonSettings extends JToggleButton {
    private int radius;
    private Color yellow;
    private Color blue;
    private int borderSize;

    public ButtonSettings(String text, int radius, Color yellow, Color blue, int borderSize) {
        super(text);
        this.radius = radius;
        this.yellow = yellow;
        this.blue = blue;
        this.borderSize = borderSize;

        setContentAreaFilled(false);
        setForeground(Color.BLACK);
        setBackground(yellow);
    }
// This below was made just for putting a border on buttons....
    @Override
    protected void paintComponent(Graphics g) {

        if (isSelected()) {
            g.setColor(blue);
        } else {
            g.setColor(yellow);
        }

        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g.setColor(getForeground());

        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
        g.drawString(getText(), x, y);
    }
// And this was made for colouring the border
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);

        g.drawRoundRect(0, 0, getWidth() - 1 - borderSize, getHeight() - 1 - borderSize, radius, radius);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 40);
    }
}