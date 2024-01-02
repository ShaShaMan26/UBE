package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;
import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import Window.GameWindow;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BulletTunnel extends BulletPattern {
    private int speed, rotation, holeIndex = 2, duration;
    private BufferedImage sprite;
    public BulletTunnel(int damVal, int speed, int duration, int rotation, BufferedImage sprite) {
        this.damVal = damVal;
        this.speed = speed;
        this.duration = duration*30;
        this.rotation = rotation;
        this.sprite = sprite;

        bullets.add(new Bullet(0, 0, 0, 0, 1, this.duration, 0, sprite));
    }

    @Override
    public void update(GameWindow gw, EnemyAttackState enemyAttackState) {
        super.update(gw, enemyAttackState);

        if (ticks < duration && ticks % (sprite.getHeight()/speed) == 0) {
            int x, y, xMultiplier = 0, yMultiplier = 0, j;
            if (rotation == 3) {
                x = sprite.getHeight()+gw.battleBox.targetBox.x+gw.battleBox.targetBox.width;
                y = gw.battleBox.targetBox.y;
                yMultiplier = sprite.getWidth()+1;
                j = gw.battleBox.targetBox.height;
            } else if (rotation == 2) {
                x = gw.battleBox.targetBox.x;
                y = gw.battleBox.targetBox.y-sprite.getHeight();
                xMultiplier = sprite.getWidth()+1;
                j = gw.battleBox.targetBox.width;
            } else if (rotation == 1) {
                x = gw.battleBox.targetBox.x-sprite.getHeight();
                y = gw.battleBox.targetBox.y;
                yMultiplier = sprite.getWidth()+1;
                j = gw.battleBox.targetBox.height;
            } else {
                x = gw.battleBox.targetBox.x;
                y = gw.battleBox.targetBox.y+gw.battleBox.targetBox.height+sprite.getHeight();
                xMultiplier = sprite.getWidth()+1;
                j = gw.battleBox.targetBox.width;
            }

            for (int i = -1; i < j/(sprite.getWidth()+1)+1; i++) {
                Bullet bullet = new Bullet(
                        damVal,
                        xMultiplier*i+x,
                        yMultiplier*i+y,
                        speed,
                        1,
                        j/60,
                        rotation,
                        sprite
                );
                bullets.add(bullet);
                gw.addComponent(bullet);
            }

            gw.removeComponent(bullets.get(holeIndex));
            bullets.remove(holeIndex);
            gw.removeComponent(bullets.get(holeIndex));
            bullets.remove(holeIndex);
            if (holeIndex < bullets.size()-1) {
                holeIndex++;
            }

            enemyAttackState.bullets.addAll(bullets);

            Bullet save = bullets.get(0);
            bullets.clear();
            bullets.add(save);
        }
    }
}
