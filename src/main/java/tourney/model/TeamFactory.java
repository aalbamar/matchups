package tourney.model;

import tourney.interfaces.TeamBehavior;

public class TeamFactory {
	private static TeamFactory instance;
	private Integer lastId = 1;
	
	private TeamFactory () {}
	
	public static synchronized TeamFactory getInstance () {
		if( instance == null )
			instance = new TeamFactory();
		
		return instance;
	}
	
	public synchronized Team getTeam () {
		return getTeam(null);
	}
	
	public synchronized Team getTeam (String name) {
		Integer id = lastId++;
		return getTeam(id,
					   name == null ? "name_" + id : name);
	}
	
	private static Team getTeam (Integer id, String name) {
		Team team = new Team(id, name);
		PlayerFactory playerfactory = PlayerFactory.getInstance();
		
		int p = 0;
		while( p++ < TeamBehavior.MAX_PLAYERS ) {
			Player player = playerfactory.getPlayer();
			player.setTeam(team);
			team.addPlayer(player);
		}
		
		return team;
	}
}
