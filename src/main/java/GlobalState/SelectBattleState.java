package GlobalState;

import Menu.ActionOption.ActionOption;
import Menu.ActionOption.BattleSelectAO;
import Menu.Assets.Arrow;
import Window.GameWindow;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SelectBattleState extends GlobalState {
    private ArrayList<BattleSelectAO> battleSelectAOS;
    private int XOFFSET = 20, YOFFSET = 40;
    private int index = 0, pageNum = 0;
    private final Arrow arrow = new Arrow();


    @Override
    public void run(GameWindow gw) {
        gw.AUDIOPLAYER.playClip(9);
        gw.PLAYER.damage(-20);

        battleSelectAOS = gw.battles;
        if (gw.battles.size() > 0) {
            for (BattleSelectAO battleSelectAO : battleSelectAOS) {
                gw.addComponent(battleSelectAO);
            }
            gw.battles.get(0).selected = true;
        } else {
            gw.addComponent(new ActionOption("No battle data found.", XOFFSET, YOFFSET));
            gw.repaint();
        }

        gw.PLAYER.setVisible(true);

        gw.addComponent(arrow);
        if (battleSelectAOS.size() < 10) {
            arrow.visible = false;
        }
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.getPressedKeys().contains(KeyEvent.VK_UP)) {
            battleSelectAOS.get(index).toggleSelected();
            index--;
            if (index < 0) {
                index = battleSelectAOS.size()-1;
                pageNum = battleSelectAOS.size() / 11;
                setPage(pageNum);
            }

            battleSelectAOS.get(index).toggleSelected();
            int hotfixIntCuzLazy = 0;
            if (index>11) {
                hotfixIntCuzLazy = 1;
            }
            if (index != 0 && (index-hotfixIntCuzLazy) % 10F == 0) {
                pageNum--;
                setPage(pageNum);
            }

            gw.invalidateKey(KeyEvent.VK_UP);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_DOWN)) {
            battleSelectAOS.get(index).toggleSelected();
            index++;
            if (index > battleSelectAOS.size()-1) {
                index = 0;
                pageNum = -1;
                setPage(pageNum);
            }

            battleSelectAOS.get(index).toggleSelected();
            if (index % 11 == 0) {
                pageNum++;
                setPage(pageNum);
            }

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

    public void setPage(int pageNum) {
        YOFFSET = 40 - pageNum*42*11;
        for (int i = 0; i < battleSelectAOS.size(); i++) {
            battleSelectAOS.get(i).Y = YOFFSET+i*42;
        }

        arrow.visible = pageNum < (battleSelectAOS.size()+1)/12;
    }
}
