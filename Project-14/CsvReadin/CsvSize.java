import java.util.*;
import java.io.*;

public class CsvSize
{


  //This is to find the number if lines in a csv file
  public int processCsvSize(String inputFile) throws IOException {

    int size = 0;
    
    //opens the file named by inputFile 

    Scanner fileReader = new Scanner(new File(inputFile));
    
    //this is the part that counts the lines
    while (fileReader.hasNextLine()){
      size += 1;
      fileReader.nextLine();
    }

      //this closes the file
    fileReader.close();
    

    return size;

  }

}