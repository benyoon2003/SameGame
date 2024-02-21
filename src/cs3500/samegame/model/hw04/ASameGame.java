package cs3500.samegame.model.hw04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;

/**
 * ASameGame is an abstract class that implements SameGameModel and is used to
 * abstract duplicated code from the variants of SameGame.
 */
public abstract class ASameGame implements SameGameModel<Piece> {
  protected List<List<Piece>> board;
  protected int swaps;
  protected int score;

  /**
   * An abstracted helper for removeMatch. Has the same functionality
   * but was abstracted for code reuse by the different variants.
   *
   * @param row the row of the piece to remove (0-based index)
   * @param col the col of the piece to remove (0-based index)
   * @throws IllegalStateException    if the game is over or has not started
   * @throws IllegalArgumentException if either argument is out-of-bounds
   * @throws IllegalStateException    if the coordinate has no piece
   * @throws IllegalStateException    if the matching block chosen is invalid according
   *                                  to the rules of the game
   */
  protected void removeOneMatchBlock(int row, int col) {
    gameNotStartedException();
    gameOverException();
    outOfBoundsException(row, col);
    noSamePieces(row, col);
    if (this.anyPiece(row, col).equals(Piece.X)) {
      throw new IllegalStateException("Coordinate has no piece");
    }

    // Makes a copy of this board so we do not have to mutate this board
    List<List<Piece>> copy = new ArrayList<>();
    for (int rowInd = 0; rowInd < this.width(); rowInd++) {
      copy.add(new ArrayList<>());
      for (int colInd = 0; colInd < this.length(); colInd++) {
        copy.get(rowInd).add(this.anyPiece(rowInd, colInd));
      }
    }

    Piece currPiece = this.anyPiece(row, col);
    int numEmptyInOrig = 0;
    int numEmptyInCopy = 0;
    this.removeOneMatchBlockHelper(row, col, currPiece, copy);

    // Counts the number of empty pieces in the original and copy boards
    for (int rowInd = 0; rowInd < this.width(); rowInd++) {
      for (int colInd = 0; colInd < this.length(); colInd++) {
        if (copy.get(rowInd).get(colInd).equals(Piece.X)) {
          numEmptyInCopy++;
        }
        try {
          if (this.board.get(rowInd).get(colInd).equals(Piece.X)) {
            numEmptyInOrig++;
          }
        } catch (NullPointerException ignored) {
        }
      }
    }
    int numNewEmpty = numEmptyInCopy - numEmptyInOrig;

    // We can only set this board as the copy if there are three or more pieces in
    // the matching block
    if (numNewEmpty >= 3) {
      this.board = copy;

      // The score is n - 2 where n is the number of pieces in a matching block
      this.score += numNewEmpty - 2;
    } else {
      throw new IllegalStateException("Not a matching block");
    }
  }

  /**
   * Marks currPiece with an empty space and recurses through the board
   * if the neighboring pieces of the currPiece are the same.
   *
   * @param row a row on the board
   * @param col a column on the board
   * @param currPiece a Piece
   * @param copy a List of a List of Pieces
   */
  private void removeOneMatchBlockHelper(int row, int col, Piece currPiece,
                                         List<List<Piece>> copy) {
    copy.get(row).set(col, Piece.X);

    // The piece to the right of currPiece is the same
    if (samePieceOnRight(row, col, copy, currPiece)) {
      this.removeOneMatchBlockHelper(row, col + 1, currPiece, copy);
    }
    // The piece below currPiece is the same
    if (samePieceOnBottom(row, col, copy, currPiece)) {
      this.removeOneMatchBlockHelper(row + 1, col, currPiece, copy);
    }
    // The piece to the left of currPiece is the same
    if (samePieceOnLeft(row, col, copy, currPiece)) {
      this.removeOneMatchBlockHelper(row, col - 1, currPiece, copy);
    }
    // The piece above currPiece is the same
    if (samePieceOnTop(row, col, copy, currPiece)) {
      this.removeOneMatchBlockHelper(row - 1, col, currPiece, copy);
    }
  }


