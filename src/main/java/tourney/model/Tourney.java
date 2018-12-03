package tourney.model;

import java.util.List;

public class Tourney {
	private List<Team> teams = null;
	private List<Player> players = null;
	private List<Couple> matches = null;

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Couple> getMatches() {
		return matches;
	}

	public void setMatches(List<Couple> matches) {
		this.matches = matches;
	}
	
	@Override
	public String toString() {
		String json = "";
		String teamsJson = "";
		
		if( teams != null && !teams.isEmpty() ) {
			for (int i = 0; i < teams.size(); i++) {
				teamsJson += teams.get(i).toString();
				if( i >= 0 && i < (teams.size() - 1) )
					teamsJson += ", ";
			}
		}
		
		json = "{\"teams\" : [" + teamsJson + "]}";
		
		return json;
	}
}
