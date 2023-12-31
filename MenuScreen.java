import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuScreen extends JFrame
{
    // components
    private JPanel menuPanel;
    private JPanel rulesPanel;
    private JLabel welcome;
    private JLabel rules1;
    private JLabel rules2;
    private JButton start;
    private JButton exit;
    private ImageIcon hangmanLogo;


    // constants
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final String RULES_1 = "Guess the word before six mistakes to win! ";
    private final String RULES_2 = "Please enter only lower case letters.";
    private final GridLayout GRID_LAYOUT = new GridLayout(0, 2);


    /**
     * Constructor for class MenuScreen.
     */
    public MenuScreen()
    {
        setTitle("Menu Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildMenuPanel();
        buildRulesPanel();
        add(menuPanel);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    } // constructor


    /**
     * Creates the JPanel menuPanel.
     */
    public void buildMenuPanel()
    {
        menuPanel = new JPanel();
        menuPanel.setLayout(GRID_LAYOUT);
        menuPanel.setBackground(Color.LIGHT_GRAY);

        welcome = new JLabel("Welcome to Hangman", SwingConstants.CENTER);
        welcome.setFont(new Font("Monospaced", Font.BOLD, 25));
        menuPanel.add(welcome);

        hangmanLogo = new ImageIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\Hangmanlogo.png").getImage().getScaledInstance(315, 315, Image.SCALE_DEFAULT));
        menuPanel.add(new JLabel(hangmanLogo));

        start = new JButton("Start");
        start.setBackground(Color.GREEN);
        start.setForeground(Color.BLACK);
        start.setFont(new Font("Monospaced", Font.BOLD, 25));
        start.addActionListener(new ButtonListener());
        menuPanel.add(start);

        exit = new JButton("Exit");
        exit.setBackground(Color.RED);
        exit.setForeground(Color.BLACK);
        exit.setFont(new Font("Monospaced", Font.BOLD, 25));
        exit.addActionListener(new ButtonListener());
        menuPanel.add(exit);
    } // method buildMenuPanel


    /**
     * Creates the JPanel rulesPanel.
     */
    public void buildRulesPanel()
    {
        rulesPanel = new JPanel();
        rulesPanel.setLayout(new BorderLayout());
        rules1 = new JLabel(RULES_1);
        rules1.setFont(new Font("Monospaced", Font.BOLD, 18));
        rules2 = new JLabel(RULES_2);
        rules2.setFont(new Font("Monospaced", Font.BOLD, 18));
        rulesPanel.add(rules1, BorderLayout.NORTH);
        rulesPanel.add(rules2, BorderLayout.SOUTH);
    } // method buildRulesPanel


    private class ButtonListener implements ActionListener
    {


        /**
         * Invoked when an action is performed.
         *
         * @param e the event to be processed.
         */
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == start)
            {
                menuPanel.removeAll();
                GameScreen gs = new GameScreen();
                JOptionPane.showMessageDialog(null, rulesPanel, "Rules", JOptionPane.PLAIN_MESSAGE);
            }
            if (e.getSource() == exit)
            {
                System.exit(0);
            }
        } // method actionPerformed
    } // class ButtonListener


    public static void main(String[] args)
    {
        MenuScreen ms = new MenuScreen();
    } // method main
} // class MenuScreen