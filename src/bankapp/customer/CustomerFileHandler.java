package bankapp.customer;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import bankapp.bank.Bank;

public class CustomerFileHandler {
  private static final String fileName = "C:/Users/dd/Documents/Java Application/Banking-Application/src/bankapp/bankdb.txt";

  static CustomerFileHandler handler;
  public static CustomerFileHandler getInstance() { // singleton object
    if(handler==null) // if object is not created then we can create new onject for this class
      handler = new CustomerFileHandler();
    return handler;
  }
  public void initialize() throws IOException{
    ArrayList<Customer> customers = Bank.customers;
    File file = new File(fileName); // get the file using file path

    try (BufferedReader br = new BufferedReader(new FileReader(file))) { // reading all content
      String customerInfo = br.readLine(); // reading single line
      while(customerInfo!=null){
        Customer customer = convertStringToCustomer(customerInfo); // converting to customer
        customers.add(customer); // add cutomer to the list 
        Bank.customerMap.put(customer.getCustomerId(), customer); // add the id and customer to map as well
        customerInfo = br.readLine(); // for reading next line
      }
    }
    
    Bank.customers = customers;
    Bank.customerId = customers.get(customers.size()-1).getCustomerId();
    Bank.accountNum = customers.get(customers.size()-1).getAccNum();
  }
  private static Customer convertStringToCustomer(String customerInfo) {
    String[] cusDetails = customerInfo.split(" ");
    Customer customer = new Customer(Integer.parseInt(cusDetails[0]), 
                                    Long.parseLong(cusDetails[1]), 
                                    cusDetails[2], 
                                    Double.parseDouble(cusDetails[3]),
                                    cusDetails[4]);
    return customer;                              
  }
  public void customerFileAdd(Customer customer) throws IOException {
    File file = new File(fileName);
    FileWriter fw = null;
    try {
      fw = new FileWriter(file, true);
      fw.write(customer.toString()+"\n");
    } catch (IOException e) {
      e.printStackTrace();
    } 
    finally{
      if(fw!=null)
        fw.close();
    }

  }
  public void customerAccountOperation() throws IOException  {
    File file = new File(fileName);
    FileWriter fw = null;
    try {
      fw = new FileWriter(file);
      Set<Integer> keyset = Bank.customerMap.keySet();
      Iterator<Integer> customerIterator = keyset.iterator();
      
      while(customerIterator.hasNext()){
        int id = (int)customerIterator.next();
        Customer customer = Bank.customerMap.get(id);
        fw.write(customer.toString()+"\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } 
    finally{
      if(fw!=null)
        fw.close();
    }
  }

}
