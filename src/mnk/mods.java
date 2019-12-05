package mnk;

public final class mods {
    public final typeOfBoard tob;
    public final int nop;

    public mods(typeOfBoard tob, int nop) {
        this.tob = tob;
        this.nop = nop;
    }

    public mods() {
        this.tob = typeOfBoard.Square;
        this.nop = 2;
    }
}
