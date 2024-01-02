package GlobalState.BattleState.Assets.Bullet.Patterns;

import GlobalState.BattleState.Assets.Bullet.Bullet;
import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import Window.GameWindow;

import java.util.ArrayList;

public abstract class BulletPattern {
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    public int ticks = 0, damVal;

    public void update(GameWindow gw, EnemyAttackState enemyAttackState) {
        ticks++;
    }
}
