package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.RelativeBullet;
import Window.Player;

import java.awt.image.BufferedImage;

public class CirclePlayer extends BulletPattern{
    public CirclePlayer(Player player, int spawnDistance, int speed, float fadeSpeed, BufferedImage sprite) {
        for (int i = 0; i < 4; i++) {
            bullets.add(new RelativeBullet(player, spawnDistance, speed, fadeSpeed, i, sprite));
        }
    }
}
