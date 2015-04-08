import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Checker{
  private int mode; //mode kalkulator
  private int style; //notasi ekspresi
  private String expression; //ekspresi matematika dari masukan pengguna
  //ctor
  public Checker(){ //ctor tanpa parameter
    mode = 0;
    style = 0;
    expression = "0 + 0 + 0 + 0";
    System.out.println("Sudah terbentuk menggunakan ctor tanpa parameter");
  }
  
  public Checker(int _mode, int _style, String _expression){ //ctor dengan parameter
    mode = _mode;
    style = _style;
    expression = _expression;
    System.out.println("Sudah terbentuk menggunakan ctor berparameter");
  }
  //getter dan setter
  public int getMode(){
    return mode;
  }
  public int getStyle(){
    return style;
  }
  public String getExpression(){
    return expression;
  }
  public void setMode(int _mode){
    mode = _mode;
  }
  public void setStyle(int _style){
    style = _style;
  }
  public void setExpression(String _expression){
    expression = _expression;
  }
  //method lain
  //hitung jumlah baris pada file eksternal  
  public int countLine(String path) throws IOException{
    int jumlah = 0;
    FileReader f = new FileReader(path);
    BufferedReader b = new BufferedReader(f);
    
    while(b.readLine() != null){
      jumlah++;
    }
    b.close();
    
    return jumlah;
  }
  //membaca file eksternal dan menyimpannya ke array
  public String[] readFile(String path) throws IOException{
    FileReader f = new FileReader(path);
    BufferedReader b = new BufferedReader(f);
    
    int nBaris = countLine(path);
    String[] arr = new String[nBaris];
    int i = 0;//iterator
    
    for(i = 0; i < nBaris; i++){
      arr[i] = b.readLine();
      System.out.println(arr[i]);
    }
    b.close();
    return arr;
  }
  
  
  //membandingkan ekspresi dengan array operator Arab
  public void compareArab(String[] arrArab, String[] eksp){
    System.out.println(getMode());
    int i = 0; //iterator untuk pengulangan arr
    int j = 0; //iterator untuk pengulangan expression
    String a; //String untuk ekspresi
    String b; //String untuk operator pada array
    while(getMode()== 0 && i < eksp.length){
      a = eksp[i];
      while(j < arrArab.length && getMode()== 0){
        b = arrArab[j];
        System.out.println("Pengecekan ekspresi: " + eksp[i] + " dengan " + arrArab[j]);
        if(a.equals(b)){
          this.setMode(1);
          System.out.println("Mode: " + getMode());
        }
        else{
          j++;
        }
      }
      j=0;
      i++;
    }
  }
  //membandingkan ekspresi dengan array operator Logika
  public void compareLogika(String[] arrLogika, String[] eksp){
    System.out.println(getMode());
    int i = 0; //iterator untuk pengulangan arr
    int j = 0; //iterator untuk pengulangan expression
    String a; //String untuk ekspresi
    String b; //String untuk operator pada array
    while(getMode()== 0 && i < eksp.length){
      a = eksp[i];
      while(j < arrLogika.length && getMode()== 0){
        b = arrLogika[j];
        System.out.println("Pengecekan ekspresi: " + eksp[i] + " dengan " + arrLogika[j]);
        if(a.equals(b)){
          this.setMode(2);
          System.out.println("Mode: " + getMode());
        }
        else{
          j++;
        }
      }
      j=0;
      i++;
    }
  }
  //membandingkan ekspresi dengan array operator Relasional
  public void compareRelasional(String[] arrRelasional, String[] eksp){
    System.out.println(getMode());
    int i = 0; //iterator untuk pengulangan arr
    int j = 0; //iterator untuk pengulangan expression
    String a; //String untuk ekspresi
    String b; //String untuk operator pada array
    while(getMode()== 0 && i < eksp.length){
      a = eksp[i];
      while(j < arrRelasional.length && getMode()== 0){
        b = arrRelasional[j];
        System.out.println("Pengecekan ekspresi: " + eksp[i] + " dengan " + arrRelasional[j]);
        if(a.equals(b)){
          this.setMode(3);
          System.out.println("Mode akhir: " + getMode());
        }
        else{
          j++;
        }
      }
      j=0;
      i++;
    }
  }
  //Cek Notasi penulisan ekspresi
  public void style(int mode, String[] ekspresi, String[] arr){
    String awal; //String awal yang tersimpan
    String akhir; //String akhir yang tersimpan
    String operator;
    int indexAkhir; //index akhir dari array
    int i = 0; //iterator ekspresi
    int j = 0; //iterator untuk array
    indexAkhir = ekspresi.length - 1;
    String[] temp;
    awal = ekspresi[0];
    if(awal.equals("")){
      temp = new String[indexAkhir];
      for(i = 0; i < indexAkhir; i++){
        temp[i] = ekspresi[i+1];
      }
      indexAkhir = temp.length - 1;
    }
    else{
      temp = new String[ekspresi.length];
      for(i = 0; i < ekspresi.length; i++){
        temp[i] = ekspresi[i];
      }
    }
    awal = temp[0];
    akhir = temp[indexAkhir];
    System.out.println("Awal: " + awal + " Akhir: " +akhir);
    while(j < arr.length){
      operator = arr[j];
      System.out.println("Operator: " + operator);
      if(awal.equals(operator)){
        setStyle(1); //notasi prefix
        System.out.println("Notasinya ialah prefix");
      }
      else if(akhir.equals(operator)){
        setStyle(3); //notasi postfix
        System.out.println("Notasinya ialah postfix");
      }
      j++;
    }
    if(getStyle() == 0){
      setStyle(2);
      System.out.println("Notasinya ialah infix");
    }
  }
  
  public static void main(String[] args){
    Checker c = new Checker();
    c.setExpression("true false xor");
    System.out.println("Ekspresi masukan: " + c.getExpression());
    String eks = c.getExpression();
    String[] eksp; //array penyimpan ekspresi
    String elim = "[ |(|)]+"; //eliminator
    eksp = eks.split(elim);
    try{
      String pathArab = "D:/Kuliah/Akademik/Tugas/OOP/Tubes2/operator_arab.txt";
      String pathLogika = "D:/Kuliah/Akademik/Tugas/OOP/Tubes2/operator_logika.txt";
      String pathRelasional = "D:/Kuliah/Akademik/Tugas/OOP/Tubes2/operator_relasional.txt";
      String[] arrArab = c.readFile(pathArab);
      String[] arrLogika = c.readFile(pathLogika);
      String[] arrRelasional = c.readFile(pathRelasional);
      for(int i = 0; i < arrArab.length; i++){
        System.out.println(arrArab[i]);
      }
      c.compareArab(arrArab, eksp);
      c.compareLogika(arrLogika, eksp);
      c.compareRelasional(arrRelasional, eksp);
      int _mode = c.getMode();
      //Di sini tulis if else (kondisi) pengecekan mode kalkulator, lalu lakukan pengecekan notasi matematika sesuai dengan mode kalkulator
      if(_mode == 1){
        c.style(_mode, eksp, arrArab);
      }
      else if(_mode == 2){
        c.style(_mode, eksp, arrLogika);
      }
      else if(_mode == 3){
        c.style(_mode, eksp, arrRelasional);
      }
    }
    catch(IOException e){
      System.out.println("Error: " + e);
    }
  }
}
