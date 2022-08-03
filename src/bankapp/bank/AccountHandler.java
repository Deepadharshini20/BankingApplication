package bankapp.bank;


import java.io.IOException;

import bankapp.Transcation.TranscationHandler;
import bankapp.customer.Customer;

public class AccountHandler {
  static TranscationHandler tHandler = new TranscationHandler();

  public void deposit(int id, double amount) throws IOException {
    Customer c = Bank.customerMap.get(id);
    if (c == null)
      System.out.println("The customer id is not found..Deposit cancelled");

    double updatedAmount = c.getBalance() + amount;
    c.setBalance(updatedAmount);
    Bank.customerMap.put(id, c);

    tHandler.addTranscation(id, "deposit", amount, c.getBalance());

    System.out.println(amount + " was deposited in this account");

  }

  public boolean withdraw(int id, double amount) throws IOException {
    Customer c = Bank.customerMap.get(id);
    if (c == null)
      System.out.println("The customer id is not found..withdraw cancelled");

    double updatedAmount = c.getBalance() - amount;
    if (c.getBalance() >= amount && updatedAmount > 1000) {
      c.setBalance(updatedAmount);
      Bank.customerMap.put(id, c);

      tHandler.addTranscation(id, "withdraw", amount, c.getBalance());
      System.out.println(amount + " taken in your account");
      return true;
    } else {
      if (updatedAmount > 1000) {
        System.out.println("Your bank balance is less than 1000");
        
      } else
        System.out.println("Insuffiecnt amount");
    }
    return false;
  }

  public void transfer(int fromId, int toId, double amount) throws IOException {
    if (withdraw(fromId, amount))
      deposit(toId, amount);
  }
}
