package pl.gregiem.warships.gui;

import pl.gregiem.warships.engine.HumanPlayer;
import pl.gregiem.warships.enums.Mode;
import pl.gregiem.warships.Start;
import java.awt.event.ActionEvent;

public class HotSeatMenu extends Menu {

    private static String[] buttons = {"Start Game", "Back"};
    private static String[] textFields = {"1st Player Name", "2nd Player Name"};

    public HotSeatMenu() {
        super(buttons, textFields);
        EventHandler menuHandler = new MenuEventHandler();
        for (int i = 0; i < 2; i++)
            menuButtonList[i].addActionListener(menuHandler);
    }

    class MenuEventHandler extends Menu.EventHandler {

        public void actionPerformed(ActionEvent event) {
            Start.currentMenu.menuPanel.setVisible(false);
            if (event.getSource() == Menu.menuButtonList[0]) {
                Start.mode = Mode.hotSeat1;
                Start.playerOne = new HumanPlayer(10);

            } else if (event.getSource() == Menu.menuButtonList[1])
                Start.currentMenu = new MultiplayerMenu();

            Start.mainFrame.invalidate();
            Start.mainFrame.validate();
        }
    }

}
