package tictactoe;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showMainMenu() {
        System.out.println("Input command:");
    }

    public static void coordinatesRequest() {
        System.out.println("Enter the coordinates:");

    }

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
            case "easy", "user","medium" -> true;
            default -> false;
        };
    }

}
