package packageSourceCode;

import java.util.Scanner;

public class ClassOne {
	
	public int add(int a , int b)
	{
		return a+b;
	}
	
	
	public static int notMain()
	{
		ClassOne classOne = new ClassOne();
		
		Scanner sc = new Scanner(System.in);
		
		int value1 = sc.nextInt();
		int value2 = sc.nextInt();
		
		int valueSum = classOne.add(value1, value2);
		
		return valueSum;
	}
	
	

}
