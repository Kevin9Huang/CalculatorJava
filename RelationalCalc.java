import java.util.*;
public class RelationalCalc {
    private String ekspresi;
    private int mode;
    private String hasil;

    public RelationalCalc(String _ekspresi, int _mode) {
        ekspresi = _ekspresi;
        mode = _mode;
    }

 public String getEkspresi()
 {
  return ekspresi;
 }
 public int getMode()
 {
  return mode;
 }
 public void setEkspresi(String _ekspresi)
 {
  ekspresi = _ekspresi;
 }
 public void setMode(int _mode)
 {
  mode = _mode;
 }
    public String Calculate() {
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
                  if(ekspresi.substring(i,i+1).matches("[-?\\d+(\\.\\d+)?]"))
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
            if(i<len)
            {
              if(ekspresi.substring(i,i+1).equals("(") || 
                 ekspresi.substring(i,i+1).equals(")") || 
                 ekspresi.substring(i,i+1).trim().isEmpty())
              {
                i++; //Abaikan tanda ( dan )
              }
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
 public void ConverttoInfix()
 {
  int[] operand = new int[10];
        String[] operator= new String[10];
        int countoperand= 0;
        int countoperator = 0;
        String bufferoperand = "";
        String bufferoperator = "";
        int len = ekspresi.length();
        int i =0;
        boolean bufferoperatorend = false;
        boolean bufferoperandend = false;
        boolean v = false;
  String ekspresitemp = "";
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
   if(i<len)
   {
    if(ekspresi.substring(i,i+1).equals("(") || 
    ekspresi.substring(i,i+1).equals(")") || 
    ekspresi.substring(i,i+1).trim().isEmpty())
    {
     i++; //Abaikan tanda ( dan )
    }
   }
        }
  for(int j = 0;j<=countoperator-1;j++)
  {
   int operandke; //Untuk operator 1, operand adalah 0 dan 1, dan untuk operator 2, operand adalah 2 dan 3
   if(j!=0)
   {
    operandke = j*2-1; 
   }
   else
   {
    operandke = 0;
   }
   ekspresitemp = ekspresitemp + Integer.toString(operand[operandke]) + operator[j] + Integer.toString(operand[operandke+1]); 
  }
  ekspresi = ekspresitemp;
  
 }
}
