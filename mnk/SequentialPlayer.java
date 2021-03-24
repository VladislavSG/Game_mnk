package mnk;

public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        // Board board = (Board) position;
        // board.makeMove();
        for (int r = 0; r < position.getSettings().M; r++) {
            for (int c = 0; c < position.getSettings().N; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
