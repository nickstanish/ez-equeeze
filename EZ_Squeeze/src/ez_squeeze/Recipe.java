package ez_squeeze;

import java.io.Serializable;
/**
 * GNU GPL v3
 * Copyright 2011-2014 Nick Stanish
 * @author Nick Stanish
 */


public class Recipe implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4805393381914173418L;
	public int grade = 0; // [-3,3], 0 is worst. Math.abs(grade) of 3 is best
	public int lemons = 0;
	public int sugar = 0;
	public int ice = 0;
	public double price = 0;
	public Flavor flavor;
	public Criticism criticism;
	public enum Flavor{
		Sweet, Sour, Vomit
	}
	public enum Criticism{
		Bad, Okay, Good, Perfect
	}
	/**
	 * Create a Recipe and grade/criticise it with a unique formula.
	 * @param lemons
	 * @param sugar
	 */
	public Recipe(int lemons, int sugar, int ice, double price){
		//put recipe into linear system that determines how sweet or sour a recipe is, and then 
		//put the number into a bigger formula that determines how successful the recipe is
		this.ice = ice;
		this.lemons = lemons;
		this.sugar = sugar;
		this.price = price;
		double a = -0.429;
		double b = 0.381;
		double x = ( ( a * lemons ) + (b * sugar ) ); // determines flavor
		double success = (2 * (((2 * Math.pow(x, 4))+ Math.pow(x, 2) + 2) / (Math.pow(x, 4) + 1))) - 4;
		gradeRecipe(x, success);
	}
    /**
     * categorizes the recipe into a criticism, grade, and flavor
     */
	private void gradeRecipe(double x, double success){
		grade = (x < 0) ? -1 : 1;
		flavor = (x < 0) ? Flavor.Sour : Flavor.Sweet;
		
		if (success >= .9){
			criticism = Criticism.Perfect;
			grade *= 3;
		}
		else if (success >= .70){
			criticism = Criticism.Good;
			grade *= 2;
		}
		else if(success >= .40){
			criticism = Criticism.Okay;
		}
		else{
			grade = 0;
			criticism = Criticism.Bad;
			flavor = Flavor.Vomit;
			
		}
	}
	
}
