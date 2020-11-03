import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SourceClassTestCase {

	@Test
	public void methodDivTestId1()
	{
		SourceClass sourceClass = new SourceClass();
		int actual = sourceClass.methodDiv(10, 5);
		int expected = 2 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodDivTestId2()
	{
		Assertions.assertThrows(Exception.class, () -> {
			SourceClass sourceClass = new SourceClass();
			int actual = sourceClass.methodDiv(10, 0);
		});
	}

	
	
	@Test
	public void methodDivTestId3()
	{
		SourceClass sourceClass = new SourceClass();
		int actual = sourceClass.methodDiv(0, 5);
		int expected = 0 ;
		assertEquals(expected, actual);
	
	}
	
	@Test
	public void methodDivTestId4()
	{
		SourceClass sourceClass = new SourceClass();
		int actual = sourceClass.methodDiv(1, 5);
		int expected = 0 ;
		assertEquals(expected, actual);
	}
	

}
