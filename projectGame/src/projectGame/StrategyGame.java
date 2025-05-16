package projectGame;

public class StrategyGame extends Game {
    @Override
    public String getName() {
        return "Strategy Game";
    }
    
    @Override
    public void start(Player player) {
        System.out.println(player.getName() + " is commanding armies in a strategy game");
        player.setCurrentGame(this);
    }
    
    @Override
    public void end(Player player, int score) {
        System.out.println(player.getName() + " finished strategy game with score: " + score);
        player.setCurrentGame(null);
        GameManager.getInstance().updateRanking(getName(), player.getPlayerId(), score);
    }
}
