package ez_squeeze.game;

import java.io.Serializable;
/*
 * GNU GPL v3
 * Copyright 2011-2014 Nick Stanish
 */

/**
 * This class encapsulates actions involved in serving cups of lemonade
 * This will automatically deduct necessary ingredients whenever a new pitcher of lemonade is made
 * Each cup uses the amount of ice selected in the recipe, but the recipe lemon/sugar fills a pitcher
 * 
 * This class is intended to be reconstructed at the beginning of each day, rather than resetting
 * at the end of the day, because it is more of a helper class.
 * @author Nick Stanish
 *
 */
public class Pitcher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9012722274127105674L;
	public static final int MAX_CUPS = 14;
	private int cupsRemaining = 0;
	private State state;
	private Recipe recipe;
	
	/**
	 * Constructor for Pitcher helper class
	 * Requires the current game state in order to get recipe information and deduct ingredients
	 * @param state - current game state
	 */
	public Pitcher(State state){
		if(state == null) throw new RuntimeException("Pitcher State Null");
		if(state.recipe == null) throw new RuntimeException("Pitcher Recipe Null");
		this.state = state;
		this.recipe = state.recipe;
		refill(); 
	}
	
	/**
	 * Simulates serving a cup out of the pitcher
	 * requires enough ingredients/supplies
	 * if no ice, don't serving ( TODO: option to serve without ice -- updating recipe? )
	 * also updates money in wallet
	 * @return true if succeeds in serving a cup
 	 */
	public boolean serve(){
		if (cupsRemaining == 0 && !refill()){
			// No servings left in pitcher and not enough ingredients to refill
			return false; 
		}
		if( state.cups <= 0) return false; // need cups in order to serve
		if( recipe.ice > state.ice ) return false; // not enough ice
		cupsRemaining--; 
		state.cups--;
		state.ice -= recipe.ice;
		state.money += state.recipe.price;
		Constants.LOG("--Served--");
		return true;
	}
	
	/**
	 * attempts to refill the pitcher using the ingredients from state
	 * and the current recipe
	 * 
	 * @return true on success
	 */
	private boolean refill(){
		if( recipe.lemons > state.lemons ) return false;	//out of lemons
		if( recipe.sugar >  state.sugar  )	 return false;	//out of sugar

		state.lemons -= recipe.lemons;
		state.sugar  -= recipe.sugar;
		cupsRemaining = MAX_CUPS;
		Constants.LOG("---Pitcher refilled---");
		return true;
	}
	
	/**
	 * Getter for the amount of cups remaining
	 * @return cupsRemaining
	 */
	public int cupsRemaining(){
		return cupsRemaining;
	}
	
}
