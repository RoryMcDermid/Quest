import java.util.*;

public class Town{

  Scanner keyboard = new Scanner(System.in);

  Scroll S = new Scroll();


  private Player_Data PlayerInfo;
  private int StageUp;
  private int ShopsOpen;

  public Player_Data processTown(Player_Data PlayerIn, int StageIn, int Rotations){

    PlayerInfo = PlayerIn;
    StageUp = StageIn;
    int ShopsEntered = 0;  
    int ShopLeave = 0;

    

    S.processScroll("\033[0m", "As the day draws to a close, you head back to town, as you enter you consider what shops you want to go to.", true);
    
    //This decides which shops are open
    if (Rotations < 2){
      ShopsOpen = 1;
    }
    if (Rotations >= 2){
      ShopsOpen = 2;
    }

    //This allows you to enter 3 shops, or the amount of shops, or you decide to not shop
    while ((ShopsEntered < 3) && (ShopsEntered < ShopsOpen) && (ShopLeave == 0)){
      ShopLeave = 0;
      ShopLeave = Shops(ShopsEntered);
      ShopsEntered++;
    }

    //This plays the evening, putting in rotations to allow for advances in the basic story line that is there to happen
    Evening(Rotations);
    
    //This plays the morning
    Morning();
    

    return PlayerInfo;


  }

  public int Shops(int Entered){

    int Leave = 0;

    while(Leave == 0){

      //This is the enterable number of shops
      int Posscap = 0;

      if (ShopsOpen >= 1){
        
        S.processScroll("\033[35m", "Press 0 to finish shopping", true);  //This is to finish shopping
        
        S.processScroll("\033[35m", "Press 1 to enter the herbalists", true);  //This is for entering the herbalists
        
        
        Posscap = 1;
      }
      if (ShopsOpen >= 2){
        S.processScroll("\033[35m", "Press 2 to enter the alchemists", true);  //This is for entering the alchemists

        Posscap = 2;
      }

      String Input = keyboard.next();

      //input validation is used to check if the input is correct or not
      int ValidInput = InputTest(Input, Posscap);


      //This is for if the input is invalid or is zero
      if (ValidInput == 0){
        Leave = 2;
        if (Entered == 0){
          S.processScroll("\033[0m", "You decide not to shop tonight.", true);
        }
        else{
          S.processScroll("\033[0m", "You decide to finish shopping for tonight.", true);
        }
      }

      if (Input.equalsIgnoreCase("1")){
        Herbalists();
      }
      else if (Input.equalsIgnoreCase("2")){
        Alchemists();
      }


      Leave = 1;

    }

    return Leave;
  }

