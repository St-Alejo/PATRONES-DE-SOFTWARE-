package projectGame;

import java.util.ArrayList;
import java.util.List;

public class Server {
	private String name;
    private List<Player> connectedPlayers;
    
    public Server(String name) {
        this.name = name;
        this.connectedPlayers = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void connect(Player player) {
        connectedPlayers.add(player);
    }
    
    public void disconnect(Player player) {
        connectedPlayers.remove(player);
    }
    
    @Override
    public String toString() {
        return "Server " + name + " (" + connectedPlayers.size() + " players connected)";
    }
}
