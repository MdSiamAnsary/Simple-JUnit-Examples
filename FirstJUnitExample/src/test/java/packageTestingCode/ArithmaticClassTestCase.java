package packageTestingCode;

import static org.junit.Assert.*;

import org.junit.Test;

import packageSourceCode.ArithmaticClass;

public class ArithmaticClassTestCase {

	@Test
	public void methodAddTest1()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodAdd(10 , 20);
		int expected = 30 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodAddTest2()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodAdd(10 , 20);
		int expected = 40 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodAddTest3()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodAdd(10 , 2);
		int expected = 12 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodAddTest4()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodAdd(0 , 20);
		int expected = 20 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodAddTest5()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodAdd(-90 , 20);
		int expected = -70 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodSubTest1()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodSub(10 , 20);
		int expected = -10 ;
		assertEquals(expected, actual);
	}

}
