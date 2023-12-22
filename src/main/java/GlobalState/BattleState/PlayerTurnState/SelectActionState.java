package GlobalState.BattleState.PlayerTurnState;

import GlobalState.BattleState.BattleState;
import GlobalState.BattleState.InBattleState;
import GlobalState.GlobalState;
import Menu.ActionOption.ActionOption;
import Menu.FancyText;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class SelectActionState extends BattleState {
    private ActionOption[] actionOptions;
    private int index;
    private FancyText text = new FancyText("No text data found.", 1);

    public SelectActionState(InBattleState inBattleState) {
        super(inBattleState);
    }

    public void setText(String text) {
        this.text = new FancyText(text, 1);
    }

    @Override
    public void run(GameWindow gw) {
        this.actionOptions = inBattleState.actionOptions;
        this.index = inBattleState.index;
        setText(gw.battle.enterTxt);

        if (!actionOptions[index].selected) {
            actionOptions[index].toggleSelected();
        }

        gw.PLAYER.setPos((155*index)+42, 445);

        gw.addComponent(text);
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.getPressedKeys().contains(KeyEvent.VK_LEFT)) {
            actionOptions[index].toggleSelected();
            index--;
            if (index < 0) {
                index = 3;
            }
            actionOptions[index].toggleSelected();
            gw.invalidateKey(KeyEvent.VK_LEFT);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_RIGHT)) {
            actionOptions[index].toggleSelected();
            index++;
            if (index > 3) {
                index = 0;
            }
            actionOptions[index].toggleSelected();
            gw.invalidateKey(KeyEvent.VK_RIGHT);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            gw.invalidateKey(KeyEvent.VK_Z);
            gw.removeComponent(text);
            text.reset();
            return actionOptions[index].interact();
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_X)) {
            gw.invalidateKey(KeyEvent.VK_X);
            text.complete();
        }

        gw.PLAYER.setPos((155*index)+42, 445);

        inBattleState.index = this.index;

        return null;
    }
}
