import java.util.*;
public class ArabCalc{
	private Stack2<Float> bil;
	private Stack2<String> operatorx;
	private String ekspresi;
	private int mode;
	private float hasil;
	private final int defaultStackSize = 30;
	private final int defaultMode = 2; //Sufix
	public ArabCalc(){
		bil = new Stack2<>(defaultStackSize);
		operatorx = new Stack2<>(defaultStackSize);
		ekspresi = "";
		mode = defaultMode;
	}
	public ArabCalc(String _ekspresi,int mode,int size){
		bil = new Stack2<>(size);
		operatorx = new Stack2<>(size);
		ekspresi = _ekspresi;
		mode = mode;
	}
	
	public String getEkspresi()
	{
		return ekspresi;
	}
	public void setEkspresi(String _ekspresi)
	{
		ekspresi = _ekspresi;
	}
	public int getMode()
	{
		return mode;
	}
	public void setMode(int _mode)
	{
		mode = _mode;
	}
	public void setHasil(float _hasil)
	{
		hasil = _hasil;
	}
	public float getHasil()
	{
		return hasil;
	}
	public void Calculate(){
		if(mode == 2)
		{
			CalculateSufix();
		}
		/*
		else if(mode == 2)
		{
			hasil = CalculateInfix();
		}
		else if(mode == 3)
		{
			hasil = CalculatePostfix();
		}
		*/
	}
	public void SmallCalculate(Stack2<Float> bil,Stack2<String> operatorx)
	{
		System.out.println("Sbelum calculate");
		Print();
		float popbil1;
		float popbil2;
		String dumpoperator;
		System.out.println("pop"+bil.getDataCount());
		popbil1 = bil.Pop();
		popbil2 = bil.Pop();
		String popoperator = operatorx.Pop();
		if(popoperator.equals("*"))
		{
			bil.Push(popbil2*popbil1);
		}
		else if(popoperator.equals("/"))
		{
			bil.Push(popbil2/popbil1);
		}
		else if(popoperator.equals("mod"))
		{
			bil.Push(popbil2%popbil1);
		}
		else if(popoperator.equals("div"))
		{
			bil.Push(popbil2 - Math.round(popbil2/popbil1-0.5f)*popbil1);
		}
		else if(popoperator.equals("+"))
		{
			bil.Push(popbil2+popbil1);
		}
		else if(popoperator.equals("-"))
		{
			bil.Push(popbil2-popbil1);
		}
		System.out.println("Selesai small calculate");
		Print();
	}
	void CalculateSufix(){
		String ekspresitemp = ekspresi;
		String bufferoperand = "";
        String bufferoperator = "";
		boolean bufferoperatorend = false;
        boolean bufferoperandend = false;
		int i = 0;
		int len = ekspresi.length();
		while(i < len)
		{
			System.out.println("iterasi"+i);
			bufferoperatorend = false;
            bufferoperandend = false;
			if(ekspresi.substring(i,i+1).matches("[0-9]")|| ekspresi.charAt(i) == '-') //	Numeric dan tanda minus
            {	
				while(!bufferoperandend){
					if(i<len)
					{
						if(ekspresi.substring(i,i+1).matches("[0-9]"))
						{
							
							bufferoperand += ekspresi.substring(i,i+1);
							i++;
						}
						else if(ekspresi.charAt(i) == '-'){
							if(i+1 < len)
							{
								System.out.println("minus" + ekspresi.substring(i+1,i+2));
								if(!ekspresi.substring(i+1,i+2).trim().isEmpty()) //akan dijalankan jika setelah tanda - bukan spasi, yang
								// berarti tanda - berarti minus(operand) dan bukan operator
								{
									
									bufferoperand += ekspresi.substring(i,i+1);
									i++;
								}
								else
								{
									System.out.println("ping");
									bufferoperandend=true;
								}
							}
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
				if(!bufferoperand.equals("")){ //Mengecek apakah buffer berisi atau tidak
					System.out.println("Buffer angka : "+bufferoperand);
					bil.Push(Float.parseFloat(bufferoperand));
					bufferoperand = "";
				}
			}
			if(i<len)
			{
				if(ekspresi.charAt(i) == '+' ||
				ekspresi.charAt(i) == '-' ||
				ekspresi.charAt(i) == '/' ||
				ekspresi.charAt(i) == '*' ||
				ekspresi.charAt(i) == 'd' ||
				ekspresi.charAt(i) == 'm' ||
				ekspresi.charAt(i) == '(' ||
				ekspresi.charAt(i) == ')')
				{
					System.out.println("operatorasdf"+ekspresi.charAt(i));
					while(!bufferoperatorend){
						if(i<len)
						{
							if(ekspresi.substring(i,i+1).matches("[modiv]"))
							{
								System.out.println("modiv");
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
					if(!bufferoperator.equals("")){ //Mengecek apakah buffer berisi atau tidak
						System.out.println("Buffer operator : "+bufferoperator);
						operatorx.Push(bufferoperator);
						bufferoperator = "";
					}
					if(operatorx.isEmpty() || ekspresi.charAt(i) == '(')
					{
						operatorx.Push(ekspresi.substring(i,i+1));
					}
					else
					{
						if(ekspresi.charAt(i) == '+' || ekspresi.charAt(i) == '-')
						{
							if(operatorx.getLastData().equals("*") ||
							operatorx.getLastData().equals("/") ||
							operatorx.getLastData().equals("div") ||
							operatorx.getLastData().equals("mod"))
							{
								SmallCalculate(bil,operatorx);
								operatorx.Push(ekspresi.substring(0,1));
							}
							else
							{
								operatorx.Push(ekspresi.substring(i,i+1));
							}
						}
						else if(ekspresi.charAt(i) == '*' || ekspresi.charAt(i) == '/' ||
						ekspresi.charAt(i) == 'd' || ekspresi.charAt(i) == 'm')
						{
							if(operatorx.getLastData().equals("*") || operatorx.getLastData().equals("/") ||
							operatorx.getLastData().equals("div") || operatorx.getLastData().equals("mod"))
							{
								SmallCalculate(bil,operatorx);
								if(ekspresi.charAt(i) == 'd')
								{
									operatorx.Push("div");
									System.out.println("ping");
									i+=2;
								}
								else if(ekspresi.charAt(i) == 'm')
								{
									operatorx.Push("mod");
									i+=2;
								}
								else
								{
									operatorx.Push(ekspresi.substring(i,i+1));
								}
							}
							else
							{
								operatorx.Push(ekspresi.substring(i,i+1));
							}
						}
						else if(ekspresi.charAt(i) == ')')
						{
							System.out.println("masuk");
							while(!operatorx.getLastData().equals("("))
							{
								SmallCalculate(bil,operatorx);
							}
							String dumpstring = operatorx.Pop();
						}

					}
					i++;
				}
			}
			if(i<len)
			{
				if(ekspresi.substring(i,i+1).trim().isEmpty())
				{
					i++; //Abaikan spasi
				}
			}
			Print();
		}
		while(!operatorx.isEmpty())
		{
			SmallCalculate(bil,operatorx);

		}
		hasil = bil.getLastData();
		//Cleaning
		while(!bil.isEmpty())
		{
			float dumpbil = bil.Pop();
		}
		while(!operatorx.isEmpty())
		{
			String dumpoperator = operatorx.Pop();
		}
	}
	/*
	void ArabCalc::CalculatePrefix() {
		char buffer[15];
		int i, len, j;
		int op1, op2, x;
		len = ekspresi.length();
		boolean bufferoperatorend = false;
		j = 0;
		
		for(i=len-1; i>=0; i--){
			bufferoperatorend = false;
			if(ekspresi.charAt(i)>='0' && ekspresi.charAt()<='9'){
				buffer[j++] = ekspresi[i];
			}
			else if(ekspresi[i]==' '){
				if(j>0){ //jika bufffer tidak kosong
					String tempbuffer = "";
					tempbuffer = String.valueOf(buffer);
					bil<<Float.parseFloat(tempbuffer);
				}
			}
			if(i>=0)
			{
				while(!bufferoperatorend){
					if(i>=0)
					{
						if(ekspresi.substring(i,i+1).matches("d|i|v|m|o|d"))
						{
							bufferoperator += ekspresi.substring(i,i+1);
							i--;
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
					operatorx.Push(Integer.parseInt(bufferoperator));
					bufferoperator = "";
				}
			}
			else if(ekspresi.charAt(i)=='+' || ekspresi.charAt(i)=='-' || ekspresi.charAt(i)=='*' || ekspresi.charAt(i)==':' || ekspresi.charAt(i)=='%' || ekspresi.charAt(i)=='/'){
				op1 = s.Pop()
				op2 = s.Pop();
				switch(ekspresi.charAt(i)){
					case '+':
						s.Push(op2 + op1);
						break;
					case '-':
						s.Push(op2 - op1);
						break;
					case '*':
						s.Push(op2  op1);
						break;
					case '/':
						s.Push((float)op2 /(float)op1);
						break;
					case 'mod':
						s.Push(op2 % op1);
						break;
					case 'div':
						s.Push(op2/op1);
						break;
				}
			}
		}
		hasil = s.getLastData();
	}

	void ArabCalc::CalculatePostfix() {
		char buffer[15];
		int i, len, j;
		int op1, op2, x;
		Stack2<int> s;
		len = ekspresi.length();
		j = 0;
		int dumpint;
		for(i=0; i<len;i++){

			if(ekspresi[i]>='0' && ekspresi[i]<='9'){
				buffer[j++] = ekspresi[i];
			}
			else if(ekspresi[i]==' '){
				if(j>0){
					buffer[j] = '\0';
					x = atof(buffer);
					s<<x;
					j = 0;
				}
			}
			else if(ekspresi[i]=='+' || ekspresi[i]=='-' || ekspresi[i]=='*' || ekspresi[i]==':' || ekspresi[i]=='%' || ekspresi[i]=='/'){
				op1 = s.getPop();
				op2 = s.getPop();
				switch(ekspresi[i]){
					case '+':
						s.Push(op2 + op1);
						break;
					case '-':
						s.Push(op2 - op1);
						break;
					case '*':
						s.Push(op2  op1);
						break;
					case ':':
						s.Push(op2 / op1);
						break;
					case '%': // di c++ nggak bisa int mod int
						s.Push(op2 % op1);
						break;
					case 'div':
						int div;
						div = (int)floor(op2 / op1);
						s<<(div);
						break;
				}
			}
		}
		return s.getLastData();
	}
	*/
	public void Print(){
		System.out.println("Stack bilangan");
		for(int i = 0;i<=bil.getDataCount()-1;i++)
		{
			System.out.println(i+"). "+bil.getData(i));
		}
		System.out.println("Stack operator");
		for(int j = 0;j<=operatorx.getDataCount()-1;j++)
		{
			System.out.println(j+"). "+operatorx.getData(j));
		}
	}

}