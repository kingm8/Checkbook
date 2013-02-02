/**
 * Written By: Matthew King
 * Queue.java
 * Defines the interface of a Queue data structure
 */

package queue;
import exceptions.NoElementsException;

public interface Queue<T>
{
	// Adds the specified element to the rear of the queue
	public void enqueue(T element);
	
	// Removes and returns the element at the front of the queue
	public T dequeue() throws NoElementsException;
	
	// Returns a reference to the element at the front of
	// the queue without removing it
	public T first() throws NoElementsException;
	
	// Returns true if queue contains no elements; false otherwise
	public boolean isEmpty();
	
	// Returns the number of elements in the queue
	public int size();
	
	// Returns a string representation of the queue
	public String toString();
}