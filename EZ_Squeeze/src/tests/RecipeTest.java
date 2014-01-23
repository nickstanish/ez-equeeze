package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ez_squeeze.game.Recipe;

public class RecipeTest {

	@Test
	public void testRecipe() {
		int lemons = 5;
		int sugar = 3;
		int ice = 2;
		double price = 1;
		Recipe recipe = new Recipe(lemons, sugar, ice, price);
		assertTrue(recipe.lemons == lemons);
		assertTrue(recipe.sugar == sugar);
		assertTrue(recipe.ice == ice);
		assertTrue(recipe.price == price);
		System.out.println(recipe.flavor);
		System.out.println(recipe.criticism);
		switch(recipe.criticism){
		case Bad:
			assertTrue(Math.abs(recipe.grade) == 0 );
			break;
		case Good:
			assertTrue(Math.abs(recipe.grade) == 2 );
			break;
		case Okay:
			assertTrue(Math.abs(recipe.grade) == 1 );
			break;
		case Perfect:
			assertTrue(Math.abs(recipe.grade) == 3 );
			break;
		}
	}

}
