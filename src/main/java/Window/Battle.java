package Window;

import Menu.ActionOption.ActSelectAO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Battle extends Component {
    public int totalHP, HP, mercyHP, atk, def, XPReward, goldReward, col;
    private Boolean isBossMonster;
    public String name, checkTxt, enterTxt, spareableTxt;
    public ArrayList<String> dialogue = new ArrayList<>(), flavorTxt = new ArrayList<>();
    // private ArrayList<Attack> attacks;
    public ActSelectAO[] actionOptions;
    public BufferedImage sprite;

    // Battle(File battleData) throws FileNotFoundException
    // Scanner fileReader = new Scanner(new FileReader(battleData));
    public Battle() {
        this.name = "Froggit";
        this.totalHP = 30;
        this.HP = totalHP;
        this.atk = 4;
        this.def = 4;
        this.XPReward = 3;
        this.goldReward = 2;
        this.mercyHP = 1;
        this.col = 2;

        try {
            this.sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/art/froggit.png")));
        } catch (Exception e) {

        }

        this.enterTxt = "Froggit hopped close!";
        this.checkTxt = "Life is difficult for this enemy.";
        this.spareableTxt = "Froggit seems reluctant to fight you.";

        this.flavorTxt.add("Froggit doesn't seem to know why it's here.");
        this.flavorTxt.add("Froggit hops to and fro.");
        this.flavorTxt.add("The battlefield is filled with the smell of mustard seed.");
        this.flavorTxt.add("You are intimidated by Froggit's raw strength.*Only kidding.");

        this.dialogue.add("Ribbit, ribbit.");
        this.dialogue.add("Croak, croak.");
        this.dialogue.add("Hop, hop.");
        this.dialogue.add("Meow.");

        actionOptions = new ActSelectAO[]{
                new ActSelectAO("Check", 101, 295, 0,
                        name+" - ATK " + atk + " DEF " + (def+1) + "*"+checkTxt,
                        ""),
                new ActSelectAO("Compliment", 357, 295, 1, "Froggit didn't understand what you said, but was flattered anyway.", "(Blushes deeply.) Ribbit.."),
                new ActSelectAO("Threaten", 101, 327, 1, "Froggit didn't understand what you said, but was scared anyway.", "Shiver, shiver."),
                // new ActSelectAO("", 357, 327, 0, "", "")
        };
    }

    public String getRandFT() {
        int index = (int) (Math.random() * flavorTxt.size());
        return flavorTxt.get(index);
    }
    public String getRandD() {
        int index = (int) (Math.random() * dialogue.size());
        return dialogue.get(index);
    }

    public void dealDamage(int damage) {
        HP -= damage;
        if (HP < 0) {
            HP = 0;
        }
    }
}
