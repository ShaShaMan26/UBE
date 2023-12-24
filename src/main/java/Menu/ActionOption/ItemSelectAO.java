package Menu.ActionOption;

import GlobalState.BattleState.FancyTextTime;
import GlobalState.*;
import Window.GameWindow;

import java.awt.*;

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

        gw.removeComponent(this);

        if (gw.PLAYER.getHealth() + healVal > 19) {
            return new FancyTextTime("Your HP was maxed out.", 1, new InitializeGameState());
        }
        return new FancyTextTime("You recovered " + healVal + " HP!", 1, new InitializeGameState());
    }
}
