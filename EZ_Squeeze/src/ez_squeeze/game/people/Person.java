package ez_squeeze.game.people;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ez_squeeze.game.Constants;
import ez_squeeze.game.Recipe.Criticism;
import ez_squeeze.game.Recipe.Flavor;
import ez_squeeze.game.forecast.Day;
import ez_squeeze.game.forecast.Days;

/**
 * GNU GPL v3 Copyright 2011-2014 Nick Stanish
 */
public class Person implements Serializable {
  private static final long serialVersionUID = -8607643105145232932L;
  private double thirstiness;
  private Flavor flavor;
  private Sex sex;
  private String name;
  private SocialClass socialClass;
  private List<Days> schedule = new ArrayList<Days>();
  private String reason = "";
  private String reaction = "";
  /**
   * Having a limited amount of money in wallet makes a player unable to set their price extremely
   * high and take advantage of the random nature of the buying process. i.e. someone set their
   * price over a million and 1 person bought because the randoms came out just right. but now there
   * is a check in place to see if the person can *afford* it.
   */
  private double satisfaction = 50;
  private double experience = 0;
  private BigDecimal wallet;

  /**
   * Constructor creates a person with a random name, gender, general thirstiness, schedule, flavor
   * preference, and wealth
   */
  public Person() {
    sex = Sex.randomSex();
    name = NameFactory.createName(sex);
    scheduler();
    thirstiness = Math.abs(Constants.random.nextDouble() - .3);
    double value = Constants.random.nextDouble();
    if (value < 0.55) {
      socialClass = SocialClass.LOWER;
    } else if (value < 0.85) {
      socialClass = SocialClass.MIDDLE;
    } else {
      socialClass = SocialClass.UPPER;
    }
    wallet = socialClass.getWealth();
    flavor = Constants.random.nextBoolean() ? Flavor.Sour : Flavor.Sweet;
  }

  /**
   * creates a schedule for the person
   */
  private void scheduler() {
    Days[] days = Days.values();
    while (schedule.isEmpty()) {
      for (int a = 0; a < 7; a++) {
        if (Constants.random.nextBoolean()) {
          schedule.add(days[a]);
        }
      }
    }
  }

  /**
   * simulates a person drinking the cup of lemonade they just bought this is intended to be run
   * AFTER checking if the person is willing to drink, and after making sure that the player is able
   * to serve another person (i.e has enough supply)
   * 
   * this generates a person's overall experience and satisfaction
   * 
   * @param criticism
   * @param flavor
   * @param temperature
   * @param ice
   */
  public void drink(Criticism criticism, Flavor flavor, double temperature, int ice) {
    experience += criticism.getScore();
    reaction = criticism.getReaction();

    int iceNeeded = (int) ((temperature - 70) / 10) + 1;
    if (ice >= iceNeeded && ice <= iceNeeded + 3) {
      experience += Constants.enough_ice;
    } else if (ice < iceNeeded) {
      experience += Constants.not_enough_ice;
    }
    if (this.flavor == flavor) {
      experience += Constants.my_flavor;
    }
    satisfaction = 50 * ((experience / Math.sqrt((experience * experience) + 1)) + 1);

  }


  public boolean visits(Day day) {
    return schedule.contains(day.day);
  }

  /**
   * given only the price, and interior variables, determines if a person is willing to buy or not
   * --recipe does not affect this, aside from past experience
   * 
   * @param price: cost of cup of lemonade
   * @return true if person will purchase
   */
  public boolean willPurchase(double price) {
    boolean purchase = false;
    if (price <= socialClass.getWealth().floatValue()) {
      if (Constants.random.nextDouble() >= thirstiness) {// thirst at this time is higher than that
                                                         // needed to require a drink
        if (Constants.random.nextDouble() <= (satisfaction / 80.0)) {
          purchase = true;
        } else {
          reason = "I'm not feeling THAT adventurous.";
        }
      } else {
        reason = "I'm not thirsty";
      }
    } else {
      reason = "Too pricey!";
      // TODO: lower satisfaction
    }
    return purchase;
  }

  /**
   * increase spendable amount of money in a persons wallet
   * 
   * @param bonus
   */
  public void giveBonus(BigDecimal bonus) {
    wallet = wallet.add(bonus);
  }

  @Override
  public String toString() {
    return name + ":\n\t" + sex.name() + "\n\t" + schedule + "\n\t" + socialClass.name() + " class";
  }

  public String getName() {
    return name;
  }

  public String getReason() {
    return reason;
  }

  /**
   * returns reaction
   * 
   * @return
   */
  public String getReaction() {
    return reaction;
  }

  public double getSatisfaction() {
    return satisfaction;
  }
}
