package mnk;

import java.util.*;

public class Main {
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);

        while (true) {
            // MENU
            System.out.println("Menu");
            System.out.println("[1] Singleplayer");
            System.out.println("[2] Multiplayer (Bot fights are allowed)");
            System.out.println("Choose item:");

            int result;
            mods mods;
            Game game;
            while (true) {
                try {
                    int point = (new Scanner(in.nextLine())).nextInt();
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
                    break;
                } catch (NoSuchElementException e) {
                    incorInp();
                }
            }
            result = game.play(new ServerBoard(inputMnk(), mods));
            printRes(result);

            //restart game
            if (!inputYN("Do you want play again? (y/n)"))
                return;
        }
    }

    private static void printRes(int result) {
        if (result == 0)
            System.out.println("Game result: Draw");
        else if (result > 0)
            System.out.println("Game result: win player " + result + ":" + Cell.values()[result - 1]);
        else
            System.out.println("Game result: player " + result + ":" + Cell.values()[result - 1] + " is cheater");
    }

    private static boolean inputYN(String message) {
        System.out.println(message);
        while (true) {
            try {
                String cl = in.nextLine();
                switch (cl) {
                    case "y":
                        return true;
                    case "n":
                        return false;
                    default:
                        throw new NoSuchElementException();
                }
            } catch (NoSuchElementException e) {
                incorInp();
            }
        }
    }

    private static mods inputMods(boolean singlePlayer) {
        if (inputYN("Do you want play in classic? (y/n)")) {
            if (singlePlayer)
                return new mods();
            else
                return new mods(typeOfBoard.Square, inputNumP());
        } else {
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

    private static void printPlayers() {
        System.out.println("[1] HumanPlayer");
        System.out.println("[2] RandomPlayer");
        System.out.println("[3] SequentialPlayer");
        System.out.println("[4] WinnerPlayer (experimental)");
    }

    private static void printBoards() {
        System.out.println("[1] Square");
        System.out.println("[2] Rhombus");
    }

    private static ArrayList<Player> inputPlayers(int np) {
        System.out.println("Input " + np + " players. Choose from list:");
        printPlayers();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < np; i++)
            players.add(inputOnePlayer());
        in.nextLine();
        return players;
    }

    private static Player inputOnePlayer() {
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

    private static MnkConst inputMnk() {
        int m, n, k;
        System.out.println("Enter natural numbers m, n, k less then 200:");
        while (true) {
            try (
                    Scanner line = new Scanner(in.nextLine())
            ) {
                m = line.nextInt();
                n = line.nextInt();
                k = line.nextInt();
                if (m > 200 || n > 200) {
                    incorInp("Your numbers are to big.");
                    continue;
                }
                if (m < 1 || n < 1 || k < 1)  {
                    incorInp("Your numbers are not natural.");
                    continue;
                }
                if (k > Math.max(m,n)) {
                    System.out.println("Your k number is biggest. I think, that result of game will be DRAW.");
                    if (inputYN("Reduce k value to maximum from m and n? (y/n)"))
                        k = Math.max(m,n);
                }
                return new MnkConst(m, n, k);
            } catch (NoSuchElementException e) {
                incorInp();
            }
        }
    }

    private static void incorInp(String message) {
        System.out.println(message + " Please, try again");
    }

    private static void incorInp() {
        System.out.println("Incorrect input. Please, try again");
    }
}
