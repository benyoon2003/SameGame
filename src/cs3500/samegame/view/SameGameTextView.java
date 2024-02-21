package cs3500.samegame.view;

import java.io.IOException;

import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;

/**
 * SameGameTextView implements SameGameView.
 *
 * @param <T> a generic type
 */
public class SameGameTextView<T> implements SameGameView<T> {
  private final SameGameModel<T> model;
  private Appendable out;

  /**
   * Creates a SameGameTextView.
   *
   * @param model a SameGameModel
   */
  public SameGameTextView(SameGameModel<T> model) {
    this.model = model;
  }

  public SameGameTextView(SameGameModel<T> model, Appendable out) {
    this.model = model;
    this.out = out;
  }

  @Override
  public String toString() {
    String board = "";
    for (int rows = 0; rows < model.width(); rows++) {
      for (int cols = 0; cols < model.length(); cols++) {
        Piece p;
        if (this.model.pieceAt(rows, cols) == null) {
          board = board + Piece.X + " ";
        } else {
          board = board + this.model.pieceAt(rows, cols).toString() + " ";
        }
      }

      board = board.substring(0, board.length() - 1) + "\n";
    }
    return board.substring(0, board.length() - 1);
  }

  @Override
  public void render() throws IOException {
    this.out.append(this.toString());
  }
}

