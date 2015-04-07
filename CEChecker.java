import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CEChecker {
    private String check;

    public CEChecker(String _check) {
        check = _check;
    }
    public boolean IsCommand() {
        boolean iscommand = false;
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("commandlist.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                    if(check.contains(sCurrentLine)) {
                        iscommand = true;
                    }
            }
        }
        catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
            return iscommand;
		}
    }
    public boolean IsExpression() {
        boolean isexpression = false;
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("expressionlist.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                    if(check.contains(sCurrentLine)) {
                        isexpression = true;
                    }
            }
        }
        catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
        return isexpression;
    }
}
