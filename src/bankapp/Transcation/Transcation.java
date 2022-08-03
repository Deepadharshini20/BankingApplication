package bankapp.Transcation;

public class Transcation {
  public int transcationId;
  public String type;
  public double amount;
  public double balance;

  public Transcation(int transcationId, String type, double amount, double balance) {
    this.transcationId = transcationId;
    this.type = type;
    this.amount = amount;
    this.balance = balance;
  }

  @Override
  public String toString() {
    return  transcationId + 
            "\t"+ type + 
            "\t"+ amount + 
            "\t"+ balance ;
  }
}
