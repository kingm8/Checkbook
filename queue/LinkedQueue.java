/**
 * Written By: Matthew King
 * LinkedQueue.java
 * Represents a linked implementation of a Queue,
 * which operates on a first-in-first-out basis
 */
 
package queue;
import exceptions.EmptyCollectionException;

public class LinkedQueue<T> implements Queue<T>
{
	private int count;
	private LinearNode<T> front, rear;
	
	public LinkedQueue()
	{
		count = 0;
		front = rear = null;
	}
	
	// Adds an element to the queue
	public void enqueue(T element)
	{
		// Set a temporary reference to a new node
		LinearNode<T> node = new LinearNode<T>(element);
		
		if (count==0)
			front = node;	// special case where queue is empty
		else	// otherwise set next reference of rear node to this node
			rear.setNext(node);
		
		// Set rear reference to current node
		rear = node;
		count++;
	}
	
	// Remove first element from the queue (FIFO)
	public T dequeue() throws EmptyCollectionException
	{
		if(isEmpty())
			throw new EmptyCollectionException("Cannot dequeue: Queue is empty!");
		
		// Temporary reference
		LinearNode<T> node = new LinearNode<T>();
		
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
		LinearNode<T> current = new LinearNode<T>();
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