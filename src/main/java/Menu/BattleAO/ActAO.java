package Menu.BattleAO;

import GlobalState.GlobalState;
import Menu.ActionOption;

import javax.imageio.ImageIO;
import java.util.Objects;

public class ActAO extends ActionOption {
    public ActAO() {
        this.X = 33+110+45;
        this.Y = 432;

        try {
            this.ICON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/act_icon.png")));
            this.SELECTEDICON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/act_selected_icon.png")));
        } catch (Exception e) {

        }
    }

    @Override
    public GlobalState interact() {
        super.interact();

        return null;
    }
}
