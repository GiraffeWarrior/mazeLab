
public class stackList {
		stackNode head = null;
		public stackList() {
			head = null;
		}
	public void push(Box data) {
		stackNode newNode = new stackNode(data, head);
		head = newNode;
	}
	public stackNode pop() {
		stackNode old = head;
		head = head.getNext();
		
		
		return old;
	}
}                     