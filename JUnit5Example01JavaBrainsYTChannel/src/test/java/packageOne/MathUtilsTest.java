package packageOne;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class MathUtilsTest {

	@Test
	public void addTestId1()
	{
		MathUtils mathUtils= new MathUtils();
		
		int expected = 25;
		int actual = mathUtils.add(12, 12);
		
		assertEquals(expected, actual);
	}

}
