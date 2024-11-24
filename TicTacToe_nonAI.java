import java.util.Scanner;

public class Tut_1_tictactoe_nonAI {
    static int brk = 0;
    static char[] row1 = { '_', '_', '_' };
    static char[] row2 = { '_', '_', '_' };
    static char[] row3 = { '_', '_', '_' };

    static int trywin(char[] chars) {
        if (chars[0] == 'X' && chars[1] == 'X' && chars[2] == 'X') {
            System.out.println("You win!");
            brk = 1;
            return 1;
        }
        if (chars[0] == 'O' && chars[1] == 'O' && chars[2] == 'O') {
            System.out.println("Computer wins!");
            brk = 1;
            return 1;
        }
        if (chars[0] == 'O' && chars[1] == 'O' && chars[2] == '_') {
            chars[2] = 'O';
            System.out.println("Computer wins!");
            brk=1;
            return 1;
        }
        if (chars[0] == 'O' && chars[1] == '_' && chars[2] == 'O') {
            chars[1] = 'O';
            System.out.println("Computer wins!");
            brk=1;
            return 1;
        }
        if (chars[0] == '_' && chars[1] == 'O' && chars[2] == 'O') {
            chars[0] = 'O';
            System.out.println("Computer wins!");
            brk=1;
            return 1;
        }
        return 0;
    }

    static void analyse() {
        int played = 0;
        int win = 0;
        char[] chars = new char[3];

        // row 1
        chars[0] = row1[0];
        chars[1] = row1[1];
        chars[2] = row1[2];
        win = trywin(chars);
        if (win == 1) {
            row1[0] = chars[0];
            row1[1] = chars[1];
            row1[2] = chars[2];
            return;
        }

        // row 2
        chars[0] = row2[0];
        chars[1] = row2[1];
        chars[2] = row2[2];
        win = trywin(chars);
        if (win == 1) {
            row2[0] = chars[0];
            row2[1] = chars[1];
            row2[2] = chars[2];
            return;
        }

        // row 3
        chars[0] = row3[0];
        chars[1] = row3[1];
        chars[2] = row3[2];
        win = trywin(chars);
        if (win == 1) {
            row3[0] = chars[0];
            row3[1] = chars[1];
            row3[2] = chars[2];
            return;
        }

        // col 1
        chars[0] = row1[0];
        chars[1] = row2[0];
        chars[2] = row3[0];
        win = trywin(chars);
        if (win == 1) {
            row1[0] = chars[0];
            row2[0] = chars[1];
            row3[0] = chars[2];
            return;
        }

        // col 2
        chars[0] = row1[1];
        chars[1] = row2[1];
        chars[2] = row3[1];
        win = trywin(chars);
        if (win == 1) {
            row1[1] = chars[0];
            row2[1] = chars[1];
            row3[1] = chars[2];
            return;
        }

        // col 3
        chars[0] = row1[2];
        chars[1] = row2[2];
        chars[2] = row3[2];
        win = trywin(chars);
        if (win == 1) {
            row1[2] = chars[0];
            row2[2] = chars[1];
            row3[2] = chars[2];
            return;
        }

        // diagonal 1
        chars[0] = row1[0];
        chars[1] = row2[1];
        chars[2] = row3[2];
        win = trywin(chars);
        if (win == 1) {
            row1[0] = chars[0];
            row2[1] = chars[1];
            row3[2] = chars[2];
            return;
        }

        // diagonal 2
        chars[0] = row1[2];
        chars[1] = row2[1];
        chars[2] = row3[0];
        win = trywin(chars);
        if (win == 1) {
            row1[2] = chars[0];
            row2[1] = chars[1];
            row3[0] = chars[2];
            return;
        }

        if (played == 0 && win == 0) {
            if (row2[1] == '_' && row2[1] != 'X') {
                row2[1] = 'O';          //center
            } else if (row1[0] == '_' && row1[0] != 'X') {
                row1[0] = 'O';          //top left corner
            } else if (row3[2] == '_' && row3[2] != 'X') {
                row3[2] = 'O';          //bottom right corner
            } else if (row1[2] == '_' && row1[2] != 'X') {
                row1[2] = 'O';           //top right corner
            } else if (row3[0] == '_' && row3[0] != 'X') {
                row3[0] = 'O';           //bottom left corner
            } else if (row3[1] == '_' && row3[1] != 'X') {
                row3[1] = 'O';           // bottom center
            } else if (row1[1] == '_' && row1[1] != 'X') {
                row1[1] = 'O';           //top center
            } else if (row2[0] == '_' && row2[0] != 'X') {
                row2[0] = 'O';           // middle left
            } else if (row2[2] == '_' && row2[2] != 'X') {
                row2[2] = 'O';           // middle right
            }
        }
    }

    static void display() {
        for (char a : row1) {
            System.out.print(a + " ");
        }
        System.out.println();
        for (char a : row2) {
            System.out.print(a + " ");
        }
        System.out.println();
        for (char a : row3) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----Start----");
        System.out.println("Play your move by entering numbers between 1 to 9\n1 -> top left corner and 9 -> bottom right corner");

        System.out.println("1 2 3");
        System.out.println("4 5 6");
        System.out.println("7 8 9");

        int moveCount = 0;

        System.out.print("Do you want to play first [y/n]? ");
        char playFirst = scanner.next().charAt(0);

        if (playFirst == 'n') {
            System.out.println("Computer's move ....");
            analyse();
            display();
            moveCount++;
        }

        while (moveCount < 9) {
            // player's move
            System.out.println("Play move ...");
            int move = scanner.nextInt();

            if (move <= 3) {
                row1[move - 1] = 'X';
            } else if (3 < move && move < 7) {
                if (move == 6) {
                    row2[2] = 'X';
                } else {
                    move = move % 3;
                    row2[move - 1] = 'X';
                }
            } else {
                if (move == 9) {
                    row3[2] = 'X';
                } else {
                    move = move % 3;
                    row3[move - 1] = 'X';
                }
            }

            moveCount++;
            if (brk == 1) {
                break;
            }
            display();
            if (moveCount == 9) {
                break;
            }

            System.out.println("Computer's move ....");
            analyse();
            display();
            moveCount++;
            if (brk == 1) {
                break;
            }
        }
        if (moveCount == 9) {
            System.out.println("It's a tie!");
        }
    }
}