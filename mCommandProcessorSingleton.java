import java.util.*;
public class mCommandProcessorSingleton {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String userinput = sc.nextLine();
        while(!userinput.equalsIgnoreCase("exit"))
        {
            CommandProcessorSingleton C = CommandProcessorSingleton.getInstance(userinput);
            C.Execute();
            userinput = sc.nextLine();
        }

    }
}
