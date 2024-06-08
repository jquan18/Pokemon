import javax.swing.*;
import java.awt.*;

public class register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JCheckBox showPasswordCheckBox;
    private JButton submitButton;
    private JButton backButton;
    private Intro intro;

    public register(Intro parent, Dimension size) {
        this.intro = parent;
        setTitle("Account Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size); // Set the size based on Intro frame size
        setLocationRelativeTo(parent);

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Pokemon2\\src\\res\\GUI usage\\registerPage.jpg");
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
        JLabel iconLabel = new JLabel(new ImageIcon("C:\\Users\\User\\IdeaProjects\\Pokemon2\\src\\res\\GUI usage\\pokemon-trainer.png"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        formPanel.add(iconLabel, gbc);

        // Title
        JLabel titleLabel = new JLabel("Account Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 45));
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

        // Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 25));
        confirmPasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        // Show Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        showPasswordCheckBox.setForeground(Color.WHITE);
        showPasswordCheckBox.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(showPasswordCheckBox, gbc);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(submitButton, gbc);

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 1;
        formPanel.add(backButton, gbc);

        // Add ActionListener to the showPasswordCheckBox
        showPasswordCheckBox.addActionListener(e -> {
                    if (showPasswordCheckBox.isSelected()) {
                        passwordField.setEchoChar((char) 0); // Show password
                        confirmPasswordField.setEchoChar((char) 0); // Show confirm password
                    } else {
                        passwordField.setEchoChar('\u2022'); // Hide password
                        confirmPasswordField.setEchoChar('\u2022'); // Hide confirm password
                    }
        });

        // Add ActionListener to the submit button
        submitButton.addActionListener(e -> {
            // Perform registration logic here************************havent set requirements for register acc*****************
            String username=usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(register.this, "Registration successful!");
                new ScriptGUI(username);
                dispose();
            } else {
                JOptionPane.showMessageDialog(register.this, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add ActionListener to the back button
        backButton.addActionListener(e -> {
            intro.setVisible(true); // Show the Intro GUI
            dispose(); // Dispose the Register GUI
        });

        // Set bounds for the form panel
        formPanel.setBounds(95, 55, 600, 700);

    // Add components to the layered pane
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(formPanel, JLayeredPane.PALETTE_LAYER);

        // Set the layered pane as the content pane
        setContentPane(layeredPane);

        // Set frame visible
        setVisible(true);
    }
}


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new register(null);
//            }
//        });
//    }


