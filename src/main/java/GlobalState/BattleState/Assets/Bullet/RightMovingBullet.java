package GlobalState.BattleState.Assets.Bullet;

import java.awt.image.BufferedImage;

public class RightMovingBullet extends Bullet {
    public RightMovingBullet(int x, int y, int speed, BufferedImage sprite) {
        super(x, y, speed, sprite);
    }

    @Override
    public void progressMovement() {
        x += speed;
    }
}
