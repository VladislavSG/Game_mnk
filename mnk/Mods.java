package mnk;

final class Mods {
    final typeOfBoard tob;
    final int nop;

    Mods(typeOfBoard tob, int nop) {
        this.tob = tob;
        this.nop = nop;
    }

    Mods() {
        this.tob = typeOfBoard.Square;
        this.nop = 2;
    }
}
