package Menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class BattleOT extends OptionText {
    private BufferedImage playerSoul;

    public BattleOT(String title, int x, int y) {
        super(title, x, y);

        try {
            playerSoul = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/player_soul.png")));
        } catch (Exception e) {

        }
    }

    public void interact() {
        super.interact();

        // play battle
    }
    @Override
    public void paint(Graphics g) {
        if (selected) {
            g.setColor(new Color(239,242,62));
        } else {
            g.setColor(Color.WHITE);
        }
        g.setFont(FONT);
        g.drawString("* "+TITLE, X, Y);
        if (selected) {
            g.setColor(Color.BLACK);
            g.drawString("*", X, Y);
            g.drawImage(playerSoul, X, Y-16, null);
        }
    }
}
