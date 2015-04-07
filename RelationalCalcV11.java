import java.util.*;
public class RelationalCalcV11 {
    private String ekspresi;
    private int mode;

    public RelationalCalcV11(String _ekspresi, int _mode) {
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
        System.out.println("Asdfasdf");
        float[] operand = new float[10];
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
                    if(ekspresi.substring(i,i+1).matches("0|1|2|3|4|5|6|7|8|9|-") || ekspresi.substring(i,i+1).equals("."))
                    {
                        System.out.println("ping");
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
                System.out.println("buffer " + bufferoperand);
                operand[countoperand] = Float.parseFloat(bufferoperand);
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
    }
    /* [0-9]|true|false|+|-|*|/|div|mod|and|or|xor */
}
