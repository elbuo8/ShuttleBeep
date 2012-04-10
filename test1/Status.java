package test1;

public class Status {
	
	public Status(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
		current = player1;
	}
	
	public String getStatus() {
		return current;
	}
	
	public String switchStatus() {
		current = player2;
		player2 = player1;
		return current;
	}
	
	
	private String player1;
	private String player2;
	private String current;
}
