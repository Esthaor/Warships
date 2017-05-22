package pl.gregiem.warships.engine;

import pl.gregiem.warships.gui.MainMenu;
import pl.gregiem.warships.enums.Mode;
import pl.gregiem.warships.Start;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Klient {
    public Socket socket;
    private int port;
    private String ip;

    public Klient() {
        ip = Start.currentMenu.textFieldList[0].getText();
        port = Integer.parseInt(Start.currentMenu.textFieldList[1].getText());
        Start.mode = Mode.onlineClient;
        try {
            socket = new Socket(ip, port);
            JOptionPane.showConfirmDialog(Start.mainFrame, "Connected with opponent!",
                                          "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

            Start.playerOne = (Player) new HumanPlayer(10);
        } catch (IOException e) {
            closeKlientConnection();
        }
    }

    public void closeKlientConnection() {
        JOptionPane.showConfirmDialog(Start.mainFrame, "Unable to connect with opponent!",
                                      "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

        Start.currentMenu = new MainMenu();
        Start.mainFrame.repaint();
    }
}
