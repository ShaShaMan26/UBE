package Menu.Assets;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LoadingText extends Component {
    public Font font = null;
    public LoadingText() {
        InputStream stream = getClass().getResourceAsStream("/fonts/8bitoperator_jve.ttf");
        try {
            assert stream != null;
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, .09F);
            attributes.put(TextAttribute.SIZE, 52F);
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }

    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Loading...", 200, 212);
    }
}
