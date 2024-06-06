import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Start extends JFrame {
    private JButton startButton;

    public Start() {
        setTitle("Choose Your Action");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2000, 1800);
        setLocationRelativeTo(null);
        playMusic("C:\\Users\\User\\IdeaProjects\\Pokemon2\\src\\Pokémon partners of different generations dancing _POKÉDANCE_ Animation Music Video.wav");


        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Pokemon2\\src\\GameBackground.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);

        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));

        // Set the bounds for the background image
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        JLabel welcomeLabel = new JLabel("Welcome to Pokemon World ! ! !");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.black);
        welcomeLabel.setBounds(320, 300, 800, 100);  // Adjust the position and size as needed

        startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 30));
        startButton.setBounds(620, 600, 200, 50);

        // Add components to the layered pane
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(welcomeLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(startButton, JLayeredPane.MODAL_LAYER);
        setContentPane(layeredPane);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Intro intro = new Intro(Start.this, getSize());
           //     register rg = new register(Start.this, getSize());

                // Hide the current Intro GUI
                setVisible(false);

                // Show the register GUI
                intro.setVisible(true);
            }
        });
        setVisible(true);
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
                int repeatCount = 2; // Set the desired number of repeats

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start();
            }
        });
    }
}
