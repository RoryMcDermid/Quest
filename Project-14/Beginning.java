import java.util.*;


public class Beginning
{

  Scanner keyboard = new Scanner(System.in);


  Scroll S = new Scroll();
  RoleInitiate R = new RoleInitiate();



  private Player_Data PlayerInfo;
  private Weapon_Data Weapon[];
  private Role_Data Role[];
  

  public Player_Data processBeginning(Player_Data PlayerInput, Weapon_Data[] WeaponInput){

    PlayerInfo = PlayerInput;
    Weapon = WeaponInput;
    Role = R.processRoleInitiate("Player/Role/Role.csv");
    



    ChooseRole();
    ChooseWeapon();
    ChooseDifficulty();
    ChooseName();


    String Shop = EnterTown();

    

    if(Shop.equalsIgnoreCase("1")){
      Baker();
    }
    else if(Shop.equalsIgnoreCase("2")){
      Sheriff();
    }



    return PlayerInfo;
  }

  //This is the code for choosing your role
  public void ChooseRole(){

    S.processScroll("\033[0m", "You wake on a paved road in the desert, with no knowledge of anything, not even your name.", true);

    S.processScroll("\033[0m", "Beside you is a tall man with short brown hair, he has a square of grey metal on the side of his face, he smiles at you and says: ", true);

    S.processScroll("\033[34m", "\"Hello there, my young adventurer, you seem lost, there is a town, it is 5 miles that way, they are good people.\"", true);

    S.processScroll("\033[0m", "The man looks off into the distance, then says: ", false);

    S.processScroll("\033[34m", "\"A storm is brewing on the horizon, my young adventurer, you must choose a path if you are to withstand it.\"", true);

    S.processScroll("\033[0m", "Which class do you wish to choose?", true);

    

    //This is to print out all of the roles, this will handle more roles being added
    for(int i = 0; i < Role.length; i++){
      S.processScroll("\033[35m", "Press " + (i+1) + " to pick " + Role[i].getRole_Name() + "", true);
    }

    String input = "";

    int whilefin = 0;
    
    //This is to check if the player has picked their role and waits if they haven't
    while(whilefin == 0){
      
      input =  keyboard.next();

      for(int i = 0; i < 4; i++){
        if(input.equalsIgnoreCase("" + (i + 1))){
          S.processScroll("\033[0m", "The man smiles and says:", true);
          S.processScroll("\033[34m", "\"You have chosen to be a " + Role[Integer.parseInt(input) - 1].getRole_Name() +", stay true to your choice, and you shall reap the rewards\"", true);
          whilefin = 1;
        }
      }
      if(whilefin == 0){

        S.processScroll("\033[0m", "The man looks at you and says:", true);
        S.processScroll("\033[34m", "\"My time here grows short, make your choice adventurer.\"", true);

      }
    }


    //This sets the player's role
    PlayerInfo.setRole(Role[Integer.parseInt(input) - 1]);


    
  }

