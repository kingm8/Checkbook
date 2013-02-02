//-------------------------------------------------
//	Written By: Matthew King
//	GitHub Username: kingm8
//	Filename: EmptyCollectionException.java
//	Description: This exception is to be thrown if the
//		program tries to remove an element from the 
//		data structure when it contains no elements
//			(at runtime)
//-------------------------------------------------

package exceptions;

public class EmptyCollectionException extends RuntimeException
{
   public EmptyCollectionException (String message)
   {
      super (message);
   }
}
