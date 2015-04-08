public class tes{
	public static void main(String[] args){
		float a = 4.0f;
		System.out.println(a-Math.round(a));
		if(a-Math.round(a) != 0.f)
		{
			System.out.println("ping");
		}
		else
		{
			System.out.println("ping2");
		}
	}
}