package mnk;

public class PlayerBoard implements Position {
    private final Position posBoard;

    public PlayerBoard (Position board) {
        posBoard = board;
    }

    @Override
    public boolean isValid(Move move) {
        return posBoard.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return posBoard.getCell(r, c);
    }

    @Override
    public MnkConst getSettings() {
        return posBoard.getSettings();
    }

    @Override
    public String toString() {
        return posBoard.toString();
    }

    @Override
    public mods getMods() {
        return posBoard.getMods();
    }


}
