package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.RelativeBullet;
import Window.Player;

import java.awt.image.BufferedImage;

public class CirclePlayer extends BulletPattern{
    public CirclePlayer(Player player, int damVal, int spawnDistance, int speed, float fadeSpeed, BufferedImage sprite) {
        this.damVal = damVal;
        for (int i = 0; i < 4; i++) {
            bullets.add(new RelativeBullet(player, damVal, spawnDistance, speed, fadeSpeed, i, sprite));
        }
    }
}
