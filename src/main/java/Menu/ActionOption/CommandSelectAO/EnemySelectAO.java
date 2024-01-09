package Menu.ActionOption.CommandSelectAO;

import Menu.ActionOption.ActionOption;

import java.awt.*;
import Window.GameWindow;

public class EnemySelectAO extends ActionOption {
    private boolean isFightSel;
    public EnemySelectAO(String title, int x, int y, boolean isFightSel) {
        super(title, x, y);

        this.isFightSel = isFightSel;
    }

    @Override
    public void paint(Graphics g) {
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        if (TITLE != null) {
            if (gw.battle.mercyHP < 1) {
                g.setColor(new Color(239,242,62));
            } else {
                g.setColor(Color.WHITE);
            }
            g.setFont(font);
            g.drawString("* " + TITLE, X, Y);
        }

        if (isFightSel) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(this.TITLE.length()*23 + this.X, this.Y-17, 102, 15);
            g.setColor(new Color(0, 254, 12));
            g.fillRect(this.TITLE.length()*23 + this.X, this.Y-17, (int) ((102F/gw.battle.totalHP) * gw.battle.HP), 15);
        }
    }
}
