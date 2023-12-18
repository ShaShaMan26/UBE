package Menu.BattleAO;

import Menu.ActionOption;

import javax.imageio.ImageIO;
import java.util.Objects;

public class ItemAO extends ActionOption {
    public ItemAO() {
        this.X = 33+110+45+110+45;
        this.Y = 432;

        try {
            this.ICON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/item_icon.png")));
            this.SELECTEDICON = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/item_selected_icon.png")));
        } catch (Exception e) {

        }
    }

    @Override
    public void interact() {
        // open item menu
    }
}
