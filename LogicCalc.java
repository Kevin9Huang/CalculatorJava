import java.util.*;

public class LogicCalc {
    private Stack2<Boolean> bil;
    private Stack2<String> operatorx;
    private String ekspresi;
    private int mode;

    public LogicCalc(String _ekspresi,int _mode,int size) {
        bil = new Stack2<>(size);
        operatorx = new Stack2<>(size);
        ekspresi = _ekspresi;
        mode = _mode;
    }

    public String Calculate(){
        Converter();
        int hasil = 0;
        String hasilstr = "";
        if(mode == 1)
        {
            hasil = CalculatePrefix();
        }
        else if(mode == 2)
        {
            hasil = CalculateInfix();
        }
        else if(mode == 3)
        {
            hasil = CalculatePostfix();
        }
        if(hasil == 1)
        {
            hasilstr = "true";
        }
        else if(hasil == 0)
        {
            hasilstr = "false";
        }
        else
        {
            hasilstr = "Undefined";
        }
        return hasilstr;
    }

    public String Converter() {
        String bin = "";
        int i = 0;
        while (i < ekspresi.length()) {
            if (ekspresi.charAt(i) == 't') {
                bin = bin + '1';
                ekspresi = ekspresi.substring(i+4,ekspresi.length()-1);
            }
            else if (ekspresi.charAt(i) == 'f') {
                bin = bin + '0';
                ekspresi = ekspresi.substring(i+5,ekspresi.length()-1);
            }
            else if (ekspresi.charAt(i) == 'a') {
                bin = bin + '&';
                ekspresi = ekspresi.substring(i+3,ekspresi.length()-1);
            }
            else if (ekspresi.charAt(i) == 'o') {
                bin = bin + '|';
                ekspresi = ekspresi.substring(i+2,ekspresi.length()-1);
            }
            else if (ekspresi.charAt(i) == 'x') {
                bin = bin + 'x';
                ekspresi = ekspresi.substring(i+3,ekspresi.length()-1);
            }
            else if (ekspresi.charAt(i) == 'n') {
                bin = bin + '~';
                ekspresi = ekspresi.substring(i+3,ekspresi.length()-1);
            }
            else if (ekspresi.charAt(i) == '(') {
                bin = bin + '(';
                ekspresi = ekspresi.substring(i+1,ekspresi.length()-1);
            }
            else if (ekspresi.charAt(i) == ')') {
                bin = bin + ')';
                ekspresi = ekspresi.substring(i+1,ekspresi.length()-1);
            }
            else if (ekspresi.charAt(i) == ' ') {
                bin = bin + ' ';
                ekspresi = ekspresi.substring(i+1,ekspresi.length()-1);
            }
        }
        ekspresi = bin;
    }

