package GlobalState.BattleState.PlayerTurnState;

import GlobalState.BattleState.BattleState;
import GlobalState.BattleState.InBattleState;
import GlobalState.GlobalState;
import Menu.ActionOption;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class SelectActionState extends BattleState {
    private ActionOption[] actionOptions;
    private int index;

    public SelectActionState(InBattleState inBattleState) {
        super(inBattleState);
    }

    @Override
    public void run(GameWindow gw) {
        this.actionOptions = inBattleState.actionOptions;
        this.index = inBattleState.index;

        if (!actionOptions[index].selected) {
            actionOptions[index].toggleSelected();
        }

        gw.PLAYER.setPos((155*index)+42, 445);
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
            return actionOptions[index].interact();
        }

        gw.PLAYER.setPos((155*index)+42, 445);

        inBattleState.index = this.index;

        return null;
    }
}
