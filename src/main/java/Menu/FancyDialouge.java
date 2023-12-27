package Menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FancyDialouge extends FancyText {
    private BufferedImage txtbox;
    public FancyDialouge(String text, int speed) {
        this.TEXT = text;
        this.SPEED = speed;
        x = 326;
        y = 125;
        width = 7;
        astPos.add(0);

        InputStream stream = getClass().getResourceAsStream("/fonts/greater_determination.ttf");
        try {
            assert stream != null;
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, 0);
            attributes.put(TextAttribute.SIZE, 12F);
            FONT = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);

            if (TEXT.length() < 6*7) {
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
    public void paint(Graphics g) {
        g.setFont(FONT);

        // draw speech bubble
        g.drawImage(txtbox, x, y, null);

        // draw text
        g.setColor(Color.BLACK);
        String[] splitText = DISPLAYEDTEXT.toString().split("/r");
        for (int i = 0; i < splitText.length; i++) {
            g.drawString(splitText[i], x+22, y+22+(14*i));
        }

        if (ticks == SPEED) {
            update();
            ticks = 0;
        }
        ticks += 1;
    }
}
