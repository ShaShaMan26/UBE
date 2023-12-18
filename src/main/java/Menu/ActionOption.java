package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ActionOption extends JComponent {
    public int X, Y;
    public String TITLE = null;
    public BufferedImage ICON = null, SELECTEDICON = null;
    public boolean selected;
    public Font FONT = null;

    public ActionOption(String title, int x, int y) {
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

    public ActionOption() {
    }

    public void interact() {
        // play sound
    }

    public void toggleSelected() {
        this.selected = !this.selected;
    }

    public void paint(Graphics g) {
        if (TITLE != null) {
            g.setColor(Color.WHITE);
            g.setFont(FONT);
            g.drawString(TITLE, X, Y);
        } else {
            if (selected) {
                g.drawImage(SELECTEDICON, X, Y, null);
            } else {
                g.drawImage(ICON, X, Y, null);
            }
        }
    }
}
