package bankapp;

import java.io.IOException;

import bankapp.Transcation.TranscationHandler;
import bankapp.bank.AccountHandler;
import bankapp.customer.CustomerFileHandler;
import bankapp.customer.CustomerHandler;

public class Main {
  public static void main(String[] args) throws IOException {
    CustomerFileHandler.getInstance().initialize();
    homepage();

  }

  public static void homepage() throws IOException {
    CustomerHandler ch = new CustomerHandler();
    AccountHandler ah = new AccountHandler();
    TranscationHandler th = new TranscationHandler();
    while (true) {
      System.out.println("1.Add customer\n2.Deposit\n3.Withdraw\n4.Money transfer\n5 Show Transcation History\n6.Stop");
      System.out.println("Enter your choice");
      int n = GetInput.getNumber();

      switch (n) {
        case 1:
          ch.addCustomer();
          homepage();
          break;
        case 2:
          System.out.println("Enter id");
          int id = GetInput.getNumber();
          int amount;
         if (ch.authenticate(id)) {
            System.out.println("Enter amount to deposit");
            amount = GetInput.getNumber();
            ah.deposit(id, (double) amount);
          }
          CustomerFileHandler.getInstance().customerAccountOperation();
          homepage();
          break;
        case 3:
          System.out.println("Enter id");
          id = GetInput.getNumber();
          if (ch.authenticate(id)) {
            System.out.println("Enter amount to withdraw");
            amount = GetInput.getNumber();
            ah.withdraw(id, (double) amount);
          }
          CustomerFileHandler.getInstance().customerAccountOperation();
          homepage();
          break;
        case 4:
          System.out.println("Enter id for transfer amount from this account");
          int fromId = GetInput.getNumber();
          boolean from = ch.authenticate(fromId);
          boolean to = false;
          int toId = 0;
          if (from) {
            System.out.println("Enter id for transfer amount to this account");
            toId = GetInput.getNumber();
            to = ch.authenticate(toId);
          }

          if (from && to) {
            System.out.println("Enter amount to deposit");
            amount = GetInput.getNumber();
            ah.transfer(fromId, toId, (double) amount);
          }
          CustomerFileHandler.getInstance().customerAccountOperation();
          homepage();
          break;
        case 5:
          System.out.println("Enter id");
          id = GetInput.getNumber();
          if (ch.authenticate(id)) {
            th.transcationHistory(id);
          }
          break;
        case 6:
          System.exit(0);
        default:
          System.out.println("Enter valid choice");
          break;
      }
    }
  }
}
