package ez_squeeze.game.people;

import ez_squeeze.game.Constants;
/**
 * 
 * @author Nick Stanish
 *
 */
public class NameFactory{
	public static String createName(Sex sex){
		String name = "";
		if (sex == Sex.Male){
			name = Constants.maleNames[Constants.random.nextInt(Constants.maleNames.length)];
		}
		else{
			name = Constants.femaleNames[Constants.random.nextInt(Constants.femaleNames.length)];
		}
		name += " " + Constants.lastNames[Constants.random.nextInt(Constants.lastNames.length)];
		return name;
	}
}