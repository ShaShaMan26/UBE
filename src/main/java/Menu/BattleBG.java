package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BattleBG extends Component {
    public int x;
    private BufferedImage sprite = null;

    public BattleBG(int x, BufferedImage sprite) {
        this.x = x;
        this.sprite = sprite;
    }

    public void paint(Graphics g) {
        super.paint(g);

        // draw bg
        g.setColor(new Color(34, 177, 76));
        g.drawRect(13, 10, 612, 236);
        for (int i = 1; i < 6; i++) {
            g.drawLine(102*i+13, 11, 102*i+13, 245);
        }
        g.drawLine(13, 128, 625, 128);

        // draw character
        g.drawImage(sprite, 102*x+14, 0, null);

        // draw text box
        g.setColor(Color.WHITE);
        g.fillRect(33, 251, 574, 139);
        g.setColor(Color.BLACK);
        g.fillRect(38, 256, 564, 129);
    }
}
