package GlobalState;

import Menu.ActionOption.BattleSelectAO;
import Menu.Assets.LoadingText;
import Window.Battle;
import Window.GameWindow;

import java.io.File;

public class InitializeGameState extends GlobalState {
    private LoadingText loadingText = new LoadingText();
    @Override
    public void run(GameWindow gw) {
        gw.addComponent(loadingText);
        gw.repaint();

        new File(System.getenv("APPDATA")+"\\UTB").mkdir();
        new File(System.getenv("APPDATA")+"\\UTB\\battles").mkdir();

        int XOFFSET = 20, YOFFSET = 45;
        String[] battleNames = new File(System.getenv("APPDATA") + "\\UTB\\battles").list();

        for (int i = 0; i < battleNames.length; i++) {
            BattleSelectAO battleSelectAO = new BattleSelectAO(battleNames[i], XOFFSET, YOFFSET+(i*42), new Battle(System.getenv("APPDATA") + "\\UTB\\battles\\" + battleNames[i]));
            gw.battles.add(battleSelectAO);
        }
    }

    @Override
    public  GlobalState update(GameWindow gw) {
        gw.removeComponent(loadingText);
        return new SelectBattleState();
    }
}
