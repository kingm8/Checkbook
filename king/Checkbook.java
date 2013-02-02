//-------------------------------------------------
//	Written By: Matthew King
//	GitHub Username: kingm8
//	Filename: Checkbook.java
//	Description: Contains the main method, calling 
//		the methods of the boundary/control class.
//-------------------------------------------------

package king;
import java.io.IOException;

public class Checkbook
{
	public static void main(String[] args)
	{
		Processor pro = new Processor();
		
		try
		{
			pro.readInput();
			pro.promptUser();
			pro.writeOutput();
		}
		catch(IOException ioe)
		{
			System.out.println("CANNOT RUN PROGRAM!");
			System.out.println("Make sure you are running the " +
				"program in the directory storing Checkbook.txt");
		}
	}
}