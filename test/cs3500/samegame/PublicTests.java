package cs3500.samegame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;
import cs3500.samegame.view.SameGameTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests all SameGameModel methods.
 */
public class PublicTests {

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
  SameGameModel<Piece> model11;
  SameGameModel<Piece> model12;
  SameGameModel<Piece> model13;
  SameGameModel<Piece> model14;
  SameGameModel<Piece> model15;
  SameGameModel<Piece> model16;
  SameGameModel<Piece> model17;
  SameGameModel<Piece> model18;
  SameGameModel<Piece> model19;
  SameGameModel<Piece> model20;
  SameGameModel<Piece> model21;
  SameGameModel<Piece> model22;
  SameGameModel<Piece> model23;
  SameGameModel<Piece> model24;

  SameGameTextView view1;
  SameGameTextView view2;
  SameGameTextView view6;
  SameGameTextView view8;
  SameGameTextView view9;
  SameGameTextView view10;
  SameGameTextView view19;
  SameGameTextView view22;
  SameGameTextView view23;

  List<List<Piece>> board1;
  List<List<Piece>> board2;
  List<List<Piece>> board3;
  List<List<Piece>> board4;
  List<List<Piece>> board5;
  List<List<Piece>> board12;
  List<List<Piece>> board13;
  List<List<Piece>> board14;
  List<List<Piece>> board19;
  List<List<Piece>> board20;
  List<List<Piece>> board21;
  List<List<Piece>> board22;
  List<List<Piece>> board23;

  @Before
  public void initalizeModels() {
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
    model11 = new FourPieceSameGame();
    model12 = new FourPieceSameGame();
    model13 = new FourPieceSameGame();
    model14 = new FourPieceSameGame();
    model15 = new FourPieceSameGame();
    model16 = new FourPieceSameGame();
    model17 = new FourPieceSameGame();
    model18 = new FourPieceSameGame();
    model19 = new FourPieceSameGame();
    model20 = new FourPieceSameGame();
    model21 = new FourPieceSameGame();
    model22 = new FourPieceSameGame();
    model23 = new FourPieceSameGame();
    model24 = new FourPieceSameGame();
  }

