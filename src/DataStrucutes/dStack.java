package DataStrucutes;


public class dStack<T> {
	private class node{
		T data;
		node next;
		public node(T data)
		{
			this.data = data;
			this.next = null;
		}
	}
	private node top;
	public dStack()
	{
		top = null;
	}
	public void add(T data)
	{
		node newNode = new node(data);
		newNode.next = top;
		top = newNode;
	}
	public T peek()
	{
		return top.data;
	}
	public T pop()
	{
		if(top == null)
			return null;
		node temp = top;
		top = top.next;
		return temp.data;
	}
	public boolean isEmpty()
	{
		return (top == null);
	}
	public dStack<T> clone()
	{
		dStack<T> cloneStack = new dStack<T>();
		cloneHelper(cloneStack, top);
		return cloneStack;
	}
	public void cloneHelper(dStack<T> stack, node p)
	{
		if(p == null)
			return;
		cloneHelper(stack, p.next);
		stack.add(p.data);
	}
}
