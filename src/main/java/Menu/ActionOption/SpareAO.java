package Menu.ActionOption;

import Window.GameWindow;
import GlobalState.*;
import GlobalState.BattleState.FancyTextTime;

import java.awt.*;

public class SpareAO extends ActionOption {
    public boolean active = false;

    public SpareAO(String title, int x, int y) {
        super(title, x, y);
    }

    @Override
    public GlobalState interact() {
        super.interact();

        if (active) {
            GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
            gw.removeComponent(this);

            return new SelectBattleState();
        }

        return null;
    }

    @Override
    public void paint(Graphics g) {
        if (active) {
            g.setColor(new Color(239,242,62));
        } else {
            g.setColor(Color.WHITE);
        }

        if (TITLE != null) {
            g.setFont(font);
            g.drawString("* " + TITLE, X, Y);
        } else {
            if (selected) {
                g.drawImage(SELECTEDICON, X, Y, null);
            } else {
                g.drawImage(ICON, X, Y, null);
            }
        }
    }
}
