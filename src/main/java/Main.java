import GlobalState.*;
import Window.GameWindow;

public class Main {
    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        GlobalState globalState = new InitializeGameState();
        globalState.run(gw);
        GlobalState tempGlobalState;

        long lastTime = System.nanoTime();
        double amountOfTicks = 30.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tempGlobalState = globalState.update(gw);

                if (tempGlobalState != null) {
                    globalState.clear(gw);
                    globalState = tempGlobalState;
                    globalState.run(gw);
                }

                gw.repaint();
                delta--;
            }
        }
    }
}
