public class Scroll
{

  public void processScroll(String font, String text, boolean enter){

    //This sets the font
    System.out.print(font);

    //This prints out a letter, then pause for a few milliseconds
    for(int i = 0; i < text.length(); i++){
      System.out.print(text.charAt(i));

      try{
        Thread.sleep(15); //15 milliseconds between characters
      }
      catch(InterruptedException ex){
        Thread.currentThread().interrupt();
      }
    }

    //This takes a new line if required
    if(enter == true){
      System.out.println("");
    }
    
  }
}
