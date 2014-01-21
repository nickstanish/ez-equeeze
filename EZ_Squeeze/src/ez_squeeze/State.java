package ez_squeeze;
/**
 * Encapsulates variables
 * @author Nick Stanish
 *
 */
public class State {
	public int lemons, ice, sugar, cups;
	public double money;
	public State(){
		lemons = 0;
		ice = 0;
		sugar = 0;
		cups = 0;
		money = Constants.startingMoney;
	}
	public void print(){
		System.out.println("Lemons: " + lemons);
		System.out.println("Sugar: " + sugar);
		System.out.println("Ice: " + ice);
		System.out.println("Cups: " + cups);
		System.out.println("Wallet: " + money);
	}
}
