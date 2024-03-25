import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer = PLAYER_X;

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (!isGameOver()) {
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3], column [1-3]): ");
            
            // Debugging: Print the row and column numbers
            // System.out.println("DEBUG: Row and Column numbers entered:");

            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;

            // Debugging: Print the row and column numbers
            // System.out.println("DEBUG: Row: " + (row + 1) + ", Column: " + (col + 1));

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                displayBoard();
                if (checkWin()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    return;
                }
                if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    return;
                }
                currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    // Initialize the game board with empty cells
    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Display the current state of the game board
    private static void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("---------");
            }
        }
    }

    // Check if the move is valid (within the board and cell is empty)
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    // Check if the board is full (no more empty cells)
    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if any player has won the game
    private static boolean checkWin() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    // Check if any row contains the same player's mark
    private static boolean checkRows() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    // Check if any column contains the same player's mark
    private static boolean checkColumns() {
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] != EMPTY && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    // Check if any diagonal contains the same player's mark
    private static boolean checkDiagonals() {
        return (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
               (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    // Check if the game is over (either a win or a draw)
    private static boolean isGameOver() {
        return checkWin() || isBoardFull();
    }
}
