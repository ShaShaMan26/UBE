package GlobalState.BattleState.Assets;

import GlobalState.BattleState.Assets.Bullet.Patterns.BulletPattern;

public class Attack {
    public BulletPattern bulletPattern;
    public int duration;
    public BattleBox battleBox;
    public Attack(BulletPattern bulletPattern, int duration, BattleBox battleBox) {
        this.bulletPattern = bulletPattern;
        this.duration = duration*30;
        this.battleBox = battleBox;
    }
}
