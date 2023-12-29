package GlobalState.BattleState.Assets.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Bullet extends Component {
    private int speed, x, y;
    private BufferedImage sprite;
    public Bullet(int x, int y, int speed, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sprite = sprite;
    }
    public abstract void progressMovement();

    /**
    x, y, width, height
     **/
    public int[] getHitbox() {
        return new int[]{x, y, sprite.getWidth(), sprite.getHeight()};
    }

    public void paint(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }
}
