package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class AIForGame {
    String getAiMove(Field gameField) {
        ArrayList<String> availableMoves = gameField.getAvailableMoves();
        Random random = new Random();
        int indexMove = random.nextInt(availableMoves.size());
        System.out.println("Making move level \"easy\"");
        return availableMoves.get(indexMove);
    }

}
