package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ez_squeeze.Day;
/**
 * Uses JUnit4 to test functionality of Day class
 * @author Nick Stanish
 *
 */
public class DayTest {
	/**
	 * test failure on create
	 */
	@Test
	public void testDay() {
		
		try {
			Day day = new Day("Saturday");
			assertTrue(day.day.name().equalsIgnoreCase("saturday"));
			day = new Day("hello world");
			fail("Bad Day Created");
		} catch (Exception e) {
			//caught so pass
		}
		
	}
	/**
	 * test create with random day
	 */
	@Test
	public void testCreate(){
		Day day = new Day();
		int index = day.day.name().length() - 3; // get last 3 letters
		assertTrue(day.day.name().substring(index).equalsIgnoreCase("day"));
	}
	/**
	 * Test cycling through a week
	 */
	@Test
	public void testNextDay() {
		try {
			Day day = new Day("Saturday");
			assertTrue(day.day.name().equalsIgnoreCase("saturday"));
			day.nextDay();
			assertTrue(day.day.name().equalsIgnoreCase("sunday"));
			day.nextDay();
			assertTrue(day.day.name().equalsIgnoreCase("monday"));
			day.nextDay();
			assertTrue(day.day.name().equalsIgnoreCase("tuesday"));
			day.nextDay();
			assertTrue(day.day.name().equalsIgnoreCase("wednesday"));
			day.nextDay();
			assertTrue(day.day.name().equalsIgnoreCase("thursday"));
			day.nextDay();
			assertTrue(day.day.name().equalsIgnoreCase("friday"));
			assertTrue(day.getNumberDay() == 5);
			assertTrue(day.getTotalDays() == 6);
		} catch (Exception e) {
			fail();
		}
	}

}
