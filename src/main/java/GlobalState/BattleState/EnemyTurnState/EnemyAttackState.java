package GlobalState.BattleState.EnemyTurnState;

import GlobalState.BattleState.BattleState;
import GlobalState.BattleState.ReturnState;
import GlobalState.GlobalState;
import Window.GameWindow;

public class EnemyAttackState extends BattleState {
    @Override
    public void run(GameWindow gw) {
        gw.PLAYER.toggleVisible();
    }

    @Override
    public GlobalState update(GameWindow gw) {
        return new ReturnState();
    }
}