    public void SmallCalculate()
    {
        boolean popbil1 = false;
        boolean popbil2 = false;
        String dumpoperator = "";
        int dumpbil = 0;
        String popoperator = operatorx.getLastData();
        dumpoperator = operatorx.Pop();
        if(popoperator.equals("~"))
        {
            if(!bil.isEmpty() && bil.getLastData() == -999)
            {
                dumpbil = bil.Pop(); //Pop -999
            }
            else if(!operatorx.isEmpty() && (operatorx.getLastData()).equals("~"))
            {
                dumpoperator = operatorx.Pop();
            }
            else
            {
                bil.Push(-999); //counter untuk merubah bil masukan berikut nya menjadi negasi dari input
            }
        }
        else if(popoperator.equals("&"))
        {
            popbil1 = bil.getLastData();
            dumpbil = bil.Pop();
            popbil2 = bil.getLastData();
            dumpbil = bil.Pop();
            if(popbil1 && popbil2)
            {
                bil.Push(1);
            }
            else
            {
                bil.Push(0);
            }
        }
        else if(popoperator.equals("|"))
        {
            popbil1 = bil.getLastData();
            dumpbil = bil.Pop();
            popbil2 = bil.getLastData();
            dumpbil = bil.Pop();
            if(popbil1 || popbil2)
            {
                bil.Push(1);
            }
            else
            {
                bil.Push(0);
            }
        }

    }
    public int CalculateInfix(){
        String dumpoperator = "";
        int dumpbil = 0;
        int hasil = 0;
        String ekspresitemp = ekspresi;
        String temp = "";
        while(ekspresitemp.length() != 0)
        {
            int isCompleteRead = 0;
            int ctemp = 0; //Menghitung panjang buffer angka
            if(ekspresitemp.charAt(0) == '0' || ekspresitemp.charAt(0) == '1')
            {
                if(bil.isEmpty())
                {
                    if(ekspresitemp.charAt(0) == '1')
                    {
                        bil.Push(1);
                    }
                    else if(ekspresitemp.charAt(0) == '0')
                    {
                        bil.Push(0);
                    }
                }
                else // stack bil mempunyai 1 elemen
                {
                    if(bil.getLastData() == -999) //cek counter untuk merubah bil masukan menjadi negasi dari input
                    {
                        if(!operatorx.isEmpty())
                        {
                            if(!((operatorx.getLastData()).equals("{")))
                            {
                                dumpbil = bil.Pop();
                                if(ekspresitemp.charAt(0) == '1')
                                {
                                    bil.Push(0);
                                }
                                else if(ekspresitemp.charAt(0) == '0')
                                {
                                    bil.Push(1);
                                }
                            }
                        }
                        else
                        {
                            dumpbil = bil.Pop();
                            if(ekspresitemp.charAt(0) == '1')
                            {
                                bil.Push(0);
                            }
                            else if(ekspresitemp.charAt(0) == '0')
                            {
                                bil.Push(1);
                            }
                        }
                    }
                    else
                    {
                        if(ekspresitemp.charAt(0) == '1')
                        {
                            bil.Push(1);
                        }
                        else if(ekspresitemp.charAt(0) == '0')
                        {
                            bil.Push(0);
                        }
                    }
                }

            }
            if(ekspresitemp.charAt(0) == '~' || ekspresitemp.charAt(0) == '&' || ekspresitemp.charAt(0) == '|' || ekspresitemp.charAt(0) == '(' || ekspresitemp.charAt(0) == ')')
            {
                if(ekspresitemp.charAt(0) == '~')
                {
                    operatorx.Push(ekspresitemp.substring(0,1));
                    SmallCalculate(bil,operatorx);
                }
                if((operatorx.isEmpty() || ekspresitemp.charAt(0) == '(') && ekspresitemp.charAt(0) != '~')
                {
                    operatorx.Push(ekspresitemp.substring(0,1));
                }
                else
                {
                    if(ekspresitemp.charAt(0) == '&' || ekspresitemp.charAt(0) == '|')
                    {
                        if((operatorx.getLastData()).equals("~"))
                        {
                            SmallCalculate(bil,operatorx);
                            operatorx.Push(ekspresitemp.substring(0,1));
                        }
                        else //operatorx.getLastData() == "~"
                        {
                            operatorx.Push(ekspresitemp.substring(0,1));

                        }
                    }
                    else if(ekspresitemp.charAt(0) == ')')
                    {
                        while(!((operatorx.getLastData()).equals("(")))
                        {
                            SmallCalculate(bil,operatorx);
                        }
                        dumpoperator = operatorx.Pop();
                        if(!bil.isEmpty())
                        {
                            if(bil.getLastData() == -999)
                            {
                                dumpbil = bil.Pop();
                                if(ekspresitemp.charAt(0) == '1')
                                {
                                    bil.Push(0);
                                }
                                else if(ekspresitemp.charAt(0) == '0')
                                {
                                    bil.Push(1);
                                }
                            }
                        }

                    }

                }
            }
            ekspresitemp = ekspresitemp.substring(1,ekspresitemp.length()-1);
        }


        while(!operatorx.isEmpty())
        {
            SmallCalculate(bil,operatorx);

        }
        hasil = bil.getLastData();
        return hasil;
        //End
    }

    public int CalculatePrefix() {
        String buffer = "";
        String jawab = "";
        int i = 0;
        int j = 0; //iterator
        boolean op1 = false;
        boolean op2 = false;
        int x = 0; //operand dan hasil
        int len = 0; //panjang
        len = ekspresi.length();
        j = 0;
        for(i=len-1; i>=0; i--){
            if(ekspresi.charAt(i) == '0' || ekspresi.charAt(i)=='1'){
                buffer.charAt(0) = ekspresi.charAt(i);
                x = Integer.parseInt(buffer);
                bil.Push(x);
            }
            else if(ekspresi.charAt(i) == '&' || ekspresi.charAt(i) == '|' || ekspresi.charAt(i)=='~' || ekspresi.charAt(i)=='x'){
                operatorx.Push(ekspresi.substring(i,i+1));
                SmallCalculate(bil,operatorx);
            }
        }
        return bil.getLastData();
    }

    public int CalculatePostfix() {
        String buffer = "";
        String jawab = "";
        int i = 0;
        int j = 0; //iterator
        boolean op1 = false;
        boolean op2 = false;
        int x = 0; //operand dan hasil
        int len = 0; //panjang
        len = ekspresi.length();
        j = 0;
        for(i=0; i<len;i++){
            if(ekspresi.charAt(i) == '0' || ekspresi.charAt(i)=='1'){
                buffer.charAt(0) = ekspresi.charAt(i);
                x = Integer.parseInt(buffer);
                bil.Push(x);
            }
            else if(ekspresi.charAt(i) == '&' || ekspresi.charAt(i) == '|' || ekspresi.charAt(i)=='~' || ekspresi.charAt(i)=='x'){
                operatorx.Push(ekspresi.substring(i,i+1));
                SmallCalculate(bil,operatorx);
            }
        }
        return bil.getLastData();
    }

}
