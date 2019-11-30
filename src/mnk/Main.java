package mnk;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // MENU
        System.out.println("Menu");
        System.out.println("[1] Singleplayer");
        System.out.println("[2] Multiplayer");
        System.out.println("Choose item:");
        while (true) {
            try {
                int point = (new Scanner(in.nextLine())).nextInt();
                int result;
                switch (point) {
                    case 1:
                        final Game game = new Game(false, new HumanPlayer(), new WinnerPlayer());
                        result = game.play(new ServerBoard(inputMnk(in)));
                        System.out.println("Game result: " + result);
                        return;
                    case 2:
                        int np = inputNumP(in);
                        List<Player> players = inputPlayers(in, np);
                        System.out.println(players);
                        /*
                        final MultiGame game = new MultiGame(false, new HumanPlayer(), new WinnerPlayer());
                        result = game.play(new ServerBoard(inputMnk(in)));
                        System.out.println("Game result: " + result);
                        */
                        return;
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                incorInp();
            }
        }
    }

    public static int inputNumP(Scanner in) {
        int np;
        System.out.println("Please, input number of players:");
        while (true) {
            try {
                np = (new Scanner(in.nextLine())).nextInt();
                if (np < 1)
                    throw new InputMismatchException();
                if (np == 1)
                    System.out.println("Interesting fact. Andre Plotnikov thinks that it isn't game");
                return np;
            } catch (InputMismatchException e) {
                incorInp();
            }
        }
    }

    public static void printPlayers() {
        System.out.println("Input all players. Choose from list:");
        System.out.println("[1] HumanPlayer");
        System.out.println("[2] RandomPlayer");
        System.out.println("[3] SequentialPlayer");
        System.out.println("[4] WinnerPlayer (experimental)");
    }

    public static List<Player> inputPlayers(Scanner in, int np) {
        printPlayers();
        List<Player> players = new ArrayList<>();
        players.add(inputOnePlayer(in));
        for (int i = 1; i < np; i++)
            players.add(inputOnePlayer(in));
        return players;
    }

    public static Player inputOnePlayer(Scanner in) {
        while (true) {
            try {
                int numPl;
                if (in.hasNextInt())
                    numPl = in.nextInt();
                else
                    numPl = (new Scanner(in.nextLine())).nextInt();

                switch (numPl) {
                    case 1:
                        return new HumanPlayer();
                    case 3:
                        return new SequentialPlayer();
                    case 4:
                        return new WinnerPlayer();
                    case 2:
                        return new RandomPlayer();
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                incorInp();
            }
        }
    }

    public static MnkConst inputMnk(Scanner in) {
        int m, n, k;
        System.out.println("Enter natural numbers m, n, k:");
        while (true) {
            try (
                    Scanner line = new Scanner(in.nextLine())
            ) {
                m = line.nextInt();
                n = line.nextInt();
                k = line.nextInt();
                if (m < 1 || n < 1 || k < 1)
                    throw new InputMismatchException();
                return new MnkConst(m, n, k);
            } catch (NoSuchElementException e) {
                incorInp();
            }
        }
    }

    public static void incorInp() {
        System.out.println("Incorrect input. Please, try again");
    }
}
