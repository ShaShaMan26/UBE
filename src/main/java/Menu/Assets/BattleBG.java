package Menu.Assets;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import Window.*;

import javax.swing.*;

public class BattleBG extends JComponent {
    public int x;
    private Font font = null, font2 = null;
    public BufferedImage sprite;
    public boolean fightOver = false;

    public BattleBG(int x, BufferedImage sprite) {
        this.x = x;
        this.sprite = sprite;

        InputStream stream = getClass().getResourceAsStream("/fonts/UMNC.ttf");
        try {
            assert stream != null;
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, .02F);
            attributes.put(TextAttribute.SIZE, 15F);
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);

            stream = getClass().getResourceAsStream("/fonts/8bit_wonder.TTF");
            attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, .05F);
            attributes.put(TextAttribute.SIZE, 12F);
            assert stream != null;
            font2 = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);
        } catch (Exception e) {

        }
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

        // draw player health
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("LV 1", 122, 417);
        g.drawString(gw.PLAYER.getHealth()+" / 20", 320, 417);

        g.setFont(font2);
        g.drawString("HP", 246, 414);

        g.setColor(new Color(252, 6, 5));
        g.fillRect(278, 399, 25, 21);
        g.setColor(new Color(251, 255, 41));
        g.fillRect(278, 399, (int)((25F/20F) * gw.PLAYER.getHealth()), 21);
    }
}
