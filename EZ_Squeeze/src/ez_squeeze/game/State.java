package ez_squeeze.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ez_squeeze.game.forecast.Day;
import ez_squeeze.game.forecast.Forecast;
import ez_squeeze.game.people.Person;

/**
 * Encapsulates variables
 * 
 * @author Nick Stanish
 *
 */
public class State implements Serializable {
  /**
	 * 
	 */
  private static final long serialVersionUID = 3476352446933003208L;
  public Integer lemons, ice, sugar, cups;
  public Recipe recipe;
  public Double money;
  public Day day = new Day();
  public Forecast forecast = new Forecast();
  public Stats stats = new Stats();
  public List<Person> persons = new ArrayList<Person>();

  public State() {}

  public State(boolean initialize) {
    this();
    if (initialize) {
      init();
    }
  }



  private void init() {
    lemons = 0;
    ice = 0;
    sugar = 0;
    cups = 0;
    money = Constants.startingMoney;
    recipe = new Recipe(2, 2, 2, 0.25);
    generatePeople();
  }

  private void generatePeople() {
    for (int i = 0; i < Constants.startingPersons; i++) {
      persons.add(new Person());
    }
  }

  public void print() {
    Constants.LOG("Lemons: " + lemons);
    Constants.LOG("Sugar: " + sugar);
    Constants.LOG("Ice: " + ice);
    Constants.LOG("Cups: " + cups);
    Constants.LOG("Wallet: " + money);
  }

  public void printCustomers() {
    for (Person person : persons) {
      Constants.LOG(person.toString());
    }
  }

  public void nextDay() {
    day.nextDay();
    forecast = new Forecast();
  }
}
