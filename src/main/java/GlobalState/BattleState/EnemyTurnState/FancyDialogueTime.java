package GlobalState.BattleState.EnemyTurnState;

import GlobalState.BattleState.FancyTextTime;
import GlobalState.GlobalState;
import Menu.FancyDialouge;

public class FancyDialogueTime extends FancyTextTime {
    public FancyDialogueTime(String title, int speed, GlobalState postState) {
        this.text = new FancyDialouge(title, speed);
        this.postState = postState;
    }
}
