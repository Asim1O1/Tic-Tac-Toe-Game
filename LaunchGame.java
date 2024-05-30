import java.util.InputMismatchException;
import java.util.Scanner;

class TicTacToe 
{
    // creating a 2d array
    static char[][] board; // static variable that can be accessed with class

    public TicTacToe() 
    {
        System.out.println("Welcome to the Game TIC Tac Toe");
        board = new char[3][3]; // creates a 2d array
        initializeBoard();
    }

    void initializeBoard() 
    {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void displayBoard() 
    {
        System.out.println("--------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("--------------");
        }
    }

    static void placeMark(int row, int col, char mark) 
    {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) // Checking for correct position
        {
            board[row][col] = mark; // placing mark on the board
        } 
        else {
            System.out.println("The position is invalid.");
        }
    }

    static boolean checkColWin() 
    {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean rowWin() 
    {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean diagonalWin() 
    {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }
}

class HumanPlayer 
{
    String name;
    char mark;

    HumanPlayer(String name, char mark) // Constructor of the HumanPlayer class
    {
        this.name = name;
        this.mark = mark;
    }

    void makeMove() 
    {
        int row;
        int column;
        Scanner userInput = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(name + "'s turn");
                System.out.println("Please provide row and column:");

                row = userInput.nextInt();
                column = userInput.nextInt();

                if (isValidMove(row, column)) {
                    TicTacToe.placeMark(row, column, mark);
                    break;
                } else {
                    System.out.println("Invalid move. The cell is already occupied or out of bounds. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integers for row and column.");
                userInput.next(); // Clear the invalid input
            }
        }
    }

    boolean isValidMove(int row, int col) 
    {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }
}

class LaunchGame 
{
    public static void main(String[] args) 
    {
        TicTacToe obj = new TicTacToe();
        
        HumanPlayer player1 = new HumanPlayer("Player 1", 'X');
        HumanPlayer player2 = new HumanPlayer("Player 2", 'O');
        HumanPlayer currentPlayer = player1;

        while (true) 
        {
            // System.out.println(currentPlayer.name + "'s turn ");
            currentPlayer.makeMove();
            TicTacToe.displayBoard();
            if (TicTacToe.checkColWin() || TicTacToe.rowWin() || TicTacToe.diagonalWin()) 
            {
                System.out.println(currentPlayer.name + " has won!");
                break;
            } 
            else 
            {
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        }
    }
}
