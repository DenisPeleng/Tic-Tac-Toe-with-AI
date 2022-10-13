package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class AIForGame {

    String difficult;

    AIForGame(String difficult) {
        setDifficult(difficult);
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    private String getAiMoveEasy(Field gameField) {
        ArrayList<String> availableMoves = gameField.getAvailableMoves();
        Random random = new Random();
        int indexMove = random.nextInt(availableMoves.size());
        System.out.println("Making move level \"easy\"");
        return availableMoves.get(indexMove);
    }

     String getAiMove(Field gameField) {
        if (difficult.equals("easy")) {
            return getAiMoveEasy(gameField);
        }
        return "";
    }
}
