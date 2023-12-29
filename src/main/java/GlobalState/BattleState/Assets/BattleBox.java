package GlobalState.BattleState.Assets;

import java.awt.*;

public class BattleBox extends Component {
    public Rectangle box = new Rectangle(), targetBox = new Rectangle();
    public boolean isTransitioning = false;
    public BattleBox(int x, int y, int width, int height) {
        box.setBounds(x, y, width, height);
    }

    public void transitionTo(int x, int y, int width, int height) {
        targetBox.setBounds(x, y, width, height);
        isTransitioning = true;
    }

    public void progressTransition(int amount) {
        while (isTransitioning && amount > 0) {
            int numCompleted = 0;
            if (box.x != targetBox.x) {
                box.x += Integer.signum(targetBox.x - box.x);
            } else {
                numCompleted++;
            }
            if (box.y != targetBox.y) {
                box.y += Integer.signum(targetBox.y - box.y);
            } else {
                numCompleted++;
            }
            if (box.width != targetBox.width) {
                box.width += Integer.signum(targetBox.width - box.width);
            } else {
                numCompleted++;
            }
            if (box.width != targetBox.width) {
                box.width += Integer.signum(targetBox.width - box.width);
            }
            if (box.height != targetBox.height) {
                box.height += Integer.signum(targetBox.height - box.height);
            } else {
                numCompleted++;
            }
            if (box.height != targetBox.height) {
                box.height += Integer.signum(targetBox.height - box.height);
            }

            if (numCompleted > 3) {
                isTransitioning = false;
            }

            amount--;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(box.x, box.y, box.width, box.height);
        g.setColor(Color.BLACK);
        g.fillRect(box.x+5, box.y+5, box.width-10, box.height-10);
    }
}
