package Window;

import audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameWindow extends JFrame implements KeyListener {
    private final BGPanel PANEL;
    public final AudioPlayer AUDIOPLAYER;
    public final Player PLAYER;
    private final ArrayList<Integer> pressedKeys = new ArrayList<>();
    private final ArrayList<Integer> invalidKeys = new ArrayList<>();
    public GameWindow() {
        this.PANEL = new BGPanel();
        this.AUDIOPLAYER = new AudioPlayer();
        AUDIOPLAYER.loadAll();

        this.getContentPane().add(PANEL);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("UTB");
        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);

        this.setVisible(true);

        this.PLAYER = new Player();
        this.addComponent(PLAYER);
    }

    public void addComponent(Component c) {
        PANEL.add(c);
    }

    public BGPanel getPanel() {
        return this.PANEL;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(e.getKeyCode()) && !invalidKeys.contains(e.getKeyCode())) {
            pressedKeys.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove((Integer) e.getKeyCode());
        invalidKeys.remove((Integer) e.getKeyCode());
    }

    public ArrayList<Integer> getPressedKeys() {
        return pressedKeys;
    }

    public void invalidateKey(Integer key) {
        pressedKeys.remove(key);
        invalidKeys.add(key);
    }
}
