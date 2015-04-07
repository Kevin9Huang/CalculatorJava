import java.util.*;
public class Stack2<T> extends Object{
	public Stack2()
	{
		size = defaultStack2Size;
		topStack2 = 0;
		data = (T[]) new Object[size];
	}
	public Stack2(int n)
	{
		size = n;
		topStack2 = 0;
		data = (T[]) new Object[size];
	}
	public Stack2(Stack2 S)
	{
		size = S.size;
		topStack2 = S.topStack2;
		data = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			data[i] = (T) S.data[i];
		}
	}
	public void Push(T x)
	{
		data[topStack2] = x;
		topStack2++;
	}
	public T Pop()
	{
		T x;
		x = data[topStack2];
		topStack2--;
		return x;
	}
	public T getData(int n)
	{
		if(n<=topStack2-1)
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
		return (topStack2-1)==size;
	}
	public boolean isEmpty()
	{
		return topStack2==0;
	}
	public int getDataCount()
	{
		return topStack2;
	}
	public T getLastData()
	{
		return getData(topStack2-1);
	}
	private static int defaultStack2Size = 100;
	private final int size;
	private int topStack2;
	private T[] data;
}