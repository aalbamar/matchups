package tourney.model;

import java.util.ArrayList;
import java.util.List;

import tourney.interfaces.TeamBehavior;

public class Team implements TeamBehavior {
	Integer id = null;
	String name = null;
	List<Player> players = null;

	public Team(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.players = new ArrayList<Player>();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayer (Player player) {
		if( player != null && this.isCompleted() ) {
			this.players.add(player);
		}
	}
	
	public void removePlayer (Player player) {
		if( player != null && this.players.size() > 0 ) {
			this.players.remove(player);
		}
	}

	@Override
	public boolean isCompleted() {
		return this.players != null ? this.players.size() == TeamBehavior.MAX_PLAYERS : false;
	}

	/*
	 * Team Rank = Average Players Ability
	 */
	@Override
	public float getRank() {
		float rank = 0;
		if( players != null ) {
			float totalAbility = 0;
			for (Player player : players) {
				totalAbility += player.getAbility();
			}
			rank = totalAbility / players.size();
		}
		
		return rank;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( this == obj )
			return true;
		else {
			Team team = (Team) obj;
			return this.id != null ? this.id.equals(team.getId())
								   : (obj == null ? true : false);
		}
	};
	
	@Override
	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	};
	
	@Override
	public String toString () {
		String json = "";
		String playersJson = "";
		
		if( players != null ) {
			for (int i = 0; i < players.size(); i++) {
				playersJson += players.get(i).toString();
				if( i > 0 && i < (players.size() - 1) )
					playersJson += ", ";
				
			}
		}
		
		json = "{\"id\" : " + id + ", " +
			   "\"name\" : \"" + name + "\"" + ", " +
			   "\"rank\" : " + getRank() + ", " +
			   "\"players\" : [" + playersJson + "]}";
		
		return json;
	}
}
