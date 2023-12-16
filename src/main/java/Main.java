import GlobalState.*;
import Window.GameWindow;

public class Main {
    public static void main(String[] args) {
        GlobalState[] globalStates = {new InitializeGameState(), new SelectBattleState()};

        GameWindow gw = new GameWindow();

        GlobalState globalState = globalStates[0];
        globalState.run(gw);
        globalState = globalStates[1];
        globalState.run(gw);
    }

}
