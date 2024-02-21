package cs3500.samegame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;
import cs3500.samegame.model.hw04.AutoMatchSameGame;
import cs3500.samegame.view.SameGameTextView;
import cs3500.samegame.view.SameGameView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the AutoMatchSameGame variant.
 */
public class AutoMatchSameGameTests {

  SameGameModel auto1;
  SameGameModel auto2;
  SameGameModel auto3;
  SameGameModel auto4;

  SameGameModel auto5;

  SameGameView view;

  List<List<Piece>> board1;
  List<List<Piece>> board2;
  List<List<Piece>> board3;
  List<List<Piece>> board4;
  List<List<Piece>> board5;

  @Before
  public void initializeModels() {
    auto1 = new AutoMatchSameGame();
    auto2 = new AutoMatchSameGame();
    auto3 = new AutoMatchSameGame();
    auto4 = new AutoMatchSameGame();
    auto5 = new AutoMatchSameGame();
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
    board5 = new ArrayList<>(List.of(new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y)),
            new ArrayList<>(List.of(Piece.X, Piece.X)),
            new ArrayList<>(List.of(Piece.X, Piece.Y, Piece.Y))));
  }

  @Test
  public void testWidth() {
    try {
      auto1.width();
    } catch (IllegalStateException ignored) {
    }
    auto1.startGame(board1, 3);
    assertEquals(3, auto1.width());
    auto2.startGame(board2, 4);
    assertEquals(6, auto2.width());
  }

  @Test
  public void testLength() {
    try {
      auto1.length();
    } catch (IllegalStateException ignored) {
    }
    auto1.startGame(board1, 3);
    assertEquals(3, auto1.length());
    auto2.startGame(board2, 4);
    assertEquals(6, auto2.length());
  }


  @Test
  public void testPieceAt() {
    try {
      auto1.pieceAt(1, 1);
    } catch (IllegalStateException ignored) {
    }
    try {
      auto1.startGame(board1, 3);
      auto1.pieceAt(-1, 2);
      auto1.pieceAt(1, 10);
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals(Piece.Y, auto1.pieceAt(0, 1));
    assertEquals(null, auto1.pieceAt(0, 0));
  }

  @Test
  public void testScore() {
    try {
      auto1.score();
    } catch (IllegalStateException ignored) {
    }
    auto3.startGame(board3, 4);
    assertEquals(0, auto3.score());
    auto3.swap(2, 0, 1, 0);
    assertEquals(7, auto3.score());
  }

  @Test
  public void testRemainingSwaps() {
    try {
      auto1.remainingSwaps();
    } catch (IllegalStateException ignored) {
    }
    auto3.startGame(board3, 4);
    assertEquals(4, auto3.remainingSwaps());
    auto3.swap(2, 0, 1, 0);
    assertEquals(3, auto3.remainingSwaps());
  }

  @Test
  public void testGameOver() {
    try {
      auto1.gameOver();
    } catch (IllegalStateException ignored) {
    }
    auto1.startGame(board1, 1);
    assertFalse(auto1.gameOver());
    auto1.swap(0, 0, 0, 1);
    assertTrue(auto1.gameOver());
    auto2.startGame(board4, 5);
    assertTrue(auto2.gameOver());
  }

  @Test
  public void testStartGame() {
    auto1.startGame(board1, 2);
    auto2.startGame(board4, 3);
    SameGameView view1 = new SameGameTextView(auto1);

    assertEquals("" +
            "X Y Y\n" +
            "X X B\n" +
            "X Y Y", view1.toString());
    try {
      auto2.startGame(board2, 3);
    } catch (IllegalStateException ignored) {
    }
    try {
      auto3.startGame(null, 3);
    } catch (IllegalArgumentException ignored) {
    }
    try {
      auto3.startGame(new ArrayList<>(), 3);
    } catch (IllegalArgumentException ignored) {
    }
    try {
      auto3.startGame(board5, 3);
    } catch (IllegalArgumentException ignored) {
    }
    try {
      auto3.startGame(board3, -1);
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testOtherStartGame() {
    // Tests deterministic board setup
    auto5.startGame(3, 3, 2, false);
    SameGameView view1 = new SameGameTextView(auto5);
    assertEquals("R G B\n" +
            "Y R G\n" +
            "B Y R", view1.toString());

    // Tests random board setup
    auto4.startGame(3, 3, 2, true);
    SameGameView view2 = new SameGameTextView(auto4);
    assertNotEquals("R G B\n" +
            "Y R G\n" +
            "B Y R", view2.toString());
    assertEquals(3, auto4.length());
    assertEquals(3, auto4.width());
    try {
      auto1.startGame(3, 3, 2, true);
    } catch (IllegalStateException ignored) {
    }
    try {
      auto3.startGame(0, -1, 2, true);
    } catch (IllegalArgumentException ignored) {
    }
    try {
      auto3.startGame(3, 2, -2, false);
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testSwap() {
    auto1.startGame(board1, 3);
    auto1.swap(0, 2, 1, 2);
    view = new SameGameTextView(auto1);
    assertEquals("" +
            "X Y B\n" +
            "X X X\n" +
            "X X X", view.toString());
    auto2.startGame(board2, 3);
    auto2.swap(4, 5, 3, 5);
    view = new SameGameTextView(auto2);
    assertEquals("" +
            "X X X X X X\n" +
            "X X B X X X\n" +
            "X X X X X X\n" +
            "X X B X X X\n" +
            "X G X Y X X\n" +
            "X X X X X X", view.toString());
    auto3.startGame(board3, 3);
    auto3.swap(5, 0, 4, 0);
    view = new SameGameTextView(auto3);
    assertEquals("" +
            "R R X X X X\n" +
            "Y G Y X X X\n" +
            "X X B R X X\n" +
            "X G X B R X\n" +
            "X X X G X X\n" +
            "Y X X X X B", view.toString());
  }

  @Test
  public void testRemoveMatch() {
    auto2.startGame(board2, 3);
    auto2.removeMatch(1, 1);
    view = new SameGameTextView(auto2);
    assertEquals("" +
            "X X X X X X\n" +
            "X X B X X B\n" +
            "X X X X X B\n" +
            "X X B X X X\n" +
            "X G X Y X B\n" +
            "X X X X X X", view.toString());
    auto3.startGame(board3, 3);
    auto3.removeMatch(1, 5);
    view = new SameGameTextView(auto3);
    assertEquals("" +
            "R R X X X X\n" +
            "Y G Y X X X\n" +
            "X X B R X X\n" +
            "X G X B R X\n" +
            "Y X X G X X\n" +
            "X X X X X B", view.toString());
  }
}
