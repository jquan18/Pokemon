import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    public Music() {
        playMusic("C:\\Users\\User\\IdeaProjects\\Pokemon2\\src\\Pokémon partners of different generations dancing _POKÉDANCE_ Animation Music Video.wav");
    }

    private void playMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            // clip.start();
            // Add a LineListener to restart the clip when playback ends
            clip.addLineListener(new LineListener() {
                int repeatCount = 30; // Set the desired number of repeats

                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        if (repeatCount > 0) {
                            repeatCount--;
                            clip.setFramePosition(0);
                            clip.start();
                        } else {
                            // Close the clip when all repeats are finished
                            clip.close();
                        }
                    }
                }
            });
            clip.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
