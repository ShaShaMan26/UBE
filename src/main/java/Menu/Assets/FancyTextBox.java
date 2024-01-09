package Menu.Assets;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import Window.GameWindow;

public class FancyTextBox extends FancyText {
    public FancyTextBox(String text, int speed) {
        super(text, speed, 53, 296, 29);

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

    @Override
    public void playSFX() {
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        int pitch = (int) ((Math.random() * (88200 - 44100)) + 44100);
        gw.AUDIOPLAYER.playClip(3, pitch);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        g.setFont(FONT);

        for (int pos : astPos) {
            g.drawString("*", x, 32*pos+y);
        }
        String[] splitText = DISPLAYEDTEXT.toString().split("/r");
        for (int i = 0; i < splitText.length; i++) {
            g.drawString(splitText[i], x+30, 32*i+y);
        }

        if (ticks == SPEED) {
            update();
            ticks = 0;
        }
        ticks += 1;
    }
}
