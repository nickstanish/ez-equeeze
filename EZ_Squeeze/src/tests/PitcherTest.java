package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ez_squeeze.game.Constants;
import ez_squeeze.game.Pitcher;
import ez_squeeze.game.Recipe;
import ez_squeeze.game.State;
/**
 * JUnit 4 Test
 * Tests usage of Pitcher class
 * @author Nick Stanish
 *
 */
public class PitcherTest {
	State state;
	/**
	 * Setup that runs before every test
	 */
	@Before
	public void setup() {
		Constants.debugging = false;
		state = new State();
		state.recipe = new Recipe(5, 4, 2, 0.25);
		state.lemons = 6;
		state.sugar	 = 5;
		state.cups	 = Pitcher.MAX_CUPS + 1;
		state.ice	 = 2 * (state.cups + 1);
	}
	/**
	 * instantiation of pitcher should fail if the state is null
	 */
	@Test
	public void testStateFailure(){
		try{
			Pitcher pitcher = new Pitcher(null);
			fail("Null pitcher");
		}
		catch(RuntimeException e){
			System.out.println("OK");
		}
	}
	/**
	 * instantiation of pitcher should fail if the recipe is null
	 */
	@Test
	public void testRecipeFailure(){
		state.recipe = null;
		try{
			Pitcher pitcher = new Pitcher(state);
			fail("Null recipe");
		}
		catch(RuntimeException e){
			System.out.println("OK");
		}
	}
	/**
	 * test tracks many aspects of the pitcher class such as
	 * -cupsRemaining after refill, and before/after serving
	 * -serving with missing ice/cups
	 * -refilling with not enough ingredients
	 */
	@Test
	public void testServeAndRefill(){
		Pitcher pitcher = new Pitcher(state);
		assertTrue(pitcher.cupsRemaining() == Pitcher.MAX_CUPS);
		
		for(int i = 0; i < Pitcher.MAX_CUPS; i ++){
			assertTrue(pitcher.cupsRemaining() == Pitcher.MAX_CUPS - i);
			assertTrue(pitcher.serve());
			assertTrue(pitcher.cupsRemaining() == Pitcher.MAX_CUPS - i - 1);
			
		}
		assertTrue(pitcher.cupsRemaining() == 0);
		assertFalse(pitcher.serve()); // refill should have failed
		assertTrue(pitcher.cupsRemaining() == 0);
		state.lemons = state.recipe.lemons;
		state.sugar = state.recipe.sugar;
		// refill should succeed, but only 1 cups is remaining
		assertTrue(pitcher.serve());
		assertTrue(pitcher.cupsRemaining() == Pitcher.MAX_CUPS - 1);
		assertFalse(pitcher.serve());
		state.cups += 2;
		// should have 2 more cups, but only enough ice for one cup
		assertTrue(pitcher.serve());
		assertFalse(pitcher.serve());
		System.out.println("OK");
	}

}
