package mnk;

final class mods {
    final typeOfBoard tob;
    final int nop;

    mods(typeOfBoard tob, int nop) {
        this.tob = tob;
        this.nop = nop;
    }

    mods() {
        this.tob = typeOfBoard.Square;
        this.nop = 2;
    }
}
