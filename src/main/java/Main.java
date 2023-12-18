import GlobalState.*;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        GlobalState globalState = new InitializeGameState();
        while (true) {
            GlobalState tempGlobalState = globalState.run(gw);
            globalState.clear(gw);
            globalState = tempGlobalState;
        }
    }

}
