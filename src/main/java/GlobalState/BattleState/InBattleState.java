package GlobalState.BattleState;

import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import Menu.ActionOption.*;
import Menu.ActionOption.CommandAO.ActAO;
import Menu.ActionOption.CommandAO.FightAO;
import Menu.ActionOption.CommandAO.ItemAO;
import Menu.ActionOption.CommandAO.MercyAO;
import Menu.Assets.BattleBG;
import GlobalState.BattleState.PlayerTurnState.SelectActionState;
import GlobalState.*;
import Window.GameWindow;

public class InBattleState extends GlobalState {
    public ActionOption[] actionOptions = new ActionOption[] {
        new FightAO(),
        new ActAO(),
        new ItemAO(),
        new MercyAO()
    };
    public int index = 0;
    public SelectActionState selectActionState = new SelectActionState();
    public BattleBG battleBG;
    private BattleState battleState;

    public void toggleCommandSelect() {
        actionOptions[index].toggleSelected();
    }

    @Override
    public void run(GameWindow gw) {
        gw.AUDIOPLAYER.setBGM(gw.battle.bgmFile);
        gw.AUDIOPLAYER.playBGM();
        gw.AUDIOPLAYER.stopBGM();
        gw.AUDIOPLAYER.resetBGM();

        selectActionState.setText(gw.battle.enterTxt);
        battleState = selectActionState;

        gw.addComponent(gw.battleBox);

        battleBG = new BattleBG(gw.battle.col, gw.battle.sprite);
        gw.addComponent(battleBG);

        for (ActionOption ao : actionOptions) {
            gw.addComponent(ao);
        }

        gw.AUDIOPLAYER.playBGM();
        battleState.run(gw);
    }

    @Override
    public GlobalState update(GameWindow gw) {
        GlobalState tempBattleState = battleState.update(gw);

        if (tempBattleState != null) {
            if (tempBattleState instanceof ReturnState) {
                battleState = selectActionState;
            }

            if (tempBattleState instanceof EnemyAttackState) {
                if (gw.battle.mercyHP == 0) {
                    selectActionState.setText(gw.battle.spareableTxt);
                } else {
                    selectActionState.setText(gw.battle.getRandFT());
                }
            }

            if (tempBattleState instanceof SelectBattleState) {
                return tempBattleState;
            }

            if (!(tempBattleState instanceof ReturnState)) {
                if (tempBattleState instanceof BattleState) {
                    battleState = (BattleState) tempBattleState;
                } else {
                    battleState.clear(gw);
                }
            }

            battleState.run(gw);
        }

        return null;
    }
}
