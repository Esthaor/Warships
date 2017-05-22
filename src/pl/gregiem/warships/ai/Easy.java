package pl.gregiem.warships.ai;

import pl.gregiem.warships.basic.Shoot;
import java.util.Random;
import pl.gregiem.warships.Start;
import pl.gregiem.warships.enums.FieldState;

public class Easy extends ComputerPlayer {

    public Easy(int size) {
        super(size);
    }

    public void shoot() {

        Random generator = new Random();
        int row, column;
        Shoot shoot;

        do {
            do {
                row = generator.nextInt(boardSize);
                column = generator.nextInt(boardSize);
            } while (opponentBoard.canIShootThere(row, column) == false);

            shoot = new Shoot(row, column);

            shoot.setResult(Start.playerOne.myBoard.resolveShoot(shoot));
            uploadResult(shoot);
        } while (shoot.getResult() != FieldState.missed && Start.playerOne.isDead() == false);
    }

}
