package Window;

import GlobalState.BattleState.Assets.Bullet.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player extends Component {
    private int health, speed;
    public int atk, def, x, y;
    private boolean statsDisplayed = false, visible = true, slow = false, diagonal = false, invincible = false;
    private BufferedImage sprite;
    public Font font = null, font2 = null;
    public Player() {
        this.x = 0;
        this.y = 0;
        health = 20;
        speed = 4;
        atk = 10;
        def = 10;


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

    public boolean collidingWith(Bullet bullet) {
        return x+8 >= bullet.x && bullet.x + bullet.sprite.getWidth() >= x+8
                && y+8 >= bullet.y && bullet.y + bullet.sprite.getHeight() >= y+8;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void toggleSlow() {
        slow = !slow;
        if (slow) {
            speed /= 2;
        } else {
            speed *= 2;
        }
    }
    public boolean isSlow() {
        return slow;
    }
    public void toggleDiagonal() {
        diagonal = !diagonal;
        if (diagonal) {
            speed -= 1;
        } else {
            speed += 1;
        }
    }
    public void toggleInvincible() {
        invincible = !invincible;
    }
    public boolean isInvincible() {
        return invincible;
    }
    public void toggleStatsDisplayed() {
        this.statsDisplayed = !statsDisplayed;
    }
    public void toggleVisible() {
        visible = !visible;
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
    public int getHealth() {
        return health;
    }

    public void paint(Graphics g) {
        if (visible) {
                g.drawImage(sprite, x, y, null);
        } else if (invincible) {
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
            g.drawImage(sprite, x, y, null);
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }

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
