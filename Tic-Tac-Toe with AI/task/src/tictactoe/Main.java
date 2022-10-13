package tictactoe;

import java.util.Scanner;

public class Main {
    static boolean isRunning = true;

    public static void main(String[] args) {
        Field gameField = new Field(3, 3);
        Scanner scanner = new Scanner(System.in);
        String strMoves = scanner.nextLine();
        gameField.fillField(strMoves);
        gameField.printField();
        while (isRunning) {
            Menu.coordinatesRequest();
            String coordinatesStr = scanner.nextLine();
            if (gameField.isValidCoordinates(coordinatesStr)) {
                gameField.setPlayerMove(coordinatesStr);
                gameField.printField();
                if (gameField.isGameEnded()) {
                    isRunning = false;
                }
            }
        }

    }


}
