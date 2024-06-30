import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize the game board
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the game board
    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Check if the current player has won the game
    private boolean isWinner() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check if there's a winning row
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    // Check if there's a winning column
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    // Check if there's a winning diagonal
    private boolean checkDiagonals() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    // Check if all three values are the same (and not empty) indicating a win
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // Check if the game board is full
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Switch the player after each turn
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Place a mark on the game board
    private void placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
        } else {
            System.out.println("Invalid move! Try again.");
            switchPlayer();
        }
    }

    // Main game loop
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's play Tic-Tac-Toe!");
        while (true) {
            System.out.println("Current board:");
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            placeMark(row, col);
            if (isWinner()) {
                System.out.println("Congratulations! Player " + currentPlayer + " wins!");
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }
            switchPlayer();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}
