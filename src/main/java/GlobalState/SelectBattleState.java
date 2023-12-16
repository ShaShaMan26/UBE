package GlobalState;

import Menu.BattleOT;
import Menu.OptionText;
import Window.GameWindow;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class SelectBattleState extends GlobalState {
    @Override
    public void run(GameWindow gw) {

        String[] battleNames = new File(System.getenv("APPDATA") + "\\UTB\\battles").list();
        final int XOFFSET = 20, YOFFSET = 45;

        ArrayList<BattleOT> battleOTs = new ArrayList<>();
        if (battleNames.length > 0) {
            for (int i = 0; i < battleNames.length; i++) {
                BattleOT battleOT = new BattleOT(battleNames[i], XOFFSET, YOFFSET+(i*42));
                gw.addComponent(battleOT);
                battleOTs.add(battleOT);
            }
        } else {
            gw.addComponent(new OptionText("No battle data found.", XOFFSET, YOFFSET));
            gw.repaint();
        }

        if (battleOTs.size() > 0) {
            int index = 0;
            battleOTs.get(index).toggleSelected();
            while (true) {
                gw.repaint();

                if (gw.getPressedKeys().contains(KeyEvent.VK_UP)) {
                    battleOTs.get(index).toggleSelected();
                    index--;
                    if (index < 0) {
                        index = battleOTs.size()-1;
                    }
                    battleOTs.get(index).toggleSelected();
                    gw.invalidateKey(KeyEvent.VK_UP);
                }

                if (gw.getPressedKeys().contains(KeyEvent.VK_DOWN)) {
                    battleOTs.get(index).toggleSelected();
                    index++;
                    if (index > battleOTs.size()-1) {
                        index = 0;
                    }
                    battleOTs.get(index).toggleSelected();
                    gw.invalidateKey(KeyEvent.VK_DOWN);
                }

                if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
                    battleOTs.get(index).interact();
                    gw.invalidateKey(KeyEvent.VK_Z);
                }
            }
        }
    }
}
