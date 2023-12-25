package GlobalState.BattleState;

import Menu.ActionOption.*;
import Menu.ActionOption.BattleAO.ActAO;
import Menu.ActionOption.BattleAO.FightAO;
import Menu.ActionOption.BattleAO.ItemAO;
import Menu.ActionOption.BattleAO.MercyAO;
import Menu.BattleBG;
import GlobalState.BattleState.PlayerTurnState.SelectActionState;
import GlobalState.*;
import Window.GameWindow;

public class InBattleState extends GlobalState {
    public ActionOption[] actionOptions = new ActionOption[]{
        new FightAO(),
        new ActAO(),
        new ItemAO(),
        new MercyAO()
    };
    public int index = 0;
    private BattleState battleState = new SelectActionState(this);
    public GlobalState prevState = null;

    @Override
    public void run(GameWindow gw) {
        gw.addComponent(new BattleBG(2, gw.battle.sprite));

        for (ActionOption ao : actionOptions) {
            gw.addComponent(ao);
        }
        gw.PLAYER.toggleStatsDisplayed();

        battleState.run(gw);
    }

    @Override
    public GlobalState update(GameWindow gw) {
        GlobalState tempBattleState = battleState.update(gw);

        if (tempBattleState != null) {
            if (tempBattleState instanceof InitializeGameState) {
                battleState = (BattleState) prevState;
            }

            if (!(tempBattleState instanceof FancyTextTime)) {
                prevState = battleState;
            } else {
                actionOptions[index].toggleSelected();
            }

            if (!(tempBattleState instanceof BattleState) && !(tempBattleState instanceof InitializeGameState)) {
                battleState.clear(gw);
            }

            if (tempBattleState instanceof SelectBattleState) {
                gw.PLAYER.toggleStatsDisplayed();
                return tempBattleState;
            }

            if (!(tempBattleState instanceof InitializeGameState)) {
                battleState = (BattleState) tempBattleState;
            }

            battleState.run(gw);
        }

        return null;
    }
}