  //This is the code for choosing your weapon
  public void ChooseWeapon(){

    S.processScroll("\033[0m", "The man thinks for a second before speaking again: ", true);

    S.processScroll("\033[34m", "\"You will need a weapon to defend yourself, what weapon style to you wish to choose?\"", true);

    S.processScroll("\033[0m", "The air grows still, as if the universe is waiting for your decision", true);

    S.processScroll("\033[0m", "A crack appears in reality, and out of it comes a very profound voice: ", true);

    S.processScroll("\033[45m", "Choose 1 if you wish to follow the martial path, cleave your enemies in two with blades and crush them with your hammers?", true);

    S.processScroll("\033[45m", "Choose 2 if you wish to follow the path of a slinger, destroy your foes from range with stones, freeze and burn them with the mystical might of a flung scone?", true);

    S.processScroll("\033[45m", "Choose 3 if you wish to follow the route of the fonts, turn rivers to destroy armies and tear the very fabric of reality apart with a single word?", true);

    S.processScroll("\033[1m", "CHOOSE ADVENTURER, CHOOSE YOUR PATH", true);

    String input =  keyboard.next();
    int whilefin = 0;

    System.out.print("\033[0m");

    //This is to check if the player has picked their weapon and waits if they haven't
    while(whilefin == 0){
      for(int i = 0; i < Weapon.length; i++){
        if(input.equalsIgnoreCase("" + (i + 1))){
          S.processScroll("\033[1m\033[44m", "You have chosen to wield a " + Weapon[i + 1].getWeapon_Name() + " follow your path and it will lead you to victory", true);
          whilefin = 1;
        }
      }
      if(whilefin == 0){
        S.processScroll("\033[0m", "The voice waits for you to choose", true);
        input =  keyboard.next();
      }
      
    }

    //Maybe put this in Weapon_Data?
    if(input.equalsIgnoreCase("1")){
      S.processScroll("\033[0m", "Out of the tear in reality, a simple bronze sword falls out, you catch it before it hits the ground and you are suddenly filled with knowledge of how to wield any martial weapon.", true);
      PlayerInfo.setWeapon(Weapon[1]);
    }
    else if(input.equalsIgnoreCase("2")){
      S.processScroll("\033[0m", "Out of the tear in reality, a sling, made of snakeskin falls out, you catch it before it hits the ground and you are suddenly filled with knowledge of how to use any sling or ranged weapon you may find, you also gain glimpses of insight into how to sling baked goods and the raw power that it can grant you.", true);
      PlayerInfo.setWeapon(Weapon[2]);
    }
    else if(input.equalsIgnoreCase("3")){
      S.processScroll("\033[0m", "Out of the tear in reality, a light floats out, it flies toward you and hits you in your chest, near your heart. As soon as the orb touches you, your mind is filled with the arcane secrets that are required to sling fonts, and it hints as to greater secrets beyond.", true);
      PlayerInfo.setWeapon(Weapon[3]);
    }



  }

  //This is the code for choosing how difficult the game will be
  public void ChooseDifficulty(){

    S.processScroll("\033[0m", "Once you have chosen your weapon, the man turns to you and says: .", false);

    S.processScroll("\033[34m", "\"I have little time left here, there are many more slides that need help, I have done all that needs done here and thus I shall leave, but before I go, you must decide how hard your journey must be.\"", true);


    //This is to ensure that the input is an int
    boolean num = false;
    String input = "";
    int diff = -1;

    while(num == false){
      input = keyboard.next();
      try {
        diff = Integer.parseInt(input);
        if(diff >= 1){
          num = true;
        }
      }
      catch( Exception e ) {
        num = false;
      }
    }


    //This sets the difficulty once it is sure that the input is an int
    PlayerInfo.setDifficulty(diff);

  }

  //This is the code for entering your name
  public void ChooseName(){
    
    S.processScroll("\033[0m", "Once you have chosen your path, the man smiles and begins to fade, you suddenly realise how many questions you have, you will only have time to ask one, what will it be?", true);

    S.processScroll("\033[35m", "Press 1 to ask what your name is", true);

    S.processScroll("\033[35m", "Press 2 to ask who he is", true);

    S.processScroll("\033[35m", "Press 3 to ask why you are here", true);

    String input =  keyboard.next();

    if(input.equalsIgnoreCase("1")){
      S.processScroll("\033[34m", "\"You are not who you once were, you are who you decide to be.\"", true);
    }
    else if(input.equalsIgnoreCase("2")){
      S.processScroll("\033[34m", "\"I am a beacon to those who are lost, a guiding voice to those without purpose, and for when thing seem most dark, I am there to remind them that all they need do, is turn on the light. I have many names, but above all, I am the Icon.\"", true);
    }
    else if(input.equalsIgnoreCase("3")){
      S.processScroll("\033[34m", "\"A great mind has turned against the peace that this world lives in, it hungers for knowledge, you may be the only chance that this world has of stopping it\"", true);
    }
    else{
      S.processScroll("\033[0m", "You don't think of anything in time", true);
    }
    S.processScroll("\033[0m", "After the man fades away, you decide to walk along the road towards the direction of the town, as you walk, you think of what your name should be", true);
    S.processScroll("\033[35m", "What, is, your, name?", true);

    if((keyboard.next().length() >= 20) || (keyboard.next().length() < 1))

    PlayerInfo.setName(keyboard.next());

    //This is a reference to an event early in beta testing
    if(PlayerInfo.getName().equalsIgnoreCase("what")){
      S.processScroll("\033[0m", "As you think of your new witty name, a crack appears in reality, and out of it comes a very profound voice: ", true);

      S.processScroll("\033[45m", "\"Hillarious Scott\"", true);
    }



    
  }

