package projectGame;

public abstract class Game {
    public abstract String getName();
    public abstract void start(Player player);
    public abstract void end(Player player, int score);
}
