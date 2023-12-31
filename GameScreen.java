import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLWarning;
import java.util.Random;

public class GameScreen extends JFrame
{
    // components
    private JPanel gamePanel;
    private JPanel finalWordPanel;
    private JPanel correctPanel;
    private JPanel incorrectPanel;
    private JPanel lowercaseErrorPanel;
    private JPanel uniqueErrorPanel;
    private JLabel underScores;
    private JLabel headLabel;
    private JLabel bodyLabel;
    private JLabel leftArmLabel;
    private JLabel rightArmLabel;
    private JLabel leftLegLabel;
    private JLabel rightLegLabel;
    private JLabel finalWord;
    private JLabel correct;
    private JLabel incorrect;
    private JLabel lowercase;
    private JLabel unique;
    private JButton guess;
    private JTextField letter;
    private ImageIcon head;
    private ImageIcon body;
    private ImageIcon leftArm;
    private ImageIcon rightArm;
    private ImageIcon leftLeg;
    private ImageIcon rightLeg;


    // constants
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final String LOWER_CASE_ERROR = "Please enter a lower case letter.";
    private final String UNIQUE_ERROR = "You already guessed that letter.";
    private final String CORRECT_GUESS = "Correct!";
    private final String INCORRECT_GUESS = "Incorrect!";
    private final String[] RANDOM_WORDS = {"language", "story", "sell", "options", "experience", "rates",
            "create", "key", "body", "young", "america", "important", "field", "few", "east", "paper",
            "single", "age", "activities", "club", "example", "girls", "additional", "password",
            "latest", "something", "road", "gift", "question", "changes", "night", "hard", "texas", "pay",
            "four", "poker", "status", "browse", "issue", "range", "building", "seller", "court", "february",
            "always", "result", "audio", "light", "write", "war", "offer", "blue", "groups", "easy",
            "given", "files", "event", "release", "analysis"};
    private final int NUM_WORDS = RANDOM_WORDS.length;
    private final GridLayout GRID_LAYOUT = new GridLayout(0, 4);


    // fields
    String userInput = "";
    String guessedLetters = "";
    String randomWord = generateWord(RANDOM_WORDS);
    int incorrectCounter = 0;


