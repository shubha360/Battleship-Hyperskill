package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        Battleship bs = new Battleship();

        bs.playGame();
    }
}

class Battleship {

    char[][] mainGrid;
    char[][] fogGrid;

    // for handling user input
    char firstAlpha;
    char secondAlpha;
    int firstNumber;
    int secondNumber;

    Battleship() {

        mainGrid = new char[10][10];
        fogGrid = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mainGrid[i][j] = fogGrid[i][j] = '~';
            }
        }
    }

    void playGame() {

        printMainGrid();
        initializeAircraftCarrier();
        printMainGrid();
        initializeBattleship();
        printMainGrid();
        initializeSubmarine();
        printMainGrid();
        initializeCruiser();
        printMainGrid();
        initializeDestroyer();
        printMainGrid();

        System.out.println("The game starts!\n");
        printEmptyGrid();
        takeAShot();
    }

    private void printMainGrid() {

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
                        if (mainGrid[i][j] != '~') {
                            System.out.print(" " + 'O');
                        } else {
                            System.out.print(" " + mainGrid[i][j]);
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printEmptyGrid() {

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
                        System.out.print(" " + "~");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printFogGrid() {

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
                        System.out.print(" " + fogGrid[i][j]);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

//    private void printHiddenGrid() {
//
//        for (int i = -1; i < 10; i++) {
//
//            if (i == -1) {
//
//                System.out.print(" ");
//                for (int k = 1; k <= 10; k++) {
//                    System.out.printf(" " + k);
//                }
//            } else {
//
//                for (int j = -1; j < 10; j++) {
//
//                    if (j == -1) {
//                        System.out.print((char) ('A' + i));
//                    } else {
//                        System.out.print(" " + mainGrid[i][j]);
//                    }
//                }
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }

    private void initializeAircraftCarrier() {

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");

        takePosition("Aircraft Carrier",5, 'A');
        System.out.println();
    }

    private void initializeBattleship() {

        System.out.println("Enter the coordinates of the Battleship (4 cells):\n");

        takePosition("Battleship", 4, 'B');
        System.out.println();
    }

    private void initializeSubmarine() {

        System.out.println("Enter the coordinates of the Submarine (3 cells):\n");

        takePosition("Submarine", 3, 'S');
        System.out.println();
    }

    private void initializeCruiser() {

        System.out.println("Enter the coordinates of the Cruiser (3 cells):\n");

        takePosition("Cruiser", 3, 'C');
        System.out.println();
    }

    private void initializeDestroyer() {

        System.out.println("Enter the coordinates of the Destroyer (2 cells):\n");

        takePosition("Destroyer", 2, 'D');
        System.out.println();
    }

    private void takePosition(String nameOfCraft, int lengthOfCell, char shipCode) {

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

                fillGrid(firstAlpha, secondAlpha, firstNumber, secondNumber, shipCode);
                break;
            }
        }
    }

    private boolean positionIsVacant(char firstAlpha, char secondAlpha, int firstNumber, int secondNumber) {

        if (firstAlpha == secondAlpha && firstNumber != secondNumber) {

            if (firstNumber < secondNumber) {

                for (int i = firstNumber - 1; i < secondNumber; i++) {

                    if (mainGrid[firstAlpha - 'A'][i] != '~') {
                        return false;
                    }
                }

                if (firstNumber == 1) {

                    if (mainGrid[firstAlpha - 'A'][secondNumber] != '~') {
                        return false;
                    }
                } else if (secondNumber == 10) {

                    if (mainGrid[firstAlpha - 'A'][firstNumber - 1] != '~') {
                        return false;
                    }
                } else {

                    if (mainGrid[firstAlpha - 'A'][firstNumber - 2] != '~' || mainGrid[firstAlpha - 'A'][secondNumber] != '~') {
                        return false;
                    }
                }

                if (firstAlpha == 'A') {

                    for (int i = firstNumber - 1; i < secondNumber; i++) {

                        if (mainGrid[1][i] != '~') {
                            return false;
                        }
                    }
                } else if (firstAlpha == 'J') {

                    for (int i = firstNumber - 1; i < secondNumber; i++) {

                        if (mainGrid[firstAlpha - 'A' - 1][i] != '~') {
                            return false;
                        }
                    }
                } else {

                    for (int i = firstNumber - 1; i < secondNumber; i++) {

                        if (mainGrid[firstAlpha - 'A' - 1][i] != '~' || mainGrid[firstAlpha - 'A' + 1][i] != '~') {
                            return false;
                        }
                    }
                }
            } else if (secondNumber < firstNumber) {

                for (int i = secondNumber - 1; i < firstNumber; i++) {

                    if (mainGrid[firstAlpha - 'A'][i] != '~') {
                        return false;
                    }
                }

                if (secondNumber == 1) {

                    if (mainGrid[firstAlpha - 'A'][firstNumber] != '~') {
                        return false;
                    }
                } else if (firstNumber == 10) {

                    if (mainGrid[firstAlpha - 'A'][secondNumber - 1] != '~') {
                        return false;
                    }
                } else {

                    if (mainGrid[firstAlpha - 'A'][secondNumber - 2] != '~' || mainGrid[firstAlpha - 'A'][firstNumber] != '~') {
                        return false;
                    }
                }

                if (firstAlpha == 'A') {

                    for (int i = secondNumber - 1; i < firstNumber; i++) {

                        if (mainGrid[1][i] != '~') {
                            return false;
                        }
                    }
                } else if (firstAlpha == 'J') {

                    for (int i = secondNumber - 1; i < firstNumber; i++) {

                        if (mainGrid[firstAlpha - 'A' - 1][i] != '~') {
                            return false;
                        }
                    }
                } else {

                    for (int i = secondNumber - 1; i < firstNumber; i++) {

                        if (mainGrid[firstAlpha - 'A' - 1][i] != '~' || mainGrid[firstAlpha - 'A' + 1][i] != '~') {
                            return false;
                        }
                    }
                }
            }
        } else if (firstAlpha != secondAlpha && firstNumber == secondNumber) {

            if (firstAlpha < secondAlpha) {

                for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                    if (mainGrid[i][firstNumber - 1] != '~') {
                        return false;
                    }
                }

                if (firstAlpha == 'A') {

                    if (mainGrid[secondAlpha - 'A' + 1][firstNumber - 1] != '~') {
                        return false;
                    }
                } else if (secondAlpha == 'J') {

                    if (mainGrid[firstAlpha - 'A' - 1][firstNumber - 1] != '~') {
                        return false;
                    }
                } else {

                    if (mainGrid[secondAlpha - 'A' + 1][firstNumber - 1] != '~' || mainGrid[firstAlpha - 'A' - 1][firstNumber - 1] != '~') {
                        return false;
                    }
                }

                if (firstNumber == 1) {

                    for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                        if (mainGrid[i][firstNumber] != '~') {
                            return false;
                        }
                    }
                } else if (firstNumber == 10) {

                    for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                        if (mainGrid[i][firstNumber - 2] != '~') {
                            return false;
                        }
                    }
                } else {

                    for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                        if (mainGrid[i][firstNumber] != '~' || mainGrid[i][firstNumber - 2] != '~') {
                            return false;
                        }
                    }
                }
            } else if (secondAlpha < firstAlpha) {

                for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                    if (mainGrid[i][firstNumber - 1] != '~') {
                        return false;
                    }
                }

                if (secondAlpha == 'A') {

                    if (mainGrid[firstAlpha - 'A' + 1][firstNumber - 1] != '~') {
                        return false;
                    }
                } else if (firstAlpha == 'J') {

                    if (mainGrid[secondAlpha - 'A' - 1][firstNumber - 1] != '~') {
                        return false;
                    }
                } else {

                    if (mainGrid[firstAlpha - 'A' + 1][firstNumber - 1] != '~' || mainGrid[secondAlpha - 'A' - 1][firstNumber - 1] != '~') {
                        return false;
                    }
                }

                if (firstNumber == 1) {

                    for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                        if (mainGrid[i][firstNumber] != '~') {
                            return false;
                        }
                    }
                } else if (firstNumber == 10) {

                    for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                        if (mainGrid[i][firstNumber - 2] != '~') {
                            return false;
                        }
                    }
                } else {

                    for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                        if (mainGrid[i][firstNumber] != '~' || mainGrid[i][firstNumber - 2] != '~') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void fillGrid(char firstAlpha, char secondAlpha, int firstNumber, int secondNumber, char shipCode) {

        if (firstAlpha == secondAlpha) {

            if (firstNumber < secondNumber) {

                for (int i = firstNumber - 1; i < secondNumber; i++) {

                    mainGrid[firstAlpha - 'A'][i] = shipCode;
                }
            } else if (secondNumber < firstNumber) {

                for (int i = secondNumber - 1; i < firstNumber; i++) {

                    mainGrid[firstAlpha - 'A'][i] = shipCode;
                }
            }
        } else if (firstNumber == secondNumber) {

            if (firstAlpha < secondAlpha) {

                for (int i = firstAlpha - 'A'; i <= secondAlpha - 'A'; i++) {

                    mainGrid[i][firstNumber - 1] = shipCode;
                }
            } else if (secondAlpha < firstAlpha) {

                for (int i = secondAlpha - 'A'; i <= firstAlpha - 'A'; i++) {

                    mainGrid[i][firstNumber - 1] = shipCode;
                }
            }
        }
    }

    private void fillFogGrid(char alpha, int number, char decider) {

        fogGrid[alpha - 'A'][number - 1] = decider;
    }

    private void takeAShot() {

        System.out.println("Take a shot!\n");

        Scanner sc = new Scanner(System.in);

        char alpha;
        char decider;
        int number;

        int aHealth = 5;
        int bHealth = 4;
        int sHealth = 3;
        int cHealth = 3;
        int dHealth = 2;

        boolean aIsAlive = true;
        boolean bIsAlive = true;
        boolean sIsAlive =  true;
        boolean cIsAlive = true;
        boolean dIsAlive = true;

        char gotHit;

        while (true) {

            String coordinates = sc.next();

            alpha = coordinates.charAt(0);
            number = Integer.parseInt(coordinates.substring(1));

            if (alpha < 'A' || alpha > 'J' ||
                    number < 1 || number > 10) {

                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            } else {

                if (fogGrid[alpha - 'A'][number - 1] != '~') {

                    printFogGrid();
                    System.out.println("\nYou hit a ship! Try again :\n");
                } else {

                    if (mainGrid[alpha - 'A'][number - 1] != '~') {

                        fillFogGrid(alpha, number, 'X');

                        switch (mainGrid[alpha - 'A'][number - 1]) {

                            case 'A' :
                                aHealth--;
                                break;

                            case 'B' :
                                bHealth--;
                                break;

                            case 'S' :
                                sHealth--;
                                break;

                            case 'C' :
                                cHealth--;
                                break;

                            case 'D' :
                                dHealth--;
                                break;
                        }

                        System.out.println();
                        printFogGrid();

                        if (aHealth == 0 && bHealth == 0 && sHealth == 0 && cHealth == 0 && dHealth == 0) {

                            System.out.println("You sank the last ship. You won. Congratulations!");
                            break;
                        } else if (aHealth == 0 && aIsAlive) {
                            System.out.println("You sank a ship! Specify a new target :\n");
                            aIsAlive = false;
                        } else if (bHealth == 0 && bIsAlive) {
                            System.out.println("You sank a ship! Specify a new target :\n");
                            bIsAlive = false;
                        } else if (sHealth == 0 && sIsAlive) {
                            System.out.println("You sank a ship! Specify a new target :\n");
                            sIsAlive = false;
                        } else if (cHealth == 0 && cIsAlive) {
                            System.out.println("You sank a ship! Specify a new target :\n");
                            cIsAlive = false;
                        } else if (dHealth == 0 && dIsAlive) {
                            System.out.println("You sank a ship! Specify a new target :\n");
                            dIsAlive = false;
                        } else {

                            System.out.println("You hit a ship! Try again :\n");
                        }
                    } else {

                        fillFogGrid(alpha, number, 'M');
                        System.out.println();
                        printFogGrid();
                        System.out.println("You missed! Try again:\n");
                    }
                }
            }
        }
    }
}
