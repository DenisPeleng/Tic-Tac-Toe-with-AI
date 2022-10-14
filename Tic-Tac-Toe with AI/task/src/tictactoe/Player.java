package tictactoe;

import java.util.Scanner;

public class Player {
    String typePlayer;
    Field gameField;
    private static final Scanner scanner = new Scanner(System.in);

    Player(String type, Field gameField) {
        this.typePlayer = type;
        this.gameField = gameField;
    }

    public String getMoves() {
        String moves = "";
        switch (typePlayer) {
            case "user" -> moves = getMovesUser();
            case "easy", "medium" -> moves = getMovesAi();
        }
        return moves;
    }

    private String getMovesAi() {
        AiForGame ai = new AiForGame(typePlayer);
        return ai.getAiMove(gameField);
    }

    private String getMovesUser() {
        Menu.coordinatesRequest();
        return scanner.nextLine();

    }
}
