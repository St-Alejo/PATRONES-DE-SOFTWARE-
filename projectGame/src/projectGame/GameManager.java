package projectGame;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    private static GameManager instance;
    
    private List<Game> games;
    private Map<String, Player> players;
    private List<Server> servers;
    private Map<String, List<PlayerScore>> rankings;
    
    private GameManager() {
        games = new ArrayList<>();
        players = new HashMap<>();
        servers = new ArrayList<>();
        servers.add(new Server("US-East"));
        servers.add(new Server("EU-Central"));
        servers.add(new Server("Asia-Pacific"));
        rankings = new HashMap<>();
    }
    
    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    public void addGame(Game game) {
        games.add(game);
        rankings.put(game.getName(), new ArrayList<>());
    }
    
    public List<String> getAvailableGames() {
        List<String> gameNames = new ArrayList<>();
        for (Game game : games) {
            gameNames.add(game.getName());
        }
        return gameNames;
    }
    
    public boolean connectPlayer(String playerId, String serverName) {
        Server server = null;
        for (Server s : servers) {
            if (s.getName().equals(serverName)) {
                server = s;
                break;
            }
        }
        if (server != null && players.containsKey(playerId)) {
            server.connect(players.get(playerId));
            return true;
        }
        return false;
    }
    
    public void updateRanking(String gameName, String playerId, int score) {
        if (rankings.containsKey(gameName)) {
            rankings.get(gameName).add(new PlayerScore(playerId, score));
            rankings.get(gameName).sort((ps1, ps2) -> ps2.getScore() - ps1.getScore());
        }
    }
    
    public List<PlayerScore> getRanking(String gameName, int top) {
        List<PlayerScore> ranking = rankings.getOrDefault(gameName, new ArrayList<>());
        return ranking.subList(0, Math.min(top, ranking.size()));
    }
    
    public List<PlayerScore> getRanking(String gameName) {
        return rankings.getOrDefault(gameName, new ArrayList<>());
    }
    
    public boolean registerPlayer(String playerId, String playerName) {
        if (!players.containsKey(playerId)) {
            players.put(playerId, new Player(playerId, playerName));
            return true;
        }
        return false;
    }
    
    public Player getPlayer(String playerId) {
        return players.get(playerId);
    }
    
    public List<String> getAvailableServers() {
        List<String> serverNames = new ArrayList<>();
        for (Server server : servers) {
            serverNames.add(server.getName());
        }
        return serverNames;
    }
    
    // Additional method needed for Facade implementation
    List<Game> getGames() {
        return games;
    }
}