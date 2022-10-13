package tictactoe;

import java.util.Scanner;

public class Main {
    static boolean isRunning = true;

    public static void main(String[] args) {
        Field gameField = new Field(3, 3);
        Scanner scanner = new Scanner(System.in);
        gameField.fillEmptyField();
        gameField.printField();
        while (isRunning) {
            Menu.coordinatesRequest();
            String coordinatesStr = scanner.nextLine();
            if (gameField.isValidCoordinates(coordinatesStr)) {
                moveAction(coordinatesStr, gameField);
            } else {
                continue;
            }
            AIForGame ai = new AIForGame();
            coordinatesStr = ai.getAiMove(gameField);
            moveAction(coordinatesStr, gameField);

        }

    }

    private static void moveAction(String coordinatesStr, Field gameField) {
        gameField.setPlayerMove(coordinatesStr);
        gameField.printField();
        if (gameField.isGameEnded()) {
            isRunning = false;
        }
    }


}
