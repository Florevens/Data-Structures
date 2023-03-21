package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

	private Node<T> front;
	private Node<T> rear;
	private int size;

	public void LinkedListQueue(){
 		front = null;
 		rear = null;
 		size = 0;
	}

	public Queue() {
            // TODO 1
    }

	public Queue(Queue<T> other) {
            // TODO 2
			Node<T> elem = other.front;
		while (elem != null) {
			this.enqueue(elem.data);
			elem = elem.next;
		}
	}

	@Override
	public boolean isEmpty() {
            // TODO 3
            return getSize() == 0;
	}

	@Override
	public int getSize() {
            // TODO 4
            return size;
	}

	@Override
	public void enqueue(T element) {
            // TODO 5
			Node<T> prevRear = rear;
			rear = new Node<T>(element, null);
			if(isEmpty()){
				front = rear;

			}
			else{
				prevRear.next = rear;
			}
			size++;

	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
			if (isEmpty()){
				throw new NoSuchElementException("The Queue is empty");
			}
			else{
				T rElem = front.data;
				front = front.next;
				if(isEmpty()){
					rear = null;
				}
				size--;
				return  rElem;
			}
		}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
			if (isEmpty()){
				throw new NoSuchElementException("The Queue is empty");
			}
			else{
				return front.data;
			}
		}

		@Override
		public UnboundedQueueInterface<T> reversed() {
				// TODO 8
				Queue<T> rev = new Queue<T>();
				revHelper(front, rev);
				 return rev;
				}
		private void revHelper(Node<T> front2, Queue<T> rev2){
			if (front2 != null) {
				revHelper(front2.next, rev2);
				rev2.enqueue(front2.data);
			}
		}
	}


class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}

