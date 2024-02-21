package cs3500.samegame.model.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.model.hw02.Piece;

/**
 * A version of SameGame that forces pieces to fall
 * as long as there is a missing piece beneath them.
 */
final public class GravitySameGame extends ASameGame {
  public GravitySameGame() {
    this.board = new ArrayList<>();
    this.score = 0;
  }

  @Override
  public void swap(int fromRow, int fromCol, int toRow, int toCol) {
    this.swapHelper(fromRow, fromCol, toRow, toCol);
    this.gravity();
  }

  @Override
  public void removeMatch(int row, int col) {
    this.removeOneMatchBlock(row, col);
    this.gravity();
  }


  @Override
  public void startGame(int rows, int cols, int swaps, boolean random) {
    this.startGameHelper(rows, cols, swaps, random);
    this.gravity();
  }

  @Override
  public void startGame(List<List<Piece>> board, int swaps) {
    this.startGameHelper(board, swaps);
    this.gravity();
  }


  /**
   * Recreates gravity in SameGame by moving all pieces with an empty
   * piece below it down and getting rid of the empty piece.
   */
  private void gravity() {
    int checkedLevels = 0;
    while (checkedLevels != this.width()) {
      for (int col = 0; col < this.length(); col++) {
        for (int row = 0; row < this.width() - 1; row++) {
          if (this.pieceAt(row + 1, col) == null && this.pieceAt(row, col) != null) {
            this.board.get(row + 1).set(col, this.board.get(row).get(col));
            this.board.get(row).set(col, Piece.X);
          }
        }
      }
      checkedLevels++;
    }
  }
}
