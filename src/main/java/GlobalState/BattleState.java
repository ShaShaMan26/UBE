package GlobalState;

import Battle.TextBox;
import Menu.ActionOption;
import Menu.BattleAO.*;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class BattleState extends GlobalState {
    @Override
    public GlobalState run(GameWindow gw) {
        gw.addComponent(new TextBox());

        ActionOption[] actionOptions = new ActionOption[]{
                new FightAO(),
                new ActAO(),
                new ItemAO(),
                new MercyAO()
        };

        for (ActionOption ao : actionOptions) {
            gw.addComponent(ao);
        }

        int index = 0;
        actionOptions[index].toggleSelected();
        while (true) {
            gw.repaint();

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
        }
    }
}
