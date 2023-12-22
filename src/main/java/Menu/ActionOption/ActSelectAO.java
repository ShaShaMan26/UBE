package Menu.ActionOption;

import GlobalState.GlobalState;
import Window.GameWindow;

public class ActSelectAO extends ActionOption {
    private String postText, reactText;
    private int mercyVal;

    public ActSelectAO(String title, int x, int y, int mercyVal, String postText, String reactText) {
        super(title, x, y);
        this.mercyVal = mercyVal;
        this.postText = postText;
        this.reactText = reactText;

    }

    @Override
    public GlobalState interact() {
        super.interact();
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        if (gw.battle.mercyHP - mercyVal == 0) {
            gw.battle.mercyHP--;
        }

        return null;
    }
}
