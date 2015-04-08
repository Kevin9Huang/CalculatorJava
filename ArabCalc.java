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
	public ArabCalc(String _ekspresi,int _mode,int size){
		bil = new Stack2<>(size);
		operatorx = new Stack2<>(size);
		ekspresi = _ekspresi;
		mode = _mode;
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
	public void SmallCalculate()
	{
		System.out.println("Sebelum calculate");
		Print();
		System.out.println("mode = "+mode);
		float popbil1 = 0.f;
		float popbil2 = 0.f;
		String dumpoperator;
		System.out.println("pop"+bil.getDataCount());
		if(mode == 2 || mode == 3) //sufix atau postfix
		{
			popbil1 = bil.Pop();
			popbil2 = bil.Pop();
		}
		else if(mode == 1) //prefix
		{
			popbil2 = bil.Pop();
			popbil1 = bil.Pop();
		}
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
			System.out.println("bil2 = "+popbil2 +"bil1 = "+popbil1);
			bil.Push(popbil2%popbil1);
		}
		else if(popoperator.equals("div"))
		{
			System.out.println("bil2 = "+popbil2 +"bil1 = "+popbil1);
			bil.Push((float)Math.round(popbil2/popbil1-0.5f));
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
								SmallCalculate();
								operatorx.Push(ekspresi.substring(i,i+1));
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
								SmallCalculate();
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
								SmallCalculate();
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
			SmallCalculate();

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
	public void CalculatePrefix() {
		String ekspresitemp = ekspresi;
		String bufferoperand = "";
        String bufferoperator = "";
		boolean bufferoperatorend = false;
        boolean bufferoperandend = false;
		int i = ekspresi.length()-1;
		while(i >= 0)
		{
			System.out.println("iterasi"+i);
			System.out.println("asdfasdf" + ekspresi.substring(i,i+1));
			bufferoperatorend = false;
            bufferoperandend = false;
			if(ekspresi.substring(i,i+1).matches("[0-9]")|| ekspresi.charAt(i) == '-') //	Numeric dan tanda minus
            {	
				while(!bufferoperandend){
					if(i>=0)
					{
						if(ekspresi.substring(i,i+1).matches("[0-9]"))
						{
							
							bufferoperand += ekspresi.substring(i,i+1);
							i--;
						}
						else if(ekspresi.charAt(i) == '-' && i!= 0)
						{								
							if(ekspresi.substring(i-1,i).trim().isEmpty())
							{
								bufferoperand += ekspresi.substring(i,i+1);
								i--;
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
					bufferoperand = new StringBuilder(bufferoperand).reverse().toString();
					if(bufferoperand.charAt(0) == '-')
					{
						bil.Push(Float.parseFloat(bufferoperand));
					}
					else
					{
						bil.Push(Float.parseFloat(bufferoperand));
					}
					bufferoperand = "";
					
				}
			}
			if(i>=0)
			{
				if(ekspresi.charAt(i) == '+' ||
				ekspresi.charAt(i) == '-' ||
				ekspresi.charAt(i) == '/' ||
				ekspresi.charAt(i) == '*' ||
				ekspresi.charAt(i) == 'v' ||
				ekspresi.charAt(i) == 'd')
				{
					while(!bufferoperatorend){
						if(i>=0)
						{
							if(ekspresi.substring(i,i+1).matches("[modiv]"))
							{
								System.out.println("modiv");
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
					if(!bufferoperator.equals("")){ //Mengecek apakah buffer berisi atau tidak
						System.out.println("Buffer operator : "+bufferoperator);
						bufferoperator = new StringBuilder(bufferoperator).reverse().toString();
						operatorx.Push(bufferoperator);
						SmallCalculate();
						bufferoperator = "";
					}
					if(i>=0)
					{
						if(ekspresi.charAt(i) == '+' ||
							ekspresi.charAt(i) == '-' ||
							ekspresi.charAt(i) == '/' ||
							ekspresi.charAt(i) == '*')
						{
							operatorx.Push(ekspresi.substring(i,i+1));
							SmallCalculate();
						}
					}
				}
				i--;
			}
			if(i>=0)
			{
				if(ekspresi.substring(i,i+1).trim().isEmpty())
				{
					i--; //Abaikan spasi
				}
			}
			Print();
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
	
	public void CalculatePostfix() {
		System.out.println("Postfix");
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
			System.out.println("asdfasdf" + ekspresi.substring(i,i+1));
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
							System.out.println("keluar");
						
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
									bufferoperandend=true;
								}
							}
							else //tanda - berada di paling akhir ekspresi contoh 9 3 -
							{
								bufferoperandend=true;
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
				ekspresi.charAt(i) == 'm')
				{
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
						SmallCalculate();
						bufferoperator = "";
					}
					if(i<len)
					{
						if(ekspresi.charAt(i) == '+' ||
							ekspresi.charAt(i) == '-' ||
							ekspresi.charAt(i) == '/' ||
							ekspresi.charAt(i) == '*')
						{
							operatorx.Push(ekspresi.substring(i,i+1));
							SmallCalculate();
						}
					}
				}
				i++;
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