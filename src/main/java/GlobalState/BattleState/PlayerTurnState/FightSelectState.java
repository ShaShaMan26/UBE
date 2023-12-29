package GlobalState.BattleState.PlayerTurnState;

import GlobalState.BattleState.BattleState;
import GlobalState.BattleState.EnemyTurnState.EnemyAttackState;
import GlobalState.BattleState.EnemyTurnState.FancyDialogueTime;
import GlobalState.BattleState.Assets.FightBox;
import GlobalState.*;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class FightSelectState extends BattleState {
    private FightBox fightBox;
    @Override
    public void run(GameWindow gw) {
        fightBox = new FightBox(gw.battle.totalHP, gw.battle.HP, gw.battle.col);
        gw.addComponent(fightBox);
        gw.PLAYER.toggleVisible();
        gw.inBattleState.toggleCommandSelect();
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (fightBox.isAttackCompleted()) {
            gw.removeComponent(fightBox);
            if (fightBox.slideIndex == -1 && gw.battle.HP == 0) {
                gw.PLAYER.toggleVisible();
                return new SelectBattleState();
            }
            return new FancyDialogueTime(gw.battle.getRandD(), 1, new EnemyAttackState());
        }

        if (fightBox.attackTriggered) {
            fightBox.progressAttack();
            if (fightBox.isSlashOver() && fightBox.slideIndex == fightBox.damage) {
                gw.AUDIOPLAYER.playClip(5);
            }
        } else if (fightBox.attackMissed) {
            fightBox.progressAttack();
        } else {
            if (gw.getPressedKeys().contains(KeyEvent.VK_Z)) {
                gw.invalidateKey(KeyEvent.VK_Z);

                double b;
                if (Math.abs(fightBox.getNeedlePos() - 272) <= 12) {
                    b = 2.2;
                } else {
                    b = (1 - (Math.abs(fightBox.getNeedlePos() - 272))/544F)*2;
                }
                int damage = (int) ((gw.PLAYER.atk - gw.battle.def + ((int) (Math.random()*3))) * b);

                fightBox.startAttack(damage);
                gw.battle.dealDamage(damage);
                gw.AUDIOPLAYER.playClip(6);
            } else {
                if (fightBox.getNeedlePos() > 544) {
                    fightBox.missAttack();
                } else {
                    fightBox.moveNeedle();
                }
            }
        }

        return null;
    }
}
