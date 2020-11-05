package packageSourceCode;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

 


public class ClassOnemainTest {
	

	
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	

	@Test
	public void testMain() {
		
		systemInMock.provideLines("-1", "9");
		int v= ClassOne.notMain();
		assertEquals(8, v);
	}
	
	

}
