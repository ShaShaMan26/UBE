package Window;

import Menu.ActionOption.ActSelectAO;
import Menu.ActionOption.ActionOption;
import Menu.ActionOption.ItemSelectAO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Battle extends Component {
    public int HP, mercyHP, atk, def, XPReward, goldReward;
    private Boolean isBossMonster;
    public String name, checkTxt, enterTxt, spareableTxt;
    public ArrayList<String> dialogue, mercyDialogue, flavorTxt;
    // private ArrayList<Attack> attacks;
    public ActSelectAO[] actionOptions;
    private BufferedImage sprite;

    // Battle(File battleData) throws FileNotFoundException
    // Scanner fileReader = new Scanner(new FileReader(battleData));
    public Battle() {
        this.HP = 30;
        this.atk = 4;
        this.def = 4;
        this.XPReward = 3;
        this.goldReward = 2;
        this.mercyHP = 1;

        this.enterTxt = "Froggit attacks you!";

        actionOptions = new ActSelectAO[]{
                new ActSelectAO("Check", 101, 295, 0, "", ""),
                new ActSelectAO("Compliment", 357, 295, 1, "", ""),
                new ActSelectAO("Threaten", 101, 327, 1, "", ""),
                // new ActSelectAO("", 357, 327, 0, "", "")
        };
    }
}
