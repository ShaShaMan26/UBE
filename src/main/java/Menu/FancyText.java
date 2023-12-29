package Menu;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public abstract class FancyText extends JComponent {
    public int x, y, width, SPEED;
    private double realIndex = -1;
    public int index = 0, numOfR = 1, ticks = 0, sfxTick = 0;
    public final ArrayList<Integer> astPos = new ArrayList<>();
    public Font FONT;
    public String TEXT;
    public final StringBuilder DISPLAYEDTEXT = new StringBuilder();

    public FancyText(String text, int speed, int x, int y, int width) {
        this.TEXT = text;
        this.SPEED = speed;
        this.x = x;
        this.y = y;
        this.width = width;
        astPos.add(0);
    }

    public abstract void playSFX();

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
                if (s.length() > width) {
                    if (s.split(" ")[s.split(" ").length-1].length() > width) {
                        DISPLAYEDTEXT.append("/r");
                    } else if (DISPLAYEDTEXT.lastIndexOf(" ") == index+1) {
                        DISPLAYEDTEXT.deleteCharAt(index+1);
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

                if (DISPLAYEDTEXT.indexOf("/r ") > -1) {
                    DISPLAYEDTEXT.delete(DISPLAYEDTEXT.indexOf("/r "), DISPLAYEDTEXT.indexOf("/r ")+3);
                }
            }
        }
    }

    public void update() {
        if (index < TEXT.length()) {
            write();

            if (index < TEXT.length()-1 && sfxTick < 1) {
                playSFX();
                sfxTick = 2;
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
}
