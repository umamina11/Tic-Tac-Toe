import java.util.Scanner;

public class TicTacToe {
    // Define the game board as a 2D array
    private char[][] board;
    // Define the current player (X or O)
    private char currentPlayer;

    // Constructor to initialize the game
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Method to initialize the game board with empty spaces
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Method to print the game board
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if the game is over
    public boolean isGameOver() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Method to check if any row has the same symbol
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    // Method to check if any column has the same symbol
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        return false;
    }

    // Method to check if any diagonal has the same symbol
    private boolean checkDiagonals() {
        return ((board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
                || (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]));
    }

    // Method to switch players after each turn
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Method to place a symbol on the board
    public void placeSymbol(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            switchPlayer();
        } else {
            System.out.println("Invalid move! Try again.");
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (!game.isGameOver()) {
            System.out.println("Current board:");
            game.printBoard();
            System.out.println("Player " + game.currentPlayer + ", enter your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            game.placeSymbol(row, col);
        }

        System.out.println("Game over!");
        System.out.println("Final board:");
        game.printBoard();
        System.out.println("Player " + game.currentPlayer + " wins!");
        scanner.close();
    }
}
