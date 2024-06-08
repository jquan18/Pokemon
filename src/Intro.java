import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Intro extends JFrame {
    private JButton registerButton;
    private JButton loginButton;

    public Intro(JFrame parent, Dimension size) {
        setTitle("Choose Your Action");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2000, 1800);
        setLocationRelativeTo(null);

        // Load the background image
		String currentWorkingDir = System.getProperty("user.dir");
        ImageIcon backgroundImage = new ImageIcon(currentWorkingDir + "/src/res/GUI usage/background.jpg");
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
        chooseActionLabel.setBounds(120, 270, 400, 50);
        chooseActionLabel.setForeground(Color.WHITE);

        // Create buttons
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");

        // Customize button appearance
        registerButton.setFont(new Font("Arial", Font.PLAIN, 30));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 30));

        // Set the bounds for the buttons
        registerButton.setBounds(210, 385, 200, 50);
        loginButton.setBounds(210, 490, 200, 50);

        // Add components to the layered pane
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(chooseActionLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(registerButton, JLayeredPane.MODAL_LAYER);
        layeredPane.add(loginButton, JLayeredPane.MODAL_LAYER);

        // Add a label for the "No, I would like to leave" option
        JLabel leaveLabel = new JLabel("No, I would like to leave");
        leaveLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        leaveLabel.setForeground(Color.WHITE);
        leaveLabel.setBounds(210, 600, 480, 30);
        leaveLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovering over label

        // Add a MouseListener to the leaveLabel
        leaveLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                leaveMousePressed(e);
            }
        });

        // Add the leaveLabel to the layered pane
        layeredPane.add(leaveLabel, JLayeredPane.MODAL_LAYER);

        // Set the layered pane as the content pane
        setContentPane(layeredPane);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the "register" class
                register rg = new register(Intro.this, getSize());

                // Hide the current Intro GUI
                setVisible(false);

                // Show the register GUI
                rg.setVisible(true);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the "login" class
                login lg = new login(Intro.this, getSize());

                // Hide the current Intro GUI
                setVisible(false);

                // Show the login GUI
                lg.setVisible(true);
            }
        });

        setVisible(true);
    }

    private void leaveMousePressed(MouseEvent evt) {
        JOptionPane.showMessageDialog(this, "Thank you!\nHave A Nice Day!");
        this.dispose(); // Dispose the Intro GUI
    }


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Intro();
//            }
//        });
//    }
}
