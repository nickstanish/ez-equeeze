package ez_squeeze;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8607643105145232932L;
	private final double perfect_recipe = 0.4;
	private final double good_recipe = 0.25;
	private final double okay_recipe = 0.1;
	private final double bad_recipe = -0.4;
	private final double not_enough_ice = -.1;
	private final double enough_ice = .1;
	private final double my_flavor = .1;
	private double thirstiness;
	private String flavor;
	private String sex;
	private String name;
	private String socialClass;
	private ArrayList<String> schedule = new ArrayList<String>();
	private String reason = "";
	private String reaction = "";
	private String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	void Scheduler(){
		Random random = new Random();
		for(int a = 0; a<7;a++){
			if (random.nextDouble() >= random.nextDouble()){
				schedule.add(days[a]);
			}
		}
	}
	public void Drink(String criticism, String flav, double temperature, int ice){
		if (criticism == "Perfect"){
			experience += perfect_recipe;
			reaction = ":D";
		}
		else if (criticism == "Good"){
			experience += good_recipe;
			reaction = ":)";
		}
		else if (criticism == "Okay"){
			experience += okay_recipe;
			reaction = ":|";
		}
		else if (criticism == "Bad"){
			experience += bad_recipe;
			reaction = ":[";
		}
		temperature = ((temperature - 70) / 10) + 1;
		int iceNeeded = formatInt(temperature);
		if(ice >= iceNeeded && ice <= iceNeeded + 3){
			experience += enough_ice;
		}
		else if(ice < iceNeeded){
			experience += not_enough_ice;
		}
		if (flavor == flav){
			experience += my_flavor;
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
	Person(){
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
			flavor = "sour";
		}
		else{
			flavor = "sweet";
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
	public ArrayList<String> getSchedule(){
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
	public String getFlavor(){
		return flavor;
	}
}
class Sex implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5820069039464963236L;
	private String sex;
	Sex(){
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
	String[] maleNames = {"Jacob", "Michael", "Joshua", "Matthew", "Ethan", "Andrew", "Daniel", "Anthony", "Christopher", "Joseph", "William", "Alexander", "Ryan", "David", "Nicholas", "Tyler", "James", "John", "Jonathan", "Nathan", "Samuel", "Christian", "Noah", "Dylan", "Benjamin", "Logan", "Brandon", "Gabriel", "Zachary", "Jose", "Elijah", "Angel", "Kevin", "Jack", "Caleb", "Justin", "Austin", "Evan", "Robert", "Thomas", "Luke", "Mason", "Aidan", "Jackson", "Isaiah", "Jordan", "Gavin", "Connor", "Aiden", "Isaac", "Jason", "Cameron", "Hunter", "Jayden", "Juan", "Charles", "Aaron", "Lucas", "Luis", "Owen", "Landon", "Diego", "Brian", "Adam", "Adrian", "Kyle", "Eric", "Ian", "Nathaniel", "Carlos", "Alex", "Bryan", "Jesus", "Julian", "Sean", "Carter", "Hayden", "Jeremiah", "Cole", "Brayden", "Wyatt", "Chase", "Steven", "Timothy", "Dominic", "Sebastian", "Xavier", "Jaden", "Jesse", "Devin", "Seth", "Antonio", "Richard", "Miguel", "Colin", "Cody", "Alejandro", "Caden", "Blake", "Carson"};
	String[] femaleNames = {"Emily", "Emma", "Madison", "Abigail", "Olivia", "Isabella", "Hannah", "Samantha", "Ava", "Ashley", "Sophia", "Elizabeth", "Alexis", "Grace", "Sarah", "Alyssa", "Mia", "Natalie", "Chloe", "Brianna", "Lauren", "Ella", "Anna", "Taylor", "Kayla", "Hailey", "Jessica", "Victoria", "Jasmine", "Sydney", "Julia", "Destiny", "Morgan", "Kaitlyn", "Savannah", "Katherine", "Alexandra", "Rachel", "Lily", "Megan", "Kaylee", "Jennifer", "Angelina", "Makayla", "Allison", "Brooke", "Maria", "Trinity", "Lillian", "Mackenzie", "Faith", "Sofia", "Riley", "Haley", "Gabrielle", "Nicole", "Kylie", "Katelyn", "Zoe", "Paige", "Gabriella", "Jenna", "Kimberly", "Stephanie", "Alexa", "Avery", "Andrea", "Leah", "Madeline", "Nevaeh", "Evelyn", "Maya", "Mary", "Michelle", "Jada", "Sara", "Audrey", "Brooklyn", "Vanessa", "Amanda", "Ariana", "Rebecca", "Caroline", "Amelia", "Mariah", "Jordan", "Jocelyn", "Arianna", "Isabel", "Marissa", "Autumn", "Melanie", "Aaliyah", "Gracie", "Claire", "Isabelle", "Molly", "Mya", "Diana", "Katie"};
	String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham", "Sullivan", "Wallace", "Woods", "Cole", "West", "Jordan", "Owens", "Reynolds", "Fisher", "Ellis", "Harrison", "Gibson", "McDonald", "Cruz", "Marshall", "Ortiz"};
	Name(String sex){
		Random rand = new Random();
		if (sex == "Male"){
			name = maleNames[rand.nextInt(maleNames.length)];
		}
		else{
			name = femaleNames[rand.nextInt(femaleNames.length)];
		}
		name += " " + lastNames[rand.nextInt(lastNames.length)];
	}
	public String getName(){
		return name;
	}
}