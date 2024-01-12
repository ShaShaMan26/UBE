package Window;

import GlobalState.BattleState.Assets.BattleBox;
import GlobalState.BattleState.InBattleState;
import Menu.ActionOption.BattleSelectAO;
import audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

public class GameWindow extends JFrame implements KeyListener {
    private final BGPanel PANEL;
    public final AudioPlayer AUDIOPLAYER;
    public final Player PLAYER;
    public Battle battle = null;
    public BattleBox battleBox = new BattleBox(33, 251, 574, 139);
    public InBattleState inBattleState = null;
    private final ArrayList<Integer> pressedKeys = new ArrayList<>();
    private final ArrayList<Integer> invalidKeys = new ArrayList<>();
    public ArrayList<BattleSelectAO> battles = new ArrayList<>();
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
        this.setTitle("Undertale Battle Engine");
        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);

        this.setVisible(true);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/art/icon.png")));
        this.setIconImage(icon.getImage());

        this.PLAYER = new Player();
        this.addComponent(PLAYER);
    }

    public void addComponent(Component c) {
        PANEL.add(c);
    }
    public void removeComponent(Component c) {
        PANEL.remove(c);
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
