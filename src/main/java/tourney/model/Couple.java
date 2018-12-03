package tourney.model;

public class Couple {
	Team team1 = null;
	Team team2 = null;
	
	public Couple(Team team1, Team team2) {
		super();
		this.team1 = team1;
		this.team2 = team2;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	
	@Override
	public String toString() {
		String json = null;
		
		json = "{\"team1\" : " + team1.toString() + ", " +
			   "\"team2\" : " + team2.toString() + "}";
		
		return json;
	}
	
}
