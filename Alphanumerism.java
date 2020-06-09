import java.io.*;
import java.util.*;
class Alphanumerism
{
  BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
  String a[] = {" One" , " Two" , " Three" , " Four" , " Five" , " Six" , " Seven" , " Eight" , " Nine"};
  String b[] = {" Twenty" , " Thirty" , " Forty" , " Fifty" , " Sixty" , " Seventy" , " Eighty" , " Ninety"};
  String t[] = {" Ten" , " Eleven" , " Twelve" , " Thirteen" , " Fourteen" , " Fifteen" , " Sixteen" , " Seventeen" , " Eighteen" , " Nineteen"};
  String h[] = {" MahaShankh" , " Shankh" , " Padma" , " Neel" , " Kharab" , " Arab" , " Crore" , " Lakh" , " Thousand" , " Hundred"};
 
  private String Tokenizer(String s) // 123456 = 1 23 456
  {
    int i , j = 0;
    String z = "";
    for(i = s.length() - 1;i >=  s.length() - 3;i --)
    {
      z = s.charAt(i) + z;
    }
    z = " " + z;
    for(i = s.length() - 4;i >= 0;i --)
    {
      j ++;
      z = s.charAt(i) + z;
      if(j % 2 == 0)
      {
        z = " " + z;
      }
    }

    if(s.length() % 2 == 0) // To make Each Token Evenly Lengthened
    {
      z = "0" + z;
    }
    return z;
  }

  // Units :
  private String A(char n)
  {
    return a[Integer.parseInt(String.valueOf(n)) - 1];
  }
  
  // Tens : 
  private String B(char n)
  {
    return b[Integer.parseInt(String.valueOf(n)) - 2];
  }

  // 10 - 19 : 
  private String T(int n)
  {
    return t[n - 10];
  }

  void HQ()throws IOException
  {
    String w = ""; 
    System.out.println("Enter a Number.");
    long n = Long.parseLong(x.readLine());
    System.out.println("");
    String s = Tokenizer(String.valueOf(n));
    s = s.trim();
    StringTokenizer t = new StringTokenizer(s);
    int c = t.countTokens(); // Helps in taking Proper Value from h[=10]
    int k = 10 - c; // This one does that
    String z[] = new String[c];
    int i , j = 0;
    while(t.hasMoreTokens()) // Stores String in an Array
    {
      z[j ++] = t.nextToken();
    }
    // 1 23 34 56 (987)   =    01 23 34 56 (987)
    for(i = 0;i < c - 1;i ++)
    {
      if(Integer.parseInt(z[i]) > 0)
      {
        if(Integer.parseInt(z[i]) >= 10 && Integer.parseInt(z[i]) <= 19)
        {
          w = w + T(Integer.parseInt(z[i])) + h[k];
        }
        else if(z[i].charAt(0) == '0') // for 02
        {
          w = w + A(z[i].charAt(1)) + h[k];
        }
        else if(z[i].charAt(1) == '0') // for 20
        {
          w = w + B(z[i].charAt(0)) + h[k];
        }
        else // for 22
        {
          w = w + B(z[i].charAt(0)) + A(z[i].charAt(1)) + h[k];
        }
        k ++;
      }
    }
    // for 087 : 
    if(z[c - 1].charAt(0) == '0' && Integer.parseInt(z[c -1]) > 0)
    {
      w = w + " and";
    }
    else // for 987
    {
    w = w + A(z[c - 1].charAt(0)) + h[k];
    }
    // (9) 87
    if(Integer.parseInt(z[c - 1].substring(1)) > 0)
    {
      if(Integer.parseInt(z[c - 1].substring(1)) >= 10 && Integer.parseInt(z[c - 1].substring(1)) <= 19)
      {
        w = w + T(Integer.parseInt(z[c - 1].substring(1)));
      }
      else if(z[c - 1].charAt(2) == '0') // for 20
      {
        w = w + B(z[c - 1].charAt(1));
      }
      else if(z[c - 1].charAt(1) == '0') // for 02
      {
      w = w + A(z[c - 1].charAt(2));
      }
      else
      {
        w = w + " and" + B(z[c - 1].charAt(1)) + A(z[c - 1].charAt(2));
      }
    }

    if(n < 0)
    {
      w = "Minus" + w;
    }
    System.out.println(w);
  }
}
