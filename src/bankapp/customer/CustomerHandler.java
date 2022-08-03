package bankapp.customer;

import java.io.IOException;

import bankapp.GetInput;
import bankapp.Transcation.Transcation;
import bankapp.Transcation.TranscationHandler;
import bankapp.bank.Bank;

abstract class Encrption{
  public static String encrpt(String pwd) {
    char[] arr = pwd.toCharArray();
    String encrp = "";
    for (int i = 0; i < arr.length; i++) {
      if ((arr[i] >= 'a' && arr[i] <= 'z') || (arr[i] >= 'A' && arr[i] <= 'Z') || (arr[i] >= '0' && arr[i] <= '9')) {
        if (arr[i] == 'Z' || arr[i] == 'z')
          encrp += (char) ((int) arr[i] - 25);
        else if (arr[i] == '9')
          encrp += (char) ((int) arr[i] - 9);
        else
          encrp += (char) ((int) arr[i] + 1);
      } else {
        encrp += arr[i];
      }
    }
    return encrp;
  }
  abstract public void print(String s);
}

public class CustomerHandler extends Encrption{
  final double BALANCE = 10000;
  static CustomerHandler c = new CustomerHandler();
   
  public void addCustomer() throws IOException {
    
    String name = GetInput.getName();
    String pwd = GetInput.getPassword();

    Customer c = new Customer(++Bank.customerId, ++Bank.accountNum, name, BALANCE, encrpt(pwd));
    Bank.customers.add(c);
    Bank.customerMap.put(Bank.customerId, c);

    CustomerFileHandler.getInstance().customerFileAdd(c);
    logTranscation(Bank.customerId);

    System.out.println("Successfully registered the new customer");
  }

  public void logTranscation(int customerId) throws IOException {
    Transcation trans = new Transcation(1, "opening", 10000, 10000);
    TranscationHandler tHandler = new TranscationHandler();
    tHandler.writeTranscation(customerId, trans);
    
  }

  public boolean authenticate(int id){
    Customer customer = Bank.customerMap.get(id);
    
    if(customer==null){
      System.out.println("The user id is not found");
      return false;
    }
    System.out.println("Enter password: ");
    String pwd = Bank.in.nextLine();

    if(customer.getPassword().equals(encrpt(pwd))){
      System.out.println("Valid user");
      return true;
    }
    else  
      System.out.println("Invalid user");

    return false;
  }

  @Override
  public void print(String s) {
    System.out.println("Enter "+s);
  }
  
}