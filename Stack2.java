import java.util.*;
public class Stack2<T> extends Object{
	public Stack2()
	{
		size = defaultStack2Size;
		topStack = 0;
		data = (T[]) new Object[size];
	}
	public Stack2(int n)
	{
		size = n;
		topStack = 0;
		data = (T[]) new Object[size];
	}
	public Stack2(Stack2 S)
	{
		size = S.size;
		topStack = S.topStack;
		data = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			data[i] = (T) S.data[i];
		}
	}
	public void Push(T x)
	{
		data[topStack] = x;
		topStack++;
	}
	public T Pop()
	{
		T x;
		topStack--;
		x = data[topStack];
		return x;
	}
	public T getData(int n)
	{
		if(n<=topStack-1)
		{
			return data[n];
		}
		else
		{
			return null;
		}
	}
	public boolean isFull()
	{
		return (topStack-1)==size;
	}
	public boolean isEmpty()
	{
		return topStack==0;
	}
	public int getDataCount()
	{
		return topStack;
	}
	public T getLastData()
	{
		return getData(topStack-1);
	}
	private static int defaultStack2Size = 100;
	private final int size;
	private int topStack;
	private T[] data;
}