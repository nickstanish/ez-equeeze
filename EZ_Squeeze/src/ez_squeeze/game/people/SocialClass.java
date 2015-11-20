package ez_squeeze.game.people;

import java.math.BigDecimal;

public enum SocialClass {
  LOWER("0.60"),

  MIDDLE("1.00"),

  UPPER("1.50");

  private SocialClass(String wealth) {
    this.wealth = new BigDecimal(wealth);
  }

  private BigDecimal wealth;

  public BigDecimal getWealth() {
    return wealth;
  }

}
