package GlobalState.BattleState;

import GlobalState.*;
import GlobalState.BattleState.PlayerTurnState.SelectActionState;
import Menu.FancyText;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class FancyTextTime extends BattleState {
    private final FancyText text;
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
        text.update();

        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            gw.invalidateKey(KeyEvent.VK_Z);

            if (text.isFullyWritten()) {
                gw.removeComponent(text);
                gw.PLAYER.toggleVisible();
                return postState;
            }
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_X)) {
            gw.invalidateKey(KeyEvent.VK_X);
            text.complete();
        }

        return null;
    }
}
