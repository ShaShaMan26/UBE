package Menu.BattleAO;

import Menu.ActionOption;

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
    public void interact() {
        // open item menu
    }
}
