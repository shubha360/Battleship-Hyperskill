package battleship;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        Battleship bs = new Battleship();

        bs.playGame();
    }
}

class Battleship {

    private char[][] grid;

    // for handling user input
    private char firstAlpha;
    private char secondAlpha;
    private int firstNumber;
    private int secondNumber;

    Battleship() {

        grid = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = '~';
            }
        }
    }

    void playGame() {

        printGrid();
        initializeAircraftCarrier();
        printGrid();
        initializeBattleship();
        printGrid();
        initializeSubmarine();
        printGrid();
        initializeCruiser();
        printGrid();
        initializeDestroyer();
        printGrid();

        System.out.println("The game starts!\n");
        printGrid();
        takeAShot();
    }

    private void printGrid() {

        for (int i = -1; i < 10; i++) {

            if (i == -1) {

                System.out.print(" ");
                for (int k = 1; k <= 10; k++) {
                    System.out.printf(" " + k);
                }
            } else {

                for (int j = -1; j < 10; j++) {

                    if (j == -1) {
                        System.out.print((char) ('A' + i));
                    } else {
                        System.out.print(" " + grid[i][j]);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void initializeAircraftCarrier() {

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");

        takePosition("Aircraft Carrier",5);
        System.out.println();
    }

    private void initializeBattleship() {

        System.out.println("Enter the coordinates of the Battleship (4 cells):\n");

        takePosition("Battleship", 4);
        System.out.println();
    }

    private void initializeSubmarine() {

        System.out.println("Enter the coordinates of the Submarine (3 cells):\n");

        takePosition("Submarine", 3);
        System.out.println();
    }

    private void initializeCruiser() {

        System.out.println("Enter the coordinates of the Cruiser (3 cells):\n");

        takePosition("Cruiser", 3);
        System.out.println();
    }

    private void initializeDestroyer() {

        System.out.println("Enter the coordinates of the Destroyer (2 cells):\n");

        takePosition("Destroyer", 2);
        System.out.println();
    }

    private void takePosition(String nameOfCraft, int lengthOfCell) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String firstCoordinate = scanner.next();
            String secondCoordinate = scanner.next();

            this.firstAlpha = firstCoordinate.charAt(0);
            this.secondAlpha = secondCoordinate.charAt(0);

            this.firstNumber = Integer.parseInt(firstCoordinate.substring(1));
            this.secondNumber = Integer.parseInt(secondCoordinate.substring(1));

            if (firstAlpha < 'A' || firstAlpha > 'J' ||
                    secondAlpha < 'A' || secondAlpha > 'J' ||
                    firstNumber < 1 || firstNumber > 10 ||
                    secondNumber < 1 || secondNumber > 10) {

                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            } else if ((firstAlpha != secondAlpha) && (firstNumber != secondNumber)) {

                System.out.println("\nError! Wrong ship location! Try again:\n");
            } else if (Math.abs(firstAlpha - secondAlpha) != lengthOfCell - 1 && Math.abs(firstNumber - secondNumber) != lengthOfCell - 1) {

                System.out.println("\nError! Wrong length of the " + nameOfCraft + "! Try again:\n");
            } else if (!positionIsVacant(firstAlpha, secondAlpha, firstNumber, secondNumber)) {

                System.out.println("\nError! You placed it too close to another one. Try again:\n");
            }
            else {

                fillGrid(firstAlpha, secondAlpha, firstNumber, secondNumber);
                break;
            }
        }
    }

    private boolean positionIsVacant(char firstAlpha, char secondAlpha, int firstNumber, int secondNumber) {

        if (firstAlpha == secondAlpha && firstNumber != secondNumber) {

            if (firstNumber < secondNumber) {

                for (int i = firstNumber - 1; i < secondNumber; i++) {

                    if (grid[firstAlpha - 'A'][i] != '~') {
                        return false;
                    }
                }

                if (firstNumber == 1) {

                    if (grid[firstAlpha - 'A'][secondNumber] != '~') {
                        return false;
                    }
                } else if (secondNumber == 10) {

                    if (grid[firstAlpha - 'A'][firstNumber - 1] != '~') {
                        return false;
                    }
                } else {

                    if (grid[firstAlpha - 'A'][firstNumber - 2] != '~' || grid[firstAlpha - 'A'][secondNumber] != '~') {
                        return false;
                    }
                }

                if (firstAlpha == 'A') {

                    for (int i = firstNumber - 1; i < secondNumber; i++) {

                        if (grid[1][i] != '~') {
                            return false;
                        }
                    }
                } else if (firstAlpha == 'J') {

                    for (int i = firstNumber - 1; i < secondNumber; i++) {

                        if (grid[firstAlpha - 'A' - 1][i] != '~') {
                            return false;
                        }
                    }
                } else {

                    for (int i = firstNumber - 1; i < secondNumber; i++) {

                        if (grid[firstAlpha - 'A' - 1][i] != '~' || grid[firstAlpha - 'A' + 1][i] != '~') {
                            return false;
                        }
                    }
                }
            } else if (secondNumber < firstNumber) {

                for (int i = secondNumber - 1; i < firstNumber; i++) {

                    if (grid[firstAlpha - 'A'][i] != '~') {
                        return false;
                    }
                }

                if (secondNumber == 1) {

                    if (grid[firstAlpha - 'A'][firstNumber] != '~') {
                        return false;
                    }
                } else if (firstNumber == 10) {

                    if (grid[firstAlpha - 'A'][secondNumber - 1] != '~') {
                        return false;
                    }
                } else {

                    if (grid[firstAlpha - 'A'][secondNumber - 2] != '~' || grid[firstAlpha - 'A'][firstNumber] != '~') {
                        return false;
                    }
                }

                if (firstAlpha == 'A') {

                    for (int i = secondNumber - 1; i < firstNumber; i++) {

                        if (grid[1][i] != '~') {
                            return false;
                        }
                    }
                } else if (firstAlpha == 'J') {

                    for (int i = secondNumber - 1; i < firstNumber; i++) {

                        if (grid[firstAlpha - 'A' - 1][i] != '~') {
                            return false;
                        }
                    }
                } else {

                    for (int i = secondNumber - 1; i < firstNumber; i++) {

                        if (grid[firstAlpha - 'A' - 1][i] != '~' || grid[firstAlpha - 'A' + 1][i] != '~') {
                            return false;
                        }
                    }
                }
            }
        } else if (firstAlpha != secondAlpha && firstNumber == secondNumber) {

            if (firstAlpha < secondAlpha) {

                for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                    if (grid[i][firstNumber - 1] != '~') {
                        return false;
                    }
                }

                if (firstAlpha == 'A') {

                    if (grid[secondAlpha - 'A' + 1][firstNumber - 1] != '~') {
                        return false;
                    }
                } else if (secondAlpha == 'J') {

                    if (grid[firstAlpha - 'A' - 1][firstNumber - 1] != '~') {
                        return false;
                    }
                } else {

                    if (grid[secondAlpha - 'A' + 1][firstNumber - 1] != '~' || grid[firstAlpha - 'A' - 1][firstNumber - 1] != '~') {
                        return false;
                    }
                }

                if (firstNumber == 1) {

                    for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                        if (grid[i][firstNumber] != '~') {
                            return false;
                        }
                    }
                } else if (firstNumber == 10) {

                    for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                        if (grid[i][firstNumber - 2] != '~') {
                            return false;
                        }
                    }
                } else {

                    for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                        if (grid[i][firstNumber] != '~' || grid[i][firstNumber - 2] != '~') {
                            return false;
                        }
                    }
                }
            } else if (secondAlpha < firstAlpha) {

                for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                    if (grid[i][firstNumber - 1] != '~') {
                        return false;
                    }
                }

                if (secondAlpha == 'A') {

                    if (grid[firstAlpha - 'A' + 1][firstNumber - 1] != '~') {
                        return false;
                    }
                } else if (firstAlpha == 'J') {

                    if (grid[secondAlpha - 'A' - 1][firstNumber - 1] != '~') {
                        return false;
                    }
                } else {

                    if (grid[firstAlpha - 'A' + 1][firstNumber - 1] != '~' || grid[secondAlpha - 'A' - 1][firstNumber - 1] != '~') {
                        return false;
                    }
                }

                if (firstNumber == 1) {

                    for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                        if (grid[i][firstNumber] != '~') {
                            return false;
                        }
                    }
                } else if (firstNumber == 10) {

                    for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                        if (grid[i][firstNumber - 2] != '~') {
                            return false;
                        }
                    }
                } else {

                    for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                        if (grid[i][firstNumber] != '~' || grid[i][firstNumber - 2] != '~') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void fillGrid(char firstAlpha, char secondAlpha, int firstNumber, int secondNumber) {

        if (firstAlpha == secondAlpha) {

            if (firstNumber < secondNumber) {

                for (int i = firstNumber - 1; i < secondNumber; i++) {

                    grid[firstAlpha - 'A'][i] = 'O';
                }
            } else if (secondNumber < firstNumber) {

                for (int i = secondNumber - 1; i < firstNumber; i++) {

                    grid[firstAlpha - 'A'][i] = 'O';
                }
            }
        } else if (firstNumber == secondNumber) {

            if (firstAlpha < secondAlpha) {

                for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                    grid[i][firstNumber - 1] = 'O';
                }
            } else if (secondAlpha < firstAlpha) {

                for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                    grid[i][firstNumber - 1] = 'O';
                }
            }
        }
    }

    private void takeAShot() {

        System.out.println("Take a shot!\n");

        Scanner sc = new Scanner(System.in);
        String res = "";

        while (true) {

            String coordinates = sc.next();

            char alpha = coordinates.charAt(0);
            int number = Integer.parseInt(coordinates.substring(1));

            if (alpha < 'A' || alpha > 'J' ||
                    number < 1 || number > 10) {

                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            } else {

                if (grid[alpha - 'A'][number - 1] == 'O') {

                    grid[alpha - 'A'][number - 1] = 'X';
                    res += "You hit a ship!";
                } else {
                    grid[alpha - 'A'][number - 1] = 'M';
                    res += "You missed!";
                }
                break;
            }
        }

        System.out.println();
        printGrid();
        System.out.println(res);
    }
}
