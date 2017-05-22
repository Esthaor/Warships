package pl.gregiem.warships.gui;

import pl.gregiem.warships.engine.HumanPlayer;
import pl.gregiem.warships.enums.Mode;
import pl.gregiem.warships.Start;
import pl.gregiem.warships.ai.Hard;
import pl.gregiem.warships.ai.Medium;
import pl.gregiem.warships.ai.Easy;
import pl.gregiem.warships.ai.UltraHard;
import java.awt.event.ActionEvent;

public class SingleplayerMenu extends Menu {

    private static String[] buttons = {"EASY", "MEDIUM", "HARD", "ULTRA HARD", "Back"};
    private static String[] textFields = {};

    public SingleplayerMenu() {
        super(buttons, textFields);
        EventHandler menuHandler = new MenuEventHandler();
        for (int i = 0; i < 5; i++)
            menuButtonList[i].addActionListener(menuHandler);
    }

    class MenuEventHandler extends Menu.EventHandler {

        public void actionPerformed(ActionEvent event) {
            Start.currentMenu.menuPanel.setVisible(false);
            if (event.getSource() == Menu.menuButtonList[0]) {
                Start.mode = Mode.singleEasy;
                Start.playerTwo = new Easy(10);
                Start.playerOne = new HumanPlayer(10);

            } else if (event.getSource() == Menu.menuButtonList[1]) {
                Start.mode = Mode.singleMedium;
                Start.playerTwo = new Medium(10);
                Start.playerOne = new HumanPlayer(10);
            } else if (event.getSource() == Menu.menuButtonList[2]) {
                Start.mode = Mode.singleHard;
                Start.playerTwo = new Hard(10);
                Start.playerOne = new HumanPlayer(10);
            } else if (event.getSource() == Menu.menuButtonList[3]) {
                Start.mode = Mode.singleUltra;
                Start.playerTwo = new UltraHard(10);
                Start.playerOne = new HumanPlayer(10);
            } else if (event.getSource() == Menu.menuButtonList[4])
                Start.currentMenu = new MainMenu();

            Start.mainFrame.repaint();
        }
    }

}