  /**
   * Helper for swap that has the exact same functionality but was
   * abstracted to reduce code duplication between variants of SameGame.
   *
   * @param fromRow the row of the first piece (0-based index)
   * @param fromCol the col of the first piece (0-based index)
   * @param toRow   the row of the first piece (0-based index)
   * @param toCol   the col of the first piece (0-based index)
   * @throws IllegalStateException    if the game is over or has not started
   * @throws IllegalArgumentException if any argument is out-of-bounds
   * @throws IllegalStateException if both coordinates have no pieces on the board
   * @throws IllegalStateException if the swap coordinates are illegal according to the game
   * @throws IllegalStateException    if there are no swaps remaining
   */
  protected void swapHelper(int fromRow, int fromCol, int toRow, int toCol) {
    gameNotStartedException();
    gameOverException();
    outOfBoundsException(fromRow, fromCol);
    outOfBoundsException(toRow, toCol);
    if (this.anyPiece(fromRow, fromCol).equals(Piece.X)
            && this.anyPiece(toRow, toCol).equals(Piece.X)) {
      throw new IllegalStateException("Coordinate has no piece");
    }
    if (fromRow == toRow && fromCol == toCol) {
      throw new IllegalStateException("Cannot swap the same piece");
    }
    if (fromRow != toRow && fromCol != toCol) {
      throw new IllegalStateException("Must swap with pieces in either the same row or column");
    }
    if (this.remainingSwaps() < 1) {
      throw new IllegalStateException("No swaps remaining");
    }

    Piece fromPiece = this.board.get(fromRow).get(fromCol);
    Piece toPiece = this.board.get(toRow).get(toCol);
    this.board.get(fromRow).set(fromCol, toPiece);
    this.board.get(toRow).set(toCol, fromPiece);
    this.swaps--;
  }

  /**
   * Throws an IllegalStateException if the game is already started.
   */
  private void gameAlreadyStartedException() {
    if (!this.board.isEmpty() && !this.board.get(0).isEmpty()) {
      throw new IllegalStateException("Cannot start a game that has already started");
    }
  }

  /**
   * Throws an IllegalStateException if the game has not started.
   */
  private void gameNotStartedException() {
    if (this.board.isEmpty() || this.board == null) {
      throw new IllegalStateException("Game has not started");
    }
  }

  /**
   * Throws an IllegalArgumentException if the given swaps is negative.
   *
   * @param swaps number of swaps
   */
  private void negativeSwapsException(int swaps) {
    if (swaps < 0) {
      throw new IllegalArgumentException("Cannot have negative # of swaps");
    }
  }

  /**
   * Throws an IllegalArgumentException if the given coordinates are invalid for this board.
   *
   * @param row the row of the board
   * @param col the column of the board
   */
  private void outOfBoundsException(int row, int col) {
    if (row >= this.width() || row < 0 || col >= this.length() || col < 0) {
      throw new IllegalArgumentException("Coordinates out of bounds");
    }
  }

  /**
   * Throws an IllegalStateException if the game is already over.
   */
  private void gameOverException() {
    if (this.gameOver()) {
      throw new IllegalStateException("Game is over");
    }
  }

  /**
   * Throws an IllegalStateException if the piece in the given coordinates
   * do not have any same pieces around it.
   * @param row a row on the board
   * @param col a column on the board
   */
  private void noSamePieces(int row, int col) {
    if (!(this.samePieceOnLeft(row, col, this.board, this.anyPiece(row, col)) ||
            this.samePieceOnTop(row, col, this.board, this.anyPiece(row, col)) ||
            this.samePieceOnRight(row, col, this.board, this.anyPiece(row, col)) ||
            this.samePieceOnBottom(row, col, this.board, this.anyPiece(row, col)))) {
      throw new IllegalStateException("No same pieces adjacent to the one selected.");
    }
  }


  @Override
  public int width() {
    gameNotStartedException();
    return this.board.size();
  }

