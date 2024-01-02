package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;
import Window.GameWindow;

import java.awt.image.BufferedImage;

public class WallOfBullets extends BulletPattern{
    public WallOfBullets(GameWindow gw, int damVal, int speed, float fadeSpeed, int rotation, BufferedImage sprite) {
        this.damVal = damVal;
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
                    fadeSpeed,
                    j/60,
                    rotation,
                    sprite
            );
            bullets.add(bullet);
            gw.addComponent(bullet);
        }

        int holeIndex = (int) ((Math.random() * ((gw.battleBox.box.height/sprite.getWidth())-4 - 2)) + 2);
        gw.removeComponent(bullets.get(holeIndex));
        bullets.remove(holeIndex);
    }
}
