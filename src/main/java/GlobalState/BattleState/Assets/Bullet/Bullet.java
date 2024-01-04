package GlobalState.BattleState.Assets.Bullet;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends Component {
    public int damVal, speed, x, y, rotation;
    public float fadeSpeed, fadeTick = 0;
    public BufferedImage sprite;
    public Bullet(int damVal, int x, int y, int speed, float fadeSpeed, int rotation, BufferedImage sprite) {
        this.damVal = damVal;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.fadeSpeed = fadeSpeed;
        this.sprite = sprite;
        rotateSprite(rotation*90);
    }
    public Bullet() {

    }

    public void progressMovement() {
        if (fadeTick >= 1) {
            if (rotation == 270) {
                x -= speed;
            } else if (rotation == 180) {
                y += speed;
            } else if (rotation == 90) {
                x += speed;
            } else {
                y -= speed;
            }
        } else {
            fadeTick += fadeSpeed;
            if (fadeTick > 1) {
                fadeTick = 1;
            }
        }
    }

    public void rotateSprite(int angle) {
        rotation = angle;

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = sprite.getWidth();
        int h = sprite.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2F, (newHeight - h) / 2F);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(sprite, 0, 0, this);
        g2d.dispose();

        sprite = rotated;
    }

    public void paint(Graphics g) {
        ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeTick));
        g.drawImage(sprite, x, y, null);
        ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}
