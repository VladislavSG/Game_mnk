package mnk;

public interface Position {
    boolean isValid(Move move);
    Cell getCell(int r, int c);
    MnkConst getSettings();
    String toString();
    mods getMods();
}
