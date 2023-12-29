package GlobalState.BattleState;

import GlobalState.GlobalState;
import Menu.Assets.FancyText;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public abstract class FancyTextTime extends BattleState {
    public FancyText text;
    public GlobalState postState;

    @Override
    public void run(GameWindow gw) {
        gw.addComponent(text);
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            gw.invalidateKey(KeyEvent.VK_Z);

            if (text.isFullyWritten()) {
                gw.removeComponent(text);
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
