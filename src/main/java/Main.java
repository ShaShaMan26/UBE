import GlobalState.*;
import Window.GameWindow;

public class Main {
    public static void main(String[] args) {
        GlobalState[] globalStates = {new InitializeGameState(), new SelectBattleState(), new BattleState()};

        GameWindow gw = new GameWindow();

        GlobalState globalState = globalStates[2];
        globalState.run(gw);
    }

}
