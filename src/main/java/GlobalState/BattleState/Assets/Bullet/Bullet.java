package GlobalState.BattleState.Assets.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Bullet extends Component {
    public int speed, x, y;
    public BufferedImage sprite;
    public Bullet(int x, int y, int speed, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sprite = sprite;
    }
    public abstract void progressMovement();

    public void paint(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }
}
