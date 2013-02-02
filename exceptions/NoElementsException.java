//-------------------------------------------------
//	Written By: Matthew King
//	GitHub Username: kingm8
//	Filename: NoElementsException.java
//	Description: This exception is to be thrown if the
//		program tries to remove an element from the 
//		data structure when it contains no elements
//			(at runtime)
//-------------------------------------------------

package exceptions;

public class NoElementsException extends RuntimeException
{
   public NoElementsException (String message)
   {
      super (message);
   }
}
