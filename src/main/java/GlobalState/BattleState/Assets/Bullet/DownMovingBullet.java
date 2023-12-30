package GlobalState.BattleState.Assets.Bullet;

import java.awt.image.BufferedImage;

public class DownMovingBullet extends Bullet {
    public DownMovingBullet(int x, int y, int speed, BufferedImage sprite) {
        super(x, y, speed, sprite);
    }

    @Override
    public void progressMovement() {
        y += speed;
    }
}
