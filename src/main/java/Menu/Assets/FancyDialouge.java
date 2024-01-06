package Menu.Assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import Window.GameWindow;

public class FancyDialouge extends FancyText {
    private BufferedImage txtbox;
    public FancyDialouge(String text, int speed) {
        super(text, speed, 0, 0, 7);

        InputStream stream = getClass().getResourceAsStream("/fonts/greater_determination.ttf");
        try {
            assert stream != null;
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, 0);
            attributes.put(TextAttribute.SIZE, 12F);
            FONT = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);

            if (text.length() < 6*7) {
                txtbox = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/sm_txtbox.png")));
            } else {
                txtbox = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/med_txtbox.png")));
                width = 21;
            }
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void playSFX() {
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        int pitch = (int) ((Math.random() * (88200 - 44100)) + 44100);
        gw.AUDIOPLAYER.playClip(4, pitch);
    }

    @Override
    public void paint(Graphics g) {
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        x = gw.battle.dioX;
        y = gw.battle.dioY;
        if (TEXT.equals("")) {
            this.TEXT = gw.battle.getRandD();
        }

        g.setFont(FONT);

        // draw speech bubble
        g.drawImage(txtbox, x, y, null);

        // draw text
        g.setColor(Color.BLACK);
        String[] splitText = DISPLAYEDTEXT.toString().split("/r");
        for (int i = 0; i < splitText.length; i++) {
            if (width < 20) {
                g.drawString(splitText[i], x+22, y+22+(20*i));
            } else {
                g.drawString(splitText[i], x+40, y+22+(20*i));
            }
        }

        if (ticks == SPEED) {
            update();
            ticks = 0;
        }
        ticks += 1;
    }
}
