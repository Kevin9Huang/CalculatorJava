import java.util.*;

public class CommandProcessor {
    private String command;

    public CommandProcessor(String _command) {
        command = _command;
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
        System.out.print(com);
        if (count != 0) {
            System.out.print(count);
        }
    }
}