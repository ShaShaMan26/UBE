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

        new File(System.getenv("APPDATA")+"\\Undertale Battle Engine").mkdir();
        new File(System.getenv("APPDATA")+"\\Undertale Battle Engine\\battles").mkdir();

        int XOFFSET = 20, YOFFSET = 40;
        String[] battleNames = new File(System.getenv("APPDATA") + "\\Undertale Battle Engine\\battles").list();

        for (int i = 0; i < battleNames.length; i++) {
            String battleName = battleNames[i];
            if (battleName.length() > 38) {
                battleName = battleName.substring(0, 36);
                battleName += "...";
            }
            BattleSelectAO battleSelectAO = new BattleSelectAO(battleName, XOFFSET, YOFFSET+(i*42), new Battle(System.getenv("APPDATA") + "\\Undertale Battle Engine\\battles\\" + battleNames[i]));
            gw.battles.add(battleSelectAO);
        }
    }

    @Override
    public  GlobalState update(GameWindow gw) {
        gw.removeComponent(loadingText);
        return new SelectBattleState();
    }
}
