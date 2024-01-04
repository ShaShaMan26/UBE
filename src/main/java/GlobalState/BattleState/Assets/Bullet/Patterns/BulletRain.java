package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BulletRain extends BulletPattern {
    public BulletRain(int speed, int damVal, int rotation, float fadeSpeed, BufferedImage sprite) {
        super(speed, damVal, rotation, fadeSpeed, sprite);
    }

    @Override
    public void generateBullets(Rectangle battleBox) {
        super.generateBullets(battleBox);

        int x, y;
        int bSpeed = (int) (Math.random()*(speed+2-speed)+speed);
        switch (rotation) {
            case 3 -> {
                x = (int) (Math.random() * sprite.getHeight() * 2);
                y = (int) (Math.random() * ((battleBox.y + battleBox.height) / sprite.getWidth() - 1 - battleBox.y / sprite.getWidth() + 1) + battleBox.y / sprite.getWidth() + 2);
                bullets.add(new Bullet(damVal, battleBox.x + battleBox.width + x + sprite.getHeight(), y * sprite.getWidth(), bSpeed, fadeSpeed, rotation, sprite));
            }
            case 2 -> {
                x = (int) (Math.random() * ((battleBox.x + battleBox.width) / sprite.getWidth() - 1 - battleBox.x / sprite.getWidth() + 1) + battleBox.x / sprite.getWidth() + 2);
                y = (int) (Math.random() * sprite.getHeight() * 2);
                bullets.add(new Bullet(damVal, x * sprite.getWidth(), battleBox.y - y - sprite.getHeight(), bSpeed, fadeSpeed, rotation, sprite));
            }
            case 1 -> {
                x = (int) (Math.random() * sprite.getHeight() * 2);
                y = (int) (Math.random() * ((battleBox.y + battleBox.height) / sprite.getWidth() - 1 - battleBox.y / sprite.getWidth() + 1) + battleBox.y / sprite.getWidth() + 2);
                bullets.add(new Bullet(damVal, battleBox.x - x - sprite.getHeight(), y * sprite.getWidth(), bSpeed, fadeSpeed, rotation, sprite));
            }
            default -> {
                x = (int) (Math.random() * ((battleBox.x + battleBox.width) / sprite.getWidth() - 1 - battleBox.x / sprite.getWidth() + 1) + battleBox.x / sprite.getWidth() + 2);
                y = (int) (Math.random() * sprite.getHeight() * 2);
                bullets.add(new Bullet(damVal, x * sprite.getWidth(), battleBox.y + battleBox.height + y, bSpeed, fadeSpeed, rotation, sprite));
            }
        }
    }

    @Override
    public boolean isOver() {
        switch (rotation) {
            case 3 -> {
                return bullets.get(0).x <= battleBox.x;
            }
            case 2 -> {
                return bullets.get(0).y >= battleBox.y+battleBox.height-sprite.getHeight();
            }
            case 1 -> {
                return bullets.get(0).x >= battleBox.x+battleBox.width-sprite.getHeight();
            }
            default -> {
                return bullets.get(0).y <= battleBox.y;
            }
        }
    }
}
