package pl.gregiem.warships.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pl.gregiem.warships.basic.MyButton;
import pl.gregiem.warships.basic.Shoot;
import pl.gregiem.warships.gui.MainMenu;
import pl.gregiem.warships.enums.Mode;
import pl.gregiem.warships.enums.FieldState;
import pl.gregiem.warships.Start;

public class NewGame {
    protected JButton buttonMenu;
    protected int boardSize;
    protected JLabel[][] myLabels;
    protected JLabel[][] opponentLabels;
    protected MyButton[][] opponentButtons;
    protected MyButton[][] myButtons;
    protected JLabel leftShipLabel;
    protected int sunkShips;
    protected JLabel name;

    public NewGame() {
        this.sunkShips = 0;
        this.setPanels();
        this.boardSize = Start.playerOne.boardSize;
        myButtons = new MyButton[boardSize][boardSize];
        opponentButtons = new MyButton[boardSize][boardSize];
        myLabels = new JLabel[2][boardSize];
        opponentLabels = new JLabel[2][boardSize];

        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++) {
                myButtons[i][j] = new MyButton("  ");
                myButtons[i][j].setSize(myButtons[i][j].getPreferredSize());
                myButtons[i][j].setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                myButtons[i][j].setEnabled(false);

                opponentButtons[i][j] = new MyButton("  ");
                opponentButtons[i][j].setSize(opponentButtons[i][j].getPreferredSize());
                opponentButtons[i][j].setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                opponentButtons[i][j].row = i;
                opponentButtons[i][j].column = j;

                opponentButtons[i][j].addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent arg0) {
                        MyButton tempButton = (MyButton) arg0.getSource();
                        int row = tempButton.row;
                        int column = tempButton.column;
                        Shoot shoot;
                        if (Start.playerOne.opponentBoard.canIShootThere(row, column)) {
                            shoot = new Shoot(row, column);
                            shoot.setResult(Start.playerTwo.myBoard.resolveShoot(shoot));
                            Start.playerOne.uploadResult(shoot);

                            if (shoot.getResult() != FieldState.missed) {
                                if (Start.playerTwo.isDead()) {
                                    paintBoards();
                                    for (int i = 0; i < boardSize; i++)
                                        for (int j = 0; j < boardSize; j++)
                                            opponentButtons[i][j].setEnabled(false);
                                    JOptionPane.showMessageDialog(null, "YOU WON");
                                }
                            } else {
                                Start.playerTwo.shoot();
                                if (Start.playerOne.isDead()) {
                                    paintBoards();
                                    for (int i = 0; i < boardSize; i++)
                                        for (int j = 0; j < boardSize; j++)
                                            opponentButtons[i][j].setEnabled(false);
                                    JOptionPane.showMessageDialog(null, "YOU LOSE");
                                }
                            }
                            if (shoot.getResult() == FieldState.sunk)
                                sunkShips = sunkShips + 1;
                        }
                        paintBoards();
                    }

                });

                char letter = (char) (i + 'a');
                String string = new StringBuilder().append(letter).toString();

                myLabels[0][i] = new JLabel(string);
                myLabels[1][i] = new JLabel(new Integer(i + 1).toString());

                opponentLabels[0][i] = new JLabel(string);
                opponentLabels[1][i] = new JLabel(new Integer(i + 1).toString());
            }
        paintBoards();
    }

    protected void setPanels() {

        Start.firstPanel.setVisible(false);
        Start.secondPanel.setVisible(false);
        Start.thirdPanel.setVisible(false);
        Start.fourthPanel.setVisible(false);

        Start.firstPanel = new JPanel(new GridBagLayout());
        Start.secondPanel = new JPanel(new GridBagLayout());
        Start.thirdPanel = new JPanel(new GridBagLayout());
        Start.fourthPanel = new JPanel(new GridBagLayout());

        Start.firstPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        Start.secondPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        Start.thirdPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        Start.fourthPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        Start.mainFrame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints frameLayout = new GridBagConstraints();

        frameLayout.gridwidth = 2;
        frameLayout.fill = GridBagConstraints.BOTH;
        frameLayout.weightx = 0.1;
        frameLayout.weighty = 0.1;
        Start.mainFrame.getContentPane().add(Start.firstPanel, frameLayout);
        frameLayout.weightx = 1.0;
        frameLayout.weighty = 1.0;
        frameLayout.gridy = 1;
        frameLayout.gridwidth = 1;
        Start.mainFrame.getContentPane().add(Start.secondPanel, frameLayout);
        frameLayout.gridx = 1;
        Start.mainFrame.getContentPane().add(Start.thirdPanel, frameLayout);
        frameLayout.weightx = 0.1;
        frameLayout.weighty = 0.1;
        frameLayout.gridwidth = 2;
        frameLayout.gridy = 2;
        frameLayout.gridx = 0;
        Start.mainFrame.getContentPane().add(Start.fourthPanel, frameLayout);
        Start.mainFrame.repaint();
        name = new JLabel();
        if (Start.mode == Mode.singleEasy)
            name.setText("Singleplayer Easy");
        if (Start.mode == Mode.singleMedium)
            name.setText("Singleplayer Medium");
        if (Start.mode == Mode.singleHard)
            name.setText("Singleplayer Hard");
        if (Start.mode == Mode.singleUltra)
            name.setText("Singleplayer Ultra Hard");
        if (Start.mode == Mode.hotSeat1)
            name.setText("Singleplayer " + Start.currentMenu.textFieldList[0].getText());
        if (Start.mode == Mode.hotSeat2)
            name.setText("Singleplayer " + Start.currentMenu.textFieldList[1].getText());
        if (Start.mode == Mode.onlineClient || Start.mode == Mode.onlineSerwer)
            name.setText("Multiplayer Online");
        buttonMenu = new JButton("Exit");
        name.setFont(new Font("SansSerif", Font.PLAIN, 20));
        GridBagConstraints layout = new GridBagConstraints();
        layout.anchor = GridBagConstraints.WEST;
        layout.weightx = 1.0;
        layout.weighty = 1.0;
        layout.insets = new Insets(5, 5, 5, 5);
        Start.firstPanel.add(name, layout);
        layout.anchor = GridBagConstraints.EAST;
        layout.gridx = 5;
        Start.fourthPanel.add(buttonMenu, layout);

        buttonMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int pick = JOptionPane.showConfirmDialog(
                        null, "Do you realy want to quit?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);
                if (pick == JOptionPane.YES_OPTION) {
                    Start.firstPanel.setVisible(false);
                    Start.secondPanel.setVisible(false);
                    Start.thirdPanel.setVisible(false);
                    Start.fourthPanel.setVisible(false);
                    Start.currentMenu = new MainMenu();
                    Start.mainFrame.repaint();
                }
            }
        });

        GridBagConstraints bottomLayout = new GridBagConstraints();
        bottomLayout.weightx = 1.0;
        bottomLayout.weighty = 1.0;
        bottomLayout.insets = new Insets(5, 5, 5, 10);
        bottomLayout.anchor = GridBagConstraints.EAST;
        bottomLayout.gridx = 0;

        String counter = new Integer(Start.playerOne.myBoard.shipCount - sunkShips).toString();
        leftShipLabel = new JLabel("Ships left: " + counter);
        Start.fourthPanel.add(leftShipLabel, bottomLayout);
    }

    protected void paintBoards() {
        Start.secondPanel.removeAll();
        Start.secondPanel.repaint();
        Start.thirdPanel.removeAll();
        Start.thirdPanel.repaint();

        String counter = new Integer(Start.playerOne.myBoard.shipCount - sunkShips).toString();
        leftShipLabel.setText("Ships left: " + counter);

        GridBagConstraints layout = new GridBagConstraints();
        layout.insets = new Insets(1, 1, 1, 1);
        layout.anchor = GridBagConstraints.CENTER;
        layout.fill = GridBagConstraints.BOTH;
        for (int i = 1; i < boardSize + 1; i++) {
            layout.gridx = i;
            Start.secondPanel.add(myLabels[1][i - 1], layout);
            Start.thirdPanel.add(opponentLabels[1][i - 1], layout);
        }
        layout.gridx = 0;
        for (int i = 1; i < boardSize + 1; i++) {
            layout.gridy = i + 1;
            Start.secondPanel.add(myLabels[0][i - 1], layout);
            Start.thirdPanel.add(opponentLabels[0][i - 1], layout);
        }
        if (Start.mode != Mode.hotSeat2)
            for (int i = 0; i < boardSize; i++)
                for (int j = 0; j < boardSize; j++) {
                    layout.gridx = j + 1;
                    layout.gridy = i + 2;
                    if (Start.playerOne.myBoard.getFieldState(i, j) == FieldState.taken)
                        myButtons[i][j].setBackground(new Color(0, 255, 0));
                    else if (Start.playerOne.myBoard.getFieldState(i, j) == FieldState.missed)
                        myButtons[i][j].setText("o");
                    else if (Start.playerOne.myBoard.getFieldState(i, j) == FieldState.destroyed)
                        myButtons[i][j].setBackground(new Color(255, 0, 0));
                    else if (Start.playerOne.myBoard.getFieldState(i, j) == FieldState.sunk)
                        myButtons[i][j].setBackground(new Color(0, 0, 0));
                    Start.secondPanel.add(myButtons[i][j], layout);

                    if (Start.playerOne.opponentBoard.getFieldState(i, j) == FieldState.missed)
                        opponentButtons[i][j].setText("o");
                    else if (Start.playerOne.opponentBoard.getFieldState(i, j) == FieldState.destroyed)
                        opponentButtons[i][j].setBackground(new Color(255, 0, 0));
                    else if (Start.playerOne.opponentBoard.getFieldState(i, j) == FieldState.sunk)
                        opponentButtons[i][j].setBackground(new Color(0, 0, 0));

                    if (Start.mode == Mode.onlineClient || Start.mode == Mode.onlineSerwer)
                        if (Start.myTurn == false)
                            opponentButtons[i][j].setEnabled(false);
                        else
                            opponentButtons[i][j].setEnabled(true);

                    Start.thirdPanel.add(opponentButtons[i][j], layout);
                }
        else
            for (int i = 0; i < boardSize; i++)
                for (int j = 0; j < boardSize; j++) {
                    layout.gridx = j + 1;
                    layout.gridy = i + 2;
                    if (Start.playerTwo.myBoard.getFieldState(i, j) == FieldState.taken)
                        myButtons[i][j].setBackground(new Color(0, 255, 0));
                    else if (Start.playerTwo.myBoard.getFieldState(i, j) == FieldState.missed)
                        myButtons[i][j].setText("o");
                    else if (Start.playerTwo.myBoard.getFieldState(i, j) == FieldState.destroyed)
                        myButtons[i][j].setBackground(new Color(255, 0, 0));
                    else if (Start.playerTwo.myBoard.getFieldState(i, j) == FieldState.sunk)
                        myButtons[i][j].setBackground(new Color(0, 0, 0));
                    Start.secondPanel.add(myButtons[i][j], layout);

                    if (Start.playerTwo.opponentBoard.getFieldState(i, j) == FieldState.missed)
                        opponentButtons[i][j].setText("o");
                    else if (Start.playerTwo.opponentBoard.getFieldState(i, j) == FieldState.destroyed)
                        opponentButtons[i][j].setBackground(new Color(255, 0, 0));
                    else if (Start.playerTwo.opponentBoard.getFieldState(i, j) == FieldState.sunk)
                        opponentButtons[i][j].setBackground(new Color(0, 0, 0));
                    Start.thirdPanel.add(opponentButtons[i][j], layout);
                }
    }

}
