import java.util.*;
public class mCommandProcessor {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        while(!userinput.equalsIgnoreCase("exit"))
        {
            CommandProcessor C = new CommandProcessor(userinput);
            C.Execute();
            userinput=sc.nextLine();
        }

    }
}
