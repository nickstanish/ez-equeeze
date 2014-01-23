package ez_squeeze.game;
import java.io.Serializable;
import java.util.Random;
/**
 * GNU GPL v3
 * Copyright 2011-2012 Nick Stanish
 * @author: Nick Stanish
 */

public class Day implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3669127812945164434L;
	private int totalDays = 0;
	public Days day;
	
	public Day(){
		Random rdm = new Random();
		day = Days.values()[ rdm.nextInt(6) ];
	}
	public Day(String s) throws Exception{
		try{
			day = Days.valueOf(s);
		}
		catch(Exception e){
			throw new Exception("Create Bad Day");
		}
	}
	public String getDay(){
		return day.name();
	}
	public int getNumberDay(){
		return day.ordinal();
	}
	public int getTotalDays(){
		return totalDays;
	}
	public void nextDay(){
		int n = (day.ordinal() + 1) % 7; // add one and cycle to beginning
		day = Days.values()[n];
		totalDays++;
	}
}
