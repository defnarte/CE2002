

import java.util.Scanner;

/**
 * This class gets input from the user. It handles possible bad input from the user
 * 
 * @version 1.0
 * @since 2018/11/12
 * @author Jason
 *
 */
public class ConsoleInputInterface
{
	public static final Scanner consoleScanner = new Scanner(System.in);
	
	/**
	 * This method keeps prompting the user to enter a positive integer input till the user enters a positive integer.
	 * It then returns the positive integer.
	 * 
	 * @param promptMessage
	 * @return userInput
	 */
	public static int getUserPositiveIntInput(String promptMessage)
	{
		int userInput = 0;
		
		do
		{
			System.out.println(promptMessage);
			
			if(consoleScanner.hasNextInt())
			{
				userInput = consoleScanner.nextInt();
			}
			
		} while(userInput < 1); 
		
		return userInput;
	}
	
	/**
	 * This method keeps prompting the user to enter a positive integer input 
	 * till the user enters a positive integer that is lower than the upperbound.
	 * 
	 * Either the prompting message or an earlier message
	 * should make it clear to the user what the upperbound is.
	 * 
	 * This method then returns the positive integer.
	 * 
	 * @param promptMessage
	 * @return userInput
	 */
	public static int getUserPositiveIntInput(String promptMessage, int upperbound)
	{
		int userInput = 0;
		
		do
		{
			System.out.println(promptMessage);
			
			if(consoleScanner.hasNextInt())
			{
				userInput = consoleScanner.nextInt();
			}
			
		} while(userInput < 1 || userInput > upperbound);
		
		return userInput;
	}
	
	/**
	 * This method keeps prompting the user to enter a Yes or No till the user enters Y or N.
	 * 
	 * Either the prompting message or an earlier message
	 * should make it clear to the user what characters to legal to enter (Y, y ,N, n).
	 * 
	 * This method then returns the boolean value.
	 * 
	 * @param promptMessage
	 * @return userInput
	 */
	public static boolean getUserBooleanInput(String promptMessage)
	{
		char userInput = '\0';
		
		do
		{
			System.out.println(promptMessage);
			
			if(consoleScanner.hasNext())
			{
				userInput = consoleScanner.nextLine().charAt(0);
			}
			
		} while(userInput != 'Y' || userInput != 'y' || userInput != 'N' || userInput != 'n');
		
		return (userInput == 'Y' || userInput == 'y');
	}
}
