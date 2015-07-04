
package uk.ac.aber.dcs.leh28.cs21120.assignment1;

public interface Queue {
	public void addToQueue(Object o);
	//add an object to the queue

	public Object takeFromQueue() throws QueueEmptyException;
	//remove an object from the queue

	public Object frontOfQueue() throws QueueEmptyException;
	//checks the head of the queue

	public int lengthOfQueue();
	//return size of queue

	public boolean isEmpty();
	//checks to see if the queue is empty

}
