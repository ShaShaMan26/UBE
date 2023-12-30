package GlobalState.BattleState.Assets.Bullet;

import java.awt.image.BufferedImage;

public class LeftMovingBullet extends Bullet {
    public LeftMovingBullet(int x, int y, int speed, BufferedImage sprite) {
        super(x, y, speed, sprite);
    }

    @Override
    public void progressMovement() {
        x -= speed;
    }
}
