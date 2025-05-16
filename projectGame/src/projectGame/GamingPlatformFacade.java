package projectGame;



import java.util.List;
import java.util.Map;

public class GamingPlatformFacade {
    private GameManager gameManager;
    
    public GamingPlatformFacade() {
        gameManager = GameManager.getInstance();
        initializeGames();
    }
    
    private void initializeGames() {
        // Create games using their factories
        GameFactory[] factories = {new FPSGameFactory(), new RPGGameFactory(), new StrategyGameFactory()};
        for (GameFactory factory : factories) {
            gameManager.addGame(factory.createGame());
        }
    }
    
    public boolean registerPlayer(String playerId, String playerName) {
        return gameManager.registerPlayer(playerId, playerName);
    }
    
    public List<String> getAvailableGames() {
        return gameManager.getAvailableGames();
    }
    
    public List<String> getAvailableServers() {
        return gameManager.getAvailableServers();
    }
    
    public boolean connectToServer(String playerId, String serverName) {
        return gameManager.connectPlayer(playerId, serverName);
    }
    
    public boolean startGameSession(String playerId, String gameName) {
        Player player = gameManager.getPlayer(playerId);
        if (player == null) {
            return false;
        }
        
        for (Game game : gameManager.getGames()) {
            if (game.getName().equals(gameName)) {
                game.start(player);
                return true;
            }
        }
        return false;
    }
    
    public boolean endGameSession(String playerId, int score) {
        Player player = gameManager.getPlayer(playerId);
        if (player == null || player.getCurrentGame() == null) {
            return false;
        }
        
        player.getCurrentGame().end(player, score);
        return true;
    }
    
    public List<PlayerScore> getRanking(String gameName) {
        return gameManager.getRanking(gameName, 10);
    }
    
    public Map<String, Object> getPlayerInfo(String playerId) {
        Player player = gameManager.getPlayer(playerId);
        if (player != null) {
            return Map.of(
                "id", player.getPlayerId(),
                "name", player.getName(),
                "currentGame", player.getCurrentGame() != null ? player.getCurrentGame().getName() : "None"
            );
        }
        return null;
    }

	
}