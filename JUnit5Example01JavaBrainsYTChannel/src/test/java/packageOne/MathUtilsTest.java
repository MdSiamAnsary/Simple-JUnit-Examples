package packageOne;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MathUtilsTest {

	@Test
	public void addTestId1()
	{
		MathUtils mathUtils = new MathUtils();
		int actual = mathUtils.add(10, 20);
		int expected = 30;
		assertEquals(expected, actual);
	}

}
