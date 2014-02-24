package ez_squeeze.game;

import ez_squeeze.game.people.Person;
/**
 * 
 * @author Nick Stanish
 *
 */
public class GamePlay {
	public static void simulateDay(State state){
		
		Constants.LOG("---\tSIMULATE DAY " + state.day.day.name() + "\t---");
		if (state == null || state.persons.size() == 0) return;
		
		state.stats.days++;
		state.stats.cupsSoldToday = 0;
		state.stats.visitorsToday = 0;
		state.stats.moneyEarnedToday = 0;
		
		Pitcher pitcher = new Pitcher(state); 			// create and fill pitcher
		for(Person joe: state.persons){
			Constants.LOG("" + joe.getName());
			if(state.cups > 0){							// throw in check for enough cups before processing calculations
				if(joe.visits(state.day)){
					state.stats.visitorsToday++;
					if(joe.willPurchase(state.recipe.price) ){
						Constants.LOG("\twilling to buy");
						if( pitcher.serve()){
							state.stats.moneyEarnedToday+= state.recipe.price;
							state.stats.cupsSoldToday++;
							Constants.LOG("\tbought");
							joe.Drink(state.recipe.criticism, state.recipe.flavor, state.forecast.temperature, state.ice);
							Constants.LOG("\t" + joe.getReaction());
							// TODO: display reaction
							//TODO; display stats
						}
						else Constants.LOG("\tunable to serve");
					}	
					else{
					//TODO:	joe.getReason(); -> stats
						Constants.LOG("\t" + joe.getReason());
					}
				}
			}
			else{
				// Out of Cups
				Constants.LOG("---\tNo more cups\t---");
			}
		}
		state.stats.moneyEarned += state.stats.moneyEarnedToday;
		state.stats.cupsSold += state.stats.cupsSoldToday;
		state.stats.averageSatisfaction =  findAverageSatisfaction(state);
		Constants.LOG("---\tDONE\t---");
	}
	public static double findAverageSatisfaction(State state){
		double s = 0;
		if (state == null || state.persons.size() == 0) return -1;
		for(Person joe: state.persons){
			s += joe.getSatisfaction();
		}
		s = s / state.persons.size();
		return s;
	}
	private static void mainTestSim(State state){
		state.cups = 25;
		state.lemons = 30;
		state.sugar = 15;
		state.ice = 50;
		GamePlay.simulateDay(state);
		state.nextDay();
	}
	public static void main(String[] args){
		Constants.LOG("TEST");
		State state = new State();
		state.recipe = new Recipe(5, 3, 2, 0.3);
		mainTestSim(state);
		mainTestSim(state);
		mainTestSim(state);
		mainTestSim(state);
		Constants.LOG(GamePlay.findAverageSatisfaction(state) + "");
	}
}
