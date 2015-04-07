import java.util.*;
public class mCEChecker {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        while(!userinput.equalsIgnoreCase("exit"))
        {
            boolean isc = false;
            boolean ise = false;
            CEChecker C = new CEChecker(userinput);
            isc = C.IsCommand();
            ise = C.IsExpression();
            if (isc) {
                System.out.println(userinput + " is a command");
            }
            else {
                System.out.println(userinput + " is not a command");
            }
            if (ise) {
                System.out.println(userinput + " is an expression");
            }
            else {
                System.out.println(userinput + " is not an expression");
            }
            userinput=sc.nextLine();
        }

    }
}
