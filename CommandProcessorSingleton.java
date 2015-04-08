import java.util.*;

public class CommandProcessorSingleton {
    private String command;

   private static CommandProcessorSingleton instance = new CommandProcessorSingleton();

   private CommandProcessorSingleton(){
       command = "";
   }

   public static CommandProcessorSingleton getInstance(String _command) {
      instance.command = _command;
      return instance;
   }

   public void Execute() {
        CEChecker C = new CEChecker(command);
        String com = "";
        String scount = "";
        int count = 0;
        if (C.IsCommand()) {
            if (command.contains("Save")) {
                com = "Proses Save";
            }
            else if (command.contains("Show All")) {
                com = "Proses ShowMem Semua";
            }
            else if (command.contains("ShowMem")) {
                scount = command.replace("ShowMem ","");
                count = Integer.parseInt(scount);
                com = "Proses ShowMem ";
            }
            else if (command.contains("Redo")) {
                scount = command.replace("Redo ","");
                count = Integer.parseInt(scount);
                com = "Proses Redo ";
            }
            else if (command.contains("Undo")) {
                scount = command.replace("Undo ","");
                count = Integer.parseInt(scount);
                com = "Proses Undo ";
            }
        }
        else if (C.IsExpression()) {
            com = "This is expression";
        }
        System.out.print(com);
        if (count != 0) {
            System.out.print(count);
        }
    }
}
