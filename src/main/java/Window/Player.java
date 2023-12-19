package Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Component {
    private int health, x, y, speed;
    private BufferedImage sprite;
    public Player() {
        this.x = 0;
        this.y = 0;
        health = 20;
        speed = 4;

        try {
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
    }

    public void paint(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }
}
