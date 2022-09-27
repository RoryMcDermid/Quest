public class AuthorsNotes
{

  Scroll S = new Scroll();

  //This is here because quite a few things are nods to or references from things that may be copyrighted, and there are some characters who are based on real life people
  public void processAuthorsNotes(){
    S.processScroll("\033[0m", "This game is designed purely for comedy and enjoyment, this game makes no claim of ownership to any copyrights of any property that is mentioned in this game and any copyright is used under the fair use act. Any person or people mentioned or referenced in this game are referenced purely for comedic purposes.", true);
  }
}