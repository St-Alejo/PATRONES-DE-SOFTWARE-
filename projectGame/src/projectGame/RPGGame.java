package projectGame;

public class RPGGame extends Game {
    @Override
    public String getName() {
        return "Role Playing Game";
    }
    
    @Override
    public void start(Player player) {
        System.out.println(player.getName() + " is starting a new RPG adventure");
        player.setCurrentGame(this);
    }
    
    @Override
    public void end(Player player, int score) {
        System.out.println(player.getName() + " completed RPG adventure with score: " + score);
        player.setCurrentGame(null);
        GameManager.getInstance().updateRanking(getName(), player.getPlayerId(), score);
    }
}
