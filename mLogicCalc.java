import java.util.*;
public class mLogicCalc {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        while(!userinput.equalsIgnoreCase("exit"))
        {
            float val = 0;
            LogicCalc R = new LogicCalc(userinput,1,20);
            R.CalculateSufix();
			val = R.getHasil();
			System.out.println(R.getEkspresi() +" = "+val);
			userinput=sc.nextLine();
        }

    }
}
