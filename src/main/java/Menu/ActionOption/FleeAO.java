package Menu.ActionOption;

import GlobalState.*;

public class FleeAO extends ActionOption {
    public FleeAO(String title, int x, int y) {
        super(title, x, y);
    }

    @Override
    public GlobalState interact() {
        super.interact();

        // flee animation plz <3
        return new SelectBattleState();
    }
}
