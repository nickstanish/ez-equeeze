package ez_squeeze;

import java.io.Serializable;
/*
 * GNU GPL v3
 * Copyright 2011-2012 Nick Stanish
 */

public class Pitcher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9012722274127105674L;
	private int rIce = 0;
	private int rLemons = 0;
	private int rSugar = 0;
	private int cups_per_pitcher = 14;
	private int cupsLeft = 0;
	private State state;
	public Pitcher(State state){
		this.state = state;
		check();
	}
	public void serve(){
		check();
		if (cupsLeft == 0){
			refill();
		}
		cupsLeft -= 1;
		state.cups--;
		if (state.ice - rIce < 0){
			rIce = 0;
		}
		state.ice -= rIce;
		System.out.println("served 1");
		reset();
	}
	public void refill(){
		if(state.lemons - rLemons < 0){
			//out of lemons
			rLemons = 0;		
		}
		if (state.sugar - rSugar < 0){
			//outs of sugar
			rSugar = 0;
		}
		state.lemons -= rLemons;
		state.sugar -= rSugar;
		cupsLeft = cups_per_pitcher;
		System.out.println("refilled");
		reset();
	}
	public void dump(){
		cupsLeft = 0;
	}
	public void check(){
		/*
		rLemons = EzSqueeze.getRecipeLemons();
		rIce = EzSqueeze.getRecipeIce();
		rSugar = EzSqueeze.getRecipeSugar();
		*/
	}
	public void reset(){
		/* TODO: change to recipe.lemons 
		 * EzSqueeze.setRecipeLemons(rLemons);
		EzSqueeze.setRecipeIce(rIce);
		EzSqueeze.setRecipeSugar(rSugar);
*/
	}
}
