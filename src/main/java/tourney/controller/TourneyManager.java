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
	private static Comparator<Team> TEAM_COMPARATOR = new Comparator<Team>() {
		@Override
		public int compare(Team t1, Team t2) {
			float rankDiff = t1.getRank() - t2.getRank();
			return rankDiff < 0 ? 1 : (rankDiff == 0 ? 0 : -1);
		}
	};
	private static Comparator<Player> PLAYER_COMPARATOR = new Comparator<Player>() {
		@Override
		public int compare(Player p1, Player p2) {
			float abilityDiff = p1.getAbility() - p2.getAbility();
			return abilityDiff < 0 ? 1 : (abilityDiff == 0 ? 0 : -1);
		}
	};
	
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
		Collections.sort(tourney.getTeams(), TEAM_COMPARATOR);
		
		// Order players desc by Ability.
		Collections.sort(tourney.getPlayers(), PLAYER_COMPARATOR);
		
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
			top5Teams.add(tourney.getTeams().get(t++));
		}
		
		output = getTop5TeamsOutput(top5Teams);
		
		return top5Teams;
	}

	public static List<Player> top5Players() {
		List<Player> top5Player = new ArrayList<Player>();
		
		int p = 0;
		int top5PlayerSize = tourney.getPlayers().size() > 5 ? 5 : tourney.getPlayers().size();
		while( p < top5PlayerSize ) {
			top5Player.add(tourney.getPlayers().get(p++));
		}
		
		output = getTop5PlayersOutput(top5Player);
		
		return top5Player;
	}
	
	public static String showTourney() {
		return tourney.toString();
	}
	
	private static String getMatchupsOutput(List<Couple> matches) {
		String json = "";
		String matchesJson = "";
		
		if( matches != null && !matches.isEmpty() ) {
			for (int i = 0; i < tourney.getMatches().size(); i++) {
				matchesJson += tourney.getMatches().get(i).toString();
				if( i >= 0 && i < (tourney.getMatches().size() - 1) )
					matchesJson += ", ";
			}
		}
		
		json = "{\"matches\" : [" + matchesJson + "]}";
		
		return json;
	}
	
	private static String getTop5TeamsOutput(List<Team> top5Teams) {
		String json = "";
		String top5TeamsJson = "";
		
		if( top5Teams != null && !top5Teams.isEmpty() ) {
			for (int i = 0; i < top5Teams.size(); i++) {
				top5TeamsJson += top5Teams.get(i).toString();
				if( i >= 0 && i < (top5Teams.size() - 1) )
					top5TeamsJson += ", ";
			}
		}
		
		json = "{\"top5Teams\" : [" + top5TeamsJson + "]}";
		
		return json;
	}
	
	private static String getTop5PlayersOutput(List<Player> top5Players) {
		String json = "";
		String top5PlayersJson = "";
		
		if( top5Players != null && !top5Players.isEmpty() ) {
			for (int i = 0; i < top5Players.size(); i++) {
				top5PlayersJson += top5Players.get(i).toString();
				if( i >= 0 && i < (top5Players.size() - 1) )
					top5PlayersJson += ", ";
			}
		}
		
		json = "{\"top5Players\" : [" + top5PlayersJson + "]}";
		
		return json;
	}
}
