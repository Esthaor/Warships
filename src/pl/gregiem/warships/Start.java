package pl.gregiem.warships;

import pl.gregiem.warships.engine.NewGame;
import pl.gregiem.warships.engine.ChangeView;
import pl.gregiem.warships.engine.Klient;
import pl.gregiem.warships.engine.Server;
import pl.gregiem.warships.gui.Menu;
import pl.gregiem.warships.gui.MainMenu;
import pl.gregiem.warships.engine.Player;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import pl.gregiem.warships.enums.Mode;

public class Start {

    public static JPanel firstPanel;
    public static JPanel secondPanel;
    public static JPanel thirdPanel;
    public static JPanel fourthPanel;
    public static JFrame mainFrame;
    public static Menu currentMenu;
    public static int tinyShipsCount = 4;
    public static int smallShipsCount = 3;
    public static int mediumShipsCount = 2;
    public static int bigShipsCount = 1;
    public static Player playerOne;
    public static Player playerTwo;
    public static Mode mode;
    public static NewGame currentGame;
    public static ChangeView changer;
    public static boolean myTurn;
    public static Server serwer;
    public static Klient klient;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame = new JFrame("BattleShips");
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setMinimumSize(new Dimension(1024, 600));
                mainFrame.setResizable(true);
                mainFrame.setVisible(true);

                currentMenu = new MainMenu();
            }
        });

    }

}
