package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class OptionText extends JComponent {
    public final int X, Y;
    public final String TITLE;
    public boolean selected;
    public final Font FONT;

    public OptionText(String title, int x, int y) {
        this.TITLE = title;
        this.X = x;
        this.Y = y;
        this.selected = false;

        InputStream stream = getClass().getResourceAsStream("/fonts/8bitoperator_jve.ttf");
        try {
            assert stream != null;
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, .09F);
            attributes.put(TextAttribute.SIZE, 32F);
            FONT = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void interact() {
        // play sound
    }

    public void toggleSelected() {
        this.selected = !this.selected;
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(FONT);
        g.drawString(TITLE, X, Y);
    }
}
