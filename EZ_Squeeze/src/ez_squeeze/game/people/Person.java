package ez_squeeze.game.people;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import ez_squeeze.game.Constants;
import ez_squeeze.game.Days;
import ez_squeeze.game.Recipe;
import ez_squeeze.game.Recipe.Criticism;
import ez_squeeze.game.Recipe.Flavor;
public class Person implements Serializable{
	/*
	 * GNU GPL v3
	 * Copyright 2011-2012 Nick Stanish
	 */

	private static final long serialVersionUID = -8607643105145232932L;
	public double thirstiness;
	public Flavor flavor;
	public Sex sex;
	public String name;
	public String socialClass;
	public ArrayList<Days> schedule = new ArrayList<Days>();
	public String reason = "";
	public String reaction = "";
	/**
	 * creates a schedule for the person
	 * TODO: make it impossible to have an empty schedule
	 */
	public void scheduler(){
		for(int a = 0; a<7;a++){
			if (Constants.random.nextBoolean()){
				schedule.add(Days.values()[a]); //TODO: optimize by creating only a single instance of array instead of copying over and over
			}
		}
	}
	public void Drink(Criticism criticism, Flavor flavor, double temperature, int ice){
		switch(criticism){
		case Bad:
			experience += Constants.bad_recipe;
			reaction = ":[";
			break;
		case Okay:
			experience += Constants.okay_recipe;
			reaction = ":|";
			break;
		case Good:
			experience +=  Constants.good_recipe;
			reaction = ":)";
			break;
		case Perfect:
			experience +=  Constants.perfect_recipe;
			reaction = ":D";
			break;
		}
		temperature = ((temperature - 70) / 10) + 1;
		int iceNeeded = formatInt(temperature);
		if(ice >= iceNeeded && ice <= iceNeeded + 3){
			experience +=  Constants.enough_ice;
		}
		else if(ice < iceNeeded){
			experience +=  Constants.not_enough_ice;
		}
		if (this.flavor == flavor){
			experience +=  Constants.my_flavor;
		}
		
	}
	public String getReaction(){
		return reaction;
	}
	private double wallet;
	private double satisfaction = 50;
	private double experience = 0;
	
	private double patience;
	private int formatInt(double formatThis){ //put in a double and get a integer
	   int formatted = 0;
	   DecimalFormat form = new DecimalFormat("#");
	   formatted = Integer.valueOf(form.format(formatThis));
	   return formatted;
	}
	//schedule
	public Person(){
		sex = Sex.randomSex();
		name = NameFactory.createName(sex);
		scheduler();
		thirstiness = Constants.random.nextDouble() - .3;
		double value = Constants.random.nextDouble();
		if (value < 0.55){
			wallet = 0.60;
			socialClass = "lower";
		}
		else if (value < 0.85){
			wallet = 1.00;
			socialClass = "middle";
		}
		else {
			wallet = 1.50;
			socialClass = "upper";
		}
		value = Constants.random.nextDouble();
		if (value >= .5){
			flavor = Flavor.Sour;
		}
		else{
			flavor = Flavor.Sweet;
		}
			
	}
	public double getThirstiness(){
		return thirstiness;
	}
	public void calcSatisfaction(){
		satisfaction = 50 * ((experience / Math.sqrt((experience*experience)+1)) + 1);
	}
	public String getReason(){
		return reason;
	}
	public ArrayList<Days> getSchedule(){
		return schedule;
	}
	public boolean visits(String day){
		boolean visit = false;
		if (schedule.contains(day)){
			visit = true;
		}
		return visit;
	}
	public boolean willPurchase(String[] array){ //array item must include 1.price
		boolean purchase = false;
		if (Double.parseDouble(array[0]) <= wallet){ 
			if (Constants.random.nextDouble() >= thirstiness){//thirst at this time is higher than that needed to require a drink
				if (Constants.random.nextDouble() <= (satisfaction / 80)){
					purchase = true;	
				}
				else{
					reason = "I'm not feeling THAT adventurous.";
				}
				
			}
			else{
				reason = "I'm not thirsty";
			}
		}
		else{
			reason = "Too pricey!";
			//lower satisfaction
		}
		return purchase;
	}
	public String getSocialClass(){
		return socialClass;
	}
	public void giveBonus(double bonus){
		wallet += bonus;
	}
	public double getWallet(){
		return wallet;
	}
	public String getName(){
		return name;
	}
	public double getSatisfaction(){
		calcSatisfaction();
		return satisfaction;
	}
	public void giveSatisfaction(double add){
		satisfaction += add;
	}
	public double getPatience(){
		return patience;
	}
	public Flavor getFlavor(){
		return flavor;
	}
	public String toString(){
		return name + ":\n\t" + sex.name() + "\n\t" + schedule + "\n\t" + socialClass + " class";
		
	}
}

