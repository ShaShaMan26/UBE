package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ClosingWalls extends BulletPattern {
    public ClosingWalls(int speed, int damVal, int rotation, float fadeSpeed, BufferedImage sprite) {
        super(speed, damVal, rotation, fadeSpeed, sprite);
    }

    @Override
    public void generateBullets(Rectangle battleBox) {
        super.generateBullets(battleBox);

        int offset = (int) (Math.random()*(sprite.getHeight()*4))-sprite.getHeight()*2;
        switch (rotation) {
            case 1:
            case 3:
                for (int i = 0; i < battleBox.height / sprite.getWidth(); i++) {
                    bullets.add(new Bullet(
                                    damVal,
                                    battleBox.x + battleBox.width + sprite.getHeight()+offset,
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
                                    battleBox.x - sprite.getHeight()*2+offset,
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
                                    battleBox.y + battleBox.height + sprite.getHeight()+offset,
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
                                    battleBox.y - sprite.getHeight()*2+offset,
                                    speed,
                                    fadeSpeed,
                                    2,
                                    sprite
                            )
                    );
                }
                break;
        }
    }

    @Override
    public boolean isOver() {
        if (rotation == 0 || rotation == 2) {
            return bullets.get(0).y <= bullets.get(bullets.size()/2+1).y+sprite.getHeight()*1.25;
        } else {
            return bullets.get(0).x <= bullets.get(bullets.size()/2+1).x+sprite.getHeight()*1.25;
        }
    }
}
