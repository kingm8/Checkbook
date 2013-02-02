/** 
 * LinearNode.java
 * Written By: Matthew King
 * Serves as the nodes for the LinkedQueue, stores references
 *  to current T object and next node in the set
 */
 
package queue;

public class LinearNode<T>
{
	private LinearNode<T> next;
	private T element;
	
	// Constructor for empty node
	public LinearNode()
	{
		next = null;
		element = null;
	}
	
	// Constructor for node instantiated to store an element
	public LinearNode(T element)
	{
		next = null;
		this.element = element;
	}
	
	// Returns next node in the collection
	public LinearNode<T> getNext()
	{
		return next;
	}
	
	// Sets the next node in the collection
	public void setNext(LinearNode<T> node)
	{
		next = node;
	}
	
	// Returns the element stored in the current node
	public T getElement()
	{
		return element;
	}
	
	// Sets the element stored in the current node
	public void setElement(T element)
	{
		this.element = element;
	}
}