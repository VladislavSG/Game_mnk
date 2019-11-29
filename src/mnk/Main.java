package mnk;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game(false, new HumanPlayer(), new WinnerPlayer());
        Scanner in = new Scanner(System.in);

        // MNK
        int m, n, k;
        System.out.println("Enter natural numbers m, n, k");
        while (true) {
            try (
                    Scanner line = new Scanner(in.nextLine())
            ) {
                m = line.nextInt();
                n = line.nextInt();
                k = line.nextInt();
                if (m < 1 || n < 1 || k < 1)
                    throw new InputMismatchException();
                break;
            } catch (NoSuchElementException e) {
                incorInp();
            }
        }

        // MENU
        System.out.println("Menu");
        System.out.println("[1] Singleplayer");
        System.out.println("[2] Multiplayer");
        System.out.println("Choose item:");
        while (true) {
            try {
                int point = (new Scanner(in.nextLine())).nextInt();
                switch (point) {
                    case 1:
                        int result = game.play(new ServerBoard(new MnkConst(m, n, k)));
                        System.out.println("Game result: " + result);
                        return;
                    case 2:
                        System.out.println("Multiplayer is under development. Choose another item");
                        break;
                    default:
                        incorInp();
                }
            } catch (InputMismatchException e) {
                incorInp();
            }
        }
    }



    public static void incorInp() {
        System.out.println("Incorrect input. Please, try again");
    }
}