  @Override
  public int length() {
    gameNotStartedException();
    return this.board.get(0).size();
  }

  @Override
  public Piece pieceAt(int row, int col) {
    gameNotStartedException();
    outOfBoundsException(row, col);

    if (this.board.get(row).get(col) == null || this.board.get(row).get(col).equals(Piece.X)) {
      return null;
    } else {
      return this.board.get(row).get(col);
    }
  }

  @Override
  public int score() {
    gameNotStartedException();
    return this.score;
  }

  @Override
  public int remainingSwaps() {
    gameNotStartedException();
    return this.swaps;
  }

  @Override
  public boolean gameOver() {
    gameNotStartedException();

    int countBlue = 0;
    int countRed = 0;
    int countYellow = 0;
    int countGreen = 0;

    // Counts the number of each piece in this board
    for (int rowInd = 0; rowInd < this.width(); rowInd++) {
      for (int colInd = 0; colInd < this.length(); colInd++) {
        switch (this.anyPiece(rowInd, colInd)) {
          case B:
            countBlue++;
            break;
          case R:
            countRed++;
            break;
          case Y:
            countYellow++;
            break;
          case G:
            countGreen++;
            break;
          default:
            break;
        }
      }
    }
    // Game is over if there are not enough pieces to make matching blocks
    if (countBlue < 3 &&
            countRed < 3 &&
            countYellow < 3 &&
            countGreen < 3) {
      return true;
    } else {

      // Must check if any of the pieces in this board is part of matching block
      int numMatchingBlock = 0;
      for (int rowInd = 0; rowInd < this.width(); rowInd++) {
        for (int colInd = 0; colInd < this.length(); colInd++) {
          if (!this.anyPiece(rowInd, colInd).equals(Piece.X) && this.matchingBlock(
                  rowInd, colInd, this.board.get(rowInd).get(colInd))) {
            numMatchingBlock++;
          }
        }
      }
      // Game is over if there are no matching blocks and there are no more swaps
      return numMatchingBlock == 0 && this.remainingSwaps() < 1;
    }
  }


  /**
   * Determines if the piece on the coordinate given is part of a matching block.
   *
   * @param rows      a row of the board
   * @param cols      a column of the board
   * @param currPiece a Piece on the board
   * @return boolean
   */
  private boolean matchingBlock(int rows, int cols, Piece currPiece) {
    int count = 1;
    if (samePieceOnBottom(rows, cols, this.board, currPiece)) {
      count++;

    }
    if (samePieceOnRight(rows, cols, this.board, currPiece)) {
      count++;
    }
    if (samePieceOnTop(rows, cols, this.board, currPiece)) {
      count++;
    }
    if (samePieceOnLeft(rows, cols, this.board, currPiece)) {
      count++;
    }
    return count > 2;
  }

  /**
   * Determines if the piece on the right of this one is the same.
   *
   * @param rows      a row of the board
   * @param cols      a column of the board
   * @param board     a list of a list of Pieces
   * @param currPiece a Piece on the board
   * @return boolean
   */
  private boolean samePieceOnRight(int rows, int cols, List<List<Piece>> board, Piece currPiece) {
    if (cols + 1 >= board.get(0).size()) {
      return false;
    } else {
      return currPiece.equals(board.get(rows).get(cols + 1));
    }
  }

  /**
   * Determines if the piece on the left of this one is the same.
   *
   * @param rows      a row of the board
   * @param cols      a column of the board
   * @param board     a list of a list of Pieces
   * @param currPiece a Piece on the board
   * @return boolean
   */
  private boolean samePieceOnLeft(int rows, int cols, List<List<Piece>> board, Piece currPiece) {
    if (cols == 0) {
      return false;
    } else {
      return currPiece.equals(this.board.get(rows).get(cols - 1));
    }
  }

