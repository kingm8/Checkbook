//-------------------------------------------------
//	Written By: Matthew King
//	GitHub Username: kingm8
//	Filename: Check.java
//	Description: Inherits several data fields and 
//		methods from Transaction. A check IS A transaction
//		with one other data element: A check number
//-------------------------------------------------

package king;
import java.text.DecimalFormat;

public class Check extends Transaction implements Comparable<Transaction>
{
	private int checkNum;
	
	// Constructor accepts instance data and calls parent constructor
	public Check(String descrip, String date, double amount, boolean withdrawal)
	{
		super(descrip, date, amount, withdrawal);
	}
	
	// Only unique part of the check
	public void setCheckNum(int num)
	{
		this.checkNum = num;
	}
	
	public int getCheckNum()
	{
		return checkNum;
	}
	
	// Returns a string representation of this Check
	public String toString()
	{
		String descrip = "";
		String[] results = (super.toString()).split("\t");
		results[1] = ("[Check# " + checkNum + "] " + results[1]);
		
		for(int i=0; i<results.length; i++)
		{
			descrip += results[i];
			
			if (i < (results.length-1))
				descrip += "\t";
		}
		
		return descrip;
	}
}