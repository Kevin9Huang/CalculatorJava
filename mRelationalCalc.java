import java.util.*;
public class mRelationalCalc {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        while(!userinput.equalsIgnoreCase("exit"))
        {
            String val = "";
            RelationalCalcV11 R = new RelationalCalcV11(userinput,2);
            val = R.CalculateInfix();
            System.out.println(val);
            userinput=sc.nextLine();
        }

    }
}
