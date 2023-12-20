package GlobalState.BattleState;

import GlobalState.GlobalState;

public abstract class BattleState extends GlobalState {
    public InBattleState inBattleState = null;
    public BattleState(){}
    public BattleState(InBattleState inBattleState) {
        this.inBattleState = inBattleState;
    }
}
