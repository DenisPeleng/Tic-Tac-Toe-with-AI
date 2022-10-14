package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class AiForGame {

    String difficult;

    AiForGame(String difficult) {
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
        switch (difficult) {
            case "easy" -> {
                return getAiMoveEasy(gameField);
            }
            case "medium" -> {
                return getAiMoveMedium(gameField);
            }
        }
        return "";
    }

    private String getAiMoveMedium(Field gameField) {
        System.out.println("Making move level \"medium\"");
        String aiMove = checkAvailableMovesToWin(gameField);
        if (!aiMove.isBlank()) {
            return aiMove;
        } else {
            aiMove = checkAvailableMovesToNotLose(gameField);
            if (!aiMove.isBlank()) {
                return aiMove;
            }

        }
        ArrayList<String> availableMoves = gameField.getAvailableMoves();
        Random random = new Random();
        int indexMove = random.nextInt(availableMoves.size());

        return availableMoves.get(indexMove);

    }

    private String checkAvailableMovesToWin(Field gameField) {

        ArrayList<String> availableMoves = gameField.getAvailableMoves();
        for (String currentMove : availableMoves
        ) {
            Field testField = new Field(gameField);
            testField.setPlayerMove(currentMove);
            if (testField.isGameEnded() && testField.currentPlayerSign.equals(testField.winner)) {
                return currentMove;
            }
        }
        return "";
    }

    private String checkAvailableMovesToNotLose(Field gameField) {
        ArrayList<String> availableMoves = gameField.getAvailableMoves();
        String currentOpponent = gameField.currentPlayerSign.equals("X") ? "O" : "X";
        for (String currentMove : availableMoves
        ) {

            Field testField = new Field(gameField);
            testField.currentPlayerSign = currentOpponent;
            testField.setPlayerMove(currentMove);
            if (testField.isGameEnded() && currentOpponent.equals(testField.winner)) {
                return currentMove;
            }
        }
        return "";
    }
}