  //This is the code for entering the town
  public String EnterTown(){

    S.processScroll("\033[0m", "You walk into the tavern, you go up to the barkeep and ask him where you can get a basic equipment and gear so that you can go out and make money", true);

    S.processScroll("\033[34m", "\"Welcome traveller, if you are wanting someone to go to for adventuring equipment, there are three people that I could suggest.\"", true);

    S.processScroll("\033[0m", "He pours a beer and offers it to you, you say that you have no gold to pay for it but he laughs and says: ", false);

    S.processScroll("\033[34m", "\"This beer is on the house, a new adventurer would help spice up our economy, so it would be good for me to get on your good side eh?\"", true);

    S.processScroll("\033[0m", "He chuckles again before continuing", true);

    S.processScroll("\033[34m", "\"Now where are my manners? My name is Jack and I built this tavern with my own two hands, what is your name?\"", true);

    S.processScroll("\033[0m", "You tell him your name.", true);


    //The ones commented out aren't being done but have been left incase they are one day needed
    S.processScroll("\033[34m", "\"Then welcome " + PlayerInfo.getName() + " to our humble little town, if you wish to get adventuring equipment you could go to the bakers, Mr Scone used to be an adventurer himself and now sells all things ranged and baking.\"", true);

    // S.processScroll("\033[34m", "\"Another person that you could go to would be the priest, he is always willing to teach those who are talented in the ways of the fonts, he even sells things that are useful to those less font aligned.\"", true);


    S.processScroll("\033[34m", "\"You could also go to the town's sheriff, he can always order up standard combat gear from the City for you. You should find him in the Guard Hall near the town square.\"", true);

    // S.processScroll("\033[34m", "\"Now finally, you could speak to the sage, some say that he is the one who discovered our current language, between the third and fourth cataclysms, he is an honest fellow, and can hold his beer.\"", true);

    S.processScroll("\033[0m", "Where will you go for adventuring equipment?", true);
    S.processScroll("\033[0m", "1: Go to the bakers", true);
    S.processScroll("\033[0m", "2: Go to the guard hall", true);


    String input = keyboard.next();

    //The options will be added when they are coded
    while ((!input.equalsIgnoreCase("1")) && (!input.equalsIgnoreCase("2"))){

      S.processScroll("\033[0m", "You sit and continue to drink your beer as you think of where to go", true);
      input = keyboard.next();

    }

    

    S.processScroll("\033[0m", "You get out of your chair, thank Jack for his advice, and decide to go and see ", false);
    if(input.equalsIgnoreCase("1")){
      S.processScroll("\033[0m", "Mr Scone the Baker", true);
    }
    else if(input.equalsIgnoreCase("2")){
      S.processScroll("\033[0m", "The Sheriff", true);
    }
    
    //This tells the game that you have met the barkeep
    PlayerInfo.setPeopleKnown(1, 2);

    return input;

  }