  //The herbalists increases the player's current health
  public void Herbalists(){

    //maybe give character personality
    S.processScroll("\033[0m", "You enter the herbalists and see a person, he greets you with a smile and says:  ", false);
    
    S.processScroll("\033[34m", "\"Welcome traveler, what do you wish to purchase today?\"", true);

    int leave = 0;
    int purchasenum = 0;


    
    while ((leave == 0) && (purchasenum != 3)){

      if (PlayerInfo.getGold() < 10){
        //This is for when the player has too little gold
        if (purchasenum == 0){
          S.processScroll("\033[33m", "Your current gold is: " +PlayerInfo.getGold(), true);
        }
        S.processScroll("\033[0m", "You don't have enough gold to buy anything.", true);
        leave = 1;
      }
      if (PlayerInfo.getHealth() + 10 > PlayerInfo.getMax_Health()){
        //This is for when the player's health is too close to their max health to use any potions
        if (purchasenum == 0){
          S.processScroll("\033[36", "mHealth = " + PlayerInfo.getHealth(), true);
        }
        S.processScroll("\033[0m", "Your health is too high to use any of the herbs.", true);
        leave = 1;
      }

      

      if ((PlayerInfo.getGold() >= 10)  && (PlayerInfo.getHealth() + 10 <= PlayerInfo.getMax_Health())){

        S.processScroll("\033[33m", "Your current gold is: " + PlayerInfo.getGold(), true);
        S.processScroll("\033[35m", "Press 0 to leave", true);
        S.processScroll("\033[35m", "Press 1 to buy a healing herb for 10 gold", true);

        
        if((PlayerInfo.getGold() >= 20) && (PlayerInfo.getHealth() + 20 <= PlayerInfo.getMax_Health())){

          S.processScroll("\033[35m", "Press 2 to buy a greater healing herb for 20 gold", true);

        }
        
        String purchase = keyboard.next();
        if (purchase.equalsIgnoreCase("0")){
          leave = 1;
        }
        else if (purchase.equalsIgnoreCase("1")){
          PlayerInfo.setGold(PlayerInfo.getGold() - 10);
          PlayerInfo.setHealth(PlayerInfo.getHealth() + 10);
          S.processScroll("\033[34m", "\"Thank you for your purchase\"", true);
          S.processScroll("\033[33m", "Gold = " + PlayerInfo.getGold(), true);
          S.processScroll("\033[36m", "Health = " + PlayerInfo.getHealth(), true);
        }
        else if (purchase.equalsIgnoreCase("2")){
          if ((PlayerInfo.getHealth() + 20 > PlayerInfo.getMax_Health()) || (PlayerInfo.getGold() - 20 < 0)){
            S.processScroll("\033[31m", "You stand still and do nothing", true);
          }
          else{
          PlayerInfo.setGold(PlayerInfo.getGold() - 20);
          PlayerInfo.setHealth(PlayerInfo.getHealth() + 20);
          S.processScroll("\033[34m", "\"Thank you for your purchase\"", true);
          S.processScroll("\033[33m", "Gold = " + PlayerInfo.getGold(), true);
          S.processScroll("\033[36m", "Health = " + PlayerInfo.getHealth(), true);
          }
        }
        else {
          S.processScroll("\033[0m", "You stand still and do nothing", true);
        }
      }
      

      
      if ((purchasenum == 3) || (PlayerInfo.getGold() < 10)){
        leave = 1;
      }
      purchasenum++;
    }
    if (purchasenum == 3){
      S.processScroll("\033[34m", "\"That is all I have for sale today, farewell traveller and may the Icon guide you\"", true); 
    }
    else {
      S.processScroll("\033[34m", "\"Farewell traveller and may the Icon guide you\"", true); 
    }
    System.out.println(""); //To create a line underneath

  }

  //The alchemists increases the player's max health
  public void Alchemists(){

    int Leave = 0;
    int purchasenum = 0;

    while((Leave == 0)  && (purchasenum < 1)){

      if (PlayerInfo.getGold() < 40){
        //This is for when the player has too little gold
        if (purchasenum == 0){
          S.processScroll("\033[33m", "Your current gold is: " +PlayerInfo.getGold(),true);
        }
        S.processScroll("\033[0m", "You don't have enough gold to buy anything.", true);
        Leave = 1;
      }
      

      

      if (PlayerInfo.getGold() >= 40){
      
        S.processScroll("\033[33m", "Your current gold is: " + PlayerInfo.getGold(), true);
        S.processScroll("\033[35m", "Press 0 to leave", true);
        S.processScroll("\033[35m", "Press 1 to buy a health increasing potion for 40 gold", true);

        
        if(PlayerInfo.getGold() >= 70){
        
          S.processScroll("\033[35m", "Press 2 to buy a greater health increasing potion for 70 gold", true);

        }
        
        String purchase = keyboard.next();
        if (purchase.equalsIgnoreCase("0")){
          Leave = 1;
        }
        else if (purchase.equalsIgnoreCase("1")){
          PlayerInfo.setGold(PlayerInfo.getGold() - 40);
          PlayerInfo.setMax_Health(PlayerInfo.getMax_Health() + 10);
          PlayerInfo.setHealth(PlayerInfo.getHealth() + 10);

          S.processScroll("\033[34m", "\"Thank you for your purchase\"", true);
          S.processScroll("\033[33m", "Gold = " + PlayerInfo.getGold(), true);
          S.processScroll("\033[36m", "Max Health = " + PlayerInfo.getMax_Health(), true);
          S.processScroll("\033[36m", "Health = " + PlayerInfo.getHealth(), true);
        }
        else if (purchase.equalsIgnoreCase("2")){
          if (PlayerInfo.getGold() - 70 < 0){
            S.processScroll("\033[31m", "You stand still and do nothing", true);
          }
          else{
          PlayerInfo.setGold(PlayerInfo.getGold() - 70);
          PlayerInfo.setMax_Health(PlayerInfo.getMax_Health() + 20);
          PlayerInfo.setHealth(PlayerInfo.getHealth() + 20);

          S.processScroll("\033[34m", "\"Thank you for your purchase\"", true);
          S.processScroll("\033[33m", "Gold = " + PlayerInfo.getGold(), true);
          S.processScroll("\033[36m", "Max Health = " + PlayerInfo.getMax_Health(), true);
          S.processScroll("\033[36m", "Health = " + PlayerInfo.getHealth(), true);
          }
        }
        else {
          S.processScroll("\033[0m", "You stand still and do nothing", true);
        }
      }
      

      
      if ((purchasenum == 3) || (PlayerInfo.getGold() < 40)){
        Leave = 1;
      }
      purchasenum++;
    }
    if (purchasenum == 3){
      S.processScroll("\033[34m", "\"That is all I have for sale today, farewell traveller and may the Icon guide you\"", true); 
    }
    else {
      S.processScroll("\033[34m", "\"Farewell traveller and may the Icon guide you\"", true); 
    }
    System.out.println(""); //To create a line underneath

  }


