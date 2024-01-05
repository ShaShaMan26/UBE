package Menu.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BattleBG extends Component {
    public int x;
    public BufferedImage sprite;
    public boolean fightOver = false;

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
        if (fightOver) {
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
            g.drawImage(sprite, 102*x+14, 0, null);
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        } else {
            g.drawImage(sprite, 102*x+14, 0, null);
        }
    }
}
