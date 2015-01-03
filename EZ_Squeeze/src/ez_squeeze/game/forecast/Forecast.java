package ez_squeeze.game.forecast;

import java.io.Serializable;

import ez_squeeze.game.Constants;

/**
 * 
 * @author Nick Stanish
 *
 */
public class Forecast implements Serializable {
  public Weather weather;
  public int temperature;

  public Forecast() {
    weather = Weather.randomWeather();
    temperature = Constants.random.nextInt(60) + 50;
  }

}
