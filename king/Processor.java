//-------------------------------------------------
//	Written By: Matthew King
//	GitHub username: kingm8
//	Filename: Processor.java
//	Description: Serves as the boundary and control 
//		class of the project. Runs all I/O and
//		arithmetic operations.
//-------------------------------------------------

package king;
import java.io.*;
import queue.*;
import java.util.Scanner;

public class Processor
{
	private double balance = 0.0;
	private String labelLine;
	private LinkedQueue<Transaction> transactions;
	private Scanner dubScan, intScan;
	private BufferedReader stringRead;
	
	// Constructor accepts no parameters; instanstiates instance data
	public Processor()
	{
		transactions = new LinkedQueue<Transaction>();
		intScan = new Scanner(System.in);
		dubScan = new Scanner(System.in);
		stringRead = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// method to read the checkbook for all previous transactions
	public void readInput() throws IOException
	{
		Scanner lineScan;
		BufferedReader readCB = new BufferedReader(new FileReader("CheckBook.txt"));
		labelLine = readCB.readLine();
		double balAfterPrev = 0.0;
		
		// while there are more transactions, keep reading
		while (readCB.ready())
		{
			// Read one line of input, use its tab delimiter
			// (Scanner is an iterator on steroids for various input)
			Transaction trans;
			lineScan = new Scanner(readCB.readLine());
			lineScan.useDelimiter("\t");
			
			// Temporarily store each input token
			String date = lineScan.next();
			String description = lineScan.next();
			String value = lineScan.next();
			String balAfter = lineScan.next();
			// Remove the dollar sign from the input for parseDouble()
			value = value.replace("$", "");
			value = value.replace("-", "");
			balAfter = balAfter.replace("$", "");
			
			// Temporarily store each double
			double amount = Double.parseDouble(value);
			double balAfterCur = Double.parseDouble(balAfter);
			
			// Decides if current transaction is a payment or deposit
			if (balAfterCur < balAfterPrev)
			{
				trans = new Transaction(description, date, amount, true);
				balance -= trans.getValue();
			}
			else
			{
				trans = new Transaction(description, date, amount, false);
				balance += trans.getValue();
			}
			
			// Instantiate instance variable (w/o constructor)
			trans.setBalAfter(balance);
			// Store this transaction in the queue
			transactions.enqueue(trans);
			// Set the balance after previous
			balAfterPrev = balance;
		}
	}
	
	// Prompt the user for new transactions
	public void promptUser()
	{
		char ans = 'y';
		
		// Introduce myself
		System.out.println("Welcome to Fresh's Checkbook Management System!");
		System.out.println("\nIf this is your first time using this program, " +
				"enter your starting balance as an initial deposit in the " +
				"first transaction prompt.");
		System.out.println("\n");
		
		// Central do-while continues prompt while user tells it to
		do
		{
			System.out.println("Answer the following questions " +
						"about your recent transaction.\n\n");
						
			// Find out if user made a deposit or withdrawal
			// Yes, it does actually matter (otherwise do i add or subtract?)
			System.out.println("If you made a withdrawal/purchase, " +
						"enter 1. If deposit, enter 2.");
			int withdep = intScan.nextInt();
			// Input checker ensures valid input
			while (withdep!=1 && withdep!=2)
			{
				System.out.println("Dude, just enter a 1 or a 2.");
				withdep = intScan.nextInt();
			}
			
			// If withdrawal/purchase, use withdrawal function
			// Otherwise, use deposit function
			if (withdep == 1)
				withdrawMoney();
			else
				depositMoney();
			
			// Obtains a character from user 
			ans = doLoop();
			// Input checker ensures character is a valid one
			while(ans!='Y' && ans!='y' && ans!='N' && ans!='n')
				{ ans = doLoop(); }
		}
		// Now operate on the character
		while (ans=='Y' || ans=='y');
		
		intScan.close();
		dubScan.close();
		try
			{ stringRead.close(); }
		catch (IOException ioe) {}
	}
	
	// Write all the output to the checkbook text file
	public void writeOutput() throws IOException
	{
		FileOutputStream fos = new FileOutputStream("CheckBook.txt");
		PrintStream printer = new PrintStream(fos);
		// Print the string that labels the categories
		printer.println(labelLine);
		
		// Iterate through the queue, printing each transaction to file
		while (!transactions.isEmpty())
		{
			// Establish temporary reference variable
			Transaction trans = transactions.dequeue();
			printer.print(trans.toString());	// PRINT IT
			
			// Carriage return, but only if it's not the last object
			if (transactions.size()>0)
				printer.println();
		}
		
		// Flush and close streams
		printer.flush();
		printer.close();
		fos.close();
	}
	
	// Prompt user for withdrawal data
	private void withdrawMoney()
	{
		// Temporary references instantiated by user prompts
		String date = datePrompt();
		String descrip = descripPrompt();
		double amount = amtPrompt();
		Transaction trans;
		
		// Checks have a specific check number associated with them
		// Account for that provision with another prompt
		System.out.println("Enter 1 if it was a check, or 2 otherwise: ");
		int check = intScan.nextInt();
		// Input checker validates input
		while (check>2 || check<1)
		{
			System.out.println("Come on just enter 1 or 2.");
			check = intScan.nextInt();
		}
		
		// If this transaction is a check, prompt for check number
		// Otherwise, instantiate standard Transaction object
		if (check==1)
		{
			System.out.println("Enter the check number: ");
			int checkNum = intScan.nextInt();
			// Polymorphism via Inheritance, cuz im a boss
			trans = new Check(descrip, date, amount, true);
			((Check)trans).setCheckNum(checkNum);
		}
		else
			trans = new Transaction(descrip, date, amount, true);
		
		// Update balance, set instance data, and add to queue
		balance -= trans.getValue();
		trans.setBalAfter(balance);
		transactions.enqueue(trans);
	}
	
	// Instantiates a Transaction using data gathered from user prompts,
	// Stores it in the LinkedQueue, updates the balance
	private void depositMoney()
	{
		// Necessary prompts
		String descrip = descripPrompt();
		String date = datePrompt();
		double amount = amtPrompt();
		
		Transaction trans = new Transaction(descrip, date, amount, false);
		balance += trans.getValue();
		trans.setBalAfter(balance);
		transactions.enqueue(trans);
	}
	
	// Prompt user for a description of the transaction
	private String descripPrompt()
	{
		try
		{
			System.out.println("Enter a brief description of the transaction.");
			String answer = stringRead.readLine();
			return answer;
		}
		catch(IOException ioe)
		{
			return "idk...IOException";
		}
	}
	
	// Prompt user for the date of the transaction
	private String datePrompt()
	{
		try
		{
			System.out.println("On what date did you make the transaction? (mm/dd/yyyy)");
			String date = stringRead.readLine();
			return date;
		}
		catch(IOException ioe)
		{
			return "01/01/2000";
		}
	}
	
	// Prompt user for the value of the transaction
	private double amtPrompt()
	{
		System.out.println("How much money was this transaction?");
		double amt = dubScan.nextDouble();
		return amt;
	}
	
	// Obtain a character to continue central do-while
	private char doLoop()
	{
		try
		{
			System.out.println("Would you like to enter " +
						"another transaction? (Yes/No)(y/n)");
			String answer = stringRead.readLine();
			
			// Input checker to ensure the string is not empty
			while (answer.isEmpty())
				answer = stringRead.readLine();
			
			return answer.charAt(0);
		}
		catch(IOException ioe)
		{
			return 'n';
		}
	}
}