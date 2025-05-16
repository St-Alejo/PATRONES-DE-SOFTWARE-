package projectGame;

public class RPGGameFactory extends GameFactory {
    @Override
    public Game createGame() {
        return new RPGGame();
    }
}
