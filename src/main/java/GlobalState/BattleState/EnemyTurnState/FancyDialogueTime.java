package GlobalState.BattleState.EnemyTurnState;

import GlobalState.BattleState.FancyTextTime;
import GlobalState.*;
import Menu.FancyDialouge;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class FancyDialogueTime extends FancyTextTime {
    public FancyDialogueTime(String title, int speed, GlobalState postState) {
        super(title, speed, postState);

        this.text = new FancyDialouge(title, speed);
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            gw.invalidateKey(KeyEvent.VK_Z);

            if (text.isFullyWritten()) {
                gw.removeComponent(text);
                return new InitializeGameState();
            }
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_X)) {
            gw.invalidateKey(KeyEvent.VK_X);
            text.complete();
        }

        return null;
    }
}
