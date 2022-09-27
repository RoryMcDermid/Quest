import java.util.*;
import java.io.*;

public class ReadFile
{

  public String[] processReadFile(String[] fileContents, String inputfile, boolean headings) throws IOException {

    //opens the file named by inputFile 
    Scanner fileReader = new Scanner(new File(inputfile));
    
    if (headings = true){
      // fileReader.nextLine(); // read headings
    }

    // loop for every planned line, fetch from file and store
    for (int i = 0; i < fileContents.length; i++) {
        fileContents[i] = fileReader.nextLine();
    }

    // close the file
    fileReader.close();

    return fileContents;
  }


}