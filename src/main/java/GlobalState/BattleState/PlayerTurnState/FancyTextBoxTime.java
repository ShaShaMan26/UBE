package GlobalState.BattleState.PlayerTurnState;

import GlobalState.BattleState.FancyTextTime;
import GlobalState.GlobalState;
import Menu.FancyTextBox;

public class FancyTextBoxTime extends FancyTextTime {
    public FancyTextBoxTime(String title, int speed, GlobalState postState) {
        this.text = new FancyTextBox(title, speed);
        this.postState = postState;
    }
}
