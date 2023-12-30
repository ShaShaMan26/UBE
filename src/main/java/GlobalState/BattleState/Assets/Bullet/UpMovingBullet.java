package GlobalState.BattleState.Assets.Bullet;

import java.awt.image.BufferedImage;

public class UpMovingBullet extends Bullet {
    public UpMovingBullet(int x, int y, int speed, BufferedImage sprite) {
        super(x, y, speed, sprite);
    }

    @Override
    public void progressMovement() {
        y -= speed;
    }
}