  @Before
  public void initializeBoards() {
    board1 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.R, Piece.R, Piece.B)),
            new ArrayList<>(List.of(Piece.G, Piece.Y, Piece.R)),
            new ArrayList<>(List.of(Piece.Y, Piece.G, Piece.B))));
    board2 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.R, Piece.R, Piece.Y)),
            new ArrayList<>(List.of(Piece.R, Piece.R, Piece.B)),
            new ArrayList<>(List.of(Piece.Y, Piece.B, Piece.B))));
    board3 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.Y, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.Y, Piece.B, Piece.B)),
            new ArrayList<>(List.of(Piece.Y, Piece.Y, Piece.Y))));
    board4 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.B)),
            new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y))));
    board5 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.X)),
            new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y))));
    board12 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.G, Piece.G, Piece.B)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.B))));
    board13 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.R, Piece.R, Piece.B)),
            new ArrayList<>(List.of(Piece.G, Piece.Y, Piece.R)),
            new ArrayList<>(List.of(Piece.Y, Piece.G, Piece.B))));
    board14 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.R, Piece.X, Piece.X, Piece.R)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.X, Piece.X)),
            new ArrayList<>(List.of(Piece.X, Piece.R, Piece.X, Piece.X))));
    board19 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.B, Piece.B, Piece.X, Piece.Y)),
            new ArrayList<>(List.of(Piece.B, Piece.B, Piece.Y, Piece.X)),
            new ArrayList<>(List.of(Piece.X, Piece.B, Piece.Y, Piece.B)),
            new ArrayList<>(List.of(Piece.X, Piece.B, Piece.B, Piece.B))));
    board20 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.X, Piece.X, Piece.X)),
            new ArrayList<>(List.of(Piece.R, Piece.X, Piece.Y, Piece.G)),
            new ArrayList<>(List.of(Piece.G, Piece.B, Piece.B, Piece.B))));
    board21 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.X, Piece.X, Piece.X)),
            new ArrayList<>(List.of(Piece.R, Piece.X, Piece.Y, Piece.G)),
            new ArrayList<>(List.of(Piece.G, Piece.X, Piece.X, Piece.X))));
    board22 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.R, Piece.R, Piece.X, Piece.X)),
            new ArrayList<>(List.of(Piece.R, Piece.X, Piece.Y, Piece.G)),
            new ArrayList<>(List.of(Piece.G, Piece.X, Piece.X, Piece.X))));
    board23 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.R, Piece.R, Piece.X, Piece.X)),
            new ArrayList<>(List.of(Piece.R, Piece.X, Piece.Y, Piece.G)),
            new ArrayList<>(List.of(Piece.G, Piece.X, Piece.X, Piece.X))));
  }

  @Before
  public void setup() {
    model1.startGame(3, 3, 2, false);
    view1 = new SameGameTextView(model1);
    model2.startGame(1, 1, 1, false);
    view2 = new SameGameTextView(model2);
    model6.startGame(board1, 0);
    view6 = new SameGameTextView(model6);
    model7.startGame(6, 1, 2, false);
    model8.startGame(board2, 3);
    model8.removeMatch(0, 1);
    model8.removeMatch(2, 1);
    view8 = new SameGameTextView(model8);
    model9.startGame(board3, 2);
    model12.startGame(board12, 2);
    model13.startGame(board13, 0);
    model14.startGame(board14, 1);
    model15.startGame(3, 3, 2, true);
    view9 = new SameGameTextView(model15);
    view10 = new SameGameTextView(model14);
    model19.startGame(board19, 3);
    model19.removeMatch(1, 1);
    view19 = new SameGameTextView(model19);
    model20.startGame(board20, 0);
    model21.startGame(board21, 2);
    model22.startGame(board22, 2);
    model22.swap(1, 0, 1, 1);
    view22 = new SameGameTextView(model22);
    model23.startGame(board23, 1);
    model23.swap(0, 1, 2, 1);
    view23 = new SameGameTextView(model23);
  }

  @Test
  public void testToString() {
    assertEquals("Y", Piece.Y.toString());
    assertEquals("R R B\n" +
            "G Y R\n" +
            "Y G B", view6.toString());
    assertEquals("" +
            "X X X Y\n" +
            "X X Y X\n" +
            "X X Y X\n" +
            "X X X X", view19.toString());
  }

  @Test
  public void testSwap() {
    try {
      // Swapping when game not started
      model10.swap(1, 1, 2, 1);

      // Swapping when game is over
      model12.swap(0, 0, 0, 2);

      // Swapping empty pieces
      model20.swap(0, 1, 0, 2);

      // Swapping same piece
      model20.swap(1, 1, 1, 1);

      // Swapping with piece that is not horizontal or vertically aligned
      model20.swap(0, 0, 2, 2);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalStateException ignored) {
    }
    try {
      // Out of bounds exceptions
      model9.swap(-1, 0, 1, 1);
      model9.swap(2, 2, 1, 5);
      model9.swap(2, 5, 1, 2);
      model9.swap(2, 2, -4, 2);
      model6.swap(0, 0, 1, 0);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals("" +
            "R R X X\n" +
            "X R Y G\n" +
            "G X X X", view22.toString());
    assertEquals("" +
            "R X X X\n" +
            "R X Y G\n" +
            "G R X X", view23.toString());
  }

  @Test
  public void testRemoveMatch() {
    assertEquals("" +
            "X X Y\n" +
            "X X X\n" +
            "Y X X", view8.toString());
    assertEquals("" +
            "X X X Y\n" +
            "X X Y X\n" +
            "X X Y X\n" +
            "X X X X", view19.toString());
    try {
      // Game has not started
      model10.removeMatch(0, 0);

      // Game is over
      model12.removeMatch(1, 1);
      model13.removeMatch(0, 0);

      // Coordinate has no piece
      model14.removeMatch(1, 3);

      // Invalid matching block
      model6.removeMatch(2, 2);
      model9.removeMatch(1, 1);
    } catch (IllegalStateException ignored) {
    }

    try {
      // Argument out of bounds
      model9.removeMatch(1, 4);
      model9.removeMatch(1, -1);
      model9.removeMatch(-1, 2);
      model9.removeMatch(4, 2);
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testWidth() {
    assertEquals(3, model1.width());
    assertEquals(1, model2.width());
    assertEquals(6, model7.width());

    try {
      model18.width();
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalStateException ignored) {
    }
  }

  @Test
  public void testLength() {
    assertEquals(3, model1.length());
    assertEquals(1, model2.length());
    assertEquals(1, model7.length());

    try {
      model18.width();
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalStateException ignored) {
    }
  }

  @Test
  public void testPieceAt() {
    assertEquals(Piece.G, model1.pieceAt(0, 1));
    assertEquals(Piece.R, model6.pieceAt(1, 2));

    // tests if the game has not started
    try {
      model18.pieceAt(1, 1);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalStateException ignored) {
    }

    // Tests arguments out of bounds
    try {
      model1.pieceAt(-1, 0);
      model1.pieceAt(4, 0);
      model1.pieceAt(2, -1);
      model1.pieceAt(2, 4);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testScore() {
    assertEquals(3, model8.score());
    assertEquals(0, model22.score());
    assertEquals(7, model19.score());

    // Tests if the game has not started
    try {
      model18.score();
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalStateException ignored) {
    }
  }

  @Test
  public void testRemainingSwaps() {
    assertEquals(0, model23.remainingSwaps());
    assertEquals(1, model22.remainingSwaps());

    // Tests if the game has not started
    try {
      model24.remainingSwaps();
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalStateException ignored) {
    }
  }

  @Test
  public void testGameOver() {
    assertTrue(model12.gameOver());
    assertTrue(model13.gameOver());
    assertFalse(model9.gameOver());
    assertFalse(model14.gameOver());
    assertFalse(model20.gameOver());
    assertTrue(model21.gameOver());

    // Tests if game is already over
    try {
      model18.gameOver();
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalStateException ignored) {
    }
  }

  @Test
  public void testStartGame() {
    // Tests deterministic board setup
    assertEquals("R G B\n" +
            "Y R G\n" +
            "B Y R", view1.toString());
    assertEquals("R", view2.toString());

    // Tests random board setup
    assertNotEquals("R G B\n" +
            "Y R G\n" +
            "B Y R", view9.toString());
    assertEquals(3, model15.length());
    assertEquals(3, model15.width());

    // Tests IllegalArgumentException
    try {
      model3.startGame(0, 1, 2, true);
      model4.startGame(1, 1, -1, false);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalArgumentException ignored) {
    }

    // Tests IllegalStateException
    try {
      model7.startGame(2, 2, 2, true);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalStateException ignored) {
    }
  }

  @Test
  public void testOtherStartGame() {
    assertEquals("R R B\n" +
            "G Y R\n" +
            "Y G B", view6.toString());
    assertEquals("R X X R\n" +
            "X X X X\n" +
            "X R X X", view10.toString());

    // Tests NullPointerException
    try {
      model5.startGame(null, 0);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalArgumentException ignored) {
    }

    // Tests IllegalStateException
    try {
      model14.startGame(board4, 2);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalStateException ignored) {
    }

    // Tests IllegalArgumentException
    try {
      model16.startGame(new ArrayList<>(), 0);
      model17.startGame(board5, 2);
      Assert.fail("Should have thrown an exception by now");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testCreateListOfPieces() {
    assertEquals(Piece.R, model1.createListOfPieces()[0]);
    assertEquals(Piece.G, model1.createListOfPieces()[1]);
    assertEquals(Piece.B, model1.createListOfPieces()[2]);
    assertEquals(Piece.Y, model1.createListOfPieces()[3]);
    assertEquals(4, model1.createListOfPieces().length);
  }
}
