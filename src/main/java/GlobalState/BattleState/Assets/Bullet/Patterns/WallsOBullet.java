package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WallsOBullet extends BulletPattern {
    public WallsOBullet(int speed, int damVal, int rotation, float fadeSpeed, BufferedImage sprite) {
        super(speed, damVal, rotation, fadeSpeed, sprite);
    }

    @Override
    public void generateBullets(Rectangle battleBox) {
        super.generateBullets(battleBox);

        switch (rotation) {
            case 3:
                for (int i = 0; i < battleBox.height / sprite.getWidth(); i++) {
                    bullets.add(new Bullet(
                                    damVal,
                                    battleBox.x + battleBox.width + sprite.getHeight(),
                                    i * sprite.getWidth() + battleBox.y,
                                    speed,
                                    fadeSpeed,
                                    rotation,
                                    sprite
                            )
                    );
                }
                break;
            case 2:
                for (int i = 0; i < battleBox.width / sprite.getWidth(); i++) {
                    bullets.add(new Bullet(
                                    damVal,
                                    i * sprite.getWidth() + battleBox.x,
                                    battleBox.y - sprite.getHeight()*2,
                                    speed,
                                    fadeSpeed,
                                    rotation,
                                    sprite
                            )
                    );
                }
                break;
            case 1:
                for (int i = 0; i < battleBox.height / sprite.getWidth(); i++) {
                    bullets.add(new Bullet(
                                    damVal,
                                    battleBox.x - sprite.getHeight()*2,
                                    i * sprite.getWidth() + battleBox.y,
                                    speed,
                                    fadeSpeed,
                                    rotation,
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
                                    rotation,
                                    sprite
                            )
                    );
                }
                break;
        }
        int holeIndex = (int) (Math.random()*bullets.size());
        bullets.remove(holeIndex);
    }

    @Override
    public boolean isOver() {
        return switch (rotation) {
            case 3 -> bullets.get(0).x <= battleBox.x;
            case 2 -> bullets.get(0).y >= battleBox.y + battleBox.height - sprite.getHeight();
            case 1 -> bullets.get(0).x >= battleBox.x + battleBox.width - sprite.getHeight();
            default -> bullets.get(0).y <= battleBox.y;
        };
    }
}
