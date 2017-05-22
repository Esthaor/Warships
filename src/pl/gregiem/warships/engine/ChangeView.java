package pl.gregiem.warships.engine;

import pl.gregiem.warships.enums.Mode;
import pl.gregiem.warships.Start;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChangeView {
    private JPanel panel;

    public ChangeView(int mySunk, int yourSunk) {

        Start.firstPanel.setVisible(false);
        Start.secondPanel.setVisible(false);
        Start.thirdPanel.setVisible(false);
        Start.fourthPanel.setVisible(false);
        if (Start.mode == Mode.hotSeat1)
            Start.mode = Mode.hotSeat2;
        else
            Start.mode = Mode.hotSeat1;

        JLabel missed = new JLabel("You missed!");
        JLabel text = new JLabel();
        if (Start.mode == Mode.hotSeat2)
            text.setText("Player: " + Start.currentMenu.textFieldList[1].getText() + " turn");
        else
            text.setText("Player: " + Start.currentMenu.textFieldList[0].getText() + " turn");
        text.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        missed.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints frameLayout = new GridBagConstraints();

        frameLayout.gridwidth = 2;
        frameLayout.fill = GridBagConstraints.BOTH;

        Start.mainFrame.getContentPane().add(panel, frameLayout);

        panel.add(missed, frameLayout);

        frameLayout.gridy = 1;

        panel.add(text, frameLayout);

        frameLayout.gridy = 2;

        JButton button = new JButton("OK");

        panel.add(button, frameLayout);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panel.setVisible(false);
                Start.currentGame = (NewGame) new HotSeatView(mySunk, yourSunk);

            }

        });
    }
}
