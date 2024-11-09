import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeawayReservationForm extends JFrame {
    private JTextField nameField, emailField, phoneField, departurePortField, destinationPortField, dateField, passengersField, specialRequirementsField;
    private JComboBox<String> cabinPreferenceCombo, paymentMethodCombo;

    public SeawayReservationForm() {
        setTitle("Seaway Reservation Registration Form");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set font for labels and text fields
        Font font = new Font("Roman", Font.ITALIC, 14);

        // Create form fields
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        departurePortField = new JTextField(20);
        destinationPortField = new JTextField(20);
        dateField = new JTextField(20);
        passengersField = new JTextField(20);
        specialRequirementsField = new JTextField(20);

        // Set font for text fields
        nameField.setFont(font);
        emailField.setFont(font);
        phoneField.setFont(font);
        departurePortField.setFont(font);
        destinationPortField.setFont(font);
        dateField.setFont(font);
        passengersField.setFont(font);
        specialRequirementsField.setFont(font);

        String[] cabinOptions = { "Economy", "Business", "First Class", "Suite" };
        cabinPreferenceCombo = new JComboBox<>(cabinOptions);
        cabinPreferenceCombo.setFont(font);

        String[] paymentOptions = { "Credit Card", "Debit Card", "PayPal", "Bank Transfer" };
        paymentMethodCombo = new JComboBox<>(paymentOptions);
        paymentMethodCombo.setFont(font);

        // Create a custom panel with a gradient background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.cyan, 0, getHeight(), Color.pink);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());

        // Add labels and fields to the form with GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(font);
        panel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(font);
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(font);
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel departurePortLabel = new JLabel("Departure Port:");
        departurePortLabel.setFont(font);
        panel.add(departurePortLabel, gbc);
        gbc.gridx = 1;
        panel.add(departurePortField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        JLabel destinationPortLabel = new JLabel("Destination Port:");
        destinationPortLabel.setFont(font);
        panel.add(destinationPortLabel, gbc);
        gbc.gridx = 1;
        panel.add(destinationPortField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        JLabel dateLabel = new JLabel("Date of Departure:");
        dateLabel.setFont(font);
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        panel.add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        JLabel passengersLabel = new JLabel("Number of Passengers:");
        passengersLabel.setFont(font);
        panel.add(passengersLabel, gbc);
        gbc.gridx = 1;
        panel.add(passengersField, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        JLabel cabinLabel = new JLabel("Cabin Preference:");
        cabinLabel.setFont(font);
        panel.add(cabinLabel, gbc);
        gbc.gridx = 1;
        panel.add(cabinPreferenceCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        JLabel specialRequirementsLabel = new JLabel("Special Requirements:");
        specialRequirementsLabel.setFont(font);
        panel.add(specialRequirementsLabel, gbc);
        gbc.gridx = 1;
        panel.add(specialRequirementsField, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        JLabel paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setFont(font);
        panel.add(paymentLabel, gbc);
        gbc.gridx = 1;
        panel.add(paymentMethodCombo, gbc);

        // Add submit button
        JButton submitButton = new JButton("Submit");
        gbc.gridx = 0; gbc.gridy = 10; gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collecting data
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String departurePort = departurePortField.getText();
                String destinationPort = destinationPortField.getText();
                String date = dateField.getText();
                String passengers = passengersField.getText();
                String cabinPreference = (String) cabinPreferenceCombo.getSelectedItem();
                String specialRequirements = specialRequirementsField.getText();
                String paymentMethod = (String) paymentMethodCombo.getSelectedItem();

                // Validate fields
                StringBuilder errorMessage = new StringBuilder("Please fill in the following fields:\n");
                boolean hasError = false;

                if (name.isEmpty()) {
                    errorMessage.append("- Full Name\n");
                    hasError = true;
                }
                
                if (email.isEmpty() || !isValidEmail(email)) {
                    errorMessage.append("- Valid Email Address (must contain '@' and a domain)\n");
                    hasError = true;
                }
                
                if (phone.isEmpty() || !isValidPhone(phone)) {
                    errorMessage.append("- Phone Number (must be exactly 10 digits)\n");
                    hasError = true;
                }
                
                if (departurePort.isEmpty()) {
                    errorMessage.append("- Departure Port\n");
                    hasError = true;
                }
                
                if (destinationPort.isEmpty()) {
                    errorMessage.append("- Destination Port\n");
                    hasError = true;
                }
                
                if (date.isEmpty()) {
                    errorMessage.append("- Date of Departure\n");
                    hasError = true;
                }
                
                if (passengers.isEmpty()) {
                    errorMessage.append("- Number of Passengers\n");
                    hasError = true;
                }
                
                if (specialRequirements.isEmpty()) {
                    errorMessage.append("- Special Requirements\n");
                    hasError = true;
                }

                // Show error messages if any validation fails
                if (hasError) {
                    JOptionPane.showMessageDialog(null, errorMessage.toString(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Displaying the collected data
                    String message = String.format("Reservation Details:\nName: %s\nEmail: %s\nPhone: %s\nDeparture Port: %s\nDestination Port: %s\nDate: %s\nPassengers: %s\nCabin: %s\nSpecial Requirements: %s\nPayment Method: %s",
                            name, email, phone, departurePort, destinationPort, date, passengers, cabinPreference, specialRequirements, paymentMethod);
                    JOptionPane.showMessageDialog(null, message, "Reservation Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Add the custom panel to the frame
        add(panel);
    }

    // Helper method to validate email
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.indexOf("@") < email.length() - 1 && email.indexOf(".") > email.indexOf("@") + 1;
    }

    // Helper method to validate phone number
    private boolean isValidPhone(String phone) {
        return phone.length() == 10 && phone.matches("\\d+");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SeawayReservationForm form = new SeawayReservationForm();
            form.setVisible(true);
        });
    }
}