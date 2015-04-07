import java.util.*;
public class mRelationalCalc {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        while(!userinput.equalsIgnoreCase("exit"))
        {
            String val = "";
            RelationalCalc R = new RelationalCalc(userinput,1);
            val = R.CalculateInfix();
            System.out.println(val);
			R.ConverttoInfix();
			System.out.println(R.getEkspresi());
			userinput=sc.nextLine();
        }

    }
}
