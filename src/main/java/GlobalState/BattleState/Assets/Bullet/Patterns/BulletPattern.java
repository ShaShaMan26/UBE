package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.BattleBox;
import GlobalState.BattleState.Assets.Bullet.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class BulletPattern {
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public Rectangle battleBox;
    public int speed, damVal, rotation;
    public float fadeSpeed;
    public BufferedImage sprite;
    public BulletPattern(int speed, int damVal, int rotation, float fadeSpeed, BufferedImage sprite) {
        this.speed = speed;
        this.damVal = damVal;
        this.rotation = rotation;
        this.fadeSpeed = fadeSpeed;
        this.sprite = sprite;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    public void generateBullets(Rectangle battleBox) {
        this.battleBox = battleBox;
    }
    public abstract boolean isOver();
}
