import java.io.*;

public class MobInitiate
{

  ReadFile R = new ReadFile();
  CsvSize C = new CsvSize();

  private int size;
  private Mob_Data mob_list[];
  private String inputfile;


  public Mob_Data[] processMobInitiate(String in){



    //this is to allow the whole file to know what the file being read in is   
    inputfile = in;
    try {
    size = C.processCsvSize(inputfile);
    }
    catch(IOException e) {
    e.printStackTrace();
    }
    
    
    mob_list = new Mob_Data[size];
    readMob_Data();

    return mob_list;
  }

  public void readMob_Data(){

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

      mob_list[i] = new Mob_Data();    

      mob_list[i].setName(csvColumnList[0]);
      mob_list[i].setAttack(Double.parseDouble(csvColumnList[1]));
      mob_list[i].setHealth(Double.parseDouble(csvColumnList[2]));
      mob_list[i].setArmour(Double.parseDouble(csvColumnList[3]));
      mob_list[i].setSpeed(Double.parseDouble(csvColumnList[4]));
      mob_list[i].setGold(Double.parseDouble(csvColumnList[5]));
      mob_list[i].setXp(Double.parseDouble(csvColumnList[6]));
      mob_list[i].setId(csvColumnList[7]);


      
      

    }

    
  }
}