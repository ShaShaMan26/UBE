package Menu.Assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Arrow extends Component {
    private BufferedImage sprite;
    public boolean visible = true;
    public Arrow() {
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/arrow.png")));
        } catch (Exception e) {
        }
    }

    public void paint(Graphics g) {
        if (visible) {
            g.drawImage(sprite, 302, 468, null);
        }
    }
}
