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
	private int lemons = 0;
	private int ice = 0;
	private int sugar = 0;
	private int rIce = 0;
	private int rLemons = 0;
	private int rSugar = 0;
	private int cups;
	private int cups_per_pitcher = 14;
	private int cupsLeft = 0;
	Pitcher(){
		check();
	}
	public void serve(){
		check();
		if (cupsLeft == 0){
			refill();
		}
		cupsLeft -= 1;
		cups--;
		if (ice - rIce < 0){
			rIce = 0;
		}
		ice -= rIce;
		System.out.println("served 1");
		reset();
	}
	public void refill(){
		if(lemons - rLemons < 0){
			//out of lemons
			rLemons = 0;		
		}
		if (sugar - rSugar < 0){
			//outs of sugar
			rSugar = 0;
		}
		lemons -= rLemons;
		sugar -= rSugar;
		cupsLeft = cups_per_pitcher;
		System.out.println("refilled");
		reset();
	}
	public void dump(){
		cupsLeft = 0;
	}
	public void check(){
		/*rLemons = EzSqueeze.getRecipeLemons();
		rIce = EzSqueeze.getRecipeIce();
		rSugar = EzSqueeze.getRecipeSugar();
		cups = EzSqueeze.getCups();
		lemons = EzSqueeze.getLemons();
		ice = EzSqueeze.getIce();
		sugar = EzSqueeze.getSugar();*/
	}
	public void reset(){
		/*EzSqueeze.setRecipeLemons(rLemons);
		EzSqueeze.setRecipeIce(rIce);
		EzSqueeze.setRecipeSugar(rSugar);
		EzSqueeze.setCups(cups);
		EzSqueeze.setLemons(lemons);
		EzSqueeze.setIce(ice);
		EzSqueeze.setSugar(sugar);*/
	}
}
