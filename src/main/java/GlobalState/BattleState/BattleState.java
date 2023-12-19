package GlobalState.BattleState;

import Battle.TextBox;
import GlobalState.GlobalState;
import Menu.ActionOption;
import Menu.BattleAO.*;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class BattleState extends GlobalState {
    private ActionOption[] actionOptions = new ActionOption[]{
        new FightAO(),
        new ActAO(),
        new ItemAO(),
        new MercyAO()
    };
    private int index = 0;
    @Override
    public void run(GameWindow gw) {
        gw.addComponent(new TextBox());

        for (ActionOption ao : actionOptions) {
            gw.addComponent(ao);
        }

        actionOptions[index].toggleSelected();
        gw.PLAYER.toggleStatsDisplayed();

    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.getPressedKeys().contains(KeyEvent.VK_LEFT)) {
            actionOptions[index].toggleSelected();
            index--;
            if (index < 0) {
                index = 3;
            }
            actionOptions[index].toggleSelected();
            gw.invalidateKey(KeyEvent.VK_LEFT);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_RIGHT)) {
            actionOptions[index].toggleSelected();
            index++;
            if (index > 3) {
                index = 0;
            }
            actionOptions[index].toggleSelected();
            gw.invalidateKey(KeyEvent.VK_RIGHT);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            actionOptions[index].interact();
            gw.invalidateKey(KeyEvent.VK_Z);
        }

        gw.PLAYER.setPos((155*index)+42, 445);

        return null;
    }
}
