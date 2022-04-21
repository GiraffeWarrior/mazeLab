
public class stackNode {
	Box data;
	stackNode next;
	public stackNode(Box data, stackNode next) {
		this.data = data;
		this.next = next;
	}
	public Box getData() {
		return data;
	}
	public void setData(Box data) {
		this.data = data;
	}
	public stackNode getNext() {
		return next;
	}
	public void setNext(stackNode next) {
		this.next = next;
	}
}
