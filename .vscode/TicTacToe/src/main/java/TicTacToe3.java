/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author umami
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author umami
 */
import java.util.Scanner;

public class TicTacToe3 {
    private static char[][] board;
    private static char currentPlayer;
    private static int numPlayers;
    private static int movesLeft;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        initializeGame();
        while (true) {
            printBoard();
            int[] move = getMove();
            makeMove(move[0], move[1]);
            if (checkWinner()) {
                printBoard();
                System.out.println(currentPlayer + " wins!");
                break;
            } else if (movesLeft == 0) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
            switchPlayer();
        }
        scanner.close();
    }

    private static void initializeGame() {
        System.out.println("Welcome to Tic-Tac-Toe for three players!");
        System.out.println("Enter the size of the board (e.g., 3 for 3x3): ");
        int boardSize = scanner.nextInt();
        board = new char[boardSize][boardSize];
        System.out.println("Enter the number of players (2 or 3): ");
        numPlayers = scanner.nextInt();
        if (numPlayers != 2 && numPlayers != 3) {
            System.out.println("Invalid number of players. Exiting.");
            System.exit(0);
        }
        currentPlayer = 'X';
        movesLeft = boardSize * boardSize;
        initializeBoard();
    }

    private static void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] getMove() {
        int[] move = new int[2];
        System.out.println("Player " + currentPlayer + ", enter your move (row column): ");
        move[0] = scanner.nextInt();
        move[1] = scanner.nextInt();
        return move;
    }

    private static void makeMove(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board.length || board[row][col] != '-') {
            System.out.println("Invalid move. Try again.");
        } else {
            board[row][col] = currentPlayer;
            movesLeft--;
        }
    }

    private static boolean checkWinner() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < board.length; i++) {
            if (checkLine(i, 0, 0, 1) || // Check row
                checkLine(0, i, 1, 0) || // Check column
                (i == 0 && checkLine(0, 0, 1, 1)) || // Check diagonal (top-left to bottom-right)
                (i == 0 && checkLine(0, board.length - 1, 1, -1))) { // Check diagonal (top-right to bottom-left)
                return true;
            }
        }
        return false;
    }

    private static boolean checkLine(int row, int col, int rowInc, int colInc) {
        char player = board[row][col];
        for (int i = 0; i < board.length; i++) {
            if (board[row][col] != player) {
                return false;
            }
            row += rowInc;
            col += colInc;
        }
        return true;
    }

    private static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else if (currentPlayer == 'O' && numPlayers == 3) {
            currentPlayer = 'E'; // E for third player
        } else {
            currentPlayer = 'X';
        }
    }
}