  public void Evening(int Rotations){

    if (Rotations == 1){
      S.processScroll("\033[0m", "After finishing shopping, you go to the tavern to see if you can get a room, when you get there, you tell people tales of your adventures, when you finish telling stories and ask the bartender how much a room is he laughs and says: ", false);
      if(PlayerInfo.getPeopleKnown(2) == 0){
        S.processScroll("\033[34m", "\"My friend, since you have entertained my customers, your room and food are free, my name is Jack and I built this tavern with my own two  hands, what is your name friend?\"", true);
        S.processScroll("\033[0m", "You tell Jack your name", true );
        S.processScroll("\033[34m", "\"Well met " + PlayerInfo.getName() +"\"", true);
        PlayerInfo.setPeopleKnown(1, 0);
      }
      else if (PlayerInfo.getPeopleKnown(2) == 1){
        S.processScroll("\033[34m", "\"" + PlayerInfo.getName() +"my good friend, you have entertained my customers and caused them to stay and drink longer than usual, as long as you keep  doing this, your room and food are free.\"", true);
      }
    }
    else{
      S.processScroll("\033[0m", "After finishing shopping, you go to the tavern to get your room, when you get there, you tell people tales of your adventures.", true);

      int Shark_Kills = 0;

      //This checks how many times that the player has killed shark based creatures, for the shark killing quest
      for(int p = 0; p < PlayerInfo.getPrefixLength(); p++){
        for(int t = 0; t < PlayerInfo.getTypeLength(); t++){
          for(int s = 0; s < PlayerInfo.getSuffixLength(); s++){
            Shark_Kills = Shark_Kills + PlayerInfo.getKills(p, t, 1, s);
          }
        }
      }

      
      //This rewards the player for killing sharks by setting it so they are healed every morning
      if ((PlayerInfo.getPeopleKnown(2) == 1) && (Shark_Kills >= 3)){
        S.processScroll("\033[0m", "After you finish your discussion, Jack beckons you over to the bar, when you get there he begins to speak: .", false);
        S.processScroll("\033[34m", "\"" + PlayerInfo.getName() +" my good friend, I cannot thank you enough for the work that you have been doing, with the shark numbers thinned, supplies are getting back into our town, those steam wagons are great but the sharks can destroy the bronze shells in moments. As a reward for the ongoing removal of sharks in the area, I will make sure that healing herbs are added to your meals, just to assist with your adventuring as it is a boon to us all.\"", true);
        PlayerInfo.setPeopleKnown(2, 2);
      }

    }


    

    
    //maybe have varying food here?
    //maybe describe the room
    S.processScroll("\033[0m", "After eating your dinner, you head up to your room for a good night's sleep", true);
    

  }

