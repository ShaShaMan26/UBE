package Menu.ActionOption.CommandSelectAO;

import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import GlobalState.BattleState.EnemyTurnState.FancyDialogueTime;
import GlobalState.BattleState.PlayerTurnState.FancyTextBoxTime;
import GlobalState.*;
import Menu.ActionOption.ActionOption;
import Window.GameWindow;

public class ItemSelectAO extends ActionOption {
    private final int healVal;
    public ItemSelectAO(String title, int x, int y, int healVal) {
        super(title, x, y);
        this.healVal = healVal;
    }

    @Override
    public GlobalState interact() {
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        gw.AUDIOPLAYER.playClip(2);
        gw.PLAYER.damage(-healVal);

        gw.removeComponent(this);

        String text = "You recovered " + healVal + " HP!";
        if (gw.PLAYER.getHealth() > 19) {
            text = "Your HP was maxed out.";
        }

        gw.PLAYER.toggleVisible();
        return new FancyTextBoxTime(text, 1,
                new FancyDialogueTime(gw.battle.getRandD(), 1, new EnemyAttackState()));
    }
}
