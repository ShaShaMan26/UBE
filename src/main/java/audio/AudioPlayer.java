package audio;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AudioPlayer {

    private Clip clip;
    private Clip bgm;
    private final ArrayList<Clip> clips = new ArrayList<>();
    private final URL[] soundURL = new URL[2];
    private float volume;
    private FloatControl fc;

    public AudioPlayer() {
        soundURL[0] = getClass().getResource("/audio/menu_navigate.wav");
        soundURL[1] = getClass().getResource("/audio/menu_select.wav");
    }

    public void setBGM (int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(volume);
            clips.add(clip);
            bgm = clip;
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void playClip(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(volume);
            clips.add(clip);
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
        clip.start();
    }

    public void loadAll() {
        for (URL url : soundURL) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                fc.setValue(volume);
                clips.add(clip);
                audioInputStream.close();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }
            clip.start();
            clip.stop();
        }
    }
    public void playBGM() {
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopBGM() {
        bgm.stop();
    }

    public void resetBGM() {
        bgm.setMicrosecondPosition(0);
    }

    public void close() {
        for (Clip clip : clips) {
            clip.close();
        }
    }

    public void setVolume(float volume) {
        this.volume = volume;
        if (fc != null) {
            fc.setValue(volume);
        }
    }
}
