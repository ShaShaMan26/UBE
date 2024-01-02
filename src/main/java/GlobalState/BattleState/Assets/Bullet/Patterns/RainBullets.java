package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;
import GlobalState.BattleState.Assets.Bullet.RelativeBullet;
import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import Window.GameWindow;

import java.awt.image.BufferedImage;

public class RainBullets extends BulletPattern {
    BufferedImage sprite;
    public RainBullets(BufferedImage sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update(GameWindow gw, EnemyAttackState enemyAttackState) {
        if (ticks < 1) {
            int x = (int) (Math.random()*(gw.battleBox.targetBox.x+gw.battleBox.targetBox.width-sprite.getWidth()-gw.battleBox.targetBox.x) + gw.battleBox.targetBox.x);
            int y = (int) (Math.random()*(gw.battleBox.targetBox.y-((gw.battleBox.targetBox.y+sprite.getHeight())-(sprite.getHeight()*3)))+(gw.battleBox.targetBox.y-(sprite.getHeight()*3)));
            int speed = (int) (Math.random()*(gw.battleBox.targetBox.height/(sprite.getHeight()/1.25)-(gw.battleBox.targetBox.height/(sprite.getHeight()*1.5)))+(gw.battleBox.targetBox.height/(sprite.getHeight()*1.5)));
            float fadeSpeed = (int) (Math.random()*(8-1)+1) / 10F;

            Bullet bullet = new Bullet(x, y, speed, fadeSpeed, 100, 2, sprite);
            gw.addComponent(bullet);
            enemyAttackState.bullets.add(bullet);

            ticks = (int) (Math.random()*(10-3) + 3);

            if ((int) (Math.random()*6) > 4) {
                RelativeBullet rBullet =  new RelativeBullet(gw.PLAYER, gw.PLAYER.y-y, speed, fadeSpeed, 2, sprite);
                gw.addComponent(rBullet);
                enemyAttackState.bullets.add(rBullet);
            }
        }

        ticks--;
    }
}
