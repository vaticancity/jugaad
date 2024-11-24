import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeAI {

    static String[] board = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
    static String huPlayer = "X";
    static String aiPlayer = "O";
    static int round = 0;

    public static void main(String[] args) {
        reset();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter your move (0-8): ");
                int userMove = scanner.nextInt();
                if (userMove < 0 || userMove > 8 || !board[userMove].equals(" ")) {
                    System.out.println("Invalid move. Try again.");
                } else {
                    move(userMove, huPlayer);
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between 0 and 8.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public static void printBoard() {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(" " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " ");
            if (i < 6) {
                System.out.println("-----------");
            }
        }
    }

    public static void move(int elementId, String player) {
        if (board[elementId].equals(" ")) {
            round++;
            board[elementId] = player;
            printBoard();

            if (winning(board, player)) {
                if (player.equals(huPlayer)) {
                    System.out.println("YOU WIN!");
                } else {
                    System.out.println("YOU LOSE!");
                }
                reset();
                return;
            } else if (round > 8) {
                System.out.println("TIE!");
                reset();
                return;
            } else if (player.equals(huPlayer)) {
                round++;
                Move bestMove = minimax(board, aiPlayer);
                board[bestMove.index] = aiPlayer;
                System.out.println("AI moves:");
                printBoard();

                if (winning(board, aiPlayer)) {
                    System.out.println("YOU LOSE!");
                    reset();
                } else if (round == 9) {
                    System.out.println("TIE!");
                    reset();
                }
            }
        }
    }

    public static void reset() {
        round = 0;
        board = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};
        printBoard();
    }

    public static Move minimax(String[] newBoard, String player) {
        List<Integer> availableSpots = new ArrayList<>();
        for (int i = 0; i < newBoard.length; i++) {
            if (newBoard[i].equals(" ")) {
                availableSpots.add(i);
            }
        }

        if (winning(newBoard, huPlayer)) {
            return new Move(-1, -10); // -1 is dummy index
        } else if (winning(newBoard, aiPlayer)) {
            return new Move(-1, 10); // -1 is dummy index
        } else if (availableSpots.isEmpty()) {
            return new Move(-1, 0); // -1 is dummy index
        }

        List<Move> moves = new ArrayList<>();
        for (int spot : availableSpots) {
            String originalValue = newBoard[spot];
            newBoard[spot] = player;

            Move move = new Move(spot, 0);
            if (player.equals(aiPlayer)) {
                move.score = minimax(newBoard, huPlayer).score;
            } else {
                move.score = minimax(newBoard, aiPlayer).score;
            }

            newBoard[spot] = originalValue; // Undo the move
            moves.add(move);
        }

        Move bestMove = null;
        if (player.equals(aiPlayer)) {
            int bestScore = Integer.MIN_VALUE;
            for (Move m : moves) {
                if (m.score > bestScore) {
                    bestScore = m.score;
                    bestMove = m;
                }
            }
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (Move m : moves) {
                if (m.score < bestScore) {
                    bestScore = m.score;
                    bestMove = m;
                }
            }
        }

        return bestMove;
    }

    public static boolean winning(String[] board, String player) {
        return (board[0].equals(player) && board[1].equals(player) && board[2].equals(player)) ||
                (board[3].equals(player) && board[4].equals(player) && board[5].equals(player)) ||
                (board[6].equals(player) && board[7].equals(player) && board[8].equals(player)) ||
                (board[0].equals(player) && board[3].equals(player) && board[6].equals(player)) ||
                (board[1].equals(player) && board[4].equals(player) && board[7].equals(player)) ||
                (board[2].equals(player) && board[5].equals(player) && board[8].equals(player)) ||
                (board[0].equals(player) && board[4].equals(player) && board[8].equals(player)) ||
                (board[2].equals(player) && board[4].equals(player) && board[6].equals(player));
    }

    static class Move {
        int index;
        int score;

        Move(int index, int score) {
            this.index = index;
            this.score = score;
        }
    }
}
