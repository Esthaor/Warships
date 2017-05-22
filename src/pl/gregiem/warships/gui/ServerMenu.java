package pl.gregiem.warships.gui;

import pl.gregiem.warships.engine.Server;
import pl.gregiem.warships.enums.Mode;
import pl.gregiem.warships.Start;
import java.awt.event.ActionEvent;

public class ServerMenu extends Menu {

    private static String[] buttons = {"Connect", "Back"};
    private static String[] textFields = {"Port"};

    public ServerMenu() {
        super(buttons, textFields);
        EventHandler menuHandler = new MenuEventHandler();
        for (int i = 0; i < 2; i++)
            menuButtonList[i].addActionListener(menuHandler);
    }

    class MenuEventHandler extends Menu.EventHandler {

        public void actionPerformed(ActionEvent event) {
            Start.currentMenu.menuPanel.setVisible(false);
            if (event.getSource() == Menu.menuButtonList[0]) {
                Start.mode = Mode.onlineSerwer;
                Start.serwer = new Server();
            } else if (event.getSource() == Menu.menuButtonList[1])
                Start.currentMenu = new MultiplayerMenu();

            Start.mainFrame.invalidate();
            Start.mainFrame.validate();
        }
    }

}
