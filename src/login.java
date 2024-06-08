import javax.swing.*;
import java.awt.*;


public class login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;
    private JButton backButton;
    private Intro intro;
	private SaveGame saveGame;

    public login(Intro parent, Dimension size) {
		String currentWorkingDir = System.getProperty("user.dir");
        this.intro = parent;
        setTitle("login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size); // Set the size based on Intro frame size
        setLocationRelativeTo(parent);

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon(currentWorkingDir + "/src/res/GUI usage/loginPage.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);

        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));

        // Set the bounds for the background image
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Initialize components
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false); // Make the panel transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Icon
        JLabel iconLabel = new JLabel(new ImageIcon(currentWorkingDir + "/src/res/GUI usage/pokemon-trainer.png"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        formPanel.add(iconLabel, gbc);

        // Title
        JLabel titleLabel = new JLabel("Login Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 55));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(usernameLabel, gbc);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 25));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Show Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        showPasswordCheckBox.setForeground(Color.WHITE);
        showPasswordCheckBox.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(showPasswordCheckBox, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(loginButton, gbc);

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 1;
        formPanel.add(backButton, gbc);

        // Add ActionListener to the showPasswordCheckBox
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('\u2022'); // Hide password
            }
        });

        // Add ActionListener to the login button
        loginButton.addActionListener(e -> {
            // Perform login logic here
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());


            // Check if either username or password fields are blank
            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(login.this, "Username and password cannot be blank.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Login logic
                saveGame = new SaveGame();
                if (saveGame.loginGUI(username, password)) {
                    JOptionPane.showMessageDialog(login.this, "Login successful!\nWelcome " + username);
					saveGame.gameMenu(username);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(login.this, "Login failed: Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add ActionListener to the back button
        backButton.addActionListener(e -> {
            intro.setVisible(true); // Show the Intro GUI
            dispose(); // Dispose the login GUI
        });

        // Set bounds for the form panel
        formPanel.setBounds(780, 48, 600, 700);

        // Add components to the layered pane
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(formPanel, JLayeredPane.PALETTE_LAYER);

        // Set the layered pane as the content pane
        setContentPane(layeredPane);

        // Set frame visible
        setVisible(true);
    }

//public static void main(String[] args) {
//    SwingUtilities.invokeLater(new Runnable() {
//        @Override
//        public void run() {
//            new login(null, new Dimension(800, 600));
//        }
//    });
//}
}
