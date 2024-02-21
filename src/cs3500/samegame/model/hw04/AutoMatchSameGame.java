package cs3500.samegame.model.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.model.hw02.Piece;

/**
 * AutoMatchSameGame is a variant of SameGame that automatically
 * gets rid of all matching blocks after a move.
 */
final public class AutoMatchSameGame extends ASameGame {

  /**
   * Constructs AutoMatchSameGame.
   */
  public AutoMatchSameGame() {
    this.board = new ArrayList<>();
    this.score = 0;
  }

  /**
   * A variant of the swap method, different from the interface.
   * After a swap is executed, all matching blocks on the board
   * are replaced with empty spaces.
   * @param fromRow the row of the first piece (0-based index)
   * @param fromCol the col of the first piece (0-based index)
   * @param toRow   the row of the first piece (0-based index)
   * @param toCol   the col of the first piece (0-based index)
   */
  @Override
  public void swap(int fromRow, int fromCol, int toRow, int toCol) {
    this.swapHelper(fromRow, fromCol, toRow, toCol);
    this.autoRemoveMatch();
  }

  @Override
  public void removeMatch(int row, int col) {
    this.removeOneMatchBlock(row, col);
    this.autoRemoveMatch();
  }

  /**
   * Checks every piece on the board to check if it is part of
   * a matching block and removes it if it is.
   */
  private void autoRemoveMatch() {
    for (int row = 0; row < this.width(); row++) {
      for (int col = 0; col < this.length(); col++) {
        try {
          this.removeOneMatchBlock(row, col);
        }
        catch (IllegalStateException e) {
          if (e.getMessage().equals("Game has not started")) {
            throw new IllegalStateException("Game has not started");
          }
        }
      }
    }
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
