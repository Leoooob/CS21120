package uk.ac.aber.dcs.leh28.cs21120.assignment1;

public class MyQueue implements Queue {
	private MyQueueNode head, tail;
	private int size = 0;

	public MyQueue() {
		head = null;
		tail = null;
		size= 0;
	}

	public void addToQueue(Object o) {
		// TODO Auto-generated method stub
		MyQueueNode newTail = new MyQueueNode(o, null);
		if(tail == null) {
			head = tail = newTail;
		}
		else {
			tail.next = newTail;
			tail = newTail;
		}
		size++;
	}

	public Object takeFromQueue() throws QueueEmptyException {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new QueueEmptyException();
		}
		Object tmp = head.data;
		head = head.next;
		size --;
		return tmp;
	}

	public Object frontOfQueue() throws QueueEmptyException {
		// TODO Auto-generated method stub
		if(isEmpty()){
			throw new QueueEmptyException();
		}
		return head.data;
	}

	public int lengthOfQueue() {
		// TODO Auto-generated method stub
		return size;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;	//returns true if size == 0
	}

}
