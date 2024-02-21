package cs3500.samegame.model.hw02;

/**
 * Represents a piece on the board.
 *
 * @implNote    equals() and hashCode() are not overwritten because Piece is an enum
 *              X represents an empty space
 */
public enum Piece {
  R("R"), G("G"), B("B"), Y("Y"), X("X");

  private final String disp;

  /**
   * Constructs a Piece enum.
   * @param disp a String
   */
  Piece(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return this.disp;
  }
}
