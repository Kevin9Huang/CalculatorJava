import java.util.*;
public class mArabCalc {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        while(!userinput.equalsIgnoreCase("exit"))
        {
            float val = 0.f;
            ArabCalc R = new ArabCalc(userinput,2,20);
            R.CalculateInfix();
			val = R.getHasil();
			System.out.println(R.getEkspresi() +" = "+val);
			userinput=sc.nextLine();
        }
    }
}