  /**
   * Determines if the piece below this one is the same.
   *
   * @param rows      a row of the board
   * @param cols      a column of the board
   * @param board     a list of a list of Pieces
   * @param currPiece a Piece on the board
   * @return boolean
   */
  private boolean samePieceOnBottom(int rows, int cols, List<List<Piece>> board, Piece currPiece) {
    if (rows + 1 >= board.size()) {
      return false;
    } else {
      return currPiece.equals(board.get(rows + 1).get(cols));
    }
  }

  /**
   * Determines if the piece above this one is the same.
   *
   * @param rows      a row of the board
   * @param cols      a column of the board
   * @param board     a list of a list of Pieces
   * @param currPiece a Piece on the board
   * @return boolean
   */
  private boolean samePieceOnTop(int rows, int cols, List<List<Piece>> board, Piece currPiece) {
    if (rows == 0) {
      return false;
    } else {
      return currPiece.equals(board.get(rows - 1).get(cols));
    }
  }

  /**
   * Helper for the startGame method that has the exact same
   * functionality but was abstracted for the purposes of reducing
   * code duplication among SameGame variants.
   *
   * @param rows   the number of rows in the board
   * @param cols   the number of columns in the board
   * @param swaps  the number of swaps allowed in the game
   * @param random true if the board should be setup randomly
   * @throws IllegalStateException    if the game has already started
   * @throws IllegalArgumentException if rows or cols are less than or equal to 0
   * @throws IllegalArgumentException if swaps is negative
   */
  protected void startGameHelper(int rows, int cols, int swaps, boolean random) {
    gameAlreadyStartedException();
    negativeSwapsException(swaps);
    if (rows < 1 || cols < 1) {
      throw new IllegalArgumentException("Dimensions of board cannot be less than 1");
    }
    Random rand = new Random();
    if (random) {
      for (int rowInd = 0; rowInd < rows; rowInd++) {
        this.board.add(new ArrayList<Piece>());
        for (int colInd = 0; colInd < cols; colInd++) {
          this.board.get(rowInd).add(createListOfPieces()[rand.nextInt(4)]);
        }
      }
    } else {
      int detIndex = 0;
      for (int rowInd = 0; rowInd < rows; rowInd++) {
        this.board.add(new ArrayList<Piece>());
        for (int colInd = 0; colInd < cols; colInd++) {
          if (detIndex == 4) {
            detIndex = 0;
          }
          this.board.get(rowInd).add(createListOfPieces()[detIndex]);
          detIndex++;
        }
      }
    }
    this.swaps = swaps;
  }

  /**
   * Helper for the startGame method that has the exact same
   * functionality but was abstracted for the purposes of reducing
   * code duplication among SameGame variants.
   *
   * @param board the board to start the game with
   * @param swaps the number of swaps
   * @throws IllegalStateException    if the game has already started
   * @throws IllegalArgumentException if board is null or empty or
   *                                  an invalid representation
   * @throws IllegalArgumentException if individual rows or columns of the board
   *                                  have different lengths (a non-rectangular board)
   * @throws IllegalArgumentException if swaps is negative
   */
  protected void startGameHelper(List<List<Piece>> board, int swaps) {
    try {
      gameAlreadyStartedException();
      negativeSwapsException(swaps);
      if (board.isEmpty()) {
        throw new IllegalArgumentException("Given board is empty");
      }

      for (int row = 1; row < board.size(); row++) {
        if (board.get(row).size() != board.get(row - 1).size()) {
          throw new IllegalArgumentException("Non rectangular board");
        }
      }
      this.swaps = swaps;
      this.board = new ArrayList<>(board);
    }
    catch (NullPointerException ignored) {
      throw new IllegalArgumentException("Cannot make a null board the model board");
    }
  }

  @Override
  public Piece[] createListOfPieces() {
    return new Piece[]{Piece.R, Piece.G, Piece.B, Piece.Y};
  }

  /**
   * Handles an empty piece on the board.
   *
   * @param row a row on the board
   * @param col a column on the board
   * @return Piece
   */
  private Piece anyPiece(int row, int col) {
    if (pieceAt(row, col) == null) {
      return Piece.X;
    } else {
      return pieceAt(row, col);
    }
  }

}
