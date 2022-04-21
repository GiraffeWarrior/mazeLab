
public class queueList<T> {
	queueNode<T> head = null;
	queueNode<T> tail = null;
	
                

public void enqueue(T data) {
	
	queueNode<T> newNode = new queueNode<T>(data, null, head);
	
	if(tail == null) {
		tail = newNode;
		}
	if(head!= null) {
		head.prev = newNode;
	}
	
	head = newNode;
}

public queueNode<T> dequeue() {
	queueNode<T> old = tail;
	if(tail.getPrev() != null) {
	tail = tail.getPrev();
	tail.next = null;
	}
	else {
		head = null;
		tail = null;
	}
	
	return old;
}

}