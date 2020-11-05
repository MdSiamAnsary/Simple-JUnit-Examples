import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

//import org.junit.jupiter.api.Test;
//This has to be commented out

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Rule;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;



public class ClassOneTest {
	
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testSumOfNumbersFromSystemInTestId01() {
		systemInMock.provideLines("1", "9");
	    assertEquals(5, ClassOne.sumOfNumbersFromSystemIn());
	}
	
	@Test
	public void testSumOfNumbersFromSystemInTestId02() {
		systemInMock.provideLines("-1", "9");
	    assertEquals(8, ClassOne.sumOfNumbersFromSystemIn());
	}

}
