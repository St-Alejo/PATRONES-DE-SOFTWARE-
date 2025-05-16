package projectGame;

public class StrategyGameFactory extends GameFactory {
    @Override
    public Game createGame() {
        return new StrategyGame();
    }
}
