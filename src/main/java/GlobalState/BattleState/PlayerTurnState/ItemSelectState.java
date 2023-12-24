package GlobalState.BattleState.PlayerTurnState;

import GlobalState.BattleState.BattleState;
import GlobalState.*;
import Menu.ActionOption.ActionOption;
import Menu.ActionOption.ItemSelectAO;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class ItemSelectState extends BattleState {
    private final ItemSelectAO[] actionOptions = new ItemSelectAO[]{
            new ItemSelectAO("Heal 5HP", 101, 295, 5),
            new ItemSelectAO("Heal 10HP", 357, 295, 10),
            new ItemSelectAO("Heal 15HP", 101, 327, 15),
            new ItemSelectAO("Heal 20HP", 357, 327, 20)
    };
    private int index = 0;

    @Override
    public void run(GameWindow gw) {
        for (ItemSelectAO ao : actionOptions) {
            gw.addComponent(ao);
        }
        actionOptions[index].toggleSelected();

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
        if (gw.getPressedKeys().contains(KeyEvent.VK_UP)) {
            actionOptions[index].toggleSelected();
            index-=2;
            if (previousIndex == 0 && index < 0) {
                index = 2;
            } else if (previousIndex == 1 && index < 1) {
                index = 3;
            }
            actionOptions[index].toggleSelected();
            gw.invalidateKey(KeyEvent.VK_UP);
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_DOWN)) {
            actionOptions[index].toggleSelected();
            index+=2;
            if (previousIndex == 2 && index > 2) {
                index = 0;
            } else if (previousIndex == 3 && index > 3) {
                index = 1;
            }
            actionOptions[index].toggleSelected();
            gw.invalidateKey(KeyEvent.VK_DOWN);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            gw.invalidateKey(KeyEvent.VK_Z);

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

            for (ItemSelectAO ao : actionOptions) {
                gw.removeComponent(ao);
            }

            return new InitializeGameState();
        }

        int x = 65, y = 277;
        if (index > 1) {
            y += 32;
        }
        if (index % 2 != 0) {
            x += 256;
        }
        gw.PLAYER.setPos(x, y);

        return null;
    }
}
