package projectGame;

public class FPSGameFactory extends GameFactory {
    @Override
    public Game createGame() {
        return new FPSGame();
    }
}
