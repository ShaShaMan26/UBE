package Window;

import GlobalState.BattleState.Assets.Bullet.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Component {
    private int health, speed;
    public int atk, x, y;
    private boolean visible = true, slow = false, diagonal = false, invincible = false;
    public boolean split = false;
    private BufferedImage sprite, splitSprite;
    public Player() {
        this.x = 0;
        this.y = 0;
        health = 20;
        speed = 4;
        atk = 10;

        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/player_soul.png")));
            splitSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/soul_split.png")));
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
    public void toggleVisible() {
        visible = !visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
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
        if (split) {
            g.drawImage(splitSprite, x-1, y, null);
        } else if (visible) {
                g.drawImage(sprite, x, y, null);
        } else if (invincible) {
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
            g.drawImage(sprite, x, y, null);
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
    }
}
