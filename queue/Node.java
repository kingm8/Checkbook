/** 
 * Node.java
 * Written By: Matthew King
 * Serves as the nodes for the LinkedQueue, stores references
 *  to current T object and next node in the set
 */
 
package queue;

public class Node<T>
{
	private Node<T> next;
	private T item;
	
	public Node()
	{
		next = null;
		item = null;
	}
	
	public Node(T item)
	{
		next = null;
		this.item = item;
	}
	
	public Node<T> getNext()
	{
		return next;
	}
	
	public void setNext(Node<T> node)
	{
		next = node;
	}
	
	public T getitem()
	{
		return item;
	}
	
	public void setitem(T item)
	{
		this.item = item;
	}
}