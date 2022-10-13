package tictactoe;

import java.util.ArrayList;

public class Field {
    String[][] field;
    String currentPlayerSign = "X";
    boolean isWinX = false;
    boolean isWinO = false;

    Field(int x, int y) {
        field = new String[x][y];
    }

    void printField() {
        for (int i = 0; i < field.length * 2 + 3; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (String[] strings : field) {
            System.out.print("| ");
            for (String string : strings) {
                System.out.print(string.isEmpty() ? " " : string);
                System.out.print(" ");
            }
            System.out.println("|");
        }
        for (int i = 0; i < field.length * 2 + 3; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    void fillEmptyField() {
        fillField("_".repeat(Math.max(0, field.length * field.length)));
    }

    void fillField(String strMoves) {
        String[] movesArr = strMoves.split("");
        int movesIndex = 0;
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (!movesArr[movesIndex].equals("_")) {
                    field[i][j] = movesArr[movesIndex];
                    if (movesArr[movesIndex].equals("X")) counterX++;
                    else counterO++;

                } else {
                    field[i][j] = "";
                }
                movesIndex++;
            }
        }
        if (counterX < counterO || counterX == counterO) currentPlayerSign = "X";
        else currentPlayerSign = "O";
    }

    boolean isGameEnded() {
        isWinVertical();
        isWinHorizontal();
        isWinDiagonal();
        if (isWinO && isWinX) {
            System.out.println("It's impossible");
            return true;
        } else if (isWinX) {
            System.out.println("X wins");
            return true;
        } else if (isWinO) {
            System.out.println("O wins");
            return true;
        } else if (!isMoveAvailable()) {
            System.out.println("Draw");
            return true;
        } else {
            return false;
        }
    }

    private void isWinVertical() {
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[j][i].equals("X")) {
                    counterX++;
                } else if (field[j][i].equals("O")) {
                    counterO++;
                }
            }
            counterX = 0;
            counterO = 0;
        }
        if (counterX == field.length) {
            isWinX = true;
        }
        if (counterO == field.length) {
            isWinO = true;
        }
    }

    private void isWinHorizontal() {
        int counterX = 0;
        int counterO = 0;
        for (String[] strings : field) {
            for (String string : strings) {
                if (string.equals("X")) {
                    counterX++;
                } else if (string.equals("O")) {
                    counterO++;
                }
            }
            if (counterX == field.length) {
                isWinX = true;
            }
            if (counterO == field.length) {
                isWinO = true;
            }
            counterX = 0;
            counterO = 0;
        }

    }

    private void isWinDiagonal() {
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i][i].equals("X")) {
                counterX++;
            } else if (field[i][i].equals("O")) {
                counterO++;
            }

        }
        if (counterX == field.length) {
            isWinX = true;
        }
        if (counterO == field.length) {
            isWinO = true;
        }
        counterX = 0;
        counterO = 0;
        int j = field.length - 1;
        for (String[] strings : field) {
            if (strings[j].equals("X")) {
                counterX++;
            } else if (strings[j].equals("O")) {
                counterO++;
            }
            j--;
        }
        if (counterX == field.length) {
            isWinX = true;
        }
        if (counterO == field.length) {
            isWinO = true;
        }
    }


    boolean isValidCoordinates(String coordinatesStr) {
        try {
            String[] coordinatesArr = coordinatesStr.split(" ");

            int x = Integer.parseInt(coordinatesArr[0]) - 1;
            int y = Integer.parseInt(coordinatesArr[1]) - 1;
            if (x >= field.length || y >= field[field.length - 1].length) {
                System.out.printf("Coordinates should be from 1 to %d!%n", field.length);
            }
            if (!field[x][y].isBlank()) {
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return false;
        }


        return true;
    }

    void setPlayerMove(String coordinatesStr) {
        String[] coordinatesArr = coordinatesStr.split(" ");
        int x = Integer.parseInt(coordinatesArr[0]) - 1;
        int y = Integer.parseInt(coordinatesArr[1]) - 1;
        field[x][y] = currentPlayerSign;
        currentPlayerSign = currentPlayerSign.equals("X") ? "O" : "X";
    }

    private boolean isMoveAvailable() {
        return getAvailableMoves().size() > 0;
    }


    ArrayList<String> getAvailableMoves() {
        ArrayList<String> availableMoves = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].isBlank()) {
                    int x = i + 1;
                    int y = j + 1;
                    availableMoves.add(x + " " + y);
                }
            }
        }
        return availableMoves;
    }


}
