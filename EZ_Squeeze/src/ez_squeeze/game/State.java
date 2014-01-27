package ez_squeeze.game;

import java.io.Serializable;
import java.util.ArrayList;

import ez_squeeze.game.people.Person;

/**
 * Encapsulates variables
 * @author Nick Stanish
 *
 */
public class State implements Serializable{
	public int lemons, ice, sugar, cups;
	public Recipe recipe;
	public double money;
	public ArrayList<Person> persons = new ArrayList<Person>();
	public State(){
		lemons = 0;
		ice = 0;
		sugar = 0;
		cups = 0;
		money = Constants.startingMoney;
		recipe = new Recipe(2,2,2,0.25);
		for(int i = 0; i < Constants.startingPersons; i++){
			persons.add(new Person());
		}
		printCustomers();
	}
	public void print(){
		Constants.LOG("Lemons: " + lemons);
		Constants.LOG("Sugar: " + sugar);
		Constants.LOG("Ice: " + ice);
		Constants.LOG("Cups: " + cups);
		Constants.LOG("Wallet: " + money);
	}
	public void printCustomers(){
		for(Person person: persons){
			Constants.LOG(person.toString());
		}
	}
}