package Menu.ActionOption.BattleAO;

import GlobalState.GlobalState;
import Menu.ActionOption.ActionOption;
import Window.GameWindow;

public class ItemSelectAO extends ActionOption {
    private final int healVal;
    public ItemSelectAO(String title, int x, int y, int healVal) {
        super(title, x, y);
        this.healVal = healVal;
    }

    @Override
    public GlobalState interact() {
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        gw.AUDIOPLAYER.playClip(2);
        gw.PLAYER.damage(-healVal);

        return null;
    }
}
