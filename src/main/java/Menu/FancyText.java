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
    private final int X = 53, Y = 296, SPEED;
    private double realIndex = -1, ticks = 0;
    private int index = 0, numOfR = 1;
    private final ArrayList<Integer> astPos = new ArrayList<>();
    private final ArrayList<Character> invChars = new ArrayList<>();
    private final Font FONT;
    private final String TEXT;
    private final StringBuilder DISPLAYEDTEXT = new StringBuilder();

    public FancyText(String text, int speed) {
        this.TEXT = text;
        this.SPEED = speed;
        astPos.add(0);

        invChars.add(' ');

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
                if (s.length()*14 > 435) {
                    if (s.split(" ")[s.split(" ").length-1].length() > 20) {
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

            if (realIndex < (long) TEXT.length()
                    && realIndex % index < 0.1
                    && !invChars.contains(TEXT.charAt(index))) {
                playSFX();
            }
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
            g.drawString("*", X, 32*pos+Y);
        }
        String[] splitText = DISPLAYEDTEXT.toString().split("/r");
        for (int i = 0; i < splitText.length; i++) {
            g.drawString(splitText[i], X+30, 32*i+Y);
        }

        if (ticks == SPEED) {
            update();
            ticks = 0;
        }
        ticks += 1;
    }
}
