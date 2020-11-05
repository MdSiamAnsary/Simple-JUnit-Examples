package packageSourceCode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ClassOneaddTest {

	@Test
	public void testAdd() {
		
		ClassOne classOne = new ClassOne();
		int actual = classOne.add(10, 20);
		int expected = 30;
		
		assertEquals(expected, actual);
	}

}
