package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.RelativeBullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Launcher extends BulletPattern {
    public Launcher(int speed, int damVal, int rotation, float fadeSpeed, BufferedImage sprite) {
        super(speed, damVal, rotation, fadeSpeed, sprite);
    }

    @Override
    public void generateBullets(Rectangle battleBox) {
        super.generateBullets(battleBox);

    }

    @Override
    public boolean isOver() {
        return false;
    }
}
