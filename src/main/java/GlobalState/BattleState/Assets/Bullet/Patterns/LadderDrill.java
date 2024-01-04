package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LadderDrill extends BulletPattern {
    public LadderDrill(int speed, int damVal, int rotation, float fadeSpeed, BufferedImage sprite) {
        super(speed, damVal, rotation, fadeSpeed, sprite);
    }

    @Override
    public void generateBullets(Rectangle battleBox) {
        super.generateBullets(battleBox);

        for (int i = 0; i < battleBox.width/2/sprite.getWidth(); i++) {
            bullets.add(new Bullet(
                            damVal,
                            i*sprite.getWidth()+battleBox.x,
                            battleBox.y-sprite.getHeight()*2,
                            speed,
                            fadeSpeed,
                            2,
                            sprite
                    )
            );
        }
        for (int i = battleBox.width/2/sprite.getWidth(); i < battleBox.width/sprite.getWidth(); i++) {
            bullets.add(new Bullet(
                            damVal,
                            i*sprite.getWidth()+battleBox.x,
                            battleBox.y + battleBox.height + sprite.getHeight(),
                            speed,
                            fadeSpeed,
                            0,
                            sprite
                    )
            );
        }
    }

    @Override
    public boolean isOver() {
        return bullets.get(0).y >= battleBox.y+battleBox.height-sprite.getHeight();
    }
}
