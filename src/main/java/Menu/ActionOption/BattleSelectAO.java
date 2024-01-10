package Menu.ActionOption;

import GlobalState.BattleState.InBattleState;
import GlobalState.GlobalState;
import Window.*;

import java.awt.*;

public class BattleSelectAO extends ActionOption {
    private Battle battle;

    public BattleSelectAO(String title, int x, int y, Battle battle) {
        super(title, x, y);
        this.battle = battle;
    }

    @Override
    public GlobalState interact() {
        super.interact();
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        gw.PLAYER.atk = battle.playerAtk;
        battle.reset();
        gw.battle = battle;
        gw.inBattleState = new InBattleState();
        return gw.inBattleState;
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
