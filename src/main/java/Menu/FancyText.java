package Menu;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Window.GameWindow;

import javax.swing.*;

public class FancyText extends JComponent {
    public int x, y, width, SPEED;
    private double realIndex = -1;
    public int index = 0, numOfR = 1, ticks = 0, sfxTick = 0;
    public final ArrayList<Integer> astPos = new ArrayList<>();
    public Font FONT;
    public String TEXT;
    public final StringBuilder DISPLAYEDTEXT = new StringBuilder();

    public FancyText(String text, int speed) {
        this.TEXT = text;
        this.SPEED = speed;
        x = 53;
        y = 296;
        width = 29;
        astPos.add(0);

        InputStream stream = getClass().getResourceAsStream("/fonts/8bitoperator_jve.ttf");
        try {
            assert stream != null;
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.TRACKING, .09F);
            attributes.put(TextAttribute.SIZE, 32F);
            FONT = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(attributes);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
    public FancyText(){}

    public boolean isFullyWritten() {
        return index >= TEXT.length();
    }

    public void reset() {
        DISPLAYEDTEXT.setLength(0);
        index = 0;
        realIndex = -1;
        numOfR = 1;
        astPos.clear();
        astPos.add(0);
    }

    public void playSFX() {
        GameWindow gw = (GameWindow)this.getRootPane().getContentPane().getParent().getParent().getParent();
        int pitch = (int) ((Math.random() * (88200 - 44100)) + 44100);
        gw.AUDIOPLAYER.playClip(3, pitch);
    }

    public void write() {
        char currentChar = TEXT.charAt(index);

        if (currentChar == '.'
                || currentChar == '!'
                || currentChar == '?') {
            realIndex += 0.1F;
        } else if (currentChar == ','
                || currentChar == ':'
                || currentChar == ';') {
            realIndex += 0.2F;
        } else {
            realIndex++;
        }

        index = (int) Math.floor(realIndex);

        if (realIndex < (long) TEXT.length()) {
            currentChar = TEXT.charAt(index);

            if (realIndex % index > 0.1) {
                realIndex += Math.subtractExact((long) realIndex, index);
            } else {
                String s = DISPLAYEDTEXT.toString().split("/r")[DISPLAYEDTEXT.toString().split("/r").length-1];
                if (s.length()*14 > width*14) {
                    if (s.split(" ")[s.split(" ").length-1].length() > width) {
                        DISPLAYEDTEXT.append("/r");
                    } else {
                        for (int i = 1; i < DISPLAYEDTEXT.length(); i++) {
                            if (DISPLAYEDTEXT.charAt(index-i) == ' ') {
                                DISPLAYEDTEXT.replace(index-i, index-i+1, "/r");
                                break;
                            }
                        }
                    }
                    numOfR++;
                }
                if (currentChar == '*') {
                    DISPLAYEDTEXT.append("/r");
                    astPos.add(numOfR);
                    numOfR++;
                } else {
                    DISPLAYEDTEXT.append(currentChar);
                }
            }
        }
    }

    public void update() {
        if (index < TEXT.length()) {
            write();

            if (index < TEXT.length()-1 && sfxTick < 1) {
                playSFX();
                sfxTick = 3;
            }
        }

        if (realIndex % index < 0.1) {
            sfxTick--;
        }
    }

    public void complete() {
        reset();
        while (index < TEXT.length()) {
            write();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        g.setFont(FONT);

        for (int pos : astPos) {
            g.drawString("*", x, 32*pos+y);
        }
        String[] splitText = DISPLAYEDTEXT.toString().split("/r");
        for (int i = 0; i < splitText.length; i++) {
            g.drawString(splitText[i], x+30, 32*i+y);
        }

        if (ticks == SPEED) {
            update();
            ticks = 0;
        }
        ticks += 1;
    }
}
