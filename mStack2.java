public class mStack{
	public static void main(String[] args)
	{
		Stack2<Integer> a = new Stack2<>();
		Stack2<Integer> b = new Stack2<>(50);
		System.out.println(a.getDataCount());
		a.Push(10);
		a.Push(20);
		int temp = a.Pop();
		System.out.println("n A : "+a.getLastData());
		System.out.println("n A : "+a.getDataCount());
		System.out.println("temp : "+temp);
	}
}
