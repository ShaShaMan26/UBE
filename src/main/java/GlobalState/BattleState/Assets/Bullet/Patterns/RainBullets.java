package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;
import GlobalState.BattleState.Assets.Bullet.RelativeBullet;
import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import Window.GameWindow;

import java.awt.image.BufferedImage;

public class RainBullets extends BulletPattern {
    public int duration;
    public BufferedImage sprite;
    public RainBullets(int damVal, int duration, BufferedImage sprite) {
        this.damVal = damVal;
        this.duration = duration*30;
        this.sprite = sprite;
        Bullet b1 = new Bullet(0, 0, 0, 0, 1, duration, 0, sprite);
        bullets.add(b1);
    }

    @Override
    public void update(GameWindow gw, EnemyAttackState enemyAttackState) {
        if (ticks < 1) {

            int x = (int) (Math.random()*(gw.battleBox.targetBox.x+gw.battleBox.targetBox.width-sprite.getWidth()-gw.battleBox.targetBox.x) + gw.battleBox.targetBox.x);
            int y = (int) (Math.random()*(gw.battleBox.targetBox.y-((gw.battleBox.targetBox.y+sprite.getHeight())-(sprite.getHeight()*3)))+(gw.battleBox.targetBox.y-(sprite.getHeight()*3)));
            int speed = (int) (Math.random()*(gw.battleBox.targetBox.height/(sprite.getHeight()/1.25)-(gw.battleBox.targetBox.height/(sprite.getHeight()*1.5)))+(gw.battleBox.targetBox.height/(sprite.getHeight()*1.5)));
            float fadeSpeed = (int) (Math.random()*(8-1)+1) / 10F;

            Bullet bullet = new Bullet(damVal, x, y, speed, fadeSpeed, 200, 2, sprite);
            bullets.add(bullet);
            gw.addComponent(bullet);
            enemyAttackState.bullets.add(bullet);

            ticks = (int) (Math.random()*(10-3) + 3);

            if ((int) (Math.random()*6) > 4) {
                RelativeBullet rBullet =  new RelativeBullet(gw.PLAYER, damVal, gw.PLAYER.y-y, speed, fadeSpeed, 2, sprite);
                gw.addComponent(rBullet);
                enemyAttackState.bullets.add(rBullet);
            }
        }

        ticks--;
    }
}
