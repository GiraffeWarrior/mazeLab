
public class queueNode<T> {
	T data;
	queueNode<T> prev;
	queueNode<T> next;
	public queueNode(T data, queueNode<T> prev, queueNode<T> next){
		this.data = data;
		this.prev = prev;
		this.next = next;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public queueNode<T> getPrev() {
		return prev;
	}
	public void setPrev(queueNode<T> prev) {
		this.prev = prev;
	}
	public queueNode<T> getNext() {
		return next;
	}
	public void setNext(queueNode<T> next) {
		this.next = next;
	}
}
