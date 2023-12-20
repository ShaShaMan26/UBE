package Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player extends Component {
    private int health, x, y, speed;
    private boolean statsDisplayed = false;
    private BufferedImage sprite;
    public Font font = null, font2 = null;
    public Player() {
        this.x = 0;
        this.y = 0;
        health = 3;
        speed = 4;


        InputStream stream = getClass().getResourceAsStream("/fonts/UMNC.ttf");
        try {
            assert stream != null;
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, .02F);
            attributes.put(TextAttribute.SIZE, 15F);
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);

            stream = getClass().getResourceAsStream("/fonts/8bit_wonder.ttf");
            attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, .05F);
            attributes.put(TextAttribute.SIZE, 12F);
            assert stream != null;
            font2 = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);

            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/player_soul.png")));
        } catch (Exception e) {

        }
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void toggleStatsDisplayed() {
        this.statsDisplayed = !statsDisplayed;
    }

    public void moveUp() {
        y -= speed;
    }
    public void moveDown() {
        y += speed;
    }
    public void moveLeft() {
        x -= speed;
    }
    public void moveRight() {
        x += speed;
    }
    public void damage(int amount) {
        health -= amount;

        if (health > 20) {
            health = 20;
        } else if (health < 0) {
            health = 0;
        }
    }

    public void paint(Graphics g) {
        g.drawImage(sprite, x, y, null);

        if (statsDisplayed) {
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("LV 1", 122, 417);
            g.drawString(health+" / 20", 320, 417);

            g.setFont(font2);
            g.drawString("HP", 246, 414);

            g.setColor(new Color(252, 6, 5));
            g.fillRect(278, 399, 25, 21);
            g.setColor(new Color(251, 255, 41));
            g.fillRect(278, 399, (int)((25F/20F) * health), 21);
        }
    }
}
