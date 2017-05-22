package pl.gregiem.warships.engine;

import pl.gregiem.warships.gui.MainMenu;
import pl.gregiem.warships.enums.Mode;
import pl.gregiem.warships.Start;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Server {
    private ServerSocket serverSocket;
    public Socket socket;
    private int port;

    public Server() {
        port = Integer.parseInt(Start.currentMenu.textFieldList[0].getText());
        Start.mode = Mode.onlineSerwer;
        initializeServer();
        try {
            if (serverSocket != null) {
                socket = serverSocket.accept();
                JOptionPane.showConfirmDialog(Start.mainFrame, "Connected with opponent!",
                                              "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

                Start.playerOne = (Player) new HumanPlayer(10);

            }
        } catch (NullPointerException | IOException e) {
            closeSerwerConnection();
        }
    }

    private void initializeServer() {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeSerwerConnection() {
        try {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
        }
        JOptionPane.showConfirmDialog(Start.mainFrame, "Unable to connect with opponent!",
                                      "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

        Start.currentMenu = new MainMenu();
        Start.mainFrame.repaint();
    }
}
