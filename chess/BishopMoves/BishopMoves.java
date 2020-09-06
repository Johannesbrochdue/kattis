import java.util.Scanner;


public class BishopMoves {
    
    private static Scanner scanner = new Scanner(System.in);
    private static String[][] chessBoard = new String[8][8];
    private static String[] black = new String[32];
    private static String[] white = new String[32];
    private static String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H" };
    private static int[] numbers = { 8, 7, 6, 5, 4, 3, 2, 1 };
    public static void main(String[] args) {
        createChessBoard();
        // printBlack();
        // printWhite();
        int loopNumber = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < loopNumber; i++) {
            possibleMove(scanner.nextLine());
        }
    }

    private static void possibleMove(String input) {
        String[] moveInfo = input.split(" ");
        String startPos = moveInfo[0] + moveInfo[1];
        String endPos = moveInfo[2] + moveInfo[3];
        //System.out.println("\nSTARPOS: " + startPos);
        //System.out.println("ENDPOS: " + endPos + "\n");
        String bishopColor = isDifferentColor(startPos, endPos);
        if (!isValidSquare(startPos) || !isValidSquare(endPos)) {
            printImpossible();
        }
        else if (startPos.equals(endPos)) {
            System.out.println("0 " + buildString(startPos));
        } else if (bishopColor.equals("DIFFERENT")) {
            //System.out.println("\nSQUARES HAVE DIFFERENT COLOR");
            printImpossible();
        } else {
            //findMove(startPos, endPos);
            findMove(startPos, endPos);
        }

    }

    private static void createChessBoard() {
        
        int b = 0;
        int w = 0;
        for (int rad = 7; rad >= 0; rad--) {
            for (int kol = 7; kol >= 0; kol--) {
                chessBoard[rad][kol] = letters[kol] + String.valueOf(numbers[rad]);
                if (rad % 2 == 0 && kol % 2 == 0) {
                    white[w] = letters[kol] + String.valueOf(numbers[rad]);
                    w++;
                } else if (rad % 2 != 0 && kol % 2 != 0) {
                    white[w] = letters[kol] + String.valueOf(numbers[rad]);
                    w++;
                } else if (rad % 2 == 0 && kol % 2 != 0) {
                    black[b] = letters[kol] + String.valueOf(numbers[rad]);
                    b++;
                } else {
                    black[b] = letters[kol] + String.valueOf(numbers[rad]);
                    b++;
                }
            }
        }

    }
    
    private static String isDifferentColor(String startPos, String endPos) {
        String startColor = "a";
        String endColor = "b";

        for (String s : black) {
            if (s.equals(startPos)) {
                startColor = "BLACK";
            } else if (s.equals(endPos)) {
                endColor = "BLACK";
            }
        }
        if (startColor.equals(endColor)) {
            return "BLACK";
        }
        for (String s : white) {
            if (s.equals(startPos)) {
                startColor = "WHITE";
            } else if (s.equals(endPos)) {
                endColor = "WHITE";
            }
        }
        if (startColor.equals(endColor)) {
            return "WHITE";
        }
        return "DIFFERENT";
    }


    private static void findMove(String startPos, String endPos) {
        int noMoves = 0;
        int startRow = 0;
        int startKol = 0;
        int endRow = 0;
        int endKol = 0;
        int verticalMove = 0;
        int horizontalMove = 0;

        for (int rad = 7; rad >= 0; rad--) {
            for (int kol = 7; kol >= 0; kol--) {
                if (chessBoard[rad][kol].equals(startPos)) {
                    startRow = rad;
                    startKol = kol;
                } else if (chessBoard[rad][kol].equals(endPos)) {
                    endRow = rad;
                    endKol = kol;
                }
            }
        }

        if (isOnDiagonal(startRow, startKol, endRow, endKol)) {
            System.out.println("1 " + buildString(startPos, endPos));
        }
        
        verticalMove = startRow - endRow;
        horizontalMove = startKol - endKol;

        double hyp = sqrt(square(verticalMove) + square(horizontalMove));

        int middleCol = 0;
        int middleRow = 0;
        double k1 = 0;
        double k2 = 0;

        for (int row = 7; row >= 0; row--) {
            for (int kol = 7; kol >= 0; kol--) {
                k1 = getK1(row, kol, endRow, endKol);
                k2 = getK2(row, kol, startRow, startKol);
                // System.out.println("\nrow : " + row + " kol: " + kol);
                // System.out.println("k^2 + k^2 = " + (Math.pow(k1, 2) + Math.pow(k2, 2)));
                // System.out.println("Hyp^2 = " + Math.pow(hyp, 2));
                // System.out.println("k1 = " + k1 + " k2 = " + k2);
                // System.out.println("Startpos: " + startPos);
                // System.out.println("Endpos: " + endPos);
                // System.out.println("Current square: " + chessBoard[row][kol]);
                int k2k2 = (int) Math.round(square(k1) + square(k2));
                if (k2k2 == (int) Math.round(Math.pow(hyp, 2)) && k1 != 0 && k2 != 0) {
                    if (isOnDiagonal(row, kol, startRow, startKol, endRow, endKol)) {
                        System.out.println("2 " + buildString(startPos, chessBoard[row][kol], endPos));
                        return;
                    }
                }
            }
        }
    }

    private static String buildString(String startPos, String middlePos, String endPos) {
        String start = startPos.charAt(0) + " " + startPos.charAt(1);
        String middle = middlePos.charAt(0) + " " + middlePos.charAt(1);
        String end = endPos.charAt(0) + " " + endPos.charAt(1);
        return start + " " + middle + " " + end;

    }

    private static String buildString(String startPos, String endPos) {
        String start = startPos.charAt(0) + " " + startPos.charAt(1);
        String end = endPos.charAt(0) + " " + endPos.charAt(1);
        return start + " " + end;

    }

    private static String buildString(String startPos) {
        return startPos.charAt(0) + " " + startPos.charAt(1);
    }
    
    private static double getK1(int row, int kol, int endRow, int endKol) {
        if (row > endRow) {
            if (kol > endKol) {
                return sqrt(square(row - endRow) + square(kol - endKol));
            } else if (kol < endKol) {
                return sqrt(square(row - endRow) + square(endKol - kol));
            } else {
                return sqrt(square(row - endRow));
            }
        } else if (row < endRow) {
            if (kol > endKol) {
                return sqrt(square(endRow - row) + square(kol - endKol));
            } else if (kol < endKol) {
                return sqrt(square(endRow - row) + square(endKol - kol));
            } else {
                return sqrt(endRow - row);
            }
        } else {
            if (kol > endKol) {
                return sqrt(kol - endKol);
            } else if (kol < endKol) {
                return sqrt(endKol - kol);
            } else {
                return 0;
            }
        }
    }

    private static double getK2(int row, int kol, int startRow, int startKol) {
        if (row > startRow) {
            if (kol > startKol) {
                return sqrt(square(row - startRow) + square(kol - startKol));
            } else {
                return sqrt(square(row - startRow) + square(startKol - kol));
            }
        } else {
            if (kol > startKol) {
                return sqrt(square(row - startRow) + square(kol - startKol));
            } else {
                return sqrt(square(row - startRow) + square(startKol - kol));
            }
        }
    }
    
    private static boolean isValidSquare(String chessPos) {
        for (int row = 0; row < 8; row++) {
            for (int kol = 0; kol < 8; kol++) {
                if (chessBoard[row][kol].equals(chessPos.replace(" ", ""))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isOnDiagonal(int startRow, int startKol, int endRow, int endKol) {
        return (Math.abs(endRow - startRow)) == (Math.abs(endKol - startKol));
    }
    
    private static boolean isOnDiagonal(int middleRow, int middleKol, int startRow, int startKol, int endRow,
            int endKol) {
        
        return (Math.abs(middleRow - startRow)) == (Math.abs(middleKol - startKol)) && (Math.abs(endRow - middleRow)) == (Math.abs(endKol - middleKol));
    }

    private static double square(double number) {
        return Math.pow(number, 2);
    }

    private static double sqrt(double number) {
        return Math.sqrt(number);
    }
    
    private static void printImpossible() {
        System.out.println("Impossible");
    }
    
    private static void printBoard() {
        for (int rad = 0; rad < 8; rad++) {
            System.out.println("");
            for (int kol = 0; kol < 8; kol++) {
                System.out.print(chessBoard[rad][kol]);
            }
        }
    }

    private static void printBlack() {
        System.out.println("\n\nBLACK: \n");
        for (String square : black) {
            System.out.print(square);
        }
    }

    private static void printWhite() {
        System.out.println("\n\nWHITE: \n");
        for (String square : white) {
            System.out.print(square);
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}