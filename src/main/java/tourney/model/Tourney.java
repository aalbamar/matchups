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
		String result = "Torneo no cargado.";
		String teamsString = "";
		String matchesString = "";
		
		if( teams != null && !teams.isEmpty() ) {
			for (Team team : teams) {
				teamsString += team.toString() + "\n";
			}
		}
		
		if( matches != null && !matches.isEmpty() ) {
			for (Couple match : matches) {
				matchesString += match.toString() + "\n";
			}
		}
		
		if( !teamsString.equals("") ) {
			result = "Equipos:" + "\n";
			result += teamsString;
			
			if( !matchesString.equals("") ) {
				result = "Emparejamientos:" + "\n";
				result += matchesString;
			}
		}
		
		return result;
	}
}