  //This is the code for meeting the baker
  public void Baker(){

    S.processScroll("\033[0m", "You enter the bakery, behind the counter is a tall man with brown hair, he has an eye made of metal and a stained apron, as you walk in he turns to you and says: ", false);
    S.processScroll("\033[34m", "\"Welcome traveller, you look like you have came a long way. Why are you here?\"", true);
    S.processScroll("\033[0m", "You think for a moment about your response", true);
    S.processScroll("\033[35m", "1: Power", true);
    S.processScroll("\033[35m", "2: Adventure", true);
    S.processScroll("\033[35m", "3: Knowledge", true);
    String input = keyboard.next();
    
    //This will effect the story once the story is added
    if (input.equalsIgnoreCase("1")){
      S.processScroll("\033[0m", "The baker chuckles ", false);
      S.processScroll("\033[34m", "\"Ah, you are one who wishes for power, but remember, it is our choices that show who we really are.\"", false);
      S.processScroll("\033[0m", " As he finishes the sentance, a pulse of, something, passes through the air, barely noticable but overwhelmingly powerful at the same time.", true);
    }
    else if (input.equalsIgnoreCase("2")){
      S.processScroll("\033[34m", "\"Ah, you are one who travels for the thrill, I remember when I was like that,\"", false);
      S.processScroll("\033[0m", " The baker seems to drift off for a second before continuing to speak ", false);
      S.processScroll("\033[34m", "\"a head full of stories and a heart yearning for adventure.\"", true);
    }
    else if (input.equalsIgnoreCase("3")){
      S.processScroll("\033[34m", "\"Ah, you are one who thirsts for knowledge, to throw off the reins of obliviousness, a noble goal, but a difficult one.\"", true);
    }
    else{
      S.processScroll("\033[34m", "\"Ah, you are one who travels without direction, searching for one,\"", false);
      S.processScroll("\033[0m", " The baker seems to drift off for a second before continuing to speak ", false);
      S.processScroll("\033[34m", "\"I remember when I was like you, a head full of stories and a wish to seek what was beyond the horizon.\"", true);
    }

    S.processScroll("\033[34m", "\"So, traveller, what is your name?\"", true);
    
    
    S.processScroll("\033[0m", "You tell him your name", true);
    

    S.processScroll("\033[34m", "\"Nice to meet you " + PlayerInfo.getName() + ", my name is Scone, Paddy Scone, professed baker and high level scone slinger. I see that you are desperate to go and adventure, once you have leveled up, I will teach you how to sling scones, not just simple rocks\"", true);

    S.processScroll("\033[0m", "After your talk with Mr Scone, you head out into the desert to seek ", false);

    if (input.equalsIgnoreCase("1")){
      
      S.processScroll("\033[0m", "power", true);
    }
    else if (input.equalsIgnoreCase("2")){
      S.processScroll("\033[0m", "adventure", true);
      
    }
    else if (input.equalsIgnoreCase("3")){
      S.processScroll("\033[0m", "knowledge", true);
    }
    else{
     
      S.processScroll("\033[0m", "meaning", true);
    }
    
    //This tells the game that the player has met the baker
    PlayerInfo.setPeopleKnown(1, 0);
    
  }

