package ez_squeeze.game.forecast;

import java.io.Serializable;

import ez_squeeze.game.Constants;

/**
 * 
 * @author Nick Stanish
 *
 */
public enum Weather implements Serializable {
  Sunny, Cloudy, Rainy;
  public static Weather randomWeather() {
    int random = Constants.random.nextInt(Weather.values().length);
    return Weather.values()[random];
  }
}
