package cs3500.samegame.model.hw04;

import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;

/**
 * A factory class that creates a SameGame.
 */
final public class SameGameCreator {

  /**
   * An enumeration describing three possible SameGame variants.
   */
  public enum GameType {
    FOURPIECE("fourpiece"), GRAVITY("gravity"), AUTOMATCH("automatch");
    private final String disp;
    GameType(String s) {
      this.disp = s;
    }

    @Override
    public String toString() {
      return this.disp;
    }
  }

  /**
   * Constructs a SameGameCreator.
   */
  public SameGameCreator() {
    // No params for SameGameCreator
  }

  /**
   * Creates a SameGame depending on the given GameType.
   * @param type a GameType
   * @return SameGameModel
   */
  public static SameGameModel<Piece> createGame(GameType type) {
    switch (type) {
      case FOURPIECE:
        return new FourPieceSameGame();
      case GRAVITY:
        return new GravitySameGame();
      case AUTOMATCH:
        return new AutoMatchSameGame();
      default:
        break;
    }
    return null;
  }
}
