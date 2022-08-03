package bankapp.customer;
public class Customer {
  private static String SPACE = " ";
  public int customerId;
  public long accNum;
  public String name;
  public String password;
  public double balance;
  
  public Customer(int customerId,long accNum,String name,double balance,String password){
    this.customerId = customerId;
    this.accNum = accNum;
    this.name = name;
    this.password = password;
    this.balance = balance;
  }
  @Override
  public String toString() {
    return customerId + SPACE+
            accNum+ SPACE+
            name+ SPACE+
            balance+SPACE+
            password+SPACE;
  }
  public int getCustomerId() {
    return customerId;
  }
  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
  public long getAccNum() {
    return accNum;
  }
  public void setAccNum(long accNum) {
    this.accNum = accNum;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public double getBalance() {
    return balance;
  }
  public void setBalance(double balance) {
    this.balance = balance;
  }
}
