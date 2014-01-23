package ez_squeeze;

import java.io.Serializable;

/**
 * Encapsulates variables
 * @author Nick Stanish
 *
 */
public class State implements Serializable{
	public int lemons, ice, sugar, cups;
	public Recipe recipe;
	public double money;
	public State(){
		lemons = 0;
		ice = 0;
		sugar = 0;
		cups = 0;
		money = Constants.startingMoney;
		recipe = new Recipe(2,2,2,0.25);
	}
	public void print(){
		System.out.println("Lemons: " + lemons);
		System.out.println("Sugar: " + sugar);
		System.out.println("Ice: " + ice);
		System.out.println("Cups: " + cups);
		System.out.println("Wallet: " + money);
	}
}
