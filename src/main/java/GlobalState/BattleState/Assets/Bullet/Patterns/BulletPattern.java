package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;

import java.util.ArrayList;

public abstract class BulletPattern {
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
