/**
 * Written By: Matthew King
 * LinkedQueue.java
 * Represents a linked implementation of a Queue,
 * which operates on a first-in-first-out basis
 */
 
package queue;
import exceptions.NoElementsException;

public class LinkedQueue<T> implements Queue<T>
{
	private int count;
	private Node<T> front, rear;
	
	public LinkedQueue()
	{
		count = 0;
		front = rear = null;
	}
	
	// Append an element on the queue
	public void enqueue(T element)
	{
		// Set a temporary reference to a new node
		Node<T> node = new Node<T>(element);
		
		if (count==0)		// special case where queue is empty
			front = node;
		else				// otherwise set next reference of rear node to this node
			rear.setNext(node);
		
		// Set rear reference to current node
		rear = node;
		count++;
	}
	
	// Remove first element from the queue (FIFO)
	public T dequeue() throws NoElementsException
	{
		if(isEmpty())
			throw new NoElementsException("Cannot dequeue: Queue is empty!");
		
		// Temporary reference
		Node<T> node = new Node<T>();
		
		// handle case of removing only existing element
		if(count==1)	// null all references; instantiate temp node
		{
			node.setElement(front.getElement());
			front = rear = null;
			count--;
		}
		else	// Otherwise set temp node element
		{
			node.setElement(front.getElement());
			front = front.getNext();	// Update front reference
			count--;
		}
		
		return node.getElement();
	}
	
	// Peek at the first element without removing it
	public T first()
	{
		return front.getElement();
	}
	
	// Returns true if there are no elements in queue, false otherwise
	public boolean isEmpty()
	{
		if(count<=0)
			return true;
		else
			return false;
	}
	
	// Return the number of elements in queue
	public int size()
	{
		return count;
	}
	
	// Returns a string representation of the queue
	public String toString()
	{
		String descrip = "<FRONT OF QUEUE>\n";
		Node<T> current = new Node<T>();
		current = front;
		
		while (current!=null)
		{
			descrip += (current.getElement()).toString() + "\n\n";
			current = current.getNext();
		}
		
		descrip += "<END OF QUEUE>";
		
		return descrip;
	}
}