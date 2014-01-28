package forecast;

import ez_squeeze.game.Constants;
/**
 * 
 * @author Nick Stanish
 *
 */
public enum Weather {
	Sunny, Cloudy, Rainy;
	public static Weather randomWeather(){
		int random = Constants.random.nextInt(Weather.values().length);
		return Weather.values()[random];
	}
}
