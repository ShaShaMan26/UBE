package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;
import GlobalState.BattleState.Assets.Bullet.RelativeBullet;
import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import Window.GameWindow;

import java.awt.image.BufferedImage;

public class CirclePlayer extends BulletPattern{
    private int spawnDistance, speed;
    private float fadeSpeed;
    private BufferedImage sprite;
    public CirclePlayer(int damVal, int spawnDistance, int speed, float fadeSpeed, BufferedImage sprite) {
        this.damVal = damVal;
        this.spawnDistance = spawnDistance;
        this.speed = speed;
        this.fadeSpeed = fadeSpeed;
        this.sprite = sprite;

        bullets.add(new Bullet(0, 0, 0, 0, 1F, (spawnDistance/sprite.getHeight()), 0, sprite));
    }

    @Override
    public void update(GameWindow gw, EnemyAttackState enemyAttackState) {
        super.update(gw, enemyAttackState);
        if (ticks == 1) {
            for (int i = 0; i < 4; i++) {
                RelativeBullet bullet = new RelativeBullet(gw.PLAYER, damVal, spawnDistance, speed, fadeSpeed, i, sprite);
                gw.addComponent(bullet);
                enemyAttackState.bullets.add(bullet);
            }
        }
    }
}
