package ez_squeeze.game;

import java.text.DecimalFormat;
import java.util.Random;

public class Constants {
	public static final Random random = new Random();
	public static final boolean debugging = true; // enable to print to console
    public static final double startingMoney = 25.00; //starting money
    public static final double defaultPrice = 0.25; //default price
    public static final DecimalFormat priceFormat = new DecimalFormat("$0.00");
	public static final double perfect_recipe = 0.4;
	public static final double good_recipe = 0.25;
	public static final double okay_recipe = 0.1;
	public static final double bad_recipe = -0.4;
	public static final double not_enough_ice = -.1;
	public static final double enough_ice = .1;
	public static final double my_flavor = .1;
	public static final int smallSizeLemons = 15;
    public static final int mediumSizeLemons = 40;
    public static final int largeSizeLemons = 100;
    public static final int smallSizeIce = 75;
    public static final int mediumSizeIce = 200;
    public static final int largeSizeIce = 500;
    public static final int smallSizeCups = 100;
    public static final int mediumSizeCups = 250;
    public static final int largeSizeCups = 600;
    public static final int smallSizeSugar = 12;
    public static final int mediumSizeSugar = 30;
    public static final int largeSizeSugar = 75;
    public static final double smallPriceLemons = 2.50;
    public static final double mediumPriceLemons = 6.00;
    public static final double largePriceLemons = 13.00;
    public static final double smallPriceSugar = 1.50;
    public static final double mediumPriceSugar = 3.50;
    public static final double largePriceSugar = 8.00;
    public static final double smallPriceIce = 1.00;
    public static final double mediumPriceIce = 2.39;
    public static final double largePriceIce = 4.83;
    public static final double smallPriceCups = .70;
    public static final double mediumPriceCups = 1.50;
    public static final double largePriceCups = 3.25;
    public static final String[] maleNames = {"Jacob", "Michael", "Joshua", "Matthew", "Ethan", "Andrew", "Daniel", "Anthony", "Christopher", "Joseph", "William", "Alexander", "Ryan", "David", "Nicholas", "Tyler", "James", "John", "Jonathan", "Nathan", "Samuel", "Christian", "Noah", "Dylan", "Benjamin", "Logan", "Brandon", "Gabriel", "Zachary", "Jose", "Elijah", "Angel", "Kevin", "Jack", "Caleb", "Justin", "Austin", "Evan", "Robert", "Thomas", "Luke", "Mason", "Aidan", "Jackson", "Isaiah", "Jordan", "Gavin", "Connor", "Aiden", "Isaac", "Jason", "Cameron", "Hunter", "Jayden", "Juan", "Charles", "Aaron", "Lucas", "Luis", "Owen", "Landon", "Diego", "Brian", "Adam", "Adrian", "Kyle", "Eric", "Ian", "Nathaniel", "Carlos", "Alex", "Bryan", "Jesus", "Julian", "Sean", "Carter", "Hayden", "Jeremiah", "Cole", "Brayden", "Wyatt", "Chase", "Steven", "Timothy", "Dominic", "Sebastian", "Xavier", "Jaden", "Jesse", "Devin", "Seth", "Antonio", "Richard", "Miguel", "Colin", "Cody", "Alejandro", "Caden", "Blake", "Carson"};
	public static final String[] femaleNames = {"Emily", "Emma", "Madison", "Abigail", "Olivia", "Isabella", "Hannah", "Samantha", "Ava", "Ashley", "Sophia", "Elizabeth", "Alexis", "Grace", "Sarah", "Alyssa", "Mia", "Natalie", "Chloe", "Brianna", "Lauren", "Ella", "Anna", "Taylor", "Kayla", "Hailey", "Jessica", "Victoria", "Jasmine", "Sydney", "Julia", "Destiny", "Morgan", "Kaitlyn", "Savannah", "Katherine", "Alexandra", "Rachel", "Lily", "Megan", "Kaylee", "Jennifer", "Angelina", "Makayla", "Allison", "Brooke", "Maria", "Trinity", "Lillian", "Mackenzie", "Faith", "Sofia", "Riley", "Haley", "Gabrielle", "Nicole", "Kylie", "Katelyn", "Zoe", "Paige", "Gabriella", "Jenna", "Kimberly", "Stephanie", "Alexa", "Avery", "Andrea", "Leah", "Madeline", "Nevaeh", "Evelyn", "Maya", "Mary", "Michelle", "Jada", "Sara", "Audrey", "Brooklyn", "Vanessa", "Amanda", "Ariana", "Rebecca", "Caroline", "Amelia", "Mariah", "Jordan", "Jocelyn", "Arianna", "Isabel", "Marissa", "Autumn", "Melanie", "Aaliyah", "Gracie", "Claire", "Isabelle", "Molly", "Mya", "Diana", "Katie"};
	public static final String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham", "Sullivan", "Wallace", "Woods", "Cole", "West", "Jordan", "Owens", "Reynolds", "Fisher", "Ellis", "Harrison", "Gibson", "McDonald", "Cruz", "Marshall", "Ortiz"};
	public static final int startingPersons = 100;
	/**
	 * wrapper for print statements
	 * @param s
	 */
	public static void LOG(String s){
		if(debugging) System.out.println(s);
	}
	public static void LOGERROR(String s){
		if(debugging) System.err.println(s);
	}
}
