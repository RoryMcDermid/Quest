import java.io.*;

public class WeaponInitiate
{

  ReadFile R = new ReadFile();
  CsvSize C = new CsvSize();

  private int size;
  private Weapon_Data Weapon_List[];
  private String inputfile;


  public Weapon_Data[] processWeaponInitiate(String in){

    //this is to allow the whole file to know what the file being read in is
    inputfile = in;
    try {
    size = C.processCsvSize(inputfile);
    }
    catch(IOException e) {
    e.printStackTrace();
    }
    
    
    Weapon_List = new Weapon_Data[size];
    readWeapon_Data();

    return Weapon_List;
  }

  public void readWeapon_Data(){

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

      Weapon_List[i] = new Weapon_Data();    

      Weapon_List[i].setWeapon_Name(csvColumnList[0]);
      Weapon_List[i].setWeapon_Attack(Integer.parseInt(csvColumnList[1]));
      Weapon_List[i].setWeapon_Id(csvColumnList[2]);


      
      

    }

    
  }
}