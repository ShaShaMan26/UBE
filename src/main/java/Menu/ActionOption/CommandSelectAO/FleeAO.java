package Menu.ActionOption.CommandSelectAO;

import GlobalState.*;
import GlobalState.BattleState.PlayerTurnState.FancyTextBoxTime;
import Menu.ActionOption.ActionOption;
import Window.GameWindow;

public class FleeAO extends ActionOption {
    public FleeAO(String title, int x, int y) {
        super(title, x, y);
    }

    @Override
    public GlobalState interact() {
        super.interact();

        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        gw.removeComponent(this);

        // flee animation plz <3
        gw.PLAYER.setVisible(false);
        gw.AUDIOPLAYER.stopBGM();
        return new FancyTextBoxTime("Escaped...", 1, new SelectBattleState());
    }
}
