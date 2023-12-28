package Menu.ActionOption.BattleAO;

import GlobalState.BattleState.EnemyTurnState.FancyDialogueTime;
import GlobalState.*;
import Menu.ActionOption.ActionOption;

import javax.imageio.ImageIO;
import java.util.Objects;

public class FightAO extends ActionOption {
    public FightAO() {
        this.X = 33;
        this.Y = 432;

        try {
            this.ICON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/fight_icon.png")));
            this.SELECTEDICON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/fight_selected_icon.png")));
        } catch (Exception e) {

        }
    }

    @Override
    public GlobalState interact() {
        super.interact();

        return new FancyDialogueTime("Should be burning in hell.", 1, new InitializeGameState());
    }
}
