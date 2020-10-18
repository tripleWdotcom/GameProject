package com.company;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Game {
    Scanner scan=new Scanner(System.in);
    ArrayList<Player> playerList=new ArrayList<Player>();
    Store store = new Store();

    boolean win= false;


    public Game(){
        System.out.println("=============== WELCOME ==============");
        System.out.println("How many players? (1-4)");
        while (!scan.hasNextInt()) {
            System.out.println("Insert valid number 1-4");
            scan.nextLine();
        }
        var nPlayers=scan.nextInt();
        if(nPlayers <1)
            System.out.println("No one will play? then BYE BYE \n EXITING GAME...");
        if(nPlayers>4)
            System.out.println("I said up to just 4 players, but ok Lets go bananas");
        for(int i=0 ; i<nPlayers;i++) {
            System.out.printf("Type Name of Player %d : ", i+1);
            var name = scan.next();
            playerList.add(new Player(name));
            System.out.printf(playerList.get(i).name + " - will be Player %d.%n",i+1);
        }

        System.out.println("How many rounds do you want to play? (5-30)");
        while (!scan.hasNextInt()) {
            System.out.println("Insert valid number 5-30");
            scan.nextLine();
        }
        var nRounds=scan.nextInt();
        System.out.println("Processing...");
        delay();
        clear();

        var roundCounter=0;
        while (!win && roundCounter<nRounds){
            for(int i=0;i<playerList.size();i++){
                clear();
                System.out.println("ROUND "+ (roundCounter+1));
                System.out.println("=== "+playerList.get(i).name + " === is your turn to play----------------");
                roundOptions(playerList.get(i));
                //decrease heath animals of player method
            }
            roundCounter++;
        }

    }
    public void roundOptions(Player playerName){
        //System.out.println("=== "+playerName.name + " === is playing----------------");
        System.out.println("You have this amount of money available: " + playerName.money);
        showAnimals(playerName);
        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n1: Buy \n2: Sell \n3: Feed \n4: Mate Animals \n5: something else" +
                "\n---------------------------------------------");
        int opt =scan.nextInt();
        System.out.println("Option "+opt+ " was selected \nProcessing...");
        clear();
        switch (opt) {
            case 1->buyOptions(playerName);
            case 2->sellOptions(playerName.name);
            //case 3->buyOptions(playerName.name);
           // case 4->sellOptions(playerName.name);
        }
        decreaseAnimalsHealth(playerName);



    }
   public void buyOptions(Player playerName){
       System.out.println("=== "+playerName.name + " === is playing----------------");

       System.out.println("Type option" +
               "\n---------------------------------------------" +
               "\n1: Buy Animal \n2: Buy food \n3: something else" +
               "\n---------------------------------------------");
       int opt =scan.nextInt();
       System.out.println("Option "+opt+ " was selected \nProcessing...");
       //delay();
       clear();

       switch (opt) {
           case 1-> buyAnimalsOptions(playerName);
          // case 2->sellOptions(playerName);

       }
   }

    public void buyAnimalsOptions(Player playerName) {
        System.out.println("=== " + playerName.name + " === is playing----------------");
        System.out.println("You have this amount of money available: $" + playerName.money);
        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n1: Dog  $100 \n2: Crocodile $200 \n3: Condor $300 \n4: Unicorn $400 \n5: Rabbit $500 \n6: Stop Buying animals"  +
                "\n---------------------------------------------");
        int opt = scan.nextInt();
        System.out.println("Option " + opt + " was selected \nProcessing...");
        //delay();
        clear();

        try{
            if(opt>=1 &&opt<=5) {
                playerName.haveAnimal.add(store.buyAnimal(playerName, opt));
               // playerName.money-=;
            }
        }
        catch(Exception e){
            System.out.println("Insert valid option (1-5)");
        }
        if(opt==6)
            return;
        buyAnimalsOptions(playerName);

    }
    public void sellOptions(String playerName){
        System.out.println("=== "+playerName + " === is playing----------------");

        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n1: Sell animal \n2: something else" +
                "\n---------------------------------------------");
        int opt =scan.nextInt();
        System.out.println("Option "+opt+ " was selected \nProcessing...");
        delay();
        clear();

        switch (opt) {
          //  case 1->buyOptions(playerName);
            case 2->sellOptions(playerName);

        }

    }
    public void feedOptions(){
        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n---------------------------------------------");

    }
    public void mateOptions(){
        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n---------------------------------------------");

    }

    static public void clear(){

        System.out.println("\n".repeat(50));

    }
    static public void delay(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static public void decreaseAnimalsHealth(Player playerName){
        if(playerName.haveAnimal.size()!=0){
            for(var animal:  playerName.haveAnimal) {
                animal.health=Animal.randomHealthDecrease(animal.health);
                //System.out.println("This is hopefully the HP decreased  " +Animal.randomHealthDecrease(playerName.haveAnimal.get(i).health));
            }
        }
    }
    static public void showAnimals(Player playerName){
        if(playerName.haveAnimal.size()!=0){
            System.out.println(playerName.name +"'s STORE" +
                    "\n---------------------------------------------");
            for(int i =0; i<playerName.haveAnimal.size();i++) {
                System.out.print("-"+playerName.haveAnimal.get(i).getClass().getSimpleName());
                System.out.print(" "+ " '"+playerName.haveAnimal.get(i).animalName.toUpperCase()+ "' ");
                System.out.print(" "+ "("+ (int)playerName.haveAnimal.get(i).health+")"+ "health");
                System.out.print(" ("+ playerName.haveAnimal.get(i).gender+")\n");

            }
            for(int i =0; i<playerName.haveAnimal.size();i++) {
                Animal.randomHealthDecrease(playerName.haveAnimal.get(i).health);
               // System.out.println("This is hopefully the HP decreased  " +Animal.randomHealthDecrease(playerName.haveAnimal.get(i).health));

            }


        }
        else
            System.out.println("Your STORE is empty"+
                    "\n---------------------------------------------");
    }


}
