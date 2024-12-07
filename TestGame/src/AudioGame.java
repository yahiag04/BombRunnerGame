import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioGame {

    private Clip clip;

    public AudioGame(String pathAudio) {
        try {
            // Load the audio file
            File audioFile = new File(pathAudio);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get the clip
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the audio
            clip.start(); // Start playing
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }


}
