package mnk;

import java.util.Arrays;
import java.util.Map;

public class ServerBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.A, 'A',
            Cell.U, 'U',
            Cell.BLOCK, ' ',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final MnkConst settings;
    private int kolEmpty;
    private final mods mods;

    public ServerBoard(MnkConst settings, mods mods) {
        this.mods = mods;
        this.settings = settings;
        this.cells = new Cell[settings.M][settings.N];
        this.kolEmpty = settings.M * settings.N;
        switch (this.mods.tob) {
            case Rhombus:
                for (int r = 0; r < settings.M; r++) {
                    for (int c = 0; c < settings.N; c++) {
                        int dist = Math.abs(2*r-settings.M+1)*settings.N +
                                     Math.abs(2*c-settings.N+1)*settings.M;
                        if (dist <= settings.N*settings.M+Math.min(settings.N, settings.M) - 1)
                            cells[r][c] = Cell.E;
                        else
                            cells[r][c] = Cell.BLOCK;
                    }
                }
                break;
            default:
                for (Cell[] row : cells) {
                    Arrays.fill(row, Cell.E);
                }
                break;
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return new PlayerBoard(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        kolEmpty--;

        if (lastWinTurn(move.getRow(), move.getColumn())) {
            return Result.WIN;
        }
        if (kolEmpty == 0) {
            return Result.DRAW;
        }

        turn = Cell.values()[(turn.ordinal() + 1) % mods.nop];
        return Result.UNKNOWN;
    }

    private boolean lastWinTurn(int row, int column) {
        if (kolInLine(row, column, 1, 1) +
                kolInLine(row, column, -1, -1) > settings.K ||
            kolInLine(row, column, -1, 1) +
                kolInLine(row, column, 1, -1) > settings.K ||
            kolInLine(row, column, 0, 1) +
                kolInLine(row, column, 0, -1) > settings.K ||
            kolInLine(row, column, 1, 0) +
                kolInLine(row, column, -1, 0) > settings.K
        ) {
            return true;
        } else {
            return false;
        }
    }

    private int kolInLine(int row, int column, int drow, int dcolumn) {
        int kol = 0;
        int i = row;
        int j = column;
        while (i >= 0 && j >= 0 && i < settings.M && j < settings.N && cells[i][j] == cells[row][column]) {
            kol++;
            i += drow;
            j += dcolumn;
        }
        return kol;
    }


    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < settings.M
                && 0 <= move.getColumn() && move.getColumn() < settings.N
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public MnkConst getSettings() {
        return settings;
    }

    @Override
    public String toString() {
        int shG = ((Integer)(settings.N - 1)).toString().length() + 1;
        final StringBuilder sb = new StringBuilder(String.format("%"+shG+"s", " "));

        for (int i = 0; i < settings.N; i++)
            sb.append(String.format("%"+shG+"s", i));
        for (int r = 0; r < settings.M; r++) {
            sb.append("\n");
            sb.append(String.format("%"+shG+"s", r));
            for (int c = 0; c < settings.N; c++) {
                sb.append(String.format("%"+shG+"s", SYMBOLS.get(cells[r][c])));
            }
        }
        return sb.toString();
    }

    @Override
    public mods getMods() {
        return mods;
    }
}
