package ez_squeeze.game.people;
import java.io.Serializable;
import java.util.ArrayList;

import ez_squeeze.game.Constants;
import ez_squeeze.game.Recipe.Criticism;
import ez_squeeze.game.Recipe.Flavor;
import ez_squeeze.game.forecast.Day;
import ez_squeeze.game.forecast.Days;
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
	public double wallet;
	public double satisfaction = 50;
	private double experience = 0;
	/**
	 * creates a schedule for the person
	 * TODO: make it impossible to have an empty schedule
	 */
	private void scheduler(){
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
		int iceNeeded = (int)((temperature - 70) / 10) + 1;
		if(ice >= iceNeeded && ice <= iceNeeded + 3){
			experience +=  Constants.enough_ice;
		}
		else if(ice < iceNeeded){
			experience +=  Constants.not_enough_ice;
		}
		if (this.flavor == flavor){
			experience +=  Constants.my_flavor;
		}
		satisfaction = 50 * ((experience / Math.sqrt((experience*experience)+1)) + 1);
		
	}
	public String getReaction(){
		return reaction;
	}
	//schedule
	public Person(){
		sex = Sex.randomSex();
		name = NameFactory.createName(sex);
		scheduler();
		thirstiness = Math.abs(Constants.random.nextDouble() - .3);
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
		flavor = Constants.random.nextBoolean() ? Flavor.Sour : Flavor.Sweet;
	}
	public double getThirstiness(){
		return thirstiness;
	}
	public String getReason(){
		return reason;
	}
	public boolean visits(Day day){
		return schedule.contains(day.day);
	}
	public boolean willPurchase(double price){
		boolean purchase = false;
		if (price <= wallet){ 
			if (Constants.random.nextDouble() >= thirstiness){//thirst at this time is higher than that needed to require a drink
				if (Constants.random.nextDouble() <= (satisfaction / 80.0)){
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
	public void giveBonus(double bonus){
		wallet += bonus;
	}
	public String toString(){
		return name + ":\n\t" + sex.name() + "\n\t" + schedule + "\n\t" + socialClass + " class";	
	}
}

