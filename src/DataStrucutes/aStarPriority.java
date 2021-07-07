package DataStrucutes;

import Game.Cell;
//Kind of Priority Queue for Setting up A* Algorithm//
public class aStarPriority{
	private class node{
		dStack<Cell> data;
		node next;
		public node(dStack<Cell> data)
		{
			this.data = data;
			this.next = null;
		}
	}
	private int size;
	private node front;
	public aStarPriority()
	{
		front = null;
		size = 0;
	}
	public void add(dStack<Cell> data)
	{
		node newNode = new node(data);
		size++;
		node g = null;
		node p = front;
		
		while( p!=null && p.data.peek().compareTo(data.peek()) >= 0)
		{
			g = p;
			p = p.next;
		}
		//top
		if(g == null)
		{
			newNode.next = p;
			front = newNode;
		}
		//between
		else
		{
			g.next = newNode;
			newNode.next = p;
		}
		
	}
	public dStack<Cell> peek()
	{
		return front.data;
	}
	public dStack<Cell> remove()
	{
		if(front == null)
			return null;
		node temp = front;
		size--;
		front = front.next;
		return temp.data;
	}
	public boolean isEmpty()
	{
		return (front == null);
	}
	public int size()
	{
		return size;
	}
	public aStarPriority clone()
	{
		aStarPriority cloneQueue = new aStarPriority();
		cloneHelper(cloneQueue, front);
		return cloneQueue;
	}
	public void cloneHelper(aStarPriority queue, node p)
	{
		if(p == null)
			return;
		cloneHelper(queue, p.next);
		queue.add(p.data);
	}
}
