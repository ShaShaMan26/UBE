package GlobalState.BattleState;

import GlobalState.*;
import GlobalState.BattleState.EnemyTurnState.FancyDialogueTime;
import Menu.FancyText;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class FancyTextTime extends BattleState {
    public FancyText text;
    private final GlobalState postState;
    public FancyTextTime(String title, int speed, GlobalState postState) {
        this.text = new FancyText(title, speed);
        this.postState = postState;
    }

    @Override
    public void run(GameWindow gw) {
        gw.addComponent(text);
        gw.PLAYER.toggleVisible();
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            gw.invalidateKey(KeyEvent.VK_Z);

            if (text.isFullyWritten()) {
                gw.removeComponent(text);
                return new FancyDialogueTime("umm... you do know can't actually kill ghosts, right?", text.SPEED, postState);
            }
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_X)) {
            gw.invalidateKey(KeyEvent.VK_X);
            text.complete();
        }

        return null;
    }
}
