package bankapp.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bankapp.customer.Customer;

public class Bank {
  public static ArrayList<Customer> customers= new ArrayList<>(); // List of customers
  public static HashMap<Integer,Customer> customerMap = new HashMap<>(); // mapping id and customer in hashmap 
  
  public static Scanner in = new Scanner(System.in);

  public static int customerId;
  public static long accountNum;
} 
