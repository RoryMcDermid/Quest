import java.io.*;

public class BadgeInitiate
{

  ReadFile R = new ReadFile();
  CsvSize C = new CsvSize();

  private int size;
  private Badge_Data Badge_List[];
  private String inputfile;


  public Badge_Data[] processBadgeInitiate(String in){

    //this is to allow the whole file to know what the file being read in is
    inputfile = in;
    try {
    size = C.processCsvSize(inputfile);
    }
    catch(IOException e) {
    e.printStackTrace();
    }
    
    
    Badge_List = new Badge_Data[size];
    readBadge_Data();

    return Badge_List;
  }

  public void readBadge_Data(){

    String csvRowsList[] = new String[size];

    try {
    csvRowsList = R.processReadFile(csvRowsList, inputfile, false);
    }
    catch(IOException e) {
    e.printStackTrace();
    }

    String[] csvColumnList;
    
    //this is to input all of the variables into the array of objects
    for (int i = 0; i < size; i++){
      csvColumnList = csvRowsList[i].split("<>");

      Badge_List[i] = new Badge_Data();    

      Badge_List[i].setBadge_Name(csvColumnList[0]);
      
      for(int a = 0; a < 2; a++){
        Badge_List[i].setBadge_Stat_Increase(Integer.parseInt(csvColumnList[a + 1]), a);
      }

      Badge_List[i].setBadge_Acquired(Boolean.parseBoolean(csvColumnList[4]));


      
      

    }

    
  }
}