  //This is the code for meeting the sheriff
  public void Sheriff(){

    S.processScroll("\033[0m", "As you are approaching the Guard Hall, two of the Town's Watch stop you. the one on your left begins to speak: ", true);

    S.processScroll("\033[34m", "\"Greetings traveller, what brings you to the Guard Hall?\"", true);


    
    S.processScroll("\033[0m", "You think for a moment about your response", true);
    S.processScroll("\033[35m", "1: Equipment", true);
    S.processScroll("\033[35m", "2: Adventure", true);
    S.processScroll("\033[35m", "3: Work", true);
    String input = keyboard.next();
    int peace = 0;

    if (input.equalsIgnoreCase("1")){
      S.processScroll("\033[0m", "The right guard chuckles ", false);
      S.processScroll("\033[34m", "\"Well, if you can get the Sheriff to order any special gear, I will be impressed\"", true);
      S.processScroll("\033[0m", "The left guard begins to speak as the right guard stops for a breath: ", false);
      S.processScroll("\033[34m", "\"If you do manage to get any special gear ordered in, see if you might be able to get us some as well, we would greatly appreciate it.\"", true);
      //If you do buy them gear, they may save you from death, once the storyline is added
    }
    else if (input.equalsIgnoreCase("2")){
      S.processScroll("\033[0m", "The right guard chuckles ", false);
      S.processScroll("\033[34m", "\"Well, if you see any guards in danger, we would appreciate it if you help them, we don't have much in the way of skill or equipment, what with us being in a border town and all.\"", true);
      S.processScroll("\033[0m", "The left guard begins to speak as the right guard stops for a breath: ", false);
      S.processScroll("\033[34m", "\"The sheriff does make sure that our gear is up to standard, but better gear never hurts.\"", true);
      //Add in quests to help guards in peril, once the storyline is added 
    }
    else if (input.equalsIgnoreCase("3")){
      S.processScroll("\033[0m", "The right guard chuckles ", false);
      S.processScroll("\033[34m", "\"I'm sure that the Sheriff would pay resonably for another keeper of the peace, you would need to speak with him about the specifics.\"", true);
      S.processScroll("\033[0m", "The left guard begins to speak as the right guard stops for a breath: ", false);
      S.processScroll("\033[34m", "\"The job isn't hard considering that we do most of the peace keeping around here, you would just need to watch for any lawbreakers while you are adventuring.\"", true);
      peace = 1;
      //This allows you to get the peacekeeper badge which will effect the storyline

    }
    else{
      S.processScroll("\033[0m", "The right guard chuckles ", false);
      S.processScroll("\033[34m", "\"You seem a bit tongue tied, but you seem like a good sort.\"", true);
      S.processScroll("\033[0m", "The left guard begins to speak as the right guard stops for a breath: ", false);
      S.processScroll("\033[34m", "\"If you ain't, Law will see right through you.\"", true);
    }

    PlayerInfo.setPeopleKnown(1, 4);

    S.processScroll("\033[0m", "After a quick conversation with the guards, you enter the Guard Hall, inside there are 3 doors, marked Equipment, Law and PR, you knock on the door that says Law.", true);

    S.processScroll("\033[34m", "\"Come in.\"", true);

    S.processScroll("\033[0m", "You enter an office with rather spartan furnishings, behind the desk is a man in full armour doing pullups. As you enter he stops and looks at you.", true);

    S.processScroll("\033[34m", "\"So you are the new adventurer that my guards met,", false);

    

    //This allows the player to choose if they wish to be a peacekeeper
    if(peace == 1){
      S.processScroll("\033[34m", "would you be interested in being a fellow Peace Keeper?\"", true);
      S.processScroll("\033[35m", "1: Yes", true);
      S.processScroll("\033[35m", "2: No", true);
      input = keyboard.next();

      if(input.equalsIgnoreCase("1")){
        S.processScroll("\033[34m", "\"Fantastic, all you need to do is uphold the law while you are around, and you will be fine.", true);
        PlayerInfo.setBadge(1, 0);
      }
      else{
        S.processScroll("\033[34m", "\"Well, thats too bad I suppose I will need to find more recruits elsewhere.", true);
      }

      S.processScroll("\033[0m", "He takes a breath before continuing to speak.", true);

      S.processScroll("\033[34m", "\"So then,", false);

    }

    S.processScroll("\033[34m", " if you are you are wanting gear, you can come and purchase some from me, I will be buying some in within the near future. If you are wanting adventure, just walk out of the town and go for a wander, the adventure will find you.\"", true);

    S.processScroll("\033[0m", "After he finishes speaking, he goes back to doing pullups, you take this as a dismissal and head out to seek adventure, knowledge and power in equal measure. You nod to the guards as you leave the building and head out into the desert,", false);

    if(PlayerInfo.getBadge(0) == 1){
      S.processScroll("\033[0m", " for you are a Peace Keeper, and you shall spread your peace to all whom you meet.", true);
    }
    else{
      S.processScroll("\033[0m", " for glory awaits you amongst these dunes.", true);
    }

    PlayerInfo.setPeopleKnown(1, 3);

    
    
  }

  

  
}