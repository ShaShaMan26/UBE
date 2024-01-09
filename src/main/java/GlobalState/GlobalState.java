package GlobalState;

import Window.*;

import java.awt.*;

public abstract class GlobalState {
    public abstract void run(GameWindow gw);
    public abstract GlobalState update(GameWindow gw);
    public void clear(GameWindow gw) {
        for (Component c : gw.getPanel().getComponents()) {
            if (!(c instanceof Player)) {
                gw.getPanel().remove(c);
            }
        }
    }
}
