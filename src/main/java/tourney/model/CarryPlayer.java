package tourney.model;

public class CarryPlayer extends Player {
	private static final Float SPEEDSEEK = 2F;
	private static final Float SKILLSEEK = 0.5F;
	
	public CarryPlayer(Integer id, String name, String surname,
					   Float speed, Float skill) {
		super(id, name, surname,
			  speed, skill,
			  SPEEDSEEK, SKILLSEEK);
	}
}
