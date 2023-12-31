package GlobalState.BattleState.EnemyTurnState;

import GlobalState.BattleState.Assets.Bullet.*;
import GlobalState.BattleState.Assets.Bullet.Patterns.WallOfBullets;
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
    private int battleTicks = 0, duration, invincibleDuration = 0, r = 0;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    // duration, battleBox size
    public EnemyAttackState() {
        //this.duration = duration*30;
        this.duration = 15*30;
    }

    @Override
    public void run(GameWindow gw) {
        gw.battleBox.transitionTo(gw.battle.battleBox);

        // add bullets from data
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.battleBox.isTransitioning) {
            gw.battleBox.progressTransition(6);
        } else {
            if (bullets.size() < 1) {
                BufferedImage sprite = null;
                try {
                    sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/bullet.png")));
                } catch (Exception e) {

                }

                bullets.addAll(new WallOfBullets(gw, 10, 0.1F, r, sprite).getBullets());
                for (Bullet bullet : bullets) {
                    gw.addComponent(bullet);
                }

                if (r < 4) {
                    r++;
                } else {
                    r = 0;
                }
            }

            battleTicks++;

            if (battleTicks > duration) {
                if (battleTicks == duration+1) {
                    if (gw.PLAYER.isInvincible()) {
                        gw.PLAYER.toggleInvincible();
                        invincibleDuration = 0;
                        gw.PLAYER.toggleVisible();
                    }
                    if (gw.PLAYER.isVisible()) {
                        gw.PLAYER.toggleVisible();
                    }
                    gw.battleBox.transitionTo(33, 251, 574, 139);
                    for (Bullet bullet : bullets) {
                        gw.removeComponent(bullet);
                    }
                }
                if (gw.battleBox.isTransitioning) {
                    gw.battleBox.progressTransition(24);
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
                if (bullet.duration < 1
                        || bullet.x < 0
                        || bullet.x > gw.getWidth()
                        || bullet.y < 0
                        || bullet.y > gw.getHeight()) {
                    removedBullets.add(bullet);
                }

                if (gw.PLAYER.collidingWith(bullet) && invincibleDuration < 1) {
                    invincibleDuration = 30;
                    gw.PLAYER.toggleInvincible();
                    gw.PLAYER.damage(1);
                    gw.AUDIOPLAYER.playClip(7);
                    removedBullets.add(bullet);
                }
            }

            if (invincibleDuration > 0) {
                if (invincibleDuration % 3== 0) {
                    gw.PLAYER.toggleVisible();
                }
                invincibleDuration--;
                if (invincibleDuration == 0) {
                    gw.PLAYER.toggleInvincible();
                }
            }

            // remove bullets
            for (Bullet bullet : removedBullets) {
                bullets.remove(bullet);
                gw.removeComponent(bullet);
            }
        }

        return null;
    }
}
