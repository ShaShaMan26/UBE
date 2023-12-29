package GlobalState.BattleState.PlayerTurnState;

import GlobalState.BattleState.BattleState;
import GlobalState.BattleState.ReturnState;
import GlobalState.GlobalState;
import Menu.ActionOption.ActionOption;
import Menu.ActionOption.FightEnemySelectAO;
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
        if (isFightSelect) {
            actionOption = new FightEnemySelectAO(gw.battle.name, 101, 295);
        } else {
            actionOption = new ActionOption(gw.battle.name, 101, 295);
        }
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
