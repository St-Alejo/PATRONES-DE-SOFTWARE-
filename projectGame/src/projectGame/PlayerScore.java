package projectGame;

public class PlayerScore {
	 private String playerId;
	    private int score;
	    
	    public PlayerScore(String playerId, int score) {
	        this.playerId = playerId;
	        this.score = score;
	    }
	    
	    public String getPlayerId() {
	        return playerId;
	    }
	    
	    public int getScore() {
	        return score;
	    }
	    
	    @Override
	    public String toString() {
	        return playerId + ": " + score;
	    }
}
