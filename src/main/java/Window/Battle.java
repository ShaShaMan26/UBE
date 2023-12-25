package Window;

import Menu.ActionOption.ActSelectAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class Battle extends Component {
    public int HP, mercyHP, atk, def, XPReward, goldReward;
    private Boolean isBossMonster;
    public String name, checkTxt, enterTxt, spareableTxt;
    public ArrayList<String> dialogue, mercyDialogue, flavorTxt;
    // private ArrayList<Attack> attacks;
    public ActSelectAO[] actionOptions;
    public BufferedImage sprite;

    // Battle(File battleData) throws FileNotFoundException
    // Scanner fileReader = new Scanner(new FileReader(battleData));
    public Battle() {
        this.name = "Froggit";
        this.HP = 30;
        this.atk = 4;
        this.def = 4;
        this.XPReward = 3;
        this.goldReward = 2;
        this.mercyHP = 1;

        try {
            this.sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/froggit.png")));
        } catch (Exception e) {

        }

        this.enterTxt = "Froggit attacks you!";
        this.checkTxt = "Life is difficult for this enemy.";

        actionOptions = new ActSelectAO[]{
                new ActSelectAO("Check", 101, 295, 0,
                        name+" - ATK " + atk + " DEF " + (def+1) + "*"+checkTxt,
                        ""),
                new ActSelectAO("Compliment", 357, 295, 1, "Froggit didn't understand what you said, but was flattered anyway.", ""),
                new ActSelectAO("Threaten", 101, 327, 1, "Froggit didn't understand what you said, but was scared anyway.", ""),
                // new ActSelectAO("", 357, 327, 0, "", "")
        };
    }
}
