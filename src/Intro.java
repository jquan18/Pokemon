import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Intro extends JFrame {
    private JButton registerButton;
    private JButton loginButton;

    public Intro() {
        setTitle("Choose Your Action");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("C:/Users/SCSM11/IdeaProjects/Pokemon/src/res/background.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);

        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));

        // Set the bounds for the background image
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Create a label for "Choose your action"
        JLabel chooseActionLabel = new JLabel("Choose Your Action:");
        chooseActionLabel.setFont(new Font("Arial", Font.BOLD, 30));
        chooseActionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chooseActionLabel.setBounds(130, 280, 400, 50);
        chooseActionLabel.setForeground(Color.WHITE);

        // Create buttons
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");

        // Customize button appearance
        registerButton.setFont(new Font("Arial", Font.PLAIN, 30));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 30));

        // Set the bounds for the buttons
        registerButton.setBounds(230, 350, 200, 50);
        loginButton.setBounds(230, 500, 200, 50);

        // Add components to the layered pane
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(chooseActionLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(registerButton, JLayeredPane.MODAL_LAYER);
        layeredPane.add(loginButton, JLayeredPane.MODAL_LAYER);

        // Set the layered pane as the content pane
        setContentPane(layeredPane);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the "register" class
                register rg = new register(Intro.this);

                // Hide the current Intro GUI
                setVisible(false);

                // Show the register GUI
                rg.setVisible(true);
            }
        });
        setVisible(true);


registerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create an instance of the "register" class
            login lg = new login(Intro.this);

            // Hide the current Intro GUI
            setVisible(false);

            // Show the register GUI
            lg.setVisible(true);
        }
    });
    setVisible(true);

}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Intro();
            }
        });
    }
}