    /**
     * Constructor for class GameScreen.
     */
    public GameScreen()
    {
        setTitle("Game Screen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildGamePanel();
        buildFinalWordPanel();
        buildCorrectPanel();
        buildIncorrectPanel();
        buildLowercaseErrorPanel();
        buildUniqueErrorPanel();
        add(gamePanel);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    } // constructor


    /**
     * Creates the JPanel gamePanel.
     */
    public void buildGamePanel()
    {
        gamePanel = new JPanel();
        gamePanel.setLayout(GRID_LAYOUT);
        gamePanel.setBackground(Color.LIGHT_GRAY);

        letter = new JTextField();
        letter.setHorizontalAlignment(JTextField.CENTER);
        letter.setFont(new Font("Monospaced", Font.BOLD, 25));
        gamePanel.add(letter);

        guess = new JButton("Guess");
        guess.setBackground(Color.GREEN);
        guess.setForeground(Color.BLACK);
        guess.setFont(new Font("Monospaced", Font.BOLD, 25));
        guess.addActionListener(new GameScreen.ButtonListener());
        gamePanel.add(guess);

        head = new ImageIcon(new ImageIcon("C:\\Users\\HP\\Desktop\\CS\\Hangman\\head7greybackgrnd2.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        headLabel = new JLabel(head);
        headLabel.setVisible(false);
        gamePanel.add(headLabel);

        gamePanel.add(new JLabel());

        underScores = new JLabel(buildUnderscores(), SwingConstants.CENTER);
        underScores.setFont(new Font("Monospaced", Font.BOLD, 15));
        gamePanel.add(underScores);

        leftArm = new ImageIcon(new ImageIcon("C:\\Users\\HP\\Desktop\\CS\\Hangman\\leftarm.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        leftArmLabel = new JLabel(leftArm);
        leftArmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gamePanel.add(leftArmLabel);
        leftArmLabel.setVisible(false);

        body = new ImageIcon(new ImageIcon("C:\\Users\\HP\\Desktop\\CS\\Hangman\\body6.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        bodyLabel = new JLabel(body);
        gamePanel.add(bodyLabel);
        bodyLabel.setVisible(false);

        rightArm = new ImageIcon(new ImageIcon("C:\\Users\\HP\\Desktop\\CS\\Hangman\\rightarm.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        rightArmLabel = new JLabel(rightArm);
        rightArmLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gamePanel.add(rightArmLabel);
        rightArmLabel.setVisible(false);

        gamePanel.add(new JLabel());

        leftLeg = new ImageIcon(new ImageIcon("C:\\Users\\HP\\Desktop\\CS\\Hangman\\leftleg.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        leftLegLabel = new JLabel(leftLeg);
        leftLegLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gamePanel.add(leftLegLabel);
        leftLegLabel.setVisible(false);

        gamePanel.add(new JLabel());

        rightLeg = new ImageIcon(new ImageIcon("C:\\Users\\HP\\Desktop\\CS\\Hangman\\rightleg.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        rightLegLabel = new JLabel(rightLeg);
        rightLegLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gamePanel.add(rightLegLabel);
        rightLegLabel.setVisible(false);
    } // method buildGamePanel


    /**
     * Creates the JPanel finalWordPanel.
     */
    public void buildFinalWordPanel()
    {
        finalWordPanel = new JPanel();
        finalWord = new JLabel("The word was: " + randomWord);
        finalWord.setFont(new Font("Monospaced", Font.BOLD, 18));
        finalWordPanel.add(finalWord, SwingConstants.CENTER);
    } // method buildFinalWordPanel


    /**
     * Creates the JPanel correctPanel.
     */
    public void buildCorrectPanel()
    {
        correctPanel = new JPanel();
        correct = new JLabel(CORRECT_GUESS);
        correct.setFont(new Font("Monospaced", Font.BOLD, 18));
        correctPanel.add(correct, SwingConstants.CENTER);
    } // method buildCorrectPanel


    /**
     * Creates the JPanel incorrectPanel.
     */
    public void buildIncorrectPanel()
    {
        incorrectPanel = new JPanel();
        incorrect = new JLabel(INCORRECT_GUESS);
        incorrect.setFont(new Font("Monospaced", Font.BOLD, 18));
        incorrectPanel.add(incorrect, SwingConstants.CENTER);
    } // method buildIncorrectPanel


    /**
     * Creates the JPanel lowercaseErrorPanel
     */
    public void buildLowercaseErrorPanel()
    {
        lowercaseErrorPanel = new JPanel();
        lowercase = new JLabel(LOWER_CASE_ERROR);
        lowercase.setFont(new Font("Monospaced", Font.BOLD, 18));
        lowercaseErrorPanel.add(lowercase, SwingConstants.CENTER);
    } // method buildLowercaseErrorPanel


    /**
     * Creates the JPanel uniqueErrorPanel
     */
    public void buildUniqueErrorPanel()
    {
        uniqueErrorPanel = new JPanel();
        unique = new JLabel(UNIQUE_ERROR);
        unique.setFont(new Font("Monospaced", Font.BOLD, 18));
        uniqueErrorPanel.add(unique, SwingConstants.CENTER);
    } // method buildUniqueErrorPanel


    /**
     * Randomly selects a word from the constant RANDOM_WORDS.
     *
     * @param randWords the list of words to select from.
     *
     * @return the randomly selected word of this method.
     */
    public String generateWord(String[] randWords)
    {
        randWords = RANDOM_WORDS;
        Random rand = new Random();
        int randNumber = rand.nextInt(NUM_WORDS);
        return randWords[randNumber];
    } // method generateWords


    /**
     * Constructs the underScores of the String randomWord.
     *
     * @return the underScores of the String randomWord.
     */
    public String buildUnderscores()
    {
        String underScores = "";
        for (int i = 0; i < randomWord.length(); i++)
        {
            underScores += "_ ";
        }
        return underScores;
    } // method buildUnderscores


    /**
     * Updates the underScores after each guess by the user.
     *
     * @param letter the letter that is inputted by the user.
     *
     * @param word the randomly generated word.
     *
     * @param prevUnderScores the previous underScores, the state of the underScores before each guess by the user.
     */
    public void updateUnderscores(String letter, String word, String prevUnderScores)
    {
        int[] indexes = new int[word.length()];
        int j = 0;
        for (int i = 0; i < word.length(); i++)
        {
            if (word.charAt(i) == letter.charAt(0))
            {
                indexes[j] = i + i;
                j++;
            }
        }
        j = 0;
        StringBuilder sb = new StringBuilder(prevUnderScores);
        for (int i = 0; i < sb.length(); i++)
        {
            if (i == indexes[j])
            {
                sb.setCharAt(i, letter.charAt(0));
                j++;
            }
        }
        underScores.setText(sb.toString());
    } // method updateUnderscores


    /**
     * Indicates weather the String underScores contains underscores -> ( _ )
     *
     * @param underScores the underScores of the String randomWord.
     *
     * @return true if the String underScores contains underscores; false otherwise.
     */
    public boolean checkUnderscores(String underScores)
    {
        for (int i = 0; i < underScores.length(); i++)
        {
            if (underScores.charAt(i) == '_')
            {
                return true;
            }
        }
        return false;
    } // method checkUnderscores


    /**
     * Indicates weather the user inputted an alphabetical lowercase letter.
     *
     * @param letter the letter that is inputted by the user.
     *
     * @return true if the String letter is an alphabetical lowercase letter; false otherwise.
     */
    public boolean isLowercaseLetter(String letter)
    {
        return (Character.isLowerCase(letter.charAt(0)) && letter.length() == 1);
    } // method isLowercaseLetter


    /**
     * Indicates weather the user input is different from before.
     *
     * @param letter the letter that is inputted by the user.
     *
     * @param gl a String of every input by the user.
     *
     * @return true if the String gl does not contain the String letter; false otherwise.
     */
    public boolean isUnique(String letter, String gl)
    {
        return (!gl.contains(letter));
    } // method isUnique


    /**
     * Indicates weather the user input is correct.
     *
     * @param letter the letter that is inputted by the user.
     *
     * @return true if the String randomWord contains the String letter; false otherwise.
     */
    public boolean isCorrect(String letter)
    {
        return (randomWord.contains(letter));
    } // method isCorrect


    private class ButtonListener implements ActionListener
    {


        /**
         * Invoked when an action is performed.
         *
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == guess)
            {
                userInput = letter.getText();
                if (!isLowercaseLetter(userInput))
                {
                    JOptionPane.showMessageDialog(null, lowercaseErrorPanel, "Lowercase Error", JOptionPane.PLAIN_MESSAGE);
                }
                else if (!isUnique(userInput, guessedLetters))
                {
                    JOptionPane.showMessageDialog(null, uniqueErrorPanel, "Unique Error", JOptionPane.PLAIN_MESSAGE);
                }
                else if (isCorrect(userInput))
                {
                    JOptionPane.showMessageDialog(null, correctPanel, "Correct Guess", JOptionPane.PLAIN_MESSAGE);
                    updateUnderscores(userInput, randomWord, underScores.getText());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, incorrectPanel, "Incorrect Guess", JOptionPane.PLAIN_MESSAGE);
                    incorrectCounter++;
                    switch (incorrectCounter)
                    {
                        case 1:
                            headLabel.setVisible(true);
                            break;
                        case 2:
                            bodyLabel.setVisible(true);
                            break;
                        case 3:
                            leftArmLabel.setVisible(true);
                            break;
                        case 4:
                            rightArmLabel.setVisible(true);
                            break;
                        case 5:
                            leftLegLabel.setVisible(true);
                            break;
                        case 6:
                            rightLegLabel.setVisible(true);
                            JOptionPane.showMessageDialog(null, finalWordPanel, "Final Word", JOptionPane.PLAIN_MESSAGE);
                            gamePanel.removeAll();
                            LoseScreen ls = new LoseScreen();
                            break;
                    }
                }
                guessedLetters += userInput;
                letter.setText("");
                if (!checkUnderscores(underScores.getText()))
                {
                    gamePanel.removeAll();
                    WinScreen ws = new WinScreen();
                }
            }
        } // method actionPerformed
    } // class ButtonListener
} // class GameScreen
