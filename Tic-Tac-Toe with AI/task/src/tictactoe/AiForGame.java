package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class AiForGame {

    private String difficult;
    private String playerSign;

    AiForGame(String difficult) {
        setDifficult(difficult);
    }

    private void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    private String getAiMoveEasy(Field gameField) {
        ArrayList<String> availableMoves = gameField.getAvailableMoves();
        Random random = new Random();
        int indexMove = random.nextInt(availableMoves.size());
        return availableMoves.get(indexMove);
    }

    String getAiMove(Field gameField) {
        System.out.printf("Making move level \"%s\"%n", difficult);
        switch (difficult) {
            case "easy" -> {
                return getAiMoveEasy(gameField);
            }
            case "medium" -> {
                return getAiMoveMedium(gameField);
            }
            case "hard" -> {
                return getAiMoveHard(gameField);
            }
        }
        return "";
    }

    private String getAiMoveHard(Field gameField) {
        Field testField = new Field(gameField);
        playerSign = gameField.getCurrentPlayerSign();
        minMaxMethod(testField);
        String[] newMove = minMaxMethod(testField);
        return newMove[1];
    }

    private String getAiMoveMedium(Field gameField) {
        String aiMove = checkAvailableMovesToWin(gameField);
        if (!aiMove.isBlank()) {
            return aiMove;
        } else {
            aiMove = checkAvailableMovesToNotLose(gameField);
            if (!aiMove.isBlank()) {
                return aiMove;
            }

        }
        return getAiMoveEasy(gameField);

    }

    private String checkAvailableMovesToWin(Field gameField) {

        ArrayList<String> availableMoves = gameField.getAvailableMoves();
        for (String currentMove : availableMoves
        ) {
            Field testField = new Field(gameField);
            testField.setPlayerMove(currentMove);
            if (testField.isGameEnded() && testField.getCurrentPlayerSign().equals(testField.getWinner())) {
                return currentMove;
            }
        }
        return "";
    }

    private String checkAvailableMovesToNotLose(Field gameField) {
        ArrayList<String> availableMoves = gameField.getAvailableMoves();
        String currentOpponent = gameField.getCurrentPlayerSign().equals("X") ? "O" : "X";
        for (String currentMove : availableMoves
        ) {

            Field testField = new Field(gameField);
            testField.setCurrentPlayerSign(currentOpponent);
            testField.setPlayerMove(currentMove);
            if (testField.isGameEnded() && currentOpponent.equals(testField.getWinner())) {
                return currentMove;
            }
        }
        return "";
    }

    private String[] minMaxMethod(Field testField) {
        if (testField.isGameEnded()) {
            String[] result = new String[2];
            result[0] = String.valueOf(score(testField));
            return result;
        }
        ArrayList<String> movesArr = new ArrayList<>();
        ArrayList<Integer> scoresArr = new ArrayList<>();
        ArrayList<String> availableMoves = testField.getAvailableMoves();
        for (String currentMove : availableMoves
        ) {
            Field testFieldMinMax = new Field(testField);
            testFieldMinMax.setPlayerMove(currentMove);
            String[] resultMove = minMaxMethod(testFieldMinMax);
            movesArr.add(currentMove);
            scoresArr.add(Integer.parseInt(resultMove[0]));
        }
        int bestMoveIndex = 0;
        String bestScoreStr = "";
        if (testField.getCurrentPlayerSign().equals(playerSign)) {
            int bestScore = -1000;
            for (int i = 0; i < movesArr.size(); i++) {
                if (scoresArr.get(i) > bestScore) {
                    bestScore = scoresArr.get(i);
                    bestScoreStr = String.valueOf(scoresArr.get(i));
                    bestMoveIndex = i;
                }
            }
        } else {
            int bestScore = 1000;
            for (int i = 0; i < movesArr.size(); i++) {
                if (scoresArr.get(i) < bestScore) {
                    bestScore = scoresArr.get(i);
                    bestScoreStr = String.valueOf(scoresArr.get(i));
                    bestMoveIndex = i;
                }
            }
        }
        String[] result = new String[2];
        result[0] = bestScoreStr;
        result[1] = movesArr.get(bestMoveIndex);
        return result;
    }

    private int score(Field testFieldMinMax) {
        String opponent = playerSign.equals("X") ? "O" : "X";
        if (playerSign.equals(testFieldMinMax.getWinner())) {
            return 10;
        } else if (opponent.equals(testFieldMinMax.getWinner())) {
            return -10;
        } else {
            return 0;
        }

    }
}
