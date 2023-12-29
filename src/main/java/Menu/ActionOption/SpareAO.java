package Menu.ActionOption;

import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import GlobalState.BattleState.EnemyTurnState.FancyDialogueTime;
import Window.GameWindow;
import GlobalState.*;

import java.awt.*;

public class SpareAO extends ActionOption {
    public boolean active = false;

    public SpareAO(String title, int x, int y) {
        super(title, x, y);
    }

    @Override
    public GlobalState interact() {
        super.interact();

        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        gw.removeComponent(this);

        if (active) {
            return new SelectBattleState();
        }

        gw.PLAYER.toggleVisible();
        return new FancyDialogueTime(gw.battle.getRandD(), 1,
                new EnemyAttackState());
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
