package Menu.ActionOption.CommandAO;

import GlobalState.BattleState.PlayerTurnState.MercySelectState;
import GlobalState.GlobalState;
import Menu.ActionOption.ActionOption;

import javax.imageio.ImageIO;
import java.util.Objects;

public class MercyAO extends ActionOption {
    public MercyAO() {
        this.X = 33+110+45+110+45+110+45;
        this.Y = 432;

        try {
            this.ICON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/mercy_icon.png")));
            this.SELECTEDICON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/mercy_selected_icon.png")));
        } catch (Exception e) {

        }
    }

    @Override
    public GlobalState interact() {
        super.interact();

        return new MercySelectState();
    }
}
