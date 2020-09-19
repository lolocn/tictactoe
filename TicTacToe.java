import java.util.Scanner;

public class TicTacToe {

	private static char PLAYER_X = 'X';
	private static char PLAYER_O = 'O';

	private char[][] board = new char[3][3];

	private char currentPlayer = 'X';

	private void init() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	private void printBoard() {

		System.out.println("******");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j]);
				if (j != 2) {
					System.out.print('|');
				}
			}
			System.out.println();
		}
		System.out.println("******");
	}

	private boolean checkResult() {
		return (checkColumn() || checkRow() || checkDiagonal());
	}

	private boolean checkColumn() {
		for (int i = 0; i < 3; i++) {
			if (checkOnPoint(board[0][i], board[1][i], board[2][i]) == true) {
				return true;
			}
		}
		return false;
	}

	private boolean checkRow() {
		for (int i = 0; i < 3; i++) {
			if (checkOnPoint(board[i][0], board[i][1], board[i][2]) == true) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonal() {
		if ((checkOnPoint(board[0][0], board[1][1], board[2][2]) == true)
				|| (checkOnPoint(board[0][2], board[1][1], board[2][0]) == true)) {
			return true;
		}
		return false;
	}

	private boolean checkOnPoint(char p1, char p2, char p3) {
		if (p1 != ' ' && p1 == p2 && p2 == p3) {
			return true;
		} else {
			return false;
		}
	}

	private void changePlayer() {
		if (currentPlayer == PLAYER_X) {
			currentPlayer = PLAYER_O;
		} else {
			currentPlayer = PLAYER_X;
		}
	}

	public boolean placeMark(int row, int col) {

		if ((row >= 0) && (row < 3)) {
			if ((col >= 0) && (col < 3)) {
				if (board[row][col] == ' ') {
					board[row][col] = currentPlayer;
					return true;
				}
			}
		}
		return false;
	}

	public boolean isFull() {
		boolean result = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					result = false;
				}
			}
		}
		return result;
	}

	public static void runGame(TicTacToe ticTacToe) {
		ticTacToe.init();
		ticTacToe.printBoard();
		boolean isDraw = false;
		while (!ticTacToe.checkResult()) {
			System.out.println("Player " + ticTacToe.currentPlayer + " please play (eg: 1,2)");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			String[] tmp = input.split(",");
			int p1 = Integer.parseInt(tmp[0]);
			int p2 = Integer.parseInt(tmp[1]);

			if (ticTacToe.placeMark(p1, p2)) {
				ticTacToe.printBoard();
				ticTacToe.changePlayer();
				if (ticTacToe.isFull()) {
					System.out.println("Draw game!");
					isDraw = true;
					break;
				}
			} else {
				System.out.println("Cannot put here");
			}

		}
		if (!isDraw) {
			
			ticTacToe.changePlayer();
			System.out.print("Player " + ticTacToe.currentPlayer + " Win!");
		}
	}

	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe();
		runGame(ticTacToe);
		// ticTacToe.placeMark(0, 1);
		// ticTacToe.printBoard();

	}
}
