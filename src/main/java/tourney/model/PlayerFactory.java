package tourney.model;


public class PlayerFactory {
	private static PlayerFactory instance;
	private Integer lastId = 1;
	
	private PlayerFactory () {}
	
	public static synchronized PlayerFactory getInstance () {
		if( instance == null )
			instance = new PlayerFactory();
		
		return instance;
	}
	
	public synchronized Player getPlayer () {
		return getPlayer(null, null, null);
	}
	
	public synchronized Player getPlayer (PlayerType playerType) {
		return getPlayer(playerType, null, null);
	}
	
	public synchronized Player getPlayer (PlayerType playerType, String name, String surname) {
		Integer id = lastId++;
		PlayerType plyType = playerType == null ? (Double.valueOf(Math.rint(Math.random())).intValue() == PlayerType.CARRY.ordinal() ?
													PlayerType.CARRY : PlayerType.COACH) :
														playerType;
		return getPlayer(plyType,
						 id,
						 name == null ? "name_" + id : name,
						 surname == null ? "surname" + id : surname);
	}
	
	private Player getPlayer (PlayerType playerType, Integer id, String name, String surname) {
		Player player = null;
		Float speed = ((Double.valueOf(Math.random()).floatValue() * 100) % 100) + 0.1f; // From 0.1 to 100.0
		Float skill = ((Double.valueOf(Math.random()).floatValue() * 100) % 50) + 0.1f; // From 0.1 to 50.0
		
		switch( playerType ) {
			case CARRY:
				player = new CarryPlayer(id, name, surname,
										 speed, skill);
				break;
			case COACH:
				player = new CoachPlayer(id, name, surname,
										 speed, skill);
				break;
		}
		
		return player;
	}
}
