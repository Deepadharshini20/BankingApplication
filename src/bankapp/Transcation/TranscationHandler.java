package bankapp.Transcation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TranscationHandler {
  public void writeTranscation(int customerId, Transcation trans) throws IOException {
    String fileName = customerId + ".txt";

    boolean isNewFile = false;
    try{
      File file = new File(fileName);
      if (!file.exists()){
        file.createNewFile();
        isNewFile = true;
      }
      FileWriter writer = new FileWriter(file,true);
      
      if(!isNewFile)
        writer.write("\n");

      writer.write(trans.toString());
      writer.close();
    }
    catch(Exception e){}    
  }

  public int getLastTransctionId(int customerId) throws FileNotFoundException {
    String fileName = customerId + ".txt";

    File file = new File(fileName);
    if (!file.exists())
      return 0;

    String transInfo = "";
    Scanner in = new Scanner(file);
    while(in.hasNext()){
      transInfo = in.nextLine();
    }
    in.close();

    Transcation transcation = castToTranscation(transInfo);
    return transcation.transcationId;
  }

  private Transcation castToTranscation(String transInfo) {
    String[] transInfoArr = transInfo.split("\t");
    Transcation trans = new Transcation(Integer.parseInt(transInfoArr[0]),
                                        transInfoArr[1], 
                                        Double.parseDouble(transInfoArr[2]) , 
                                        Double.parseDouble(transInfoArr[3]));
    return trans;
  }

  public void addTranscation(int id, String type, double amount, double balance) throws IOException {
    int lastTransctionId = getLastTransctionId(id);
    Transcation transcation = new Transcation(++lastTransctionId, type, amount, balance);
   
    writeTranscation(id, transcation);
    
  }

  public void transcationHistory(int id) throws FileNotFoundException {
    System.out.println("Transcation History");

    String fileName = id + ".txt";

    File file = new File(fileName);
    if (!file.exists())
      return;

    String transInfo = "";
    Scanner in = new Scanner(file);
    while(in.hasNext()){
      transInfo = in.nextLine();
      System.out.println(transInfo);
    }
    in.close();
  }
}