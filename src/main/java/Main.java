import GlobalState.*;
import Window.GameWindow;

public class Main {
    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        GlobalState globalState = new InitializeGameState();
        while (true) {
            GlobalState tempGlobalState = globalState.run(gw);
            if (tempGlobalState != null) {
                globalState.clear(gw);
            } else {
                break;
            }
            globalState = tempGlobalState;
        }
    }

}
