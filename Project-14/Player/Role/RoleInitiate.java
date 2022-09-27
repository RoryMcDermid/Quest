import java.io.*;

public class RoleInitiate
{

  ReadFile R = new ReadFile();
  CsvSize C = new CsvSize();

  private int size;
  private Role_Data Role_List[];
  private String inputfile;


  public Role_Data[] processRoleInitiate(String in){

    //this is to allow the whole file to know what the file being read in is
    inputfile = in;
    try {
    size = C.processCsvSize(inputfile);
    }
    catch(IOException e) {
    e.printStackTrace();
    }
    
    
    Role_List = new Role_Data[size];
    readRole_Data();

    return Role_List;
  }

  public void readRole_Data(){

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

      Role_List[i] = new Role_Data();    

      Role_List[i].setRole_Name(csvColumnList[0]);
      
      for(int a = 0; a < 3; a++){
        Role_List[i].setRole_Stat_Increase(Integer.parseInt(csvColumnList[a + 1]), a);
      }
      

      
      

    }

    
  }
}