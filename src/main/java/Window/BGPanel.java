package Window;

import javax.swing.*;
import java.awt.*;

public class BGPanel extends JPanel {
    public BGPanel() {
        this.setPreferredSize(new Dimension(640, 480));
        this.setBackground(Color.BLACK);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Component c : this.getComponents()) {
            c.paint(g);
        }
    }
}
