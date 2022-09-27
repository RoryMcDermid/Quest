import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;



public class Leaderboard {
    
    private String USERNAME = "vk0XnsYUXl";
    private String PASSWORD = "IrkLIpyhl6";
    private String CONNECTION_URL = "jdbc:mysql://remotemysql.com/vk0XnsYUXl";
    
    

    
    public void processLeaderboard()  throws SQLException {
        
        try{

          // Class.forName("com.mysql.jdbc.Driver");

          Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
          
          
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * FROM Characters");
          
          while(rs.next()){
            StringBuilder build = new StringBuilder();
            build.append("Characters" + ": ").append(rs.getInt("ID"))
              .append(": ").append(rs.getString("Name"))
              .append(" ").append(rs.getInt("Level"))
              .append(" ").append(rs.getInt("Class"));
            System.out.println(build.toString());
          }
        
        }
        catch (SQLException a){
          System.err.println(a);
        }
        catch (ClassNotFoundException e) {
          e.printStackTrace();
        }


        //NewClass N = new NewClass();
        //N.processNewClass();
        
    }

    

    
    
}
