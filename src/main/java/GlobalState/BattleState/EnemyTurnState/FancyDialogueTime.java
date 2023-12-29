package GlobalState.BattleState.EnemyTurnState;

import GlobalState.BattleState.FancyTextTime;
import GlobalState.GlobalState;
import Menu.Assets.FancyDialouge;
import Window.GameWindow;

public class FancyDialogueTime extends FancyTextTime {
    private boolean isBBAdded = false;
    public FancyDialogueTime(String title, int speed, GlobalState postState) {
        this.text = new FancyDialouge(title, speed);
        this.postState = postState;
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (!isBBAdded) {
            gw.battleBox.transitionTo(238, 251, 165, 140);
            isBBAdded = true;
            gw.PLAYER.setPos((int) gw.battleBox.box.getCenterX() - 9, (int) gw.battleBox.box.getCenterY() - 9);
            gw.PLAYER.toggleVisible();
        }

        if (gw.battleBox.isTransitioning) {
            gw.battleBox.progressTransition(18);
        }
        return super.update(gw);
    }
}
