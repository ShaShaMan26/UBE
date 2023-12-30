package GlobalState.BattleState.EnemyTurnState;

import GlobalState.BattleState.Assets.Bullet.*;
import GlobalState.BattleState.BattleState;
import GlobalState.BattleState.ReturnState;
import GlobalState.GlobalState;
import Window.GameWindow;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class EnemyAttackState extends BattleState {
    private int battleTicks = 0, duration;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    // duration, battleBox size
    public EnemyAttackState() {
        //this.duration = duration*30;
        this.duration = 15*30;
    }

    @Override
    public void run(GameWindow gw) {
        // add bullets from data
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/act_icon.png")));
        } catch (Exception e) {

        }
        bullets.add(new UpMovingBullet((int) gw.battleBox.box.getCenterX(), (int) gw.battleBox.box.getCenterY(), 2, sprite));
        bullets.add(new RightMovingBullet((int) gw.battleBox.box.getCenterX(), (int) gw.battleBox.box.getCenterY(), 2, sprite));
        bullets.add(new LeftMovingBullet((int) gw.battleBox.box.getCenterX(), (int) gw.battleBox.box.getCenterY(), 2, sprite));
        bullets.add(new DownMovingBullet((int) gw.battleBox.box.getCenterX(), (int) gw.battleBox.box.getCenterY(), 2, sprite));

        for (Bullet bullet : bullets) {
            gw.addComponent(bullet);
        }
    }

    @Override
    public GlobalState update(GameWindow gw) {
        battleTicks++;

        if (battleTicks > duration) {
            if (battleTicks == duration+1) {
                gw.PLAYER.toggleVisible();
                gw.battleBox.transitionTo(33, 251, 574, 139);
            }
            if (gw.battleBox.isTransitioning) {
                gw.battleBox.progressTransition(18);
            } else {
                gw.PLAYER.toggleVisible();
                return new ReturnState();
            }
        }

        // player movement
        if (gw.getPressedKeys().contains(KeyEvent.VK_X) && !gw.PLAYER.isSlow()
                || !gw.getPressedKeys().contains(KeyEvent.VK_X) && gw.PLAYER.isSlow()) {
            gw.PLAYER.toggleSlow();
        }

        int numPressed = 0;
        if (gw.getPressedKeys().contains(KeyEvent.VK_UP)
                ^ gw.getPressedKeys().contains(KeyEvent.VK_DOWN)) {
            numPressed++;
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_RIGHT)
                ^ gw.getPressedKeys().contains(KeyEvent.VK_LEFT)) {
            numPressed++;
        }
        if (numPressed > 1) {
            gw.PLAYER.toggleDiagonal();
        }

        if (gw.getPressedKeys().contains(KeyEvent.VK_UP)) {
            gw.PLAYER.moveUp();
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_DOWN)) {
            gw.PLAYER.moveDown();
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_LEFT)) {
            gw.PLAYER.moveLeft();
        }
        if (gw.getPressedKeys().contains(KeyEvent.VK_RIGHT)) {
            gw.PLAYER.moveRight();
        }

        if (numPressed > 1) {
            gw.PLAYER.toggleDiagonal();
        }

        // player collision with walls
        if (gw.PLAYER.x <= gw.battleBox.box.x+5) {
            gw.PLAYER.x = gw.battleBox.box.x+5;
        }
        if (gw.PLAYER.x >= gw.battleBox.box.x+gw.battleBox.box.width-5-16) {
            gw.PLAYER.x = gw.battleBox.box.x+gw.battleBox.box.width-5-16;
        }
        if (gw.PLAYER.y <= gw.battleBox.box.y+5) {
            gw.PLAYER.y = gw.battleBox.box.y+5;
        }
        if (gw.PLAYER.y >= gw.battleBox.box.y+gw.battleBox.box.height-5-16) {
            gw.PLAYER.y = gw.battleBox.box.y+gw.battleBox.box.height-5-16;
        }

        // progress bullets and check for player collision with bullets
        ArrayList<Bullet> removedBullets = new ArrayList<>();
        for (Bullet bullet : bullets) {
            bullet.progressMovement();
            if (gw.PLAYER.collidingWith(bullet)) {
                gw.PLAYER.damage(1);
                gw.AUDIOPLAYER.playClip(7);
                removedBullets.add(bullet);
            }
        }
        // remove bullets
        for (Bullet bullet : removedBullets) {
            bullets.remove(bullet);
            gw.removeComponent(bullet);
        }

        return null;
    }
}
