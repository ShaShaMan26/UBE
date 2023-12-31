package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;
import Window.GameWindow;

import java.awt.image.BufferedImage;

public class WallOfBullets extends BulletPattern{
    public WallOfBullets(GameWindow gw, int speed, float fadeSpeed, int rotation, BufferedImage sprite) {
        int x, y, xMultiplier = 0, yMultiplier = 0, j;
        if (rotation == 3) {
            x = sprite.getHeight()+gw.battleBox.box.x+gw.battleBox.box.width;
            y = gw.battleBox.box.y;
            yMultiplier = sprite.getWidth()+1;
            j = gw.battleBox.box.height;
        } else if (rotation == 2) {
            x = gw.battleBox.box.x;
            y = gw.battleBox.box.y-sprite.getHeight();
            xMultiplier = sprite.getWidth()+1;
            j = gw.battleBox.box.width;
        } else if (rotation == 1) {
            x = gw.battleBox.box.x-sprite.getHeight();
            y = gw.battleBox.box.y;
            yMultiplier = sprite.getWidth()+1;
            j = gw.battleBox.box.height;
        } else {
            x = gw.battleBox.box.x;
            y = gw.battleBox.box.y+gw.battleBox.box.height+sprite.getHeight();
            xMultiplier = sprite.getWidth()+1;
            j = gw.battleBox.box.width;
        }

        for (int i = -1; i < j/(sprite.getWidth()+1)+1; i++) {
            bullets.add(new Bullet(
                    xMultiplier*i+x,
                    yMultiplier*i+y,
                    speed,
                    fadeSpeed,
                    200,
                    rotation,
                    sprite
            ));
        }

        bullets.remove((int) ((Math.random() * (gw.battleBox.box.height/(sprite.getWidth()+2) - 2)) + 2));
    }
}
