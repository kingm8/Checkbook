//-------------------------------------------------
//	Written By: Matthew King
//	GitHub Username: kingm8
//	Filename: Transaction.java
//	Description: Serves as the basic storage class
//		for user data, representing a single transaction
//		logged in a checkbook.
//-------------------------------------------------

package king;
import java.text.DecimalFormat;

public class Transaction implements Comparable<Transaction>
{
	protected String description;
	protected boolean withdrawal;
	protected int month, day, year;
	protected double value, balanceAfter;
	
	// Constructor accepts various instance data
	public Transaction(String descrip, String date, double amount, boolean withd)
	{
		this.description = descrip;
		
		// Divide the date up, and store each part as integer
		String[] dates = date.split("/");
		this.month = Integer.parseInt(dates[0]);
		this.day = Integer.parseInt(dates[1]);
		this.year = Integer.parseInt(dates[2]);
		
		this.value = amount;
		this.withdrawal = withd;
	}
	
	// Various getters for instance data
	public boolean isWithdrawal()
		{ return withdrawal; }
	public int getYear()
		{ return year; }
	public int getMonth()
		{ return month; }
	public int getDay()
		{ return day; }
	public String getDescription()
		{ return description; }
	public double getValue()
		{ return value; }
		
	// More getters/setters
	public void setBalAfter(double bal)
		{ balanceAfter = bal; }
	public double getBalAfer()
		{ return balanceAfter; }
	// Yet another getter
	public String getDate()
	{
		DecimalFormat moDay = new DecimalFormat("00");
		DecimalFormat yr = new DecimalFormat("0000");
		
		String date = (moDay.format(month) + "/");
		date += (moDay.format(day) + "/" + yr.format(year));
		
		return date;
	}
	
	// Fulfill Comparable interface with compareTo(T)
	// Compare objects based on date; then by amount; needed for sorting
	public int compareTo(Transaction trans)
	{
		int result = 0;
		
		if (this.year == trans.getYear())
		{
			if (this.month == trans.getMonth())
			{
				// If all 3 are equal, result is 0
				if (this.day == trans.getDay())
					result = 0;
				else
				{
					// if day is unequal, evaluate on it
					if (this.day > trans.getDay())
						result = 1;
					else
						result = -1;
				}
			}
			else // if month is unequal, but year is, evaluate on month
			{
				if (this.month > trans.getMonth())
					result = 1;
				else
					result = -1;
			}
		}
		else // if year is unequal, evaluate on it before nesting
		{
			if (this.year > trans.getYear())
				result = 1;
			else
				result = -1;
		}
		
		// If two transactions occurred on the same day,
		// compare based on value (ascending)
		if (result==0)
		{
			if (this.value > trans.getValue())
				result = 1;
			if (this.value < trans.getValue())
				result = -1;
		}
		
		return result;
	}
	
	// Return a string representation of this Transaction
	public String toString()
	{
		DecimalFormat fmt = new DecimalFormat("0.##");
		String result = this.getDate() + "\t";
		result += (description + "\t"); //$" + fmt.format(value));
		
		// Add a "-" to value if it is a withdrawal
		if (withdrawal)
			result += ("-$" + fmt.format(value));
		else
			result += ("$" + fmt.format(value));
			
		result += ("\t$" + fmt.format(balanceAfter));
		return result;
	}
}