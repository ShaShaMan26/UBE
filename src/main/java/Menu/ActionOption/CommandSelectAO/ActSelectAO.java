package Menu.ActionOption.CommandSelectAO;

import GlobalState.BattleState.Assets.BattleBox;
import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import GlobalState.BattleState.EnemyTurnState.FancyDialogueTime;
import GlobalState.BattleState.PlayerTurnState.FancyTextBoxTime;
import GlobalState.*;
import Menu.ActionOption.ActionOption;
import Window.GameWindow;

public class ActSelectAO extends ActionOption {
    private String postText, reactText, fPostText, fReactText;
    private int mercyVal;

    public ActSelectAO(String title, int x, int y, int mercyVal, String postText, String reactText, String fPostText, String fReactText) {
        super(title, x, y);
        this.mercyVal = mercyVal;
        this.postText = postText;
        this.reactText = reactText;
        this.fPostText = fPostText;
        this.fReactText = fReactText;
    }

    @Override
    public GlobalState interact() {
        super.interact();

        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();

        for (ActionOption ao : gw.battle.actionOptions) {
            gw.removeComponent(ao);
        }

        gw.PLAYER.toggleVisible();

        if (gw.battle.mercyHP == mercyVal || fPostText == null || fPostText.equals("")) {
            if (mercyVal > 0 && gw.battle.mercyHP - mercyVal == 0) {
                gw.battle.mercyHP--;
            }
            return new FancyTextBoxTime(postText, 1,
                    new FancyDialogueTime(reactText, 1, new EnemyAttackState()));
        } else {
            return new FancyTextBoxTime(fPostText, 1,
                    new FancyDialogueTime(fReactText, 1, new EnemyAttackState()));
        }
    }
}
