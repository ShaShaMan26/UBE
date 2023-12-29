package Menu.ActionOption;

import java.awt.*;
import Window.GameWindow;

public class FightEnemySelectAO extends ActionOption {
    public FightEnemySelectAO(String title, int x, int y) {
        super(title, x, y);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(this.TITLE.length()*23 + this.X, this.Y-17, 102, 15);
        g.setColor(new Color(0, 254, 12));
        g.fillRect(this.TITLE.length()*23 + this.X, this.Y-17, (int) ((102F/gw.battle.totalHP) * gw.battle.HP), 15);
    }
}
