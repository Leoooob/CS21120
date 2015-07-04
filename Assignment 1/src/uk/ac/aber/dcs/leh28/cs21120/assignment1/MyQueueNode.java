package uk.ac.aber.dcs.leh28.cs21120.assignment1;

public class MyQueueNode {
	MyQueueNode next;
	Object data;

	public MyQueueNode(Object data,MyQueueNode next) {
		this.next = next;
		this.data = data;
	}
}