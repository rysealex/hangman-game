import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LoseScreen extends JFrame
{
    // components
    private JPanel losePanel;
    private JLabel loser;
    private JButton menu;


    // constants
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final GridLayout GRID_LAYOUT = new GridLayout(2, 0);


    /**
     * Constructor for class LoseScreen.
     */
    public LoseScreen()
    {
        setTitle("Lose Screen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildLosePanel();
        add(losePanel);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    } // constructor


    /**
     * Creates the JPanel losePanel.
     */
    public void buildLosePanel()
    {
        losePanel = new JPanel();
        losePanel.setLayout(GRID_LAYOUT);
        losePanel.setBackground(Color.RED);

        loser = new JLabel("You Lose!", SwingConstants.CENTER);
        loser.setFont(new Font("Monospaced", Font.BOLD, 25));
        losePanel.add(loser);

        menu = new JButton("Menu");
        menu.setBackground(Color.LIGHT_GRAY);
        menu.setForeground(Color.BLACK);
        menu.setFont(new Font("Monospaced", Font.BOLD, 25));
        menu.addActionListener(new LoseScreen.ButtonListener());
        losePanel.add(menu);
    } // method buildLosePanel


    private class ButtonListener implements ActionListener
    {


        /**
         * Invoked when an action is performed.
         *
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e)
        {
            losePanel.removeAll();
            MenuScreen ms = new MenuScreen();
        } // method actionPerformed
    } // class ButtonListener
} // class LoseScreen
