package ez_squeeze;

import java.io.Serializable;


public class Recipe implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4336787500044640790L;
	private int grade; //[-5,5]
	private double x;
	private double success;
	private String flavor;
	private String criticism;
	/*
	 * +/- 3, perfect
	 * +/- 2, good
	 * +/- 1, okay
	 * 0, bad
	 */
	Recipe(int lemons, int sugar){
		//put recipe into linear system that determines how sweet or sour a recipe is, and then 
		//put the number into a bigger formula that determines how successful the recipe is
		double a = -.429;
		double b = 0.381;
		x = ( ( a * lemons ) + (b * sugar ) );
		success = (2 * (((2 * power(x, 4))+ power(x, 2) + 2) / (power(x, 4) + 1))) - 4;
		gradeRecipe();
		x = a;
	}
    private double power(double num, int x){ //base number, to the power of x
		double result;
		result = num;
		for(int a = 1; a < x; a++){
			result *= num;
		}
		if (x == 0){
			result = 1;
		}
		return result;
	}
	public void gradeRecipe(){
		if (success >= .9){
			if (x < 0){
				grade = -3;
				criticism = "Perfect";
				flavor = "sour";
			}
			else{
				grade = 3;
				criticism = "Perfect";
				flavor = "sweet";
			}
		}
		else if (success >= .70){
			if (x < 0){
				grade = -2;
				criticism = "Good";
				flavor = "sour";
			}
			else{
				grade = 2;
				criticism = "Good";
				flavor = "sweet";
			}
		}
		else if(success >= .40){
			if (x < 0){
				grade = -1;
				criticism = "Okay";
				flavor = "sour";
			}
			else{
				grade = 1;
				criticism = "Okay";
				flavor = "sweet";
			}
		}
		else{
			grade = 0;
			criticism = "Bad";
			flavor = "vomit";
			
		}
	}
	public double getX(){
		return x;
	}
	public String getCriticism(){
		return criticism;
	}
	public double getSuccess(){
		return success;
	}
	public String getFlavor(){
		return flavor;
	}
	public int getGrade(){
		return grade;
	}
	
}
