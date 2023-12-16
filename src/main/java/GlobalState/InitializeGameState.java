package GlobalState;

import Window.GameWindow;

import java.io.File;

public class InitializeGameState extends GlobalState {

    @Override
    public void run(GameWindow gw) {
        new File(System.getenv("APPDATA")+"\\UTB").mkdir();
        new File(System.getenv("APPDATA")+"\\UTB\\battles").mkdir();
    }
}
