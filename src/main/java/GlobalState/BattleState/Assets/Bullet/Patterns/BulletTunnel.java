package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;
import Window.GameWindow;

import java.awt.image.BufferedImage;

public class BulletTunnel extends BulletPattern {
    public BulletTunnel(GameWindow gw, int speed, float fadeSpeed, int rotation, BufferedImage sprite) {
        int x, y, xMultiplier = 0, yMultiplier = 0, iterations;
        if (rotation == 3) {
            x = sprite.getHeight()+gw.battleBox.box.x+gw.battleBox.box.width;
            y = gw.battleBox.box.y;
            yMultiplier = sprite.getWidth()+1;
            iterations = gw.battleBox.box.height;
        } else if (rotation == 2) {
            x = gw.battleBox.box.x;
            y = gw.battleBox.box.y-sprite.getHeight();
            xMultiplier = sprite.getWidth()+1;
            iterations = gw.battleBox.box.width;
        } else if (rotation == 1) {
            x = gw.battleBox.box.x-sprite.getHeight();
            y = gw.battleBox.box.y;
            yMultiplier = sprite.getWidth()+1;
            iterations = gw.battleBox.box.height;
        } else {
            x = gw.battleBox.box.x;
            y = gw.battleBox.box.y+gw.battleBox.box.height+sprite.getHeight();
            xMultiplier = sprite.getWidth()+1;
            iterations = gw.battleBox.box.width;
        }

        for (int k = 0; k < iterations/(sprite.getWidth()+1)+1; k++) {
            for (int i = -1; i < iterations/(sprite.getWidth()+1)+1; i++) {
                bullets.add(new Bullet(
                        xMultiplier*i+(yMultiplier*k)+x,
                        yMultiplier*i+(xMultiplier*k)+y,
                        speed,
                        fadeSpeed,
                        200,
                        rotation,
                        sprite
                ));
            }

            bullets.remove(k);
            bullets.remove(k+1);
        }
    }
}
