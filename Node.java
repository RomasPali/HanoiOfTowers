package hanoi;

public class Node {

	public static final int BOARD_SIZE = 3;
	public static final int INVALID_VALUE = -1;
	
	public static final int POLE_0 = 0;
	public static final int POLE_1 = 1;
	public static final int POLE_2 = 2;
	
	public static final int DISK_EMPTY = 0;
	public static final int DISK_SMALL = 1;
	public static final int DISK_MEDIUM = 2;
	public static final int DISK_BIG = 3;
	
	
	private Node parent;

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

	public Node() {

	}

	public void setPoleValues(int pole, int bot, int mid, int top) {
		board[0][pole] = top;
		board[1][pole] = mid;
		board[2][pole] = bot;
	}

	public boolean canMove(int poleFrom, int poleTo) {

		if (poleFrom == poleTo) {
			return false;
		}

		int poleFromValue = getTopDisk(poleFrom);
		int poleToValue = getTopDisk(poleTo);

		if ((poleFromValue > DISK_EMPTY) && ((poleToValue > poleFromValue) || poleToValue == DISK_EMPTY)) {
			return true;
		}

		return false;
	}

	public void makeMove(int poleFrom, int poleTo) {

		int poleFromTopDiskIndex = getTopDiskIndex(poleFrom);
		int poleToTopFreeIndex = getEmptyPoleIndex(poleTo);

		if (poleToTopFreeIndex != INVALID_VALUE && poleFromTopDiskIndex != INVALID_VALUE) {
			board[poleToTopFreeIndex][poleTo] = board[poleFromTopDiskIndex][poleFrom];
			board[poleFromTopDiskIndex][poleFrom] = DISK_EMPTY;
		}
	}

	public int getEmptyPoleIndex(int pole) {
		for (int i = BOARD_SIZE - 1; i >= 0; i--) {
			if (board[i][pole] == DISK_EMPTY) {
				return i;
			}
		}

		return INVALID_VALUE;
	}

	public int getTopDiskIndex(int pole) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][pole] != DISK_EMPTY) {
				return i;
			}
		}

		return INVALID_VALUE;
	}

	public int getTopDisk(int pole) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][pole] != DISK_EMPTY) {
				return board[i][pole];
			}
		}

		return 0;
	}

	public boolean isEqual(Node node) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (node.getBoardValue(i, j) != board[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	public int getBoardValue(int i, int j) {
		return board[i][j];
	}

	public void setBoardValue(int i, int j, int value) {
		board[i][j] = value;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				result += board[i][j];
			}
			result += "\n";
		}
		return result;
	}

}
