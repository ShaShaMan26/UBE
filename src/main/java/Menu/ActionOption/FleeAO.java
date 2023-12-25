package Menu.ActionOption;

import GlobalState.*;
import GlobalState.BattleState.FancyTextTime;
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
        return new FancyTextTime("Escaped...", 1, new SelectBattleState());
    }
}