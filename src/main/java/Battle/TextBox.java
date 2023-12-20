package Battle;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextBox extends Component {
    private final int X, Y;
    private final Font FONT;
    public TextBox() {
        this.X = 33;
        this.Y = 251;

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

    public void paint(Graphics g) {
        super.paint(g);

        // draw text box
        g.setColor(Color.WHITE);
        g.fillRect(X, Y, 574, 139);
        g.setColor(Color.BLACK);
        g.fillRect(X+5, Y+5, 564, 129);

        // draw text
        g.setColor(Color.WHITE);
        g.setFont(FONT);
        //g.drawString("* You encountered the Dummy.", X+20, Y+45);
    }
}
