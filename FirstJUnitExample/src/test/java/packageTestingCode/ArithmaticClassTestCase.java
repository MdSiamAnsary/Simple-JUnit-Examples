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
	
	@Test
	public void methodSubTest2()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodSub(100 , -20);
		int expected = 120 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodSubTest3()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodSub(0 , 20);
		int expected = -20 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodMulTest1()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodMul(10 , 20);
		int expected = 200 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodMulTest2()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodMul(-2 , 20);
		int expected = -40 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodMulTest3()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodMul(0 , 20);
		int expected = 00 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodDivTest1()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodDiv(10 , 20);
		int expected = 0 ;
		assertEquals(expected, actual);
	}
	
	@Test
	public void methodDivTest2()
	{
		ArithmaticClass arithmaticClass = new ArithmaticClass();
		int actual = arithmaticClass.methodDiv(20 , 20);
		int expected = 1 ;
		assertEquals(expected, actual);
	}

}
