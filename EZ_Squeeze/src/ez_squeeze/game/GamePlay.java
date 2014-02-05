package ez_squeeze.game;

import ez_squeeze.game.people.Person;

public class GamePlay {
	public static void simulateDay(State state){
		Constants.LOG("---\tSIMULATE DAY " + state.day.day.name() + "\t---");
		if (state == null || state.persons.size() == 0) return;
		for(Person joe: state.persons){
			Constants.LOG("" + joe.name);
			// throw in check for enough cups & whatnot
			if(joe.visits(state.day)){
				Constants.LOG("\tvisits");
				if(joe.willPurchase(state.recipe.price)){
					joe.Drink(state.recipe.criticism, state.recipe.flavor, state.forecast.temperature, state.ice);
					Constants.LOG("\t" + joe.getReaction());
					// TODO: get reaction
					// TODO: update state -- ingredients/pitcher and money
					//TODO; update stats
				}	
				else{
				//TODO:	joe.getReason(); -> stats
					Constants.LOG("\t" + joe.getReason());
				}
			}
		}
		Constants.LOG("---\tDONE\t---");
	}
	public static double findAverageSatisfaction(State state){
		double s = 0;
		if (state == null || state.persons.size() == 0) return -1;
		for(Person joe: state.persons){
			s += joe.satisfaction;
		}
		s = s / state.persons.size();
		return s;
	}
	public static void main(String[] args){
		Constants.LOG("TEST");
		State state = new State();
		state.recipe = new Recipe(5, 3, 2, 0.3);
		GamePlay.simulateDay(state);
		state.nextDay();
		GamePlay.simulateDay(state);
		state.nextDay();
		GamePlay.simulateDay(state);
		state.nextDay();
		Constants.LOG(GamePlay.findAverageSatisfaction(state) + "");
	}
}