  public void Morning(){

    S.processScroll("\033[0m", "After the night's sleep, you head down into the bar, Jack greets you and you have a nice chat with him over breakfast", true);

    //This tells the player that sharks are now to be killed
    if (StageUp == 2){
      S.processScroll("\033[0m", "During the chat, Jack mentions about how the different species of sand shark are beginning to trouble shipments into town and wondered if you would go out further to look for them.", true);
      S.processScroll("\033[0m", "You agree to help since Jack is already providing food and shelter for you free of charge, and the bodies of sand sharks can sell for a good amount of money as well", true);
    }

    //This tells the player that snake caves are now a thing that they will fight
    if (StageUp == 3){
      S.processScroll("\033[0m", "During the chat, Jack mentions about the fact that some of the adventurers have come across caves with sizeable amounts of monsters in them.", true);
      if (PlayerInfo.getPeopleKnown(0) == 0){
        S.processScroll("\033[0m", "The village baker overhears your conversation about monsters and comes over to chat: ", false);
        S.processScroll("\033[34m", "\"Hello my friends, I couldn't help overhearing you talking about monster dens, I used to be the one to clear them out, but I am no longer able to do so, thus they have been growing more frequent by the day.\"", true);
        S.processScroll("\033[0m", "As you chat you find out that his name is Paddy Scone, and you tell him that your name is " + PlayerInfo.getName(), true);
      }
      else if (PlayerInfo.getPeopleKnown(0) == 1){
        S.processScroll("\033[0m", "Paddy overhears your conversation about monsters and comes over to chat: ", false);
        S.processScroll("\033[34m", "\"Hello there Jack, I couldn't help overhearing you talking about monster dens with our good friend " + PlayerInfo.getName() +", I used to be the one to clear them out, but I am no longer able to do so, thus they have been growing stronger and more frequent by the day.\"", true);
      }

      S.processScroll("\033[0m", "As the conversation draws to a close, you realise that Paddy has not explained why he cannot clear out monster dens. You consider asking the baker why he is unable to clear out monster dens", true);
      S.processScroll("\033[35m", "Press 0 to leave it", true);
      S.processScroll("\033[35m", "Press 1 to ask him", true);
      int input = keyboard.nextInt();
      if ((input == 0) || (input != 1)){
        S.processScroll("\033[0m", "You say nothing", true);
      }
      else{
        S.processScroll("\033[0m", "You ask Paddy about why he cannot clear out the dens", true);
        S.processScroll("\033[34m", "\"Well, I was in a large battle, I managed to get us the victory, but at a great cost, I have been becoming slowly more allergic to sand by the day, it has now gotten to the point where I must stay in town or on main roads to avoid triggering my allergy.\"", true);
      }
      S.processScroll("\033[0m", "After the conversation you promise Paddy that you will be on the lookout for any monster dens during your day's adventures.", true);
    }

    //This tells the player that there is a charles spawn to fight
    if (StageUp == 4){
      S.processScroll("\033[0m", "During the chat, you realise that a group of regulars is not there today, you ask Jack about it.", true);
      S.processScroll("\033[34m", "\"That group is usually here, they may have left early, or they might have gone to the temple to get blessed for the day ahead, the guards will know if they have left, and the priest will be able to tell you if they went to get blessed.\"", true);

      S.processScroll("\033[0m", "You wolf down your meal, ", false);
      if (PlayerInfo.getPeopleKnown(2) < 2){
        S.processScroll("\033[0m", "and you set out to find the disappearances.", true);
      }
      else if (PlayerInfo.getPeopleKnown(2) == 2){
        S.processScroll("\033[0m", "barely tasting the healing herbs, you leave to find out where the regulars have gone.", true);
      }

      S.processScroll("\033[0m", "Where will you go:", true);

      S.processScroll("\033[35m", "Press 1 to go and find the guards.", true);  

        
      S.processScroll("\033[35m", "Press 2 to go and speak to the priest", true); 

      String Input = keyboard.next();

      if (Input.equalsIgnoreCase("1")){
        S.processScroll("\033[0m", "You find the guards at the gate, as you walk up to them, the left one begins to speak: ", false);
        S.processScroll("\033[34m", "\"Well it looks like it is our ", false);
        if(PlayerInfo.getBadge(0) != 0){
          S.processScroll("\033[34m", "fellow peacekeeper.\"", true);
        }
        else{
          S.processScroll("\033[34m", "local adventurer.\"", true);
        }
        if(PlayerInfo.getPeopleKnown(4) > 0){
          S.processScroll("\033[0m", "The right one begins to speak: ", false);
          S.processScroll("\033[34m", "\"What can we do for you today?\"", true);

          S.processScroll("\033[0m", "When you tell them about the missing tavern regulars they curse simultaneously", false);
          S.processScroll("\033[34m", "\"They had said that they saw something wierd a few days ago and were going to check it out, they never returned yesterday, we will need to mention this to Law. Speak to the priest, he should be able to tell you what happened.\"", true);
          PlayerInfo.setPeopleKnown(2, 4);
        }
        else{
          S.processScroll("\033[0m", "The right one begins to speak: ", false);
          S.processScroll("\033[34m", "\"What is the problem citizen\"", true);

          S.processScroll("\033[0m", "When you tell them about the missing tavern regulars they look at each other with worried looks on their faces", false);
          S.processScroll("\033[34m", "\"They left yesterday and didn't return, you ask the priest about what happened, and we will tell Law.\"", true);
          PlayerInfo.setPeopleKnown(2, 4);
        }
      }
      //The player must see the priest, so meeting the guards is just an additional extra
      
      S.processScroll("\033[0m", "You enter the church, standing behind the alter is a haggard looking man with blonde hair, as you walk in, he begins to speak: ", false);
      S.processScroll("\033[34m", "\"My child, you come at a trying time, some souls have been stolen from the cycle of reincarnation, and I cannot see why it happened, I have a bad feeling about this. I know that you have not been here for long, but I must ask you to go and find what happened to these men.\"", true);
      S.processScroll("\033[0m", "You decide that you will find them, it is only the right thing to do, as you are ", false);

      if(PlayerInfo.getBadge(0) != 0){
        S.processScroll("\033[0m", "a peacekeeper, it is your job to find out what happened.", true);
      }
      else{
        S.processScroll("\033[0m", "an adventurer, they would do the same for you.", true);
      }

      S.processScroll("\033[0m", "You leave the church, heading towards the feeling of unease that you feel, and where you will find the missing adventurers.", true);
      
    }

    if (StageUp != 4){
      if (PlayerInfo.getPeopleKnown(2) < 2){
        S.processScroll("\033[0m", "You finish up your meal and head out for the day's  adventures", true);
      }
      else if (PlayerInfo.getPeopleKnown(2) == 2){
        S.processScroll("\033[0m", "You finish up your meal, feeling the reinvigorating power   of the healing herbs, and head out for the day's adventures", true);
        if (PlayerInfo.getHealth() + 15 <= PlayerInfo.getMax_Health()){
          PlayerInfo.setHealth(PlayerInfo.getHealth() + 15);
        }
        else {
          PlayerInfo.setHealth(PlayerInfo.getMax_Health());
        }

        S.processScroll("\033[36m", "Health = " + PlayerInfo.getHealth(), true);
      }
    }

  }


  public int InputTest(String input, int posscap){

    //this is to test if an input is valid or not
    int inputval = 0;
    String inputs[] = new String[posscap];

    //This makes a string list of every valid input
    for (int i = 0; i < posscap; i++){
      inputs[i] = Integer.toString((i + 1));
    }

    //This checks the input against the list of valid inputs until it is found or the list is finished
    for (int a = 0; a < posscap; a++){
      if (inputs[a].equalsIgnoreCase(input)){
        inputval = 1;
      }
    }

    //This returns 0 if the input is not valid and 1 if it is valid
    return inputval;
  }
}