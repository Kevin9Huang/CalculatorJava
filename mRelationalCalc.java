import java.util.*;
public class mRelationalCalc {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        while(!userinput.equalsIgnoreCase("exit"))
        {
            String val = "";
            RelationalCalc R = new RelationalCalc(userinput,1);
            val = R.Calculate();
            System.out.println(val);
			userinput=sc.nextLine();
        }

    }
}
