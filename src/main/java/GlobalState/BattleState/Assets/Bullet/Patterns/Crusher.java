package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crusher extends BulletPattern {
    public Crusher(int speed, int damVal, int rotation, float fadeSpeed, BufferedImage sprite) {
        super(speed, damVal, rotation, fadeSpeed, sprite);
    }

    @Override
    public void generateBullets(Rectangle battleBox) {
        super.generateBullets(battleBox);

        int x, y;
        int bSpeed = (int) (Math.random()*(speed+2-speed)+speed);
        switch (rotation) {
            case 1:
            case 3:
                x = (int) (Math.random() * sprite.getHeight() * 2);
                y = (int) (Math.random() * ((battleBox.y + battleBox.height) / sprite.getWidth() - 1 - battleBox.y / sprite.getWidth() + 1) + battleBox.y / sprite.getWidth() + 1);
                bullets.add(new Bullet(damVal, battleBox.x + battleBox.width + x + sprite.getHeight(), y * sprite.getWidth(), bSpeed, fadeSpeed, 3, sprite));
                x = (int) (Math.random() * sprite.getHeight() * 2);
                bullets.add(new Bullet(damVal, battleBox.x - x - sprite.getHeight()*2, y * sprite.getWidth(), bSpeed, fadeSpeed, 1, sprite));
                break;
            default:
                x = (int) (Math.random() * ((battleBox.x + battleBox.width) / sprite.getWidth() - 1 - battleBox.x / sprite.getWidth() + 1) + battleBox.x / sprite.getWidth() + 1);
                y = (int) (Math.random() * sprite.getHeight() * 2);
                bullets.add(new Bullet(damVal, x * sprite.getWidth(), battleBox.y + battleBox.height + y, bSpeed, fadeSpeed, 0, sprite));
                y = (int) (Math.random() * sprite.getHeight() * 2);
                bullets.add(new Bullet(damVal, x * sprite.getWidth(), battleBox.y - y - sprite.getHeight(), bSpeed, fadeSpeed, 2, sprite));
                break;
        }
    }

    @Override
    public boolean isOver() {
        if (rotation == 0 || rotation == 2) {
            return bullets.get(0).y <= bullets.get(1).y+sprite.getHeight();
        } else {
            return bullets.get(0).x <= bullets.get(1).x+sprite.getHeight();
        }
    }
}
