package GlobalState.BattleState.EnemyTurnState;

import GlobalState.BattleState.BattleState;
import GlobalState.BattleState.ReturnState;
import GlobalState.GlobalState;
import Window.GameWindow;

public class EnemyAttackState extends BattleState {
    @Override
    public void run(GameWindow gw) {
        gw.PLAYER.damage(2);
        gw.AUDIOPLAYER.playClip(7);
        gw.battleBox.transitionTo(33, 251, 574, 139);
        gw.PLAYER.toggleVisible();
    }

    @Override
    public GlobalState update(GameWindow gw) {
        gw.battleBox.progressTransition(18);
        if (!gw.battleBox.isTransitioning) {
            gw.PLAYER.toggleVisible();
            return new ReturnState();
        }
        return null;
    }
}
