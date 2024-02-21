package cs3500.samegame.model.hw02;

import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.model.hw04.ASameGame;


/**
 * FourPieceSameGame implements the SameGameModel interface with concrete type Piece.
 */
final public class FourPieceSameGame extends ASameGame {

  /**
   * Creates a FourPieceSameGame and initializes the board.
   */
  public FourPieceSameGame() {
    this.board = new ArrayList<>();
    this.score = 0;
  }

  @Override
  public void swap(int fromRow, int fromCol, int toRow, int toCol) {
    this.swapHelper(fromRow, fromCol, toRow, toCol);
  }

  @Override
  public void removeMatch(int row, int col) {
    this.removeOneMatchBlock(row, col);
  }

  @Override
  public void startGame(int rows, int cols, int swaps, boolean random) {
    this.startGameHelper(rows, cols, swaps, random);
  }

  @Override
  public void startGame(List<List<Piece>> board, int swaps) {
    this.startGameHelper(board, swaps);
  }


}