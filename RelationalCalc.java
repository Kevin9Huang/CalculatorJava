import java.util.*;
public class RelationalCalc {
    private String ekspresi;
    private int mode;

    public RelationalCalc(String _ekspresi, int _mode) {
        ekspresi = _ekspresi;
        mode = _mode;
    }

    public String Calculate(){
        String hasil = "";
        if(mode == 2)
        {
            hasil = CalculateInfix();
        }
        return hasil;
    }

    public String CalculateInfix() {
        int[] operand = new int[10];
        String[] operator= new String[10];
        String val ="";
        int countoperand= 0;
        int countoperator = 0;
        String bufferoperand = "";
        String bufferoperator = "";
        int len = ekspresi.length();
        int i =0;
        boolean bufferoperatorend = false;
        boolean bufferoperandend = false;
        boolean v = false;

        while(i < len)
        {
            bufferoperatorend = false;
            bufferoperandend = false;
            while(!bufferoperandend){
                if(i<len)
                {
                    if(ekspresi.substring(i,i+1).matches("-?\\d+(\\.\\d+)?"))
                    {
                        bufferoperand += ekspresi.substring(i,i+1);
                        i++;
                    }
                    else
                    {
                        bufferoperandend=true;
                    }
                }
                else
                {
                    bufferoperandend= true;
                }

            }
            if(!bufferoperand.equalsIgnoreCase("")){
                operand[countoperand] = Integer.parseInt(bufferoperand);
                bufferoperand = "";
                countoperand++;
            }
            while(!bufferoperatorend){
                if(i<len)
                {
                    if(ekspresi.substring(i,i+1).matches(">|<|="))
                    {
                        bufferoperator += ekspresi.substring(i,i+1);
                        i++;
                    }
                    else
                    {
                        bufferoperatorend=true;
                    }
                }
                else
                {
                    bufferoperatorend= true;
                }

            }
            if(!bufferoperator.equalsIgnoreCase("")){
                operator[countoperator] = bufferoperator;
                bufferoperator = "";
                countoperator++;
            }
        }
        for(int j =0;j<=countoperand-1;j++)
        {
            System.out.println("Operand"+j+" : "+operand[j]);
        }
        for(int j =0;j<=countoperator-1;j++)
        {
            System.out.println("Operator"+j+" : "+operator[j]);
        }
        if (operator[0].equals("<")) {
            v = (operand[0] < operand[1]);
            System.out.println("asdfasdfasdf");
        }
        else if (operator[0].equals(">")) {
            v = (operand[0] > operand[1]);
        }
        else if (operator[0].equals("<=")) {
            v = (operand[0] <= operand[1]);
        }
        else if (operator[0].equals(">=")) {
            v = (operand[0] >= operand[1]);
        }
        else if (operator[0].equals("=")) {
            v = (operand[0] == operand[1]);
        }
        else if ( operator[0].equals("<>")) {
            v = (operand[0] != operand[1]);
        }
        if (v) {
            val = "True";
        }
        else {
            val = "False";
        }
        return val;
        /* fffuuuuuu */
    }
}
