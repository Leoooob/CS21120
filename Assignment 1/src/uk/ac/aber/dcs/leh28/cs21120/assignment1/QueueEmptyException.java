package uk.ac.aber.dcs.leh28.cs21120.assignment1;

public class QueueEmptyException extends RuntimeException {
	private static final long serialVersionUID = 1L;	//implementing Serializable so I need a version ID

	public QueueEmptyException() {
		super("Attempt to access empty Queue");
	}
}
