package GlobalState;

import Menu.ActionOption.BattleSelectAO;
import Menu.ActionOption.ActionOption;
import Window.GameWindow;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

public class SelectBattleState extends GlobalState {
    ArrayList<BattleSelectAO> battleSelectAOS = new ArrayList<>();
    final int XOFFSET = 20, YOFFSET = 45;
    int index = 0;

    @Override
    public void run(GameWindow gw) {
        gw.PLAYER.damage(-20);

        String[] battleNames = new File(System.getenv("APPDATA") + "\\UTB\\battles").list();

        if (battleNames.length > 0) {
            for (int i = 0; i < battleNames.length; i++) {
                BattleSelectAO battleSelectAO = new BattleSelectAO(battleNames[i], XOFFSET, YOFFSET+(i*42));
                gw.addComponent(battleSelectAO);
                battleSelectAOS.add(battleSelectAO);
            }
        } else {
            gw.addComponent(new ActionOption("No battle data found.", XOFFSET, YOFFSET));
            gw.repaint();
        }

        if (battleSelectAOS.size() > 0) {
            battleSelectAOS.get(index).toggleSelected();
        }
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.getPressedKeys().contains(KeyEvent.VK_UP)) {
            battleSelectAOS.get(index).toggleSelected();
            index--;
            if (index < 0) {
                index = battleSelectAOS.size()-1;
            }
            battleSelectAOS.get(index).toggleSelected();
            gw.invalidateKey(KeyEvent.VK_UP);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_DOWN)) {
            battleSelectAOS.get(index).toggleSelected();
            index++;
            if (index > battleSelectAOS.size()-1) {
                index = 0;
            }
            battleSelectAOS.get(index).toggleSelected();
            gw.invalidateKey(KeyEvent.VK_DOWN);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            gw.invalidateKey(KeyEvent.VK_Z);
            return battleSelectAOS.get(index).interact();
        }

        gw.PLAYER.setPos(XOFFSET, (index*42) + YOFFSET-16);

        return null;
    }
}
