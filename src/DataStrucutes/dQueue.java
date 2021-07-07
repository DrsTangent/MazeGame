package DataStrucutes;


public class dQueue<T> {
	private class node{
		T data;
		node next;
		public node(T data)
		{
			this.data = data;
			this.next = null;
		}
	}
	private int size;
	private node rear;
	private node front;
	public dQueue()
	{
		rear = null;
		front = null;
		size = 0;
	}
	public void add(T data)
	{
		node newNode = new node(data);
		size++;
		if(rear == null || front == null)
		{
			rear = newNode;
			front = newNode;
			return;
		}
		rear.next = newNode;
		rear = rear.next;
	}
	public T peek()
	{
		return front.data;
	}
	public T remove()
	{
		if(rear == null || front == null)
			return null;
		node temp = front;
		size--;
		if(rear == front)
		{
			rear = null;
			front = null;
		}
		else
		{
			front = front.next;
		}
		return temp.data;
	}
	public boolean isEmpty()
	{
		return (rear == null || front == null);
	}
	public int size()
	{
		return size;
	}
	public dQueue<T> clone()
	{
		dQueue<T> cloneQueue = new dQueue<T>();
		cloneHelper(cloneQueue, front);
		return cloneQueue;
	}
	public void cloneHelper(dQueue<T> queue, node p)
	{
		if(p == null)
			return;
		cloneHelper(queue, p.next);
		queue.add(p.data);
	}
}
