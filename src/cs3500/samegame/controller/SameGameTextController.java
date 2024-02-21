package cs3500.samegame.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;
import cs3500.samegame.view.SameGameTextView;
import cs3500.samegame.view.SameGameView;

/**
 * A SameGameTextController implements SameGameController.
 *
 * @implNote quit determines whether the user has quit mid-game
 */
public class SameGameTextController<Piece> implements SameGameController<Piece> {
  private Readable in;
  private Appendable out;
  private Boolean quit;

  /**
   * Creates a SameGameTextController.
   *
   * @param rd a Readable object for input streams from the user
   * @param ap an Appendable object for output streams
   * @throws IllegalArgumentException if either rd or ap is null
   */
  public SameGameTextController(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("The readable or appendable input is null");
    }
    this.in = rd;
    this.out = ap;
    this.quit = false;
  }

  @Override
  public void playGame(SameGameModel<Piece> model, int rows, int cols, int swaps, boolean random) {
    nullModel(model);
    model.startGame(rows, cols, swaps, random);
    this.playGameHelper(model);
  }

  @Override
  public void playGame(SameGameModel<Piece> model, List<List<Piece>> initBoard, int swaps) {
    nullModel(model);
    model.startGame(initBoard, swaps);
    this.playGameHelper(model);
  }

  /**
   * Displays the current game state and gets the input from the user as long
   * as the game is ongoing and the user has not quit.
   * @param model a SameGameModel
   */
  private void playGameHelper(SameGameModel<Piece> model) {
    try {
      Scanner scan = new Scanner(this.in);
      while (!model.gameOver() && !this.quit) {
        this.displayCurrentGame(model);
        this.getInput(scan, model);
      }
      if (!this.quit) {
        this.transmitGameOver(model);
      } else {
        this.displayCurrentGame(model);
      }
    } catch (IOException e) {
      throw new IllegalStateException("Unable to transmit output or provide inputs");
    }

  }

  /**
   * Checks if the given model is null.
   *
   * @param model a SameGameModel
   */
  private void nullModel(SameGameModel<Piece> model) {
    if (model == null) {
      throw new IllegalArgumentException("Given model is null");
    }
  }

  /**
   * Transmits the game state as the model provides it as well as the
   * remaining swaps and score.
   *
   * @param model a SameGameModel
   * @throws IOException if the rendering fails for some reason
   */
  private void displayCurrentGame(SameGameModel<Piece> model) throws IOException {
    SameGameView<Piece> gameState = new SameGameTextView<>(model, this.out);
    gameState.render();
    this.appendToAppendable(String.format("\nRemaining swaps: %d\n", model.remainingSwaps()));
    this.appendToAppendable(String.format("Score: %d\n", model.score()));
  }

  /**
   * Receives user input to determine the move to execute. This could either be
   * to remove a matching block, a swap, or quitting the game.
   *
   * @param scan  a Scanner
   * @param model a SameGameModel
   */
  private void getInput(Scanner scan, SameGameModel<Piece> model) throws IOException {
    if (scan.hasNext()) {
      String move = scan.next();
      switch (move) {
        case "m":
          this.handleMatchInputs(scan, model);
          break;
        case "s":
          this.handleSwapInputs(scan, model);
          break;
        case "Q":
        case "q":
          this.handleQuit();
          break;
        default:
          if (!this.quit) {
            this.appendToAppendable("Invalid command. Try again. The" +
                    "command should start with m, s, Q, or q\n");
          }
          break;
      }
    }
  }

  /**
   * Handles removing a matching block by obtaining user input from the Readable
   * object, determining if it is a valid input and continuing to query if it is not.
   *
   * @param scan  a Scanner
   * @param model a SameGameModel
   */
  private void handleMatchInputs(Scanner scan, SameGameModel<Piece> model) throws IOException {
    int validNumCount = 0;
    int rowInput = 0;
    int colInput = 0;
    boolean commandExecuted = false;

    while (validNumCount < 2 && !commandExecuted) {
      String userInput = scan.next();
      try {
        int inputNum = Integer.parseInt(userInput);
        if (inputNum >= 0) {
          switch (validNumCount) {
            case 0:
              rowInput = inputNum;
              break;
            case 1:
              colInput = inputNum;
              break;
            default:
              break;
          }
          validNumCount++;
        }
      } catch (NumberFormatException e) {
        if (userInput.equals("q") || userInput.equals("Q")) {
          this.handleQuit();
          break;
        }
      }
      if (!this.quit && validNumCount == 2) {
        try {
          model.removeMatch(rowInput - 1, colInput - 1);
          commandExecuted = true;
        } catch (IllegalStateException | IllegalArgumentException e) {
          this.appendToAppendable(
                  "Invalid move. Try again. " + e.getMessage() + "\n");
          validNumCount = 0;
          commandExecuted = true;
        }
      }
    }
  }

  /**
   * Handles swapping pieces by obtaining user input from the Readable object,
   * determining if it is a valid input and continuing to query if it is not.
   *
   * @param scan  a Scanner
   * @param model a SameGameModel
   */
  private void handleSwapInputs(Scanner scan, SameGameModel<Piece> model) throws IOException {
    int validNumCount = 0;
    int fromRowInput = 0;
    int fromColInput = 0;
    int toRowInput = 0;
    int toColInput = 0;
    boolean commandexecuted = false;

    while (validNumCount < 4 && !commandexecuted) {
      String userInput = scan.next();
      try {
        int inputNum = Integer.parseInt(userInput);
        if (inputNum >= 0) {
          switch (validNumCount) {
            case 0:
              fromRowInput = inputNum;
              break;
            case 1:
              fromColInput = inputNum;
              break;
            case 2:
              toRowInput = inputNum;
              break;
            case 3:
              toColInput = inputNum;
              break;
            default:
              break;
          }
          validNumCount++;
        }
      } catch (NumberFormatException e) {
        if (userInput.equals("q") || userInput.equals("Q")) {
          this.handleQuit();
          break;
        }
      }
      if (!this.quit && validNumCount == 4) {
        try {
          model.swap(fromRowInput - 1, fromColInput - 1,
                  toRowInput - 1, toColInput - 1);
          commandexecuted = true;
        } catch (IllegalStateException | IllegalArgumentException e) {
          this.appendToAppendable(
                  "Invalid move. Try again. " + e.getMessage() + "\n");
          validNumCount = 0;
          commandexecuted = true;
        }
      }
    }
  }

  /**
   * Appends the given string to the appendable output.
   *
   * @param s a String
   */
  private void appendToAppendable(String s) throws IOException {
    this.out.append(s);
  }

  /**
   * Handles when the user decides to quit mid-game.
   */
  private void handleQuit() throws IOException {
    this.quit = true;
    this.appendToAppendable("Game quit!\nState of game when quit:\n");
  }

  /**
   * Transmits when the game is over.
   *
   * @param model a SameGameModel
   */
  private void transmitGameOver(SameGameModel<Piece> model) throws IOException {
    this.appendToAppendable("Game over.\n");
    this.displayCurrentGame(model);
  }
}
