package GlobalState;

import Menu.ActionOption.ActionOption;
import Menu.ActionOption.BattleSelectAO;
import Window.GameWindow;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SelectBattleState extends GlobalState {
    ArrayList<BattleSelectAO> battleSelectAOS;
    final int XOFFSET = 20, YOFFSET = 45;
    int index = 0;

    @Override
    public void run(GameWindow gw) {
        gw.AUDIOPLAYER.playClip(9);
        gw.PLAYER.damage(-20);

        battleSelectAOS = gw.battles;
        if (gw.battles.size() > 0) {
            for (BattleSelectAO battleSelectAO : battleSelectAOS) {
                gw.addComponent(battleSelectAO);
            }
        } else {
            gw.addComponent(new ActionOption("No battle data found.", XOFFSET, YOFFSET));
            gw.repaint();
        }

        gw.PLAYER.setVisible(true);
        if (gw.battles.size() > 0) {
            gw.battles.get(0).selected = true;
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
            gw.AUDIOPLAYER.close();
            battleSelectAOS.get(index).toggleSelected();
            return battleSelectAOS.get(index).interact();
        }

        gw.PLAYER.setPos(XOFFSET, (index*42) + YOFFSET-16);

        return null;
    }
}
