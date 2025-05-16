package projectGame;

public class FPSGame extends Game {
    @Override
    public String getName() {
        return "First Person Shooter";
    }
    
    @Override
    public void start(Player player) {
        System.out.println(player.getName() + " is entering a FPS match");
        player.setCurrentGame(this);
    }
    
    @Override
    public void end(Player player, int score) {
        System.out.println(player.getName() + " finished FPS match with score: " + score);
        player.setCurrentGame(null);
        GameManager.getInstance().updateRanking(getName(), player.getPlayerId(), score);
    }
}