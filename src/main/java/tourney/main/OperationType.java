package tourney.main;

public enum OperationType {
	NEW_TOURNEY("nuevo"),
	MATCHUP_TEAMS("emparejar"),
	TOP5_TEAMS("top5e"),
	TOP5_PLAYERS("opt5j"),
	SHOW_TOURNEY("ver"),
	UNKNOWN("");
	
	private String inputOperation = null;
	
	private OperationType(String inputOperation) {
		this.inputOperation = inputOperation;
	}

	public String getInputOperation() {
		return inputOperation;
	}
}
