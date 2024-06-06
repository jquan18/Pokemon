import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.CountDownLatch;


public class ScriptGUI extends JFrame {
    private JTextArea scriptTextArea;
    private int scriptStage = 0;
    private String trainerName;
    private HandingAbnormalInput inputChecker = new HandingAbnormalInput();
    private boolean textFullyDisplayed = false;
    private Image backgroundImage;  // Declare backgroundImage at the class level

    public ScriptGUI(String trainerName) {
        this.trainerName = trainerName;
        setTitle("Pokemon Adventure");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2000, 1800);
        setLocationRelativeTo(null);

        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\User\\IdeaProjects\\Pokemon2\\src\\professorBirch.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize components
        scriptTextArea = new JTextArea();
        scriptTextArea.setEditable(false);
        scriptTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        scriptTextArea.setForeground(Color.BLACK);  // Set text color to white for better visibility

        JScrollPane scrollPane = new JScrollPane(scriptTextArea);
        scrollPane.setPreferredSize(new Dimension(1200, 1000));  // Adjust size of the text area panel

        scriptTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    synchronized (ScriptGUI.this) {
                        if (textFullyDisplayed) {
                            ScriptGUI.this.notify();  // Notify the typewriter effect to proceed
                            e.consume();  // Prevent the newline character from being added to the JTextArea
                        }
                    }
                }
            }
        });

        // Create a custom JPanel for the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(null);  // Use null layout for absolute positioning

        // Add the script area panel to the background panel
        scrollPane.setBounds(20, 50, 650, 700);  // Adjust the position and size of the script area panel
        backgroundPanel.add(scrollPane);

        // Layout setup
        setContentPane(backgroundPanel);

        // Display the first part of the script
        displayNextScript();

        setVisible(true);
    }

    private void displayNextScript() {
        switch (scriptStage) {
            case 0:
                typeWriterEffect1("BIRCH   : Hi! Sorry to keep you waiting!\nBIRCH   : Welcome to the world of POKEMON!\nBIRCH   : My name is BIRCH! But everyone calls me the POKEMON PROF!\n");
                break;
            case 1:
                typeWriterEffect1("BIRCH   : This world is inhabited by creatures known as POKEMON!\n          We humans live alongside POKEMON, at times as friendly playmates,\n          and at times as cooperative workmates.\n");
                break;
            case 2:
                typeWriterEffect1("BIRCH   : And sometimes, we band together and battle others like us.\nBIRCH   : But despite our closeness, we don't know everything about POKEMON.\nBIRCH   : In fact, there are many, many secrets surrounding POKEMON. To unravel\n          POKEMON mysteries, I've been undertaking research. That's what I do.\n");
                break;
            case 3:
                continueScript(trainerName);
                break;
            case 4:
                choosePartner();
                break;
            case 5:
                typeWriterEffect1("Your very own adventure is about to unfold.\n\nTake courage, and leap into the world of POKEMON where dreams, adventures, and friendships await!\n");
                break;
            default:
                break;
        }
        scriptStage++;
    }

    private void continueScript(String name) {
        String str1 = "BIRCH   : Right! So you're " + name + " who's moving to my hometown of LITTLEROOT. I get it now!\nBIRCH   : All right, are you ready?\n";
        typeWriterEffect1(str1);
    }

    private void choosePartner() {
        String s1 = "BIRCH  : Now, you can choose one of the Pokemon. \n\n";
        typeWriterEffect1(s1);
        // Wait for the typewriter effect to finish before showing the option dialog
        synchronized (this) {
            try {
                this.wait();  // Wait for ENTER key press to proceed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String[] options = {"Bulbasaur", "Charmander", "Squirtle"};
        int choice = JOptionPane.showOptionDialog(this, s1, "Choose your Pokemon",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        Pokemon chosenPokemon;
        switch (choice) {
            case 0:
                chosenPokemon = new Pokemon("Bulbasaur", new String[]{"Grass", "Poison"}, 45, 49, 49, 45, "Vine Whip", "Grass", 100, "Venoshock", "Poison", 60);
                break;
            case 1:
                chosenPokemon = new Pokemon("Charmander", new String[]{"Fire"}, 39, 52, 43, 65, "Scratch", "Normal", 40, "Ember", "Fire", 65);
                break;
            case 2:
                chosenPokemon = new Pokemon("Squirtle", new String[]{"Water"}, 44, 48, 65, 43, "Tackle", "Normal", 40, "Water Pulse", "Water", 65);
                break;
            default:
                chosenPokemon = new Pokemon("Bulbasaur", new String[]{"Grass", "Poison"}, 45, 49, 49, 45, "Vine Whip", "Grass", 100, "Venoshock", "Poison", 60);
                break;
        }

        // Show the chosen Pokémon in a JOptionPane message dialog
        JOptionPane.showMessageDialog(this, "You have chosen " + chosenPokemon.getName() + " as your partner!");
        scriptStage++;
        displayNextScript(); // Proceed to the next stage
    }

    public void selectPokemon(String pokemonName) {
        Pokemon chosenPokemon;
        switch (pokemonName) {
            case "Bulbasaur":
                chosenPokemon = new Pokemon("Bulbasaur", new String[]{"Grass", "Poison"}, 45, 49, 49, 45, "Vine Whip", "Grass", 100, "Venoshock", "Poison", 60);
                break;
            case "Charmander":
                chosenPokemon = new Pokemon("Charmander", new String[]{"Fire"}, 39, 52, 43, 65, "Scratch", "Normal", 40, "Ember", "Fire", 65);
                break;
            case "Squirtle":
                chosenPokemon = new Pokemon("Squirtle", new String[]{"Water"}, 44, 48, 65, 43, "Tackle", "Normal", 40, "Water Pulse", "Water", 65);
                break;
            default:
                chosenPokemon = new Pokemon("Bulbasaur", new String[]{"Grass", "Poison"}, 45, 49, 49, 45, "Vine Whip", "Grass", 100, "Venoshock", "Poison", 60);
                break;
        }

        // Show the chosen Pokémon in a JOptionPane message dialog
        JOptionPane.showMessageDialog(this, "You have chosen " + chosenPokemon.getName() + " as your partner!");

        scriptStage++;
        displayNextScript(); // Proceed to the next stage
    }

    private void healthCareScript() {
        String s1 = """
                Nurse Joy   : Welcome to our POKEMON CENTER!\n
                Nurse Joy   : We can heal your POKEMON to perfect health.\n
                Nurse Joy   : Okay, I'll take your POKEMON for a few seconds.\n
                .
                ..
                ...
                ....!
                Nurse Joy   : Thank you for waiting. We hope to see you again!
                """;
        typeWriterEffect2(s1);
    }

    private void typeWriterEffect1(String str) {
        new Thread(() -> {
            CountDownLatch latch = new CountDownLatch(1);
            char[] c = str.toCharArray();
            for (char value : c) {
                try {
                    SwingUtilities.invokeAndWait(() -> scriptTextArea.append(String.valueOf(value)));
                    Thread.sleep(50); // Adjust delay for readability
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SwingUtilities.invokeLater(() -> {
                scriptTextArea.append("\n[ Press ENTER to continue...]\n");
                textFullyDisplayed = true;
                latch.countDown();
            });
            try {
                latch.await();
                synchronized (ScriptGUI.this) {
                    ScriptGUI.this.wait();  // Wait for ENTER key press
                }
                textFullyDisplayed = false;
                displayNextScript();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void typeWriterEffect2(String str) {
        new Thread(() -> {
            char[] c = str.toCharArray();
            for (char value : c) {
                try {
                    SwingUtilities.invokeAndWait(() -> scriptTextArea.append(String.valueOf(value)));
                    Thread.sleep(50); // Adjust delay for readability
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScriptGUI("Ash"));
    }
}