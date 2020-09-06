import java.util.ArrayList;
import java.util.Scanner;

class NQueens {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            int loopCount = Integer.parseInt(scanner.nextLine());
            ArrayList<ChessPosition> queens = new ArrayList<ChessPosition>();
            for (int i = 0; i < loopCount; i++) {
                String[] queenPosition = scanner.nextLine().split(" ");
                queens.add(new ChessPosition(Integer.parseInt(queenPosition[0]), Integer.parseInt(queenPosition[1])));
            }
            decideCorrectness(queens);
        }
    }
    
    private static void decideCorrectness(ArrayList<ChessPosition> queens) {
        for (ChessPosition q : queens) {
            for (ChessPosition o : queens) {
                if (q != o) {
                    if (isOnDiagonal(q, o) || isOnSameRow(q, o) || isOnSameColumn(q, o)) {
                        System.out.println("INCORRECT");
                        return;
                    }
                }
            }
        }
        System.out.println("CORRECT");
    }

    private static boolean isOnDiagonal(ChessPosition q, ChessPosition o) {
        return (Math.abs(q.x - o.x) == Math.abs(q.y - o.y));
    }
    
    private static boolean isOnSameRow(ChessPosition q, ChessPosition o) {
		return (q.x == o.x);
	}

	private static boolean isOnSameColumn(ChessPosition q, ChessPosition o) {
		return (q.y == o.y);
	}
}

class ChessPosition {

    int y;
    int x;

    public ChessPosition(int kol, int rad) {
        x = kol;
        y = rad;
    }
}