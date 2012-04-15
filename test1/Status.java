package test1;

public class Status {
	
	public Status(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
		current = player1;
		movesplayer1 = 0;
		movesplayer2 = 0;
	}
	
	public int totalPlayer1() {
		return movesplayer1;
	}
	
	public int totalPlayer2() {
		return movesplayer2;
	}
	
	public String getStatus() {
		return current;
	}
	
	public String switchStatus() {
		current = player2;
		player2 = player1;
		player1 = current;
		return current;
	}
	
	public void incrementP1() {
		movesplayer1++;
	}
	
	public void incrementP2() {
		movesplayer2++;
	}
	
	
	private String player1;
	private String player2;
	private String current;
	private int movesplayer1;
	private int movesplayer2;
}
