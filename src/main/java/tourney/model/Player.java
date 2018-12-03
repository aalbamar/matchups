package tourney.model;

import tourney.interfaces.PlayerBehavior;

public abstract class Player implements PlayerBehavior {
	private Integer id = null;
	private PlayerType type = null;
	private String name = null;
	private String surname = null;
	private Float speed = null;
	private Float skill = null;
	private Float speedSeek = null;
	private Float skillSeek = null;
	private Team team = null;
	
	public Player(Integer id, String name, String surname,
				  Float speed, Float skill,
				  Float speedSeek, Float skillSeek) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.speed = speed;
		this.skill = skill;
		this.speedSeek = speedSeek;
		this.skillSeek = skillSeek;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Float getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	public Float getSkill() {
		return skill;
	}

	public void setSkill(Float skill) {
		this.skill = skill;
	}

	public Float getSpeedSeek() {
		return speedSeek;
	}

	public Float getSkillSeek() {
		return skillSeek;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public float getAbility() {
		float ability = 0;
		if( this.getSpeed() != null && this.getSkill() != null &&
			this.getSpeedSeek() != null && this.getSkillSeek() != null )
			ability = (this.getSpeed() * this.getSpeedSeek()) +
					  (this.getSkill() * this.getSkillSeek());
		
		return ability;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( this == obj )
			return true;
		else {
			Player player = (Player) obj;
			return this.id != null ? this.id.equals(player.getId())
								   : (obj == null ? true : false);
		}
	};
	
	@Override
	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	};
	
	@Override
	public String toString () {
		String json = null;
		
		json = "{\"id\" : " + id + ", " +
			   "\"type\" : \"" + type + "\"" + ", " +
			   "\"name\" : \"" + name + "\"" + ", " +
			   "\"surname\" : \"" + surname + "\"" + ", " +
			   "\"speed\" : " + speed + ", " +
			   "\"skill\" : " + skill + "}";
		
		return json;
	}
}
