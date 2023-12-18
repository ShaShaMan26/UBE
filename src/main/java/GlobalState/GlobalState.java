package GlobalState;

import Window.GameWindow;

import java.awt.*;

public abstract class GlobalState {
    public abstract GlobalState run(GameWindow gw);
    public void clear(GameWindow gw) {
        for (Component c : gw.getPanel().getComponents()) {
            gw.getPanel().remove(c);
        }
    }
}
