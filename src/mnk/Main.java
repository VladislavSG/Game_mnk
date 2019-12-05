package mnk;

import java.util.*;

public class Main {
    public static Scanner in;
    public static void main(String[] args) {
        in = new Scanner(System.in);

        // MENU
        System.out.println("Menu");
        System.out.println("[1] Singleplayer");
        System.out.println("[2] Multiplayer (Bot fights are allowed)");
        System.out.println("Choose item:");
        while (true) {
            try {
                int point = (new Scanner(in.nextLine())).nextInt();
                int result;
                final mods mods;
                final Game game;
                switch (point) {
                    case 1:
                        mods = inputMods(true);
                        System.out.println("Input your enemy player. Choose from list:");
                        printPlayers();
                        game = new Game(false, new ArrayList<>(List.of(new HumanPlayer(), inputOnePlayer())));
                        in.nextLine();
                        break;
                    case 2:
                        mods = inputMods(false);
                        ArrayList<Player> players = inputPlayers(mods.nop);
                        game = new Game(false, players);
                        break;
                    default:
                        throw new NoSuchElementException();
                }
                result = game.play(new ServerBoard(inputMnk(), mods));
                printRes(result);
            } catch (NoSuchElementException e) {
                incorInp();
            }
        }
    }

    private static void printRes(int result) {
        if (result == 0)
            System.out.println("Game result: Draw");
        else
            System.out.println("Game result: win player " + Cell.values()[result - 1]);
    }

    private static mods inputMods(boolean singlePlayer) {
        System.out.println("Do you want play in classic? (y/n)");
        while (true) {
            try {
                String cl = in.nextLine();
                switch (cl) {
                    case "y":
                        if (singlePlayer)
                            return new mods();
                        else
                            return new mods(typeOfBoard.Square, inputNumP());
                    case "n":
                        System.out.println("Input type of board. Choose from list:");
                        printBoards();
                        typeOfBoard tb;
                        while (true) {
                            try {
                                int x = (new Scanner(in.nextLine())).nextInt();
                                if (x > 0 && x < 3) {
                                    tb =  typeOfBoard.values()[x - 1];
                                    break;
                                } else
                                    throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                incorInp();
                            }
                        }
                        int np;
                        if (singlePlayer)
                            np = 2;
                        else
                            np = inputNumP();
                        return new mods(tb, np);
                    default:
                        throw new NoSuchElementException();
                }
            } catch (NoSuchElementException e) {
                incorInp();
            }
        }
    }

    private static int inputNumP() {
        int np;
        System.out.println("Please, input number of players between 2 and 4:");
        while (true) {
            try {
                np = (new Scanner(in.nextLine())).nextInt();
                if (np < 2 || np > 4)
                    throw new NoSuchElementException();
                return np;
            } catch (NoSuchElementException e) {
                incorInp();
            }
        }
    }

    public static void printPlayers() {
        System.out.println("[1] HumanPlayer");
        System.out.println("[2] RandomPlayer");
        System.out.println("[3] SequentialPlayer");
        System.out.println("[4] WinnerPlayer (experimental)");
    }

    public static void printBoards() {
        System.out.println("[1] Square");
        System.out.println("[2] Rhombus");
    }

    public static ArrayList<Player> inputPlayers(int np) {
        System.out.println("Input " + np + " players. Choose from list:");
        printPlayers();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < np; i++)
            players.add(inputOnePlayer());
        in.nextLine();
        return players;
    }

    public static Player inputOnePlayer() {
        while (true) {
            try {
                int numPl;
                numPl = in.nextInt();
                switch (numPl) {
                    case 1:
                        return new HumanPlayer();
                    case 2:
                        return new RandomPlayer();
                    case 3:
                        return new SequentialPlayer();
                    case 4:
                        return new WinnerPlayer();
                    default:
                        throw new NoSuchElementException();
                }
            } catch (NoSuchElementException e) {
                in.nextLine();
                incorInp();
            }
        }
    }

    public static MnkConst inputMnk() {
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

    private static void incorInp() {
        System.out.println("Incorrect input. Please, try again");
    }
}
