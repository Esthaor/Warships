package pl.gregiem.warships.basic;

import javax.swing.JButton;

public class MyButton extends JButton {
    private static final long serialVersionUID = 1L;

    public int row;
    public int column;

    public MyButton(String name) {
        super(name);
    }
}
