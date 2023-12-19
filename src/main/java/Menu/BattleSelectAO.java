package Menu;

import java.awt.*;

public class BattleSelectAO extends ActionOption {

    public BattleSelectAO(String title, int x, int y) {
        super(title, x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(font);
        if (selected) {
            g.setColor(new Color(239,242,62));
        } else {
            g.setColor(Color.WHITE);
            g.drawString("*", X, Y);
        }
        g.drawString(TITLE, X+30, Y);
    }
}
