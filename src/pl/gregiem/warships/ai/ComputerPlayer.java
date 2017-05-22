package pl.gregiem.warships.ai;

import pl.gregiem.warships.engine.Gameboard;
import pl.gregiem.warships.engine.Player;

public abstract class ComputerPlayer extends Player {

    public ComputerPlayer(int size) {
        boardSize = size;
        opponentBoard = new Gameboard(size);
        setBoard();
    }

    public abstract void shoot();

}
