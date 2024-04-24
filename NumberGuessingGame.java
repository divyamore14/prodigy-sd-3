import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {

    private int secretNumber;
    private int attempts;
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JButton guessButton;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeGame();

        JPanel panel = new JPanel();
        JLabel guessLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        panel.add(guessLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(feedbackLabel);

        add(panel);
        setVisible(true);
    }

    private void initializeGame() {
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
        attempts = 0;
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess < secretNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (guess > secretNumber) {
                feedbackLabel.setText("Too high! Try again.");
            } else {
                JOptionPane.showMessageDialog(this, "Congratulations! You've guessed the number " + secretNumber + " correctly!\nIt took you " + attempts + " attempts to win the game.", "You Win!", JOptionPane.INFORMATION_MESSAGE);
                initializeGame();
                guessField.setText("");
                feedbackLabel.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessingGame::new);
    }
}
