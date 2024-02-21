package cs3500.samegame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;
import cs3500.samegame.model.hw04.GravitySameGame;
import cs3500.samegame.view.SameGameTextView;
import cs3500.samegame.view.SameGameView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the GravitySameGame variant.
 */
public class GravitySameGameTests {
  SameGameModel gravity1;
  SameGameModel gravity2;
  SameGameModel gravity3;
  SameGameModel gravity4;

  SameGameView view;

  List<List<Piece>> board1;

  List<List<Piece>> board2;
  List<List<Piece>> board3;
  List<List<Piece>> board4;
  List<List<Piece>> board5;

  @Before
  public void initializeModels() {
    gravity1 = new GravitySameGame();
    gravity2 = new GravitySameGame();
    gravity3 = new GravitySameGame();
    gravity4 = new GravitySameGame();
  }

  @Before
  public void initializeBoards() {
    board1 = new ArrayList<>(List.of(
            new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.B)),
            new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y))));
    board2 = new ArrayList<>(List.of(
            new ArrayList<>(List.of(Piece.R, Piece.X, Piece.R, Piece.R, Piece.R, Piece.X)),
            new ArrayList<>(List.of(Piece.R, Piece.G, Piece.B, Piece.G, Piece.G, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.G, Piece.G, Piece.G, Piece.G, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.X, Piece.B, Piece.X, Piece.X, Piece.X)),
            new ArrayList<>(List.of(Piece.X, Piece.G, Piece.X, Piece.Y, Piece.X, Piece.B)),
            new ArrayList<>(List.of(Piece.X, Piece.X, Piece.X, Piece.X, Piece.X, Piece.X))));
    board3 = new ArrayList<>(List.of(
            new ArrayList<>(List.of(Piece.R, Piece.R, Piece.B, Piece.B, Piece.Y, Piece.B)),
            new ArrayList<>(List.of(Piece.Y, Piece.G, Piece.Y, Piece.B, Piece.Y, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.R, Piece.B, Piece.R, Piece.Y, Piece.B)),
            new ArrayList<>(List.of(Piece.R, Piece.G, Piece.X, Piece.B, Piece.R, Piece.B)),
            new ArrayList<>(List.of(Piece.Y, Piece.R, Piece.X, Piece.G, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.R, Piece.R, Piece.X, Piece.X, Piece.Y, Piece.B))));
    board4 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.X, Piece.X, Piece.X)),
            new ArrayList<>(List.of(Piece.R, Piece.X, Piece.Y, Piece.G)),
            new ArrayList<>(List.of(Piece.G, Piece.X, Piece.X, Piece.X))));
    board5 = new ArrayList<>(List.of(
            new ArrayList<>(List.of(Piece.X, Piece.B, Piece.R)),
            new ArrayList<>(List.of(Piece.Y, Piece.X, Piece.R)),
            new ArrayList<>(List.of(Piece.R, Piece.Y, Piece.Y))));
  }

  @Test
  public void testWidth() {
    try {
      gravity1.width();
    } catch (IllegalStateException ignored) {
    }
    gravity1.startGame(board1, 3);
    assertEquals(3, gravity1.width());
    gravity2.startGame(board2, 4);
    assertEquals(6, gravity2.width());
  }

  @Test
  public void testLength() {
    try {
      gravity1.length();
    } catch (IllegalStateException ignored) {
    }
    gravity1.startGame(board1, 3);
    assertEquals(3, gravity1.length());
    gravity2.startGame(board2, 4);
    assertEquals(6, gravity2.length());
  }


  @Test
  public void testPieceAt() {
    try {
      gravity1.pieceAt(1, 1);
    } catch (IllegalStateException ignored) {
    }
    gravity2.startGame(board1, 3);
    try {
      gravity2.pieceAt(-1, 2);
    } catch (IllegalArgumentException ignored) {
    }
    try {
      gravity2.pieceAt(1, 10);
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals(Piece.Y, gravity2.pieceAt(1, 1));
    assertEquals(null, gravity2.pieceAt(0, 0));
  }

  @Test
  public void testScore() {
    try {
      gravity1.pieceAt(1, 1);
    } catch (IllegalStateException ignored) {
    }
    gravity3.startGame(board3, 3);
    assertEquals(0, gravity3.score());
    gravity3.removeMatch(1, 5);
    assertEquals(2, gravity3.score());
  }

  @Test
  public void testRemainingSwaps() {
    try {
      gravity1.pieceAt(1, 1);
    } catch (IllegalStateException ignored) {
    }
    gravity1.startGame(board1, 1);
    gravity1.swap(2, 1, 1, 1);
    assertEquals(0, gravity1.remainingSwaps());
  }

  @Test
  public void testGameOver() {
    try {
      gravity1.gameOver();
    } catch (IllegalStateException ignored) {
    }
    gravity1.startGame(board1, 2);
    assertFalse(gravity1.gameOver());
    gravity2.startGame(board4, 2);
    assertTrue(gravity2.gameOver());
    gravity4.startGame(board5, 0);
    assertTrue(gravity4.gameOver());
  }

  /**
   * Tests the gravity functionality on a board that contains empty spaces
   * below pieces when we start a game.
   */
  @Test
  public void testStartGame() {
    gravity1.startGame(board1, 2);
    view = new SameGameTextView(gravity1);
    assertEquals("" +
            "X X Y\n" +
            "X Y B\n" +
            "X Y Y", view.toString());
    gravity2.startGame(board2, 3);
    view = new SameGameTextView(gravity2);
    assertEquals("" +
            "X X X X X X\n" +
            "X X X X X X\n" +
            "R X R R X X\n" +
            "R G B G R B\n" +
            "R G G G G B\n" +
            "R G B Y G B", view.toString());
    gravity3.startGame(board3, 3);
    view = new SameGameTextView(gravity3);
    assertEquals("" +
            "R R X X Y B\n" +
            "Y G X B Y B\n" +
            "R R X B Y B\n" +
            "R G B R R B\n" +
            "Y R Y B Y Y\n" +
            "R R B G Y B", view.toString());
  }

  /**
   * Tests the gravity functionality when a swap is executed.
   */
  @Test
  public void testSwap() {
    gravity1.startGame(board1, 2);
    gravity1.swap(2, 0, 2, 2);
    view = new SameGameTextView(gravity1);
    assertEquals("" +
            "X X X\n" +
            "X Y Y\n" +
            "Y Y B", view.toString());
    gravity2.startGame(board2, 3);
    gravity2.swap(1, 1, 5, 1);
    view = new SameGameTextView(gravity2);
    assertEquals("" +
            "X X X X X X\n" +
            "X X X X X X\n" +
            "R X R R X X\n" +
            "R G B G R B\n" +
            "R G G G G B\n" +
            "R G B Y G B", view.toString());
    gravity3.startGame(board3, 3);
    gravity3.swap(2, 2, 2, 3);
    view = new SameGameTextView(gravity3);
    assertEquals("" +
            "R R X X Y B\n" +
            "Y G X X Y B\n" +
            "R R B B Y B\n" +
            "R G B R R B\n" +
            "Y R Y B Y Y\n" +
            "R R B G Y B", view.toString());
  }

  @Test
  public void testRemoveMatch() {
    gravity3.startGame(board3, 3);
    gravity3.removeMatch(5, 0);
    view = new SameGameTextView(gravity3);
    assertEquals("" +
            "X X X X Y B\n" +
            "R X X B Y B\n" +
            "Y R X B Y B\n" +
            "R G B R R B\n" +
            "R R Y B Y Y\n" +
            "Y G B G Y B", view.toString());

    gravity3.removeMatch(3, 5);
    view = new SameGameTextView(gravity3);
    assertEquals("" +
            "X X X X Y X\n" +
            "R X X B Y X\n" +
            "Y R X B Y X\n" +
            "R G B R R X\n" +
            "R R Y B Y Y\n" +
            "Y G B G Y B", view.toString());
    gravity3.removeMatch(4, 4);
    view = new SameGameTextView(gravity3);
    assertEquals("" +
            "X X X X X X\n" +
            "R X X B X X\n" +
            "Y R X B Y X\n" +
            "R G B R Y X\n" +
            "R R Y B Y X\n" +
            "Y G B G R B", view.toString());
  }


}
