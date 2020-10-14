package com.company;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Game {
    Scanner scan=new Scanner(System.in);
    ArrayList<Player> playerList=new ArrayList<Player>();

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
            System.out.println(playerList.get(i).name);
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
        while (!win && roundCounter<=nRounds){
            for(int i=0;i<playerList.size();i++){
                clear();
                System.out.println("=== "+playerList.get(i).name + " === is your turn to play----------------");
                roundOptions(playerList.get(i));
                roundCounter++;
            }

        }

    }
    public void roundOptions(Player playerName){
        System.out.println("=== "+playerName.name + " === is playing----------------");
        System.out.println("You have this amount of money available: " + playerName.money);

        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\noption 1: Buy \noption 2: Sell \noption 3: Feed \noption 4: Mate Animals \noption 5: something else" +
                "\n---------------------------------------------");
        int opt =scan.nextInt();
        System.out.println("Option "+opt+ " was selected \nProcessing...");
        clear();
        switch (opt) {
            case 1->buyOptions(playerName.name);
            case 2->sellOptions(playerName.name);
            case 3->buyOptions(playerName.name);
            case 4->sellOptions(playerName.name);
        }

   }
   public void buyOptions(String playerName){
       System.out.println("=== "+playerName + " === is playing----------------");

       System.out.println("Type option" +
               "\n---------------------------------------------" +
               "\noption 1: Buy Animal \noption 2: Buy food \noption 3: something else" +
               "\n---------------------------------------------");
       int opt =scan.nextInt();
       System.out.println("Option "+opt+ " was selected \nProcessing...");
       //delay();
       clear();

       switch (opt) {
           case 1->buyOptions(playerName);
           case 2->sellOptions(playerName);

       }


   }
    public void sellOptions(String playerName){
        System.out.println("=== "+playerName + " === is playing----------------");

        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\noption 1: Sell animal \noption 2: something else" +
                "\n---------------------------------------------");
        int opt =scan.nextInt();
        System.out.println("Option "+opt+ " was selected \nProcessing...");
        delay();
        clear();

        switch (opt) {
            case 1->buyOptions(playerName);
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


}
