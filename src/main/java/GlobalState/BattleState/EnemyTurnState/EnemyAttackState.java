package GlobalState.BattleState.EnemyTurnState;

import GlobalState.BattleState.Assets.Attack;
import GlobalState.BattleState.Assets.Bullet.*;
import GlobalState.BattleState.Assets.Bullet.Patterns.*;
import GlobalState.BattleState.BattleState;
import GlobalState.BattleState.ReturnState;
import GlobalState.GlobalState;
import Window.GameWindow;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EnemyAttackState extends BattleState {
    private int battleTicks = 0, duration, invincibleDuration = 0;
    private BulletPattern bulletPattern;

    public EnemyAttackState() {
        this.duration = 30;
    }

    @Override
    public void run(GameWindow gw) {
        gw.inBattleState.battleBG.sprite = gw.battle.attackingSprite;

        gw.removeComponent(gw.battleBox);
        gw.addComponent(gw.battleBox);

        // add attack
        Attack attack = gw.battle.getRandA();
        bulletPattern = attack.bulletPattern;
        this.duration = attack.duration;
        gw.battleBox.transitionTo(attack.battleBox);
        bulletPattern.generateBullets(gw.battleBox.targetBox);
        for (Bullet bullet : bulletPattern.getBullets()) {
            gw.addComponent(bullet);
        }
    }

    @Override
    public GlobalState update(GameWindow gw) {
        if (gw.battleBox.isTransitioning) {
            gw.battleBox.progressTransition(18);
        } else {
            battleTicks++;

            if (battleTicks > duration) {
                if (battleTicks == duration+1) {
                    if (gw.PLAYER.isInvincible()) {
                        gw.PLAYER.toggleInvincible();
                        invincibleDuration = 0;
                    }
                    if (gw.PLAYER.isVisible()) {
                        gw.PLAYER.toggleVisible();
                    }
                    gw.battleBox.transitionTo(33, 251, 574, 139);
                    for (Bullet bullet : bulletPattern.getBullets()) {
                        gw.removeComponent(bullet);
                    }
                    if (gw.battle.mercyHP == 0) {
                        gw.inBattleState.battleBG.sprite = gw.battle.spareableSprite;
                    } else {
                        gw.inBattleState.battleBG.sprite = gw.battle.defaultSprite;
                    }
                }
                if (gw.battleBox.isTransitioning) {
                    gw.battleBox.progressTransition(24);
                } else {
                    for (Bullet bullet : bulletPattern.getBullets()) {
                        gw.removeComponent(bullet);
                    }
                    bulletPattern.bullets.clear();
                    if (gw.PLAYER.isInvincible()) {
                        gw.PLAYER.toggleInvincible();
                        invincibleDuration = 0;
                    }
                    if (gw.PLAYER.isVisible()) {
                        gw.PLAYER.toggleVisible();
                    }
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
            for (Bullet bullet : bulletPattern.getBullets()) {
                bullet.progressMovement();
                if (gw.PLAYER.collidingWith(bullet) && invincibleDuration < 1) {
                    invincibleDuration = 30;
                    gw.PLAYER.toggleInvincible();
                    gw.PLAYER.damage(bullet.damVal);
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

            if (bulletPattern.isOver()) {
                removedBullets.addAll(bulletPattern.getBullets());
                bulletPattern.generateBullets(gw.battleBox.targetBox);
                for (Bullet bullet : bulletPattern.getBullets()) {
                    gw.addComponent(bullet);
                }
            }

            // remove bullets
            for (Bullet bullet : removedBullets) {
                gw.removeComponent(bullet);
            }
        }

        return null;
    }
}
