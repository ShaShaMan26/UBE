package GlobalState.BattleState.PlayerTurnState;

import GlobalState.BattleState.BattleState;
import GlobalState.*;
import GlobalState.BattleState.ReturnState;
import Menu.ActionOption.ActionOption;
import Menu.ActionOption.CommandSelectAO.FleeAO;
import Menu.ActionOption.CommandSelectAO.SpareAO;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class MercySelectState extends BattleState {
    private final ActionOption[] actionOptions = new ActionOption[]{
        new SpareAO("Spare", 101, 295),
        new FleeAO("Flee", 357, 295)
};
    private int index = 0;

    @Override
    public void run(GameWindow gw) {
        for (ActionOption ao : actionOptions) {
            gw.addComponent(ao);
        }
        actionOptions[index].selected = true;

        if (gw.battle.mercyHP == 0) {
            ((SpareAO) actionOptions[0]).active = true;
        }

        gw.PLAYER.setPos(65, 277);
    }

    @Override
    public GlobalState update(GameWindow gw) {
        int previousIndex = index;

        if (gw.getPressedKeys().contains(KeyEvent.VK_LEFT)) {
            actionOptions[index].toggleSelected();
            index--;
            if (previousIndex == 0 && index < 0) {
                index = 1;
            } else if (previousIndex == 2 && index < 2) {
                index = 3;
            }
            actionOptions[index].toggleSelected();
            gw.invalidateKey(KeyEvent.VK_LEFT);
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_RIGHT)) {
            actionOptions[index].toggleSelected();
            index++;
            if (previousIndex == 1 && index > 1) {
                index = 0;
            } else if (previousIndex == 3 && index > 3) {
                index = 2;
            }
            actionOptions[index].toggleSelected();
            gw.invalidateKey(KeyEvent.VK_RIGHT);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            gw.invalidateKey(KeyEvent.VK_Z);
            gw.inBattleState.toggleCommandSelect();

            ActionOption selectedAO = actionOptions[index];
            for (ActionOption ao : actionOptions) {
                if (ao != selectedAO) {
                    gw.removeComponent(ao);
                }
            }

            return selectedAO.interact();
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_X)) {
            gw.invalidateKey(KeyEvent.VK_X);

            for (ActionOption ao : actionOptions) {
                gw.removeComponent(ao);
            }

            return new ReturnState();
        }

        gw.PLAYER.setPos(index*256+65, 277);

        return null;
    }
}
