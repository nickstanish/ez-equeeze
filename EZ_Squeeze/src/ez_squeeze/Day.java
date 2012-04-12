package ez_squeeze;
import java.io.Serializable;
import java.util.*;
public class Day implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3669127812945164434L;
	private int numberDay = 0;
	private int totalDays = 1;
	
	Day(){
		Random rdm = new Random();
		numberDay = rdm.nextInt(6);
	}
	public String getDay(){
		String day = "";
		switch(numberDay){
		case 0:
			day = "Sunday";
			break;
		case 1:
			day = "Monday";
			break;
		case 2:
			day = "Tuesday";
			break;
		case 3:
			day = "Wednesday";
			break;
		case 4:
			day = "Thursday";
			break;
		case 5:
			day = "Friday";
			break;
		case 6:
			day = "Saturday";
			break;
		}
		return day;
	}
	public int getNumberDay(){
		return numberDay;
	}
	public int getTotalDays(){
		return totalDays;
	}
	public void nextDay(){
		numberDay++;
		totalDays++;
		if (numberDay == 7){
			numberDay = 0;
		}
	}
}
