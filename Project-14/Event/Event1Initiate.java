import java.io.*;

public class Event1Initiate
{

  ReadFile R = new ReadFile();
  CsvSize C = new CsvSize();

  private int size;
  private Event1_Data Encounter[];
  private String inputfile;


  public Event1_Data[] processEvent1Initiate(String in){

    //this is to allow the whole file to know what the file being read in is
    inputfile = in;
    try {
    size = C.processCsvSize(inputfile);
    }
    catch(IOException e) {
    e.printStackTrace();
    }
    
    
    Encounter = new Event1_Data[size];
    readEvent1_Data();

    return Encounter;
  }

  public void readEvent1_Data(){

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

      Encounter[i] = new Event1_Data();    

      Encounter[i].setEntrance(csvColumnList[0]);


      
      /*0,0 = 1
        0,1 = 2
        0,2 = 3
        1,0 = 4
        1,1 = 5
        1,2 = 6
        2,0 = 7
        2,1 = 8
        2,2 = 9
      */
      for(int a = 0; a < 3; a++){

        for(int b = 0; b < 3; b++){
          
          Encounter[i].setApproach(csvColumnList[(a * 3) + b + 1], a, b);
        }

        
      }
      
      /*0,0,0 = 10
        0,0,1 = 11
        0,0,2 = 12
        0,1,0 = 13
        0,1,1 = 14
        0,1,2 = 15
        1,0,0 = 16
        1,0,1 = 17
        1,0,2 = 18
        1,1,0 = 19
        1,1,1 = 20
        1,1,2 = 21
        2,0,0 = 22
        2,0,1 = 23
        2,0,2 = 24
        2,1,0 = 25
        2,1,1 = 26
        2,1,2 = 27
      */
      for(int a = 0; a < 3; a++){
        for(int b = 0; b < 2; b++){
          for(int c = 0; c < 3; c++){
            Encounter[i].setApproach_Options(csvColumnList[(a * 6) + (b * 3) + c + 10], a, b, c);
          }
        }
      }

      Encounter[i].setMob_Id(Integer.parseInt(csvColumnList[28]));

      Encounter[i].setMultiplier(Integer.parseInt(csvColumnList[29]));
      

      /*0 = 30
        1 = 31
        2 = 32
      */
      for(int a = 0; a < 3; a++){
        Encounter[i].setExtra(Integer.parseInt(csvColumnList[a + 30]), a);
      }

      
      

    }

    
  }
}