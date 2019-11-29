package mnk;

public final class MnkConst {
    public final int M;
    public final int N;
    public final int K;

    public MnkConst (int M, int N, int K) {
        this.M = M;
        this.N = N;
        this.K = K;
    }

    public MnkConst () {
        this(5, 5, 3);
    }
}