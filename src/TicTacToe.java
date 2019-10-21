import javax.swing.*;
import java.util.Scanner;

public class TicTacToe {

    private char[][] board;
    private boolean xTurn;
    private Scanner scan;


    public TicTacToe() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
            xTurn = true;

            scan = new Scanner(System.in);
        }
    }

    private void displayRow(int row) {
        System.out.println(" " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]);
    }

    private void displayBoard() {
        displayRow(0);
        System.out.println("-----------");
        displayRow(1);
        System.out.println("-----------");
        displayRow(2);
    }


    private void displayMenu() {
        if (xTurn)
            System.out.println("X Turn");
        else
            System.out.println("O Turn");
        System.out.println("Displaying Menu");
        System.out.println("1: Make a move");
        System.out.println("2: Start Over");
        System.out.println("3: Quit");
        System.out.println(" Choice : ");
    }

    private boolean getMove() {
        boolean invalid = true;
        int row = 0, column = 0;
        while (invalid) {
            System.out.println("Which row, column would you like to move to? Enter two numbers between 0-2 separated by a space to indicate position.");
            row = scan.nextInt();
            column = scan.nextInt();
            if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
                if (board[row][column] != ' ')
                    System.out.println("That position is already taken");
                else
                    invalid = false;
            } else
                System.out.println("Invalid Position");

        }
        if (xTurn) {
            board[row][column] = 'X';
        } else {
            board[row][column] = 'O';
        }
        return winner(row, column);

    }

    private void restart() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        xTurn = true;
    }

    private boolean winner(int lastR, int lastC) {
        boolean winner = false; // no winner yet
        char symbol = board[lastR][lastC]; // the last move either x or o

        // controll left to right
        //the last move we made was in the row lastR, check that row for three of the same symbol

        int numFound = 0;
        for (int i = 0; i < 3; i++) {
            // start from the last row edhe tan kqyri kolonat ren
            if (board[lastR][i] == symbol)
                numFound++;
            // count numrin e simboleve te njejta
        }

        if (numFound == 3)
            winner = true;


        // controll up-down
        //the last move we made was in the column lastC, check that column for three of the same symbol
        numFound = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][lastC] == symbol)
                numFound++;
        }

        if (numFound == 3)
            winner = true;


        //controll both diagonals
        numFound = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == symbol)
                numFound++;
        }
        if (numFound == 3)
            winner = true;


        return winner;
    }

    private boolean boardFull() {
        int numSpotsFilled = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X' || board[i][j] == 'O')
                    numSpotsFilled++;
            }
        }
        return numSpotsFilled == 9;
        // kthen false nese nuk osht
    }

    public void play() {
        while (true) {

            displayBoard();
            displayMenu();
            int choice = scan.nextInt();

            if (choice == 1) {
                if (getMove()) {
                    displayBoard();

                    if (xTurn)
                        System.out.println("Player X Wins");
                    else
                        System.out.println("PLayer O Wins");
                    System.exit(0);
                } else if (boardFull()) {
                    displayBoard();
                    System.out.println("Draw");
                    System.exit(0);
                } else {
                    xTurn = !xTurn;  //switch whose turn it is                }
                }
            } else if (choice == 2)
                restart();
            else if (choice == 3)
                System.exit(0);
            else
                System.out.println("Invalid Output");
        }
    }


    }
