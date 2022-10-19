package tictactoe;

import java.util.Scanner;

public class Menu {
    static String MAIN_MENU_TEXT = "Input command:";
    static String GET_COORDINATES_TEXT = "Enter the coordinates:";
    private static final Scanner scanner = new Scanner(System.in);

    public static String[] getInputArguments() {
        String[] arguments = new String[3];
        String inputStr = scanner.nextLine();
        String[] inputArgs = inputStr.split(" ");
        if (inputArgs[0].equals("exit")) {
            arguments[0] = "exit";
            return arguments;
        }
        if (inputArgs.length < 3) {
            arguments[0] = "false";
            return arguments;
        }
        if (isValidArgumentDifficult(inputArgs[1]) && isValidArgumentDifficult(inputArgs[2]) && inputArgs[0].equals("start")) {
            arguments = inputArgs;
        } else {
            arguments[0] = "false";
        }
        return arguments;

    }

    private static boolean isValidArgumentDifficult(String arg) {
        return switch (arg) {
            case "easy", "user", "medium", "hard" -> true;
            default -> false;
        };
    }


}
