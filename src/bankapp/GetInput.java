package bankapp;

import java.util.Scanner;

import bankapp.bank.Bank;

public class GetInput {
  static Scanner in = Bank.in;
  public static int getNumber() {
    
    int n = 0;  
    while(true){
      String input = in.nextLine();
      try{
        n = Integer.parseInt(input);
        return n;
      }
      catch(NumberFormatException e){
        System.out.println("Enter only number");
      }
    }
  } 

  public static String getName() {
    String name = null;
    while(true){
      System.out.println("Enter Customer name: ");
      name = in.nextLine();
      if(!name.matches(".*[0-9].*"))
        break;
      else  
        System.out.println("Enter only String!!");
    }
    return name;
  }
  public static String getPassword() {
    System.out.println("Enter Password");
    String pwd = in.nextLine();

    while (true) {
      System.out.println("Re-enter password");
      String reEnterPwd = in.nextLine();

      if (!pwd.equals(reEnterPwd))
        System.out.println("Password is incorrect");
      else
        break;
    }
    return pwd;
  }
}
