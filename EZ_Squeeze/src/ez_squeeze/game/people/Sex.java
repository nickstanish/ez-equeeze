package ez_squeeze.game.people;

import ez_squeeze.game.Constants;
/**
 * 
 * @author Nick Stanish
 *
 */
public enum Sex {
	Male, Female;
	public static Sex randomSex(){
		if(Constants.random.nextBoolean()) return Sex.Male;
		return Sex.Female;
	}
	
}