package cs3500.samegame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.controller.SameGameController;
import cs3500.samegame.controller.SameGameTextController;
import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the SameGameController.
 */
public class ControllerTests {
  SameGameModel<Piece> model1;
  SameGameModel<Piece> model2;
  SameGameModel<Piece> model3;
  SameGameModel<Piece> model4;
  SameGameModel<Piece> model5;
  SameGameModel<Piece> model6;
  SameGameModel<Piece> model7;
  SameGameModel<Piece> model8;
  SameGameModel<Piece> model9;
  SameGameModel<Piece> model10;

  List<List<Piece>> board1;
  List<List<Piece>> board2;
  List<List<Piece>> board3;
  List<List<Piece>> board4;
  List<List<Piece>> board6;
  List<List<Piece>> board7;
  List<List<Piece>> board8;

  SameGameController<Piece> controller1;
  SameGameController<Piece> controller2;
  SameGameController<Piece> controller3;
  SameGameController<Piece> controller4;
  SameGameController<Piece> controller5;
  SameGameController<Piece> controller6;
  SameGameController<Piece> controller7;
  SameGameController<Piece> controller8;
  SameGameController<Piece> controller9;
  SameGameController<Piece> controller10;


  @Before
  public void initializeBoards() {
    board1 = new ArrayList<>(List.of(
            new ArrayList<>(List.of(Piece.R, Piece.R, Piece.B, Piece.B, Piece.Y, Piece.B)),
            new ArrayList<>(List.of(Piece.Y, Piece.G, Piece.Y, Piece.B, Piece.Y, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.R, Piece.B, Piece.R, Piece.Y, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.G, Piece.B, Piece.B, Piece.R, Piece.B)),
            new ArrayList<>(List.of(Piece.Y, Piece.R, Piece.Y, Piece.G, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.R, Piece.R, Piece.X, Piece.X, Piece.Y, Piece.B))));

    board2 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.R, Piece.X, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.R, Piece.B)),
            new ArrayList<>(List.of(Piece.Y, Piece.X, Piece.B))));

    board3 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.Y))));

    board4 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.B)),
            new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y))));
    board6 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.X))));
    board7 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.X))));
    board8 = new ArrayList<>(List.of(
            new ArrayList<>(List.of(Piece.R, Piece.R, Piece.R, Piece.R, Piece.R, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.G, Piece.B, Piece.G, Piece.G, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.G, Piece.G, Piece.G, Piece.G, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.G, Piece.B, Piece.B, Piece.X, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.G, Piece.Y, Piece.Y, Piece.Y, Piece.B)),
            new ArrayList<>(List.of(Piece.B, Piece.B, Piece.B, Piece.B, Piece.B, Piece.B))));
  }


  @Before
  public void setupModels() {
    model1 = new FourPieceSameGame();
    model2 = new FourPieceSameGame();
    model3 = new FourPieceSameGame();
    model4 = new FourPieceSameGame();
    model5 = new FourPieceSameGame();
    model6 = new FourPieceSameGame();
    model7 = new FourPieceSameGame();
    model8 = new FourPieceSameGame();
    model9 = new FourPieceSameGame();
    model10 = new FourPieceSameGame();
  }

  @Before
  public void setupControllers() {
    controller1 = new SameGameTextController<>(new StringReader(""),
            new StringBuilder());
  }

  /**
   * Tests the SameGameTextController constructor.
   */
  @Test
  public void testConstructor() {
    try {
      new SameGameTextController<Piece>(null, new StringBuilder());
      new SameGameTextController<Piece>(new StringReader(""), null);
      new SameGameTextController<Piece>(null, null);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalArgumentException ignored) {
    }
  }

  /**
   * Tests playGame with invalid inputs.
   */
  @Test
  public void testPlayGame() {
    try {
      controller1.playGame(null, 1, 3, 3, false);
      controller1.playGame(null, board1, 3);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalArgumentException ignored) {
    }
  }

  /**
   * Tests the match functionality of the controller as well as its ability to render.
   *
   * @implNote match tests are in the following order for controller6:
   *           normal match, waiting for integer when p is inputted instead,
   *           waiting for an integer when s is inputted instead,
   *           waiting for an integer when m is inputted instead,
   *           out of bounds, remove match on empty piece,
   *           invalid matching block
   */

  @Test
  public void testMatch() {
    StringBuilder out6 = new StringBuilder();
    Reader in6 = new StringReader(
            "m 1 1 " + "m -1 2 -1 2 " + "m 6 s 2 " + "m m 5 3 " +
                    "m 0 1 " + "m 4 5 " + "m 4 4 " + "q");
    controller6 = new SameGameTextController<>(in6, out6);
    controller6.playGame(model5, board8, 1);
    assertEquals("R R R R R B\n" + "R G B G G B\n" + "R G G G G B\n" + "R G B B X B\n" +
            "R G Y Y Y B\n" + "B B B B B B\n" + "Remaining swaps: 1\n" + "Score: 0\n" +
            "X X X X X B\n" + "X G B G G B\n" + "X G G G G B\n" + "X G B B X B\n" +
            "X G Y Y Y B\n" + "B B B B B B\n" + "Remaining swaps: 1\n" + "Score: 7\n" +
            "X X X X X B\n" + "X X B X X B\n" + "X X X X X B\n" + "X X B B X B\n" +
            "X X Y Y Y B\n" + "B B B B B B\n" + "Remaining swaps: 1\n" + "Score: 14\n" +
            "X X X X X X\n" + "X X B X X X\n" + "X X X X X X\n" + "X X B B X X\n" +
            "X X Y Y Y X\n" + "X X X X X X\n" + "Remaining swaps: 1\n" + "Score: 23\n" +
            "X X X X X X\n" + "X X B X X X\n" + "X X X X X X\n" + "X X B B X X\n" +
            "X X X X X X\n" + "X X X X X X\n" + "Remaining swaps: 1\n" + "Score: 24\n" +
            "Invalid move. Try again. Coordinates out of bounds\n" + "X X X X X X\n" +
            "X X B X X X\n" + "X X X X X X\n" + "X X B B X X\n" + "X X X X X X\n" +
            "X X X X X X\n" + "Remaining swaps: 1\n" + "Score: 24\n" +
            "Invalid move. Try again. Coordinate has no piece\n" +
            "X X X X X X\n" + "X X B X X X\n" + "X X X X X X\n" + "X X B B X X\n" +
            "X X X X X X\n" + "X X X X X X\n" + "Remaining swaps: 1\n" + "Score: 24\n" +
            "Invalid move. Try again. Not a matching block\n" + "X X X X X X\n" +
            "X X B X X X\n" + "X X X X X X\n" + "X X B B X X\n" + "X X X X X X\n" +
            "X X X X X X\n" + "Remaining swaps: 1\n" + "Score: 24\n" + "Game quit!\n" +
            "State of game when quit:\n" + "X X X X X X\n" + "X X B X X X\n" +
            "X X X X X X\n" + "X X B B X X\n" + "X X X X X X\n" + "X X X X X X\n" +
            "Remaining swaps: 1\n" + "Score: 24\n", out6.toString());
  }

  /**
   * Tests the swap functionality of the controller as well as its ability to render.
   *
   * @implNote Swap tests are in the following order for controller4:
   *           normal swap, out of bounds coordinates,
   *           waiting for integer when "t" is inputted instead,
   *           waiting for integer even when "s" is inputted instead,
   *           waiting for integer even when "m" is inputted instead,
   *           out of bounds, swapping same piece with negative number in input,
   *           swapping empty space with negative number in input,
   *           mismatched row and column, no swaps remaining
   *           Controller3 tests swapping when the game is already over
   */
  @Test
  public void testSwap() {
    StringBuilder out4 = new StringBuilder();
    Reader in4 = new StringReader("s 2 2 2 5 " + "s 10 1 4 1 " + "s 1 t 3 1 2 " + "s 3 3 s 2 3 " +
            "s 2 1 2 m 3 " + "s 7 7 7 2 " + "s 2 -3 2 2 2 " + "s 6 -3 3 6 4 " +
            "s 1 4 2 3 " + "s 4 1 4 2 " + "q");
    controller4 = new SameGameTextController<>(in4, out4);
    controller4.playGame(model4, board1, 4);
    assertEquals("R R B B Y B\n" + "Y G Y B Y B\n" + "R R B R Y B\n" +
            "R G B B R B\n" + "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 4\n" +
            "Score: 0\n" + "R R B B Y B\n" + "Y Y Y B G B\n" +
            "R R B R Y B\n" + "R G B B R B\n" + "Y R Y G Y Y\n" +
            "R R X X Y B\n" + "Remaining swaps: 3\n" +
            "Score: 0\n" + "Invalid move. Try again. Coordinates out of bounds\n" +
            "R R B B Y B\n" + "Y Y Y B G B\n" + "R R B R Y B\n" + "R G B B R B\n" +
            "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 3\n" + "Score: 0\n" +
            "R B R B Y B\n" + "Y Y Y B G B\n" + "R R B R Y B\n" + "R G B B R B\n" +
            "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 2\n" + "Score: 0\n" +
            "R B R B Y B\n" + "Y Y B B G B\n" + "R R Y R Y B\n" + "R G B B R B\n" +
            "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 1\n" + "Score: 0\n" +
            "R B R B Y B\n" + "B Y Y B G B\n" + "R R Y R Y B\n" + "R G B B R B\n" +
            "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 0\n" + "Score: 0\n" +
            "Invalid move. Try again. Coordinates out of bounds\n" +
            "R B R B Y B\n" + "B Y Y B G B\n" + "R R Y R Y B\n" + "R G B B R B\n" +
            "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 0\n" + "Score: 0\n" +
            "Invalid move. Try again. Cannot swap the same piece\n" +
            "R B R B Y B\n" + "B Y Y B G B\n" + "R R Y R Y B\n" + "R G B B R B\n" +
            "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 0\n" + "Score: 0\n" +
            "Invalid move. Try again. Coordinate has no piece\n" +
            "R B R B Y B\n" + "B Y Y B G B\n" + "R R Y R Y B\n" + "R G B B R B\n" +
            "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 0\n" + "Score: 0\n" +
            "Invalid move. Try again. Must swap with pieces in either the same row or column\n" +
            "R B R B Y B\n" + "B Y Y B G B\n" + "R R Y R Y B\n" + "R G B B R B\n" +
            "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 0\n" + "Score: 0\n" +
            "Invalid move. Try again. No swaps remaining\n" +
            "R B R B Y B\n" + "B Y Y B G B\n" + "R R Y R Y B\n" + "R G B B R B\n" +
            "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 0\n" + "Score: 0\n" +
            "Game quit!\n" + "State of game when quit:\n" + "R B R B Y B\n" + "B Y Y B G B\n" +
            "R R Y R Y B\n" + "R G B B R B\n" + "Y R Y G Y Y\n" + "R R X X Y B\n" +
            "Remaining swaps: 0\n" + "Score: 0\n", out4.toString());
    StringBuilder out3 = new StringBuilder();
    Reader in3 = new StringReader("s 1 2 1 3");
    controller3 = new SameGameTextController<>(in3, out3);
    controller3.playGame(model2, board4, 0);
    controller3.playGame(model3, board2, 3);
    assertEquals("Game over.\n" + "X Y Y\n" + "X X B\n" + "X Y Y\n" +
            "Remaining swaps: 0\n" + "Score: 0\n" + "Game over.\n" +
            "R X Y\n" + "X R B\n" + "Y X B\n" + "Remaining swaps: 3\n" +
            "Score: 0\n", out3.toString());
  }

  /**
   * Tests the game ending functionality of the controller as well as its ability to render.
   *
   * @implNote controller7 tests when a final swap ends the game
   *           controller8 tests when a final match ends the game
   */
  @Test
  public void testGameEnd() {
    StringBuilder out7 = new StringBuilder();
    Reader in7 = new StringReader("s 1 2 3 2 ");
    controller7 = new SameGameTextController<>(in7, out7);
    controller7.playGame(model6, board6, 1);

    StringBuilder out8 = new StringBuilder();
    Reader in8 = new StringReader("m 1 3");
    controller8 = new SameGameTextController<>(in8, out8);
    controller8.playGame(model7, board7, 0);

    assertEquals("X Y Y\n" + "X X Y\n" + "X X X\n" + "Remaining swaps: 1\n" +
            "Score: 0\n" + "Game over.\n" + "X X Y\n" + "X X Y\n" + "X Y X\n" +
            "Remaining swaps: 0\n" + "Score: 0\n", out7.toString());
    assertEquals("X Y Y\n" + "X X Y\n" + "X X X\n" + "Remaining swaps: 0\n" +
            "Score: 0\n" + "Game over.\n" + "X X X\n" + "X X X\n" + "X X X\n" +
            "Remaining swaps: 0\n" + "Score: 1\n", out8.toString());
  }

  /**
   * Tests the quit functionality of the controller as well as its rendering.
   */
  @Test
  public void testQuit() {
    StringBuilder out2 = new StringBuilder();
    Reader in2 = new StringReader("q");
    controller2 = new SameGameTextController<>(in2, out2);
    controller2.playGame(model1, board1, 3);

    StringBuilder out9 = new StringBuilder();
    Reader in9 = new StringReader("m 1 q");
    controller9 = new SameGameTextController<>(in9, out9);
    controller9.playGame(model8, board1, 3);

    StringBuilder out10 = new StringBuilder();
    Reader in10 = new StringReader("s 1 q 1 2");
    controller10 = new SameGameTextController<>(in10, out10);
    controller10.playGame(model9, board1, 3);

    assertEquals("R R B B Y B\n" + "Y G Y B Y B\n" + "R R B R Y B\n" + "R G B B R B\n" +
                    "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 3\n" + "Score: 0\n" +
                    "Game quit!\n" + "State of game when quit:\n" + "R R B B Y B\n" +
                    "Y G Y B Y B\n" + "R R B R Y B\n" + "R G B B R B\n" + "Y R Y G Y Y\n" +
                    "R R X X Y B\n" + "Remaining swaps: 3\n" + "Score: 0\n"
            , out2.toString());
    assertEquals("R R B B Y B\n" + "Y G Y B Y B\n" + "R R B R Y B\n" + "R G B B R B\n" +
                    "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 3\n" + "Score: 0\n" +
                    "Game quit!\n" + "State of game when quit:\n" + "R R B B Y B\n" +
                    "Y G Y B Y B\n" + "R R B R Y B\n" + "R G B B R B\n" + "Y R Y G Y Y\n" +
                    "R R X X Y B\n" + "Remaining swaps: 3\n" + "Score: 0\n"
            , out9.toString());
    assertEquals("R R B B Y B\n" + "Y G Y B Y B\n" + "R R B R Y B\n" + "R G B B R B\n" +
                    "Y R Y G Y Y\n" + "R R X X Y B\n" + "Remaining swaps: 3\n" + "Score: 0\n" +
                    "Game quit!\n" + "State of game when quit:\n" + "R R B B Y B\n" +
                    "Y G Y B Y B\n" + "R R B R Y B\n" + "R G B B R B\n" + "Y R Y G Y Y\n" +
                    "R R X X Y B\n" + "Remaining swaps: 3\n" + "Score: 0\n"
            , out10.toString());
  }
}

