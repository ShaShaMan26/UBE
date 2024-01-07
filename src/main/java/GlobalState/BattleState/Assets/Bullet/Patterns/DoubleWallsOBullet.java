package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DoubleWallsOBullet extends BulletPattern {
    public DoubleWallsOBullet(int speed, int damVal, int rotation, float fadeSpeed, BufferedImage sprite) {
        super(speed, damVal, rotation, fadeSpeed, sprite);
    }

    @Override
    public void generateBullets(Rectangle battleBox) {
        super.generateBullets(battleBox);

        switch (rotation) {
            case 1:
            case 3:
                for (int i = 0; i < battleBox.height / sprite.getWidth(); i++) {
                    bullets.add(new Bullet(
                                    damVal,
                                    battleBox.x + battleBox.width + sprite.getHeight(),
                                    i * sprite.getWidth() + battleBox.y,
                                    speed,
                                    fadeSpeed,
                                    3,
                                    sprite
                            )
                    );
                }
                for (int i = 0; i < battleBox.height / sprite.getWidth(); i++) {
                    bullets.add(new Bullet(
                                    damVal,
                                    battleBox.x - sprite.getHeight()*2,
                                    i * sprite.getWidth() + battleBox.y,
                                    speed,
                                    fadeSpeed,
                                    1,
                                    sprite
                            )
                    );
                }
                break;
            default:
                for (int i = 0; i < battleBox.width / sprite.getWidth(); i++) {
                    bullets.add(new Bullet(
                                    damVal,
                                    i * sprite.getWidth() + battleBox.x,
                                    battleBox.y + battleBox.height + sprite.getHeight(),
                                    speed,
                                    fadeSpeed,
                                    0,
                                    sprite
                            )
                    );
                }
                for (int i = 0; i < battleBox.width / sprite.getWidth(); i++) {
                    bullets.add(new Bullet(
                                    damVal,
                                    i * sprite.getWidth() + battleBox.x,
                                    battleBox.y - sprite.getHeight()*2,
                                    speed,
                                    fadeSpeed,
                                    2,
                                    sprite
                            )
                    );
                }
                break;
        }
        int holeIndex = (int) (Math.random()*bullets.size()/2);
        Bullet[] rBullets = new Bullet[2];
        rBullets[0] = bullets.get(holeIndex);
        rBullets[1] = bullets.get(bullets.size()/2+holeIndex);

        for (Bullet bullet : rBullets) {
            bullets.remove(bullet);
        }
    }

    @Override
    public boolean isOver() {
        switch (rotation) {
            case 1:
            case 3:
                return bullets.get(0).x <= battleBox.x;
            default:
                return bullets.get(0).y <= battleBox.y;
        }
    }
}
