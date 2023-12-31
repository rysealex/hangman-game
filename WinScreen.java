import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WinScreen extends JFrame
{
    // components
    private JPanel winPanel;
    private JLabel winner;
    private JButton menu;


    // constants
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final GridLayout GRID_LAYOUT = new GridLayout(2, 0);


    /**
     * Constructor for class WinScreen.
     */
    public WinScreen()
    {
        setTitle("Win Screen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildWinPanel();
        add(winPanel);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    } // constructor


    /**
     * Creates the JPanel winPanel.
     */
    public void buildWinPanel()
    {
        winPanel = new JPanel();
        winPanel.setLayout(GRID_LAYOUT);
        winPanel.setBackground(Color.GREEN);

        winner = new JLabel("You Win!", SwingConstants.CENTER);
        winner.setFont(new Font("Monospaced", Font.BOLD, 25));
        winPanel.add(winner);

        menu = new JButton("Menu");
        menu.setBackground(Color.LIGHT_GRAY);
        menu.setForeground(Color.BLACK);
        menu.setFont(new Font("Monospaced", Font.BOLD, 25));
        menu.addActionListener(new WinScreen.ButtonListener());
        winPanel.add(menu);
    } // method buildWinPanel


    private class ButtonListener implements ActionListener
    {
        /**
         * Invoked when an action is performed.
         *
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e)
        {
            winPanel.removeAll();
            MenuScreen ms = new MenuScreen();
        } // method actionPerformed
    } // class ButtonListener
} // class WinScreen
