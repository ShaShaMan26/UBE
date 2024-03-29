package GlobalState.BattleState.PlayerTurnState;

import GlobalState.BattleState.BattleState;
import GlobalState.BattleState.ReturnState;
import GlobalState.GlobalState;
import Menu.ActionOption.ActionOption;
import Menu.ActionOption.CommandSelectAO.EnemySelectAO;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class EnemySelectState extends BattleState {
    private final GlobalState postState;
    private ActionOption actionOption;
    private boolean isFightSelect;
    public EnemySelectState(GlobalState postState, boolean isFightSelect) {
        this.postState = postState;
        this.isFightSelect = isFightSelect;
    }

    @Override
    public void run(GameWindow gw) {
        actionOption = new EnemySelectAO(gw.battle.name, 101, 295, isFightSelect);
        gw.addComponent(actionOption);
        gw.PLAYER.setPos(65, 277);
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
            actionOption.interact();
            gw.invalidateKey(KeyEvent.VK_Z);
            gw.removeComponent(actionOption);
            return postState;
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_X)) {
            gw.invalidateKey(KeyEvent.VK_X);
            gw.removeComponent(actionOption);
            return new ReturnState();
        }

        return null;
    }
}
