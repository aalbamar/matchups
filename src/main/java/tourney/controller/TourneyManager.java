package tourney.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tourney.model.Couple;
import tourney.model.Player;
import tourney.model.Team;
import tourney.model.TeamFactory;
import tourney.model.Tourney;

public class TourneyManager {
	public static Integer MAX_TEAMS = 20;
	private static Tourney tourney = null;
	private static String output = null;
	
	public static String getOutput() {
		return output != null ? output : "";
	}

	public static void newTourney() {
		tourney = new Tourney();
		
		tourney.setTeams(new ArrayList<Team>());
		tourney.setPlayers(new ArrayList<Player>());
		tourney.setMatches(new ArrayList<Couple>());
		
		TeamFactory teamFactory = TeamFactory.getInstance();
		int t = 0;
		while( t++ < MAX_TEAMS ) {
			Team team = teamFactory.getTeam();
			tourney.getTeams().add(team);
			for (Player player : team.getPlayers()) {
				tourney.getPlayers().add(player);
			}
		}
		
		// Order teams desc by Rank.
		Collections.sort(tourney.getTeams(), new Comparator<Team>() {
			@Override
			public int compare(Team t1, Team t2) {
				float rankDiff = t1.getRank() - t2.getRank();
				return (rankDiff > 0 ? 1 : (rankDiff < 0 ? -1 : 0) * -1);
			}
		});
		
		// Order players desc by Ability.
		Collections.sort(tourney.getPlayers(), new Comparator<Player>() {
			@Override
			public int compare(Player p1, Player p2) {
				float abilityDiff = p1.getAbility() - p2.getAbility();
				return (abilityDiff > 0 ? 1 : (abilityDiff < 0 ? -1 : 0) * -1);
			}
		});
		
		output = showTourney();
	}

	public static List<Couple> matchups() {
		int c = 0;
		int oddSize = tourney.getTeams().size() % 2 != 0 ? (tourney.getTeams().size() - 1 ) : tourney.getTeams().size();
		while( c < oddSize) {
			Couple couple = new Couple(tourney.getTeams().get(c++), tourney.getTeams().get(c++));
			tourney.getMatches().add(couple);
		}
		
		output = getMatchupsOutput(tourney.getMatches());
		
		return tourney.getMatches();
	}

	public static List<Team> top5Teams() {
		List<Team> top5Teams = new ArrayList<Team>();
		
		int t = 0;
		int top5TeamsSize = tourney.getTeams().size() > 5 ? 5 : tourney.getTeams().size();
		while( t < top5TeamsSize ) {
			top5Teams.add(tourney.getTeams().get(t));
		}
		
		output = getTop5TeamsOutput(top5Teams);
		
		return top5Teams;
	}

	public static List<Player> top5Players() {
		List<Player> top5Player = new ArrayList<Player>();
		
		int t = 0;
		int top5PlayerSize = tourney.getPlayers().size() > 5 ? 5 : tourney.getPlayers().size();
		while( t < top5PlayerSize ) {
			top5Player.add(tourney.getPlayers().get(t));
		}
		
		output = getTop5PlayersOutput(top5Player);
		
		return top5Player;
	}
	
	public static String showTourney() {
		String json = "";
		String teamsJson = "";
		
		if( tourney.getTeams() != null && !tourney.getTeams().isEmpty() ) {
			for (int i = 0; i < tourney.getTeams().size(); i++) {
				teamsJson += tourney.getTeams().get(i).toString();
				if( i > 0 && i < (tourney.getTeams().size() - 1) )
					teamsJson += ", ";
				
			}
		}
		
		json = "{\"teams\" : [" + teamsJson + "]}";
		
		TourneyManager.output = json;
		
		return json;
	}
	
	private static String getMatchupsOutput(List<Couple> matches) {
		String matchesString = "";
		
		if( matches != null && !matches.isEmpty() ) {
			for (Couple match : matches) {
				matchesString += match.toString() + "\n";
			}
		}
		
		return matchesString;
	}
	
	private static String getTop5TeamsOutput(List<Team> top5Teams) {
		String top5TeamsString = "";
		
		if( top5Teams != null && !top5Teams.isEmpty() ) {
			for (Team team : top5Teams) {
				top5TeamsString += team.toString() + "\n";
			}
		}
		
		return top5TeamsString;
	}
	
	private static String getTop5PlayersOutput(List<Player> top5Players) {
		String top5PlayersString = "";
		
		if( top5Players != null && !top5Players.isEmpty() ) {
			for (Player player : top5Players) {
				top5PlayersString += player.toString() + "\n";
			}
		}
		
		return top5PlayersString;
	}
}
