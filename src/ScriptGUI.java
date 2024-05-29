import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ScriptGUI extends JFrame {
    private JTextArea scriptTextArea;
    private int scriptStage = 0;
    private String trainerName;
    private HandingAbnormalInput inputChecker = new HandingAbnormalInput();

    public ScriptGUI(String trainerName) {
        this.trainerName = trainerName;
        setTitle("Pokemon Adventure");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Initialize components
        scriptTextArea = new JTextArea();
        scriptTextArea.setEditable(false);
        scriptTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(scriptTextArea);

        scriptTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    synchronized (ScriptGUI.this) {
                        ScriptGUI.this.notify();  // Notify the typewriter effect to proceed
                    }
                    e.consume();  // Prevent the newline character from being added to the JTextArea
                }
            }
        });

        // Layout setup
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Display the first part of the script
        displayNextScript();

        setVisible(true);
    }

    private void displayNextScript() {
        switch (scriptStage) {
            case 0:
                typeWriterEffect1("""
                    BRICH   : Hi! Sorry to keep you waiting!
                    BRICH   : Welcome to the world of POKEMON!
                    BRICH   : My name is BRICH! But everyone calls me the POKEMON PROF!\n
                    """);
                break;
            case 1:
                typeWriterEffect1("""
                    BRICH  : This world is inhabited by creatures known as POKEMON! We
                             humans live alongside POKEMON, at times as friendly playmates,
                             and at times as cooperative workmates.\n\n""");
                break;
            case 2:
                typeWriterEffect1("""
                    BRICH  : And sometimes, we band together and battle others like us.
                    BRICH  : But despite our closeness, we don't know everything about POKEMON.
                    BRICH  : In fact, there are many, many secrets surrounding POKEMON. To unravel
                             POKEMON mysteries, I've been undertaking research. That's what I do.\n\n""");
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
        String str1 = "BRICH   : Right! So you're " + name + " who's moving to my hometown of LITTLEROOT.\n I get it now!\n";
        typeWriterEffect1(str1);

        String str2 = "BRICH   : All right, are you ready?\n";
        typeWriterEffect1(str2);
    }

    private void choosePartner() {
        String s1 = "BRICH  : Now, you can choose one of the Pokemom. \n\n" ;
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
            char[] c = str.toCharArray();
            for (char value : c) {
                try {
                    SwingUtilities.invokeAndWait(() -> scriptTextArea.append(String.valueOf(value)));
                    Thread.sleep(50); // Adjust delay for readability
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SwingUtilities.invokeLater(() -> scriptTextArea.append("\n[ Press ENTER to continue...]\n"));
            synchronized (ScriptGUI.this) {
                try {
                    ScriptGUI.this.wait();  // Wait for ENTER key press
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                displayNextScript();
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
