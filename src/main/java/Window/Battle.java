package Window;

import GlobalState.BattleState.Assets.Attack;
import GlobalState.BattleState.Assets.BattleBox;
import GlobalState.BattleState.Assets.Bullet.Patterns.*;
import Menu.ActionOption.CommandSelectAO.ActSelectAO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Battle extends Component {
    public int totalHP, HP, mercyHP, atk, playerAtk, def, XPReward, goldReward, col, dioX, dioY;
    public String name, checkTxt, enterTxt, spareableTxt;
    public ArrayList<String> dialogue = new ArrayList<>(), flavorTxt = new ArrayList<>();
    private ArrayList<Attack> attacks = new ArrayList<>();
    public ActSelectAO[] actionOptions;
    public BattleBox battleBox;
    public BufferedImage sprite, defaultSprite, hitSprite, attackingSprite, killedSprite, spareableSprite, talkingSprite;
    public File battleData, bgmFile;
    private Scanner battleDataReader;

    public Battle(String path) {
        bgmFile = new File(path+"\\bgm.wav");
        // reading sprite data
        try {
            this.defaultSprite = ImageIO.read(new File(path+"\\enemy_sprites\\default.png"));
            this.hitSprite = ImageIO.read(new File(path+"\\enemy_sprites\\hit.png"));
            this.attackingSprite = ImageIO.read(new File(path+"\\enemy_sprites\\attacking.png"));
            this.killedSprite = ImageIO.read(new File(path+"\\enemy_sprites\\killed.png"));
            this.spareableSprite = ImageIO.read(new File(path+"\\enemy_sprites\\spareable.png"));
            this.talkingSprite = ImageIO.read(new File(path+"\\enemy_sprites\\talking.png"));
        } catch (Exception e) {
        }
        this.sprite = defaultSprite;

        // reading battle data
        battleData = new File(path+"\\battle_data.txt");
        try {
            battleDataReader = new Scanner(new FileReader(battleData));
        } catch (Exception e) {

        }

        this.name = battleDataReader.nextLine().trim();

        this.col = Integer.parseInt(battleDataReader.nextLine().trim());
        this.dioX = Integer.parseInt(battleDataReader.nextLine().trim())*102+15;
        this.dioY = Integer.parseInt(battleDataReader.nextLine().trim())*118+16;
        this.totalHP = Integer.parseInt(battleDataReader.nextLine().trim());
        this.HP = totalHP;
        this.mercyHP = Integer.parseInt(battleDataReader.nextLine().trim());
        this.atk = Integer.parseInt(battleDataReader.nextLine().trim());
        this.def = Integer.parseInt(battleDataReader.nextLine().trim());
        this.XPReward = Integer.parseInt(battleDataReader.nextLine().trim());
        this.goldReward = Integer.parseInt(battleDataReader.nextLine().trim());

        this.playerAtk = Integer.parseInt(battleDataReader.nextLine().trim());

        this.enterTxt = battleDataReader.nextLine().trim();
        this.checkTxt = battleDataReader.nextLine().trim();
        this.spareableTxt = battleDataReader.nextLine().trim();

        while (!battleDataReader.nextLine().trim().equals("<")) {
        }
        String s = battleDataReader.nextLine().trim();
        while (!s.equals(">")) {
            flavorTxt.add(s);
            s = battleDataReader.nextLine().trim();
        }

        while (!battleDataReader.nextLine().trim().equals("<")) {
        }
        s = battleDataReader.nextLine().trim();
        while (!s.equals(">")) {
            dialogue.add(s);
            s = battleDataReader.nextLine().trim();
        }

        ArrayList<ActSelectAO> AOD = new ArrayList<>();
        AOD.add(new ActSelectAO("Check", 101, 295, 0,
                name+" - ATK " + atk + " DEF " + (def) + "*"+checkTxt,
                ""));

        while (!battleDataReader.nextLine().trim().equals("<")) {
        }
        s = battleDataReader.nextLine().trim();
        if (!s.equals(">")) {
            AOD.add(new ActSelectAO(s,
                    357,
                    295,
                    Integer.parseInt(battleDataReader.nextLine().trim()),
                    battleDataReader.nextLine().trim(),
                    battleDataReader.nextLine().trim()
                    ));
        }
        s = battleDataReader.nextLine().trim();
        if (!s.equals(">")) {
            AOD.add(new ActSelectAO(s,
                    101,
                    327,
                    Integer.parseInt(battleDataReader.nextLine().trim()),
                    battleDataReader.nextLine().trim(),
                    battleDataReader.nextLine().trim()
            ));
        }
        s = battleDataReader.nextLine().trim();
        if (!s.equals(">")) {
            AOD.add(new ActSelectAO(s,
                    357,
                    327,
                    Integer.parseInt(battleDataReader.nextLine().trim()),
                    battleDataReader.nextLine().trim(),
                    battleDataReader.nextLine().trim()
            ));
        }

        actionOptions = AOD.toArray(new ActSelectAO[0]);

        while (!battleDataReader.nextLine().trim().equals("<")) {
        }
        s = battleDataReader.nextLine().trim();
        while (!s.equals(">")) {
            int speed = Integer.parseInt(s);
            int damVal = Integer.parseInt(battleDataReader.nextLine().trim());
            int rotation = Integer.parseInt(battleDataReader.nextLine().trim());
            float fadeSpeed = Float.parseFloat(battleDataReader.nextLine().trim());
            String spriteName = battleDataReader.nextLine().trim();
            BulletPattern bulletPattern = null;
            BufferedImage bSprite = null;
            try {
                bSprite = ImageIO.read(new File(path+"\\bullet_sprites\\"+spriteName+".png"));
            } catch (Exception e) {

            }
            switch (battleDataReader.nextLine().trim().toLowerCase()) {
                case "wallsobullet" -> bulletPattern = new WallsOBullet(speed, damVal, rotation, fadeSpeed, bSprite);
                case "ladderdrill" -> bulletPattern = new LadderDrill(speed, damVal, rotation, fadeSpeed, bSprite);
                case "bulletrain" -> bulletPattern = new BulletRain(speed, damVal, rotation, fadeSpeed, bSprite);
                case "crusher" -> bulletPattern = new Crusher(speed, damVal, rotation, fadeSpeed, bSprite);
                case "doublewallsobullet" -> bulletPattern = new DoubleWallsOBullet(speed, damVal, rotation, fadeSpeed, bSprite);
                case "closingwalls" -> bulletPattern = new ClosingWalls(speed, damVal, rotation, fadeSpeed, bSprite);
                default -> {
                }
            }
            int duration = Integer.parseInt(battleDataReader.nextLine().trim());
            int x = Integer.parseInt(battleDataReader.nextLine().trim());
            int y = Integer.parseInt(battleDataReader.nextLine().trim());
            int width = Integer.parseInt(battleDataReader.nextLine().trim());
            int height = Integer.parseInt(battleDataReader.nextLine().trim());
            attacks.add(new Attack(bulletPattern, duration, new BattleBox(x, y, width, height)));
            s = battleDataReader.nextLine().trim();
        }
        battleDataReader.close();
    }

    public String getRandFT() {
        int index = (int) (Math.random() * flavorTxt.size());
        return flavorTxt.get(index);
    }
    public String getRandD() {
        int index = (int) (Math.random() * dialogue.size());
        return dialogue.get(index);
    }
    public Attack getRandA() {
        int index = (int) (Math.random() * attacks.size());
        return attacks.get(index);
    }

    public void dealDamage(int damage) {
        HP -= damage;
        if (HP < 0) {
            HP = 0;
        }
    }
}
