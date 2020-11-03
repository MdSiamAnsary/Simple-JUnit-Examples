package packageTestCode;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import packageSourceCode.SourceCodeClass;


class SourceCodeClassTestCase {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	 
	
	// ---------------------------------------------------------------------------------------
	@BeforeEach
	public void setUpTestId1() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	void methodTestId1() 
	{
	    SourceCodeClass sourceCodeClassObject = new SourceCodeClass();
	    sourceCodeClassObject.method();
	        
	    Assert.assertEquals("Hello World", outputStreamCaptor.toString().trim());
	}
	
	@AfterEach
	public void tearDownTestId1() {
	    System.setOut(standardOut);
	}
	// ---------------------------------------------------------------------------------------
	
	
	
	
	// ---------------------------------------------------------------------------------------
	@BeforeEach
	public void setUpTestId2() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	void methodTestId2() 
	{
	    SourceCodeClass sourceCodeClassObject = new SourceCodeClass();
	    sourceCodeClassObject.method();
	        
	    Assert.assertEquals("Hello World", outputStreamCaptor.toString());
	}
	
	@AfterEach
	public void tearDownTestId2() {
	    System.setOut(standardOut);
	}
	// ---------------------------------------------------------------------------------------
	
	
	// ---------------------------------------------------------------------------------------
		@BeforeEach
		public void setUpTestId3() {
		    System.setOut(new PrintStream(outputStreamCaptor));
		}
		
		@Test
		void methodTestId3() 
		{
		    SourceCodeClass sourceCodeClassObject = new SourceCodeClass();
		    sourceCodeClassObject.method();
		        
		    Assert.assertEquals("Hello World\n", outputStreamCaptor.toString().trim());
		}
		
		@AfterEach
		public void tearDownTestId3() {
		    System.setOut(standardOut);
		}
		// ---------------------------------------------------------------------------------------


}
