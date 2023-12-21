package Menu.ActionOption;

import GlobalState.GlobalState;

public class SpareAO extends ActionOption {
    public SpareAO(String title, int x, int y) {
        super(title, x, y);
    }

    @Override
    public GlobalState interact() {
        super.interact();

        // if spareable, spare, else null
        return null;
    }
}
