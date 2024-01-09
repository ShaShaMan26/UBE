package GlobalState.BattleState.EnemyTurnState;

import GlobalState.BattleState.FancyTextTime;
import GlobalState.GlobalState;
import Menu.Assets.FancyDialouge;
import Window.GameWindow;

import java.awt.event.KeyEvent;

public class FancyDialogueTime extends FancyTextTime {
    private boolean isBBAdded = false;
    public FancyDialogueTime(String title, int speed, GlobalState postState) {
        this.text = new FancyDialouge(title, speed);
        this.postState = postState;
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (!isBBAdded) {
            gw.inBattleState.battleBG.sprite = gw.battle.talkingSprite;
            gw.battleBox.transitionTo(238, 251, 165, 140);
            isBBAdded = true;
            gw.PLAYER.setPos((int) gw.battleBox.box.getCenterX() - 9, (int) gw.battleBox.box.getCenterY() - 9);
            gw.PLAYER.toggleVisible();
        }

        if (gw.battleBox.isTransitioning) {
            gw.battleBox.progressTransition(18);
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_X)) {
            gw.battleBox.progressTransition(500);
        }
        return super.update(gw);
    }
}
