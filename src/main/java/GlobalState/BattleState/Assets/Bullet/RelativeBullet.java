package GlobalState.BattleState.Assets.Bullet;

import Window.Player;

import java.awt.image.BufferedImage;

public class RelativeBullet extends Bullet {
    public RelativeBullet(Player player, int damVal, int spawnDistance, int speed, float fadeSpeed, int rotation, BufferedImage sprite) {
        this.damVal = damVal;
        this.speed = speed;
        this.fadeSpeed = fadeSpeed;
        this.duration = spawnDistance/speed+sprite.getHeight();
        this.sprite = sprite;
        rotateSprite(rotation*90);

        spawnDistance += 8;

        this.x = (player.x+8) - (this.sprite.getWidth() / 2);
        this.y = (player.y+8) - (this.sprite.getHeight() / 2);
        if (rotation == 3) {
            this.x += spawnDistance;
        } else if (rotation == 2) {
            this.y -= spawnDistance;
        } else if (rotation == 1) {
            this.x -= spawnDistance;
        } else {
            this.y += spawnDistance;
        }
    }
}
