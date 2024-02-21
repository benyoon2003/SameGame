package cs3500.samegame;

import java.io.InputStreamReader;

import cs3500.samegame.controller.SameGameController;
import cs3500.samegame.controller.SameGameTextController;
import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;
import cs3500.samegame.model.hw04.SameGameCreator;

/**
 * SameGame runs the SameGameController and allows the user
 * to enter inputs.
 */
public final class SameGame {
  /**
   * Plays any variant of Same Game using user inputs.
   *
   * @param args an array of String
   */
  public static void main(String[] args) {
    SameGameModel<Piece> model = new FourPieceSameGame();
    int numRows = 10;
    int numCols = 10;
    int numSwaps = 10;
    switch (args.length) {
      case 1:
        model = chooseGame(args[0]);
        break;
      case 2:
        model = chooseGame(args[0]);
        numRows = handleInValidInput(args[1]);
        break;
      case 3:
        model = chooseGame(args[0]);
        numRows = handleInValidInput(args[1]);
        numCols = handleInValidInput(args[2]);
        break;
      case 4:
        model = chooseGame(args[0]);
        numRows = handleInValidInput(args[1]);
        numCols = handleInValidInput(args[2]);
        numSwaps = handleInValidInput(args[3]);
        break;
      default:
        break;
    }

    SameGameController<Piece> controller = new SameGameTextController<>(
            new InputStreamReader(System.in), System.out);
    controller.playGame(model, numRows, numCols, numSwaps, true);
  }

  /**
   * Uses the user input to determine which SameGame variant to return.
   *
   * @param arg String array
   * @return SameGameModel
   * @throws IllegalArgumentException if the input is none of the available variants
   */
  private static SameGameModel<Piece> chooseGame(String arg) throws IllegalArgumentException {
    switch (arg) {
      case "fourpiece":
        return SameGameCreator.createGame(SameGameCreator.GameType.FOURPIECE);
      case "automatch":
        return SameGameCreator.createGame(SameGameCreator.GameType.AUTOMATCH);
      case "gravity":
        return SameGameCreator.createGame(SameGameCreator.GameType.GRAVITY);
      default:
        throw new IllegalArgumentException("Not a valid game type.");
    }
  }

  /**
   * Uses the string given from the user to handle inputs
   * from the user that are supposed to be integers.
   *
   * @param s a String
   * @return int
   */
  private static int handleInValidInput(String s) {
    try {
      int input = Integer.parseInt(s);
      if (input > 0) {
        return input;
      } else {
        return 10;
      }
    } catch (NumberFormatException ignored) {
      return 10;
    }

  }
}
