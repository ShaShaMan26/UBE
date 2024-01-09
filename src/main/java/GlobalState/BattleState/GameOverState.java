package GlobalState.BattleState;

import GlobalState.*;
import Window.GameWindow;

public class GameOverState extends BattleState {
    private int ticks = 0;

    @Override
    public void run(GameWindow gw) {
        gw.AUDIOPLAYER.stopBGM();
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (ticks > 75) {
            gw.AUDIOPLAYER.playClip(11);
            gw.battleBox.transitionTo(33, 251, 574, 139);
            gw.battleBox.progressTransition(900);
            gw.PLAYER.split = false;
            return new SelectBattleState();
        } else if (ticks == 30) {
            gw.AUDIOPLAYER.playClip(10);
            gw.PLAYER.split = true;
        }
        ticks++;
        return null;
    }
}
