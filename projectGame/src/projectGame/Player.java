	package projectGame;

	public class Player {
	    private String playerId;
	    private String name;
	    private Game currentGame;
	    
	    public Player(String playerId, String name) {
	        this.playerId = playerId;
	        this.name = name;
	    }
	    
	    public String getPlayerId() {
	        return playerId;
	    }
	    
	    public String getName() {
	        return name;
	    }
	    
	    public Game getCurrentGame() {
	        return currentGame;
	    }
	    
	    public void setCurrentGame(Game currentGame) {
	        this.currentGame = currentGame;
	    }
	    
	    @Override
	    public String toString() {
	        return "Player [playerId=" + playerId + ", name=" + name + 
	               ", currentGame=" + (currentGame != null ? currentGame.getName() : "None") + "]";
	    }

		
	}
