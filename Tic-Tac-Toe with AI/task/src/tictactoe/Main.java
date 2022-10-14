package tictactoe;

public class Main {
    static boolean isRunningGamePart;
    static boolean isRunning = true;

    public static void main(String[] args) {
        Field gameField = new Field(3, 3);
        Player player1 = null;
        Player player2 = null;
        while (isRunning) {
            Menu.showMainMenu();
            String[] arguments;
            boolean isNoLegalArguments = true;
            while (isNoLegalArguments) {
                arguments = Menu.getInputArguments();
                if (arguments[0].equals("start")) {
                    player1 = new Player(arguments[1], gameField);
                    player2 = new Player(arguments[2], gameField);
                    isNoLegalArguments = false;
                } else if (arguments[0].equals("exit")) {
                    return;
                } else {
                    System.out.println("Bad parameters!");
                }
            }
            isRunningGamePart = true;
            gameField.fillEmptyField();
            gameField.printField();

            while (isRunningGamePart) {
                String coordinatesStr = player1.getMoves();
                if (gameField.isValidCoordinates(coordinatesStr)) {
                    moveAction(coordinatesStr, gameField);
                    if (gameField.isGameEnded()) {
                        System.out.println(gameField.resultOfGame);
                        isRunningGamePart = false;
                        continue;
                    }
                } else {
                    continue;
                }
                coordinatesStr = player2.getMoves();
                if (gameField.isValidCoordinates(coordinatesStr)) {
                    moveAction(coordinatesStr, gameField);
                    if (gameField.isGameEnded()) {
                        System.out.println(gameField.resultOfGame);
                        isRunningGamePart = false;
                    }
                }
            }
        }

    }

    private static void moveAction(String coordinatesStr, Field gameField) {
        gameField.setPlayerMove(coordinatesStr);
        gameField.printField();
    }


}
