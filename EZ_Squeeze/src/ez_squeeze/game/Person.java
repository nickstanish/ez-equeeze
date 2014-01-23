package ez_squeeze.game;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import ez_squeeze.game.Recipe.Criticism;
import ez_squeeze.game.Recipe.Flavor;
public class Person implements Serializable{
	/*
	 * GNU GPL v3
	 * Copyright 2011-2012 Nick Stanish
	 */

	private static final long serialVersionUID = -8607643105145232932L;
	private double thirstiness;
	private Flavor flavor;
	private String sex;
	private String name;
	private String socialClass;
	private ArrayList<Days> schedule = new ArrayList<Days>();
	private String reason = "";
	private String reaction = "";
	public void Scheduler(){
		Random random = new Random();
		for(int a = 0; a<7;a++){
			if (random.nextDouble() >= random.nextDouble()){
				schedule.add(Days.values()[a]);
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
		sex = new Sex().getSex();
		name = new Name(sex).getName();
		Scheduler();
		Random rdm = new Random();
		thirstiness = rdm.nextDouble() - .3;
		double value = rdm.nextDouble();
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
		value = rdm.nextDouble();
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
			Random rdm = new Random();
			if (rdm.nextDouble() >= thirstiness){//thirst at this time is higher than that needed to require a drink
				if (rdm.nextDouble() <= (satisfaction / 80)){
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
	public void setSex(String gender){
		sex = gender;
	}
	public String getSex(){
		return sex;
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
}
class Sex implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5820069039464963236L;
	private String sex;
	public Sex(){
		Random rdm = new Random();
		if (rdm.nextInt(101)<= 50){
			sex = "Male";
		}
		else{
			sex = "Female";
		}
		
	}
	public String getSex(){
			return sex;
		}
	
}
class Name implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7016407411617595373L;
	private String name;
	public Name(String sex){
		Random rand = new Random();
		if (sex == "Male"){
			name = Constants.maleNames[rand.nextInt(Constants.maleNames.length)];
		}
		else{
			name = Constants.femaleNames[rand.nextInt(Constants.femaleNames.length)];
		}
		name += " " + Constants.lastNames[rand.nextInt(Constants.lastNames.length)];
	}
	public String getName(){
		return name;
	}
}