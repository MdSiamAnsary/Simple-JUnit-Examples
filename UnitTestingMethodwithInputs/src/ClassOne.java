import java.util.Scanner;

public class ClassOne {
	
	public static int sumOfNumbersFromSystemIn() 
	{
	    Scanner scanner = new Scanner(System.in);
	    int firstSummand = scanner.nextInt();
	    int secondSummand = scanner.nextInt();
	    return firstSummand + secondSummand;
	  }

}
