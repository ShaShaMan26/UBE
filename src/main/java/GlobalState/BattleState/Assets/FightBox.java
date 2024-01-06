package GlobalState.BattleState.Assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FightBox extends Component {
    private BufferedImage eye;
    private BufferedImage[] slashFrames = new BufferedImage[6];
    private int spriteX, ticks = 0, hp, totalHP;
    public int damage, slideIndex, x = 47;
    public boolean attackTriggered = false, attackMissed = false;
    private Font font;
    public FightBox(int totalHP, int hp, int spriteX) {
        this.totalHP = totalHP;
        this.hp = hp;
        this.spriteX = 102*spriteX+14;

        InputStream stream = getClass().getResourceAsStream("/fonts/hachicro.ttf");
        try {
            assert stream != null;
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, 0F);
            attributes.put(TextAttribute.SIZE, 28F);
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);

            eye = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/fight_eye.png")));
            for (int i = 0; i < slashFrames.length; i++) {
                slashFrames[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/slash/"+(i+1)+".png")));
            }
        } catch (Exception e) {

        }
    }

    public void moveNeedle() {
        x+=12;
    }

    public int getNeedlePos() {
        return x - 48;
    }

    public void startAttack(int damage) {
        this.damage = damage;
        this.hp -= damage;
        this.slideIndex = damage;
        attackTriggered = true;
    }

    public void progressAttack() {
        ticks++;
    }

    public void missAttack() {
        ticks = slashFrames.length*6;
        slideIndex = 3;
        attackMissed = true;
    }

    public boolean isAttackCompleted() {
        return isSlashOver() && slideIndex < 0;
    }

    public boolean isSlashOver() {
        return ticks >= slashFrames.length*6;
    }

    public void paint(Graphics g) {
        // draw eye
        g.drawImage(eye, 48, 263, null);

        // draw slash
        if (!isSlashOver() && ticks / 5 > 0 && ticks / 5 < 6) {
            g.drawImage(slashFrames[ticks / 5], spriteX, 128, null);
        }

        // draw damage fx
        if (isSlashOver()) {
            g.setFont(font);
            String s;
            if (attackTriggered) {
                g.setColor(Color.BLACK);
                g.fillRect(spriteX+2, 115, 104, 17);
                g.setColor(Color.DARK_GRAY);
                g.fillRect(spriteX+4, 117, 102, 15);
                if (hp + slideIndex > -1) {
                    g.setColor(new Color(0, 254, 12));
                    g.fillRect(spriteX+4, 117, (int) ((102F/totalHP) * (hp + slideIndex)), 15);
                }
                g.setColor(new Color(244, 1, 5));
                s = ""+damage;
            } else {
                g.setColor(Color.LIGHT_GRAY);
                s = "MISS";
            }

            g.drawString(s, 104/2 - (s.length()*28 / 2) + spriteX+2, 115);

            if (!isAttackCompleted() && ticks % 2 == 0) {
                if (hp + slideIndex > 0) {
                    slideIndex--;
                } else {
                    slideIndex = -1;
                }
            }
        }

        // draw needle
        if (!attackMissed) {
            if (ticks / 2 % 3 == 0) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(x-6, 255, 11, 129);
            if (ticks / 2 % 3 == 0) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillRect(x-3, 258, 5, 123);
        }
    }
}
