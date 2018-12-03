package tourney.model;

public class CoachPlayer extends Player {
	private static final Float SPEEDSEEK = 0.2F;
	private static final Float SKILLSEEK = 3F;
	
	public CoachPlayer(Integer id, String name, String surname,
					   Float speed, Float skill) {
		super(id, name, surname,
			  speed, skill,
			  SPEEDSEEK, SKILLSEEK);
	}
}
