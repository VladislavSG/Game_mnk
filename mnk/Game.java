package mnk;

import java.util.ArrayList;

public class Game {
    private final boolean log;
    private final ArrayList<Player> players;

    public Game(final boolean log, final ArrayList<Player> players) {
        this.log = log;
        this.players = players;
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < players.size() ; i++) {
                final int result = move(board, players.get(i), i+1);
                if (result != Integer.MIN_VALUE) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        if (board.getNumEmpty() <= 0)
            return 0;
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return -no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
