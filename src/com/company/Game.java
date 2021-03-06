package com.company;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    Scanner scan = new Scanner(System.in);
    ArrayList<Player> playerList = new ArrayList<>();
    Store store = new Store();
    boolean win = false;


    public Game() {
        gameLogo();
        info();
        int nPlayers = promptInt("How many players? (1-4)", 1, 4);
        for (int i = 0; i < nPlayers; i++) {
            System.out.printf("Type Name of Player %d : ", i + 1);
            var name = scan.nextLine();
            playerList.add(new Player(name));
            System.out.printf(ANSI_CYAN+ playerList.get(i).name.toUpperCase() +ANSI_RESET+ " - will be Player %d.%n\n", i + 1);
        }
        var nRounds = promptInt("\nHow many rounds do you want to play? (5-30) (Recommended rounds: 15-20)", 5, 30);
        System.out.println(ANSI_YELLOW+ "Processing..."+ANSI_RESET);
        delay();
        clear();

        var roundCounter = 0;
        while (!win && roundCounter < nRounds) {
            Player toRemoveNoMoneyPlayer=null;
            for (Player player : playerList) {
                clear();
                System.out.println(ANSI_CYAN + "ROUND " + (roundCounter + 1) + ANSI_RESET);
                System.out.println("=== " + ANSI_CYAN + player.name.toUpperCase() + ANSI_RESET + " === is your turn to play----------------");
                roundOptions(player);
                saveSickAnimal(player);
                if(checkPlayerNoMoney(player)){
                    toRemoveNoMoneyPlayer=player;
                }
            }
            playerList.remove(toRemoveNoMoneyPlayer);
            roundCounter++;
            if(roundCounter==nRounds-1) {
                System.out.println( ANSI_YELLOW+"THIS WILL BE THE "+nRounds+ " ROUND.\n\nIT WILL BE THE FINAL ROUND. MAKE THE RIGHT DECISIONS. GOOD LUCK!!"+ANSI_RESET);
                delay();
                shortDelay();
            }

        }
        finalRound();
        findWinner();

    }

    public void roundOptions(Player playerName) {
        if (playerName.money < 1) {
            System.out.println(playerName.name + "has no money...");
            if (playerName.haveAnimal.size() != 0) {
                System.out.println("you have to sell you animals to get some money");
                sellOptions(playerName);
            } else {
                System.out.println("You have no money and no animals. GAME OVER for you...");
                delay();
                return;
            }
        }
        System.out.println("You have this amount of money available: $" + playerName.money);
        decreaseAnimalsHealth(playerName);
        removeDeadAnimals(playerName);
        showAnimals(playerName);
        showFood(playerName);
        System.out.println("""
                Options
                ---------------------------------------------
                1: Buy (Animals/Food) \s
                2: Sell Animals\s
                3: Feed Animals\s
                4: Mate Animals\s
                5: Do nothing (Skip Round)
                ---------------------------------------------""");
        var opt = promptInt("Choose option number", 1, 5);
        System.out.println("\nOption " + opt + " was selected" +ANSI_YELLOW+ "\nProcessing..."+ANSI_RESET);
        clear();
        switch (opt) {
            case 1 -> buyOptions(playerName);
            case 2 -> sellOptions(playerName);
            case 3 -> feedOptions(playerName);
            case 4 -> mateOptions(playerName);
        }
    }

    public void buyOptions(Player playerName) {
        System.out.println(ANSI_CYAN+ "=== " + playerName.name.toUpperCase() +" === "+ANSI_RESET+ "is playing----------------");

        System.out.println("""
                Options
                ---------------------------------------------
                1: Buy Animal\s
                2: Buy food
                ---------------------------------------------""");
        int opt = promptInt("Type Option: ", 1, 3);
        System.out.println("\nOption " + opt + " was selected" +ANSI_YELLOW+ "\nProcessing..."+ANSI_RESET);
        //delay();
        clear();

        switch (opt) {
            case 1 -> buyAnimalsOptions(playerName);
            case 2 -> buyFoodOptions(playerName);
            case 3 -> roundOptions(playerName);
        }
    }

    public void buyAnimalsOptions(Player playerName) {
        clear();

        System.out.println(ANSI_CYAN+ "=== " + playerName.name.toUpperCase() +" === "+ANSI_RESET+ "is playing----------------");
        System.out.println("You have this amount of money available: $" + playerName.money);
        System.out.println("Animal LIST" +
                "\n---------------------------------------------" +
                "\n1: Dog  $" + Dog.price + "\n2: Crocodile $" + Crocodile.price +
                "\n3: Condor $" + Condor.price + "\n4: Unicorn $" + Unicorn.price +
                "\n5: Rabbit $" + Rabbit.price + "\n6: Stop Buying animals" +
                "\n---------------------------------------------");
        int opt = promptInt("Choose option number: ", 1, 6);
        System.out.println("Option " + opt + " was selected.\n");
        //delay();
        switch (opt) {
            case 1 -> {
                if (playerName.money >= Dog.price) {
                    playerName.haveAnimal.add(store.buyAnimal(opt));
                    playerName.money -= Dog.price;
                } else {
                    System.out.println("Not enough money. You have:" + playerName.money +
                            "  You need: " + Dog.price + " to buy it.");
                    delay();
                }
            }
            case 2 -> {
                if (playerName.money >= Crocodile.price) {
                    playerName.haveAnimal.add(store.buyAnimal(opt));
                    playerName.money -= Crocodile.price;
                } else {
                    System.out.println("Not enough money. You have:" + playerName.money +
                            "  You need: " + Crocodile.price + " to buy it.");
                    delay();
                }
            }
            case 3 -> {
                if (playerName.money >= Condor.price) {
                    playerName.haveAnimal.add(store.buyAnimal(opt));
                    playerName.money -= Condor.price;
                } else {
                    System.out.println("Not enough money. You have:" + playerName.money +
                            "  You need: " + Condor.price + " to buy it.");
                    delay();
                }
            }
            case 4 -> {
                if (playerName.money >= Unicorn.price) {
                    playerName.haveAnimal.add(store.buyAnimal(opt));
                    playerName.money -= Unicorn.price;
                } else {
                    System.out.println("Not enough money. You have:" + playerName.money +
                            "  You need: " + Unicorn.price + " to buy it.");
                    delay();
                }
            }
            case 5 -> {
                if (playerName.money >= Rabbit.price) {
                    playerName.haveAnimal.add(store.buyAnimal(opt));
                    playerName.money -= Rabbit.price;
                } else {
                    System.out.println("Not enough money. You have:" + playerName.money +
                            "  You need: " + Rabbit.price + " to buy it.");
                    delay();
                }
            }
        }
        if (opt == 6)
            return;
        buyAnimalsOptions(playerName);
    }

    public void buyFoodOptions(Player playerName) {
        clear();
        System.out.print(ANSI_YELLOW+ """
                PIZZA ONLY for Dogs and Unicorns
                BURGER ONLY for Crocodiles, Condors and Dogs
                SALAD ONLY for Rabbits and Condors
                -----------------------------------------------
                """ +ANSI_RESET);
        System.out.println(ANSI_CYAN+ "=== " + playerName.name.toUpperCase() +" === "+ANSI_RESET+ "is playing----------------");
        System.out.println("You have this amount of money available: $" + playerName.money);
        System.out.println("Food List" +
                "\n---------------------------------------------" +
                "\n1: Big Pizza  -  $" + BigPizza.pricePerK + "\n2: Burger  -  $" + Burger.pricePerK +
                " \n3: Salad  -  $" + Salad.pricePerK + " \n4: Stop Buying Food" +
                "\n---------------------------------------------");
        int opt = promptInt("Choose Option number: ", 1, 4);
        System.out.println("Option " + opt + " was selected.");
        //delay();
        switch (opt) {
            case 1 -> {
                if (playerName.money >= BigPizza.pricePerK) {
                    System.out.println("How many Kg?");
                    while (!scan.hasNextInt()) {
                        System.out.println("Fail INPUT - Try Again\nHow many Kg?:");
                        scan.nextLine();
                    }
                    var amount = scan.nextInt();
                    var totalAmount = amount * BigPizza.pricePerK;
                    BigPizza.quantity += amount;
                    if (totalAmount < playerName.money) {
                        if (playerName.haveFood.containsKey(BigPizza.name)) {
                            playerName.haveFood.replace(BigPizza.name, BigPizza.quantity);
                        } else {
                            playerName.haveFood.put(BigPizza.name, BigPizza.quantity);
                        }
                        playerName.money -= totalAmount;
                    } else {
                        System.out.println(ANSI_RED + "NOT ENOUGH MONEY FOR THAT QUANTITY" + ANSI_RESET);
                        delay();
                    }

                } else {
                    System.out.println(ANSI_RED + "Not enough money. You have:" + playerName.money +
                            "  You need: " + BigPizza.pricePerK + " to buy it." + ANSI_RESET);
                    delay();

                }
            }
            case 2 -> {
                if (playerName.money >= Burger.pricePerK) {
                    System.out.println("How many Kg?");
                    while (!scan.hasNextInt()) {
                        System.out.println("Fail INPUT - Try Again\nHow many Kg?:");
                        scan.nextLine();
                    }
                    var amount = scan.nextInt();
                    var totalAmount = amount * Burger.pricePerK;
                    Burger.quantity += amount;
                    if (totalAmount <= playerName.money) {
                        if (playerName.haveFood.containsKey(Burger.name)) {
                            playerName.haveFood.replace(Burger.name, Burger.quantity);
                        } else {
                            playerName.haveFood.put(Burger.name, Burger.quantity);
                        }
                        playerName.money -= totalAmount;
                    } else {
                        System.out.println(ANSI_RED + "NOT ENOUGH MONEY FOR THAT QUANTITY" + ANSI_RESET);
                        delay();
                    }
                } else {
                    System.out.println("Not enough money. You have:" + playerName.money +
                            "  You need: " + Burger.pricePerK + " to buy it.");
                    delay();

                }
            }
            case 3 -> {
                if (playerName.money >= Salad.pricePerK) {
                    System.out.println("How many Kg?");
                    while (!scan.hasNextInt()) {
                        System.out.println("Fail INPUT - Try Again\nHow many Kg?:");
                        scan.nextLine();
                    }
                    var amount = scan.nextInt();
                    var totalAmount = amount * Salad.pricePerK;
                    Salad.quantity += amount;
                    if (totalAmount < playerName.money) {
                        if (playerName.haveFood.containsKey(Salad.name)) {
                            playerName.haveFood.replace(Salad.name, Salad.quantity);
                        } else {
                            playerName.haveFood.put(Salad.name, Salad.quantity);
                        }
                        playerName.money -= totalAmount;
                    } else {
                        System.out.println(ANSI_RED + "NOT ENOUGH MONEY FOR THAT QUANTITY" + ANSI_RESET);
                        delay();
                    }
                } else {
                    System.out.println("Not enough money. You have:" + playerName.money +
                            "  You need: " + BigPizza.pricePerK + " to buy it.");
                    delay();

                }
            }
        }
        if (opt == 4) {
            return;
        }
        buyFoodOptions(playerName);
    }

    public void sellOptions(Player playerName) {
        if (playerName.haveAnimal.size() != 0) {
            System.out.println(ANSI_CYAN+ "=== " + playerName.name.toUpperCase() +" === "+ANSI_RESET+ "is playing----------------");
            System.out.println("List of Animals you can sell back to the STORE (press 'E' to Exit)" +
                    "\n---------------------------------------------");

            for (Animal animal : playerName.haveAnimal) {
                System.out.print("-" + animal.getClass().getSimpleName());
                System.out.print(" " + " '" + animal.animalName.toUpperCase() + "' ");
                System.out.print(" " + "(" + animal.health + "% hp)");
                System.out.print("  (" + animal.gender + ")" + ANSI_CYAN + "- you get from the store: $" +
                        animal.getCurrentPrice() + ANSI_RESET + "\n");
            }
            System.out.println("---------------------------------------------");
            System.out.println("Write the name of the Animal: ");
            var opt = scan.nextLine();
            var exists = false;
            for (int i = 0; i < playerName.haveAnimal.size(); i++) {
                if (playerName.haveAnimal.get(i).animalName.equalsIgnoreCase(opt)) {
                    playerName.money += playerName.haveAnimal.get(i).getCurrentPrice();
                    System.out.println("'" + opt.toUpperCase() + "'" + " Sold for : $" + playerName.haveAnimal.get(i).getCurrentPrice());
                    delay();
                    clear();
                    playerName.haveAnimal.remove(i);
                    exists = true;
                }
            }
            if (playerName.haveAnimal.size() == 0) {
                System.out.println("No more animals to sell...");
                delay();
                return;
            }
            if (opt.equalsIgnoreCase("e"))
                return;
            if (!exists) {
                System.out.println("That name does not exist");
                shortDelay();
                clear();
            }
            sellOptions(playerName);
        } else {
            System.out.println("You dont have animals. Buy some animals first !!");
            delay();
            clear();
            roundOptions(playerName);
        }
    }

    public void feedOptions(Player playerName) {
        if (playerName.haveFood.size() != 0) {
            System.out.println("Write the name of the animal you want to feed "+ANSI_YELLOW+"(Type 'E' for Exit)"+ANSI_RESET +
                    "\n---------------------------------------------");
            System.out.print(ANSI_YELLOW+ """
                    PIZZA ONLY for Dogs and Unicorns
                    BURGER ONLY for Crocodiles, Condors and Dogs
                    SALAD ONLY for Rabbits and Condors
                    -----------------------------------------------"""+ANSI_RESET);

            for (Animal animal : playerName.haveAnimal) {
                System.out.print("\n-" + animal.getClass().getSimpleName());
                System.out.print(" " + " '" + animal.animalName.toUpperCase() + "' ");
                System.out.print(" " + "(" + animal.health + "% hp)");
            }
            System.out.println("\n---------------------------------------------");
            System.out.print("Animal to feed: ");
            var animalToFeed = scan.nextLine();
            if (animalToFeed.equalsIgnoreCase("e")) {
                System.out.println("Exiting...");
                shortDelay();
                return;

            }
            Animal hungryAnimal = null;
            for (int i = 0; i < playerName.haveAnimal.size(); i++) {
                if (playerName.haveAnimal.get(i).animalName.equalsIgnoreCase(animalToFeed)) {
                    hungryAnimal = playerName.haveAnimal.get(i);
                }
            }
            if (hungryAnimal == null) {
                System.out.println("That name does not exist, Try again");
                shortDelay();
                clear();
                feedOptions(playerName);
            } else {
                showFood(playerName);
                var foodInput = "";
                var exit = false;
                do {
                    System.out.println("What kid of food do you want to use. Type (S) for Salad, (B) for Burger and (P) for Big Pizza ");
                    foodInput = scan.nextLine();
                    if (foodInput.equalsIgnoreCase("s") && playerName.haveFood.containsKey(Salad.name) && playerName.haveFood.get(Salad.name) != 0) {
                        foodInput = "Salad";
                        exit = true;
                    }
                    if (foodInput.equalsIgnoreCase("b") && playerName.haveFood.containsKey(Burger.name) && playerName.haveFood.get(Burger.name) != 0) {
                        foodInput = "Burger";
                        exit = true;
                    }
                    if (foodInput.equalsIgnoreCase("p") && playerName.haveFood.containsKey(BigPizza.name) && playerName.haveFood.get(BigPizza.name) != 0) {
                        foodInput = "Big Pizza";
                        exit = true;
                    }
                } while (!exit);

                var kgInput = promptInt("How many Kg do you want to FEED your " + hungryAnimal.getClass().getSimpleName(), 1, playerName.haveFood.get(foodInput));
                if (playerName.feedAnimal(hungryAnimal, kgInput, foodInput)) {
                    playerName.haveFood.replace(foodInput, (playerName.haveFood.get(foodInput) - kgInput));
                    System.out.println(hungryAnimal.getClass().getSimpleName() + " '" + animalToFeed.toUpperCase() + "' is eating its " + foodInput + "...");
                    delay();
                    clear();
                } else {
                    System.out.println("Choose the CORRECT FOOD for the ANIMAL");
                    delay();
                    clear();
                    feedOptions(playerName);
                }
            }

        } else {
            System.out.println("You dont have Food in your store yet. Go to BUY FOOD.");
            delay();
            clear();
            roundOptions(playerName);
        }


    }


    public void mateOptions(Player playerName) {
        if (playerName.haveAnimal.size() != 0) {
            System.out.println(ANSI_CYAN+ "=== " + playerName.name.toUpperCase() +" === "+ANSI_RESET+ "is playing----------------");
            System.out.println("Write the name of the animal you want to MATE "+ANSI_YELLOW+"(Type 'E' for Exit)"+ANSI_RESET +
                    "\n---------------------------------------------");

            for (Animal animal : playerName.haveAnimal) {
                System.out.print("-" + animal.getClass().getSimpleName());
                System.out.print(" " + " '" + animal.animalName.toUpperCase() + "' ");
                System.out.print(" " + "(" + animal.health + "% hp)");
                System.out.print("  (" + animal.gender + ")\n");
            }
            System.out.println("---------------------------------------------");

            System.out.println("Animal 1: ");
            var animal1 = scan.nextLine();
            if (animal1.equalsIgnoreCase("e")) {
                System.out.println("Exiting...");
                delay();
                return;
            } else if (!animal1.equalsIgnoreCase("e")) {
                System.out.println("Animal 2: ");
                var animal2 = scan.nextLine();
                if (animal2.equalsIgnoreCase("e")) {
                    System.out.println("Exiting...");
                    delay();
                    return;
                } else {
                    Animal a = null;
                    Animal b = null;
                    for (int i = 0; i < playerName.haveAnimal.size(); i++) {
                        if (playerName.haveAnimal.get(i).animalName.equalsIgnoreCase(animal1)) {
                            a = playerName.haveAnimal.get(i);
                        }
                        if (playerName.haveAnimal.get(i).animalName.equalsIgnoreCase(animal2) && !animal1.equalsIgnoreCase(animal2)) {
                            b = playerName.haveAnimal.get(i);
                        }
                    }

                    if (b != null && a != null) {
                        if ((playerName.toMate(a, b))) {
                            System.out.println("\nAnimals mating. Be patient...\n ");
                            delay();
                            if (Math.random() >= 0.5) {
                                System.out.println("It is taking while. That's a GOOD sign :)\n");
                                delay();
                                System.out.println(ANSI_PURPLE+ """
                                              _____           _____
                                          ,ad8PPPP88b,     ,d88PPPP8ba,
                                         d8P"      "Y8b, ,d8P"      "Y8b
                                        dP'           "8a8"           `Yd
                                        8(              "              )8
                                        I8                             8I
                                         Yb,                         ,dP
                                          "8a,                     ,a8"
                                            "8a,                 ,a8"
                                              "Yba             adP"
                                                `Y8a         a8P'
                                                  `88,     ,88'
                                                    "8b   d8"  
                                                     "8b d8"   
                                                      `888'
                                                        \""""+ANSI_RESET);
                                System.out.println(ANSI_GREEN+"LUCKY !!! your "+a.getClass().getSimpleName() +" got offspring\n"+ANSI_RESET);
                                var check = a.getClass().getSimpleName();
                                switch (check) {
                                    case "Dog" -> playerName.haveAnimal.add(new Dog(store.nameToAnimal(), Animal.genderToNewAnimal()));
                                    case "Condor" -> playerName.haveAnimal.add(new Condor(store.nameToAnimal(), Animal.genderToNewAnimal()));
                                    case "Crocodile" -> playerName.haveAnimal.add(new Crocodile(store.nameToAnimal(), Animal.genderToNewAnimal()));
                                    case "Rabbit" -> playerName.haveAnimal.add(new Rabbit(store.nameToAnimal(), Animal.genderToNewAnimal()));
                                    case "Unicorn" -> playerName.haveAnimal.add(new Unicorn(store.nameToAnimal(), Animal.genderToNewAnimal()));
                                }
                                System.out.println("New animal added to your STORE. Next player´s turn in 3... 2... 1... ");
                            } else {
                                System.out.println("Unlucky :( ... no offspring \nNext player´s turn in 3... 2... 1...");
                            }
                            delay();
                        } else
                            mateOptions(playerName);
                    } else {
                        System.out.println("Names do not match, Try again");
                        delay();
                        clear();
                        mateOptions(playerName);
                    }
                }
            }
        } else {
            System.out.println("You dont have animals. Buy some animals first !!");
            delay();
            clear();
            roundOptions(playerName);
        }
    }


    static public void clear() {
        System.out.println("\n".repeat(40));
    }

    static public void delay() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public void shortDelay() {
        try {
            TimeUnit.MILLISECONDS.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public void decreaseAnimalsHealth(Player playerName) {
        if (playerName.haveAnimal.size() != 0) {
            for (var animal : playerName.haveAnimal) {
                animal.health = Animal.randomHealthDecrease(animal.health);
            }
        }
    }

    static public void saveSickAnimal(Player playerName) {
        if (playerName.haveAnimal.size() != 0) {
            for (Animal animal : playerName.haveAnimal) {
                animal.animalGetSick();
                if (animal.sick && animal.sickBefore==0) {
                    clear();
                    System.out.println(ANSI_RED + "OH NO !! I am sorry " + playerName.name.toUpperCase() +
                            "\nBefore going to the next round you need to make an important decision." +
                            "\nUnfortunately " + "'" + animal.animalName.toUpperCase() + "'" + " is SICK. The cost of the veterinary is $" + animal.vetPrice + ANSI_RESET);
                    if (playerName.money > animal.vetPrice) {
                        var opt = promptInt("Do you want to try to save it? (type '1' for YES or '2' for NO )", 1, 2);
                        if (opt == 1) {
                            animal.veterinaryMiracle();
                            playerName.money -= animal.vetPrice;
                        } else {
                            animal.die();
                        }
                        clear();
                    } else {
                        System.out.println("You dont have the money to save it...");
                        delay();
                        animal.die();
                        clear();
                    }
                }
            }
            removeDeadAnimals(playerName);
        }
    }

    static public void showAnimals(Player playerName) {
        if (playerName.haveAnimal.size() != 0) {

            System.out.print(ANSI_GREEN + playerName.name.toUpperCase() + "'s ANIMAL STORE" +
                    "\n---------------------------------------------" + ANSI_RESET);
            for (Animal animal : playerName.haveAnimal) {
                System.out.print(ANSI_GREEN + "\n-" + animal.getClass().getSimpleName() + "  '" + animal.animalName.toUpperCase() + "'  " + animal.initialAge + " year(s) old  " + " (" + animal.health + "% hp)" + "  (" + animal.gender + ") ");
                if(animal.initialAge==animal.maxAge-4)
                    System.out.print(ANSI_BLUE+"- $ TOP SELLING POINT"+ ANSI_RESET);
                if(animal.initialAge==animal.maxAge-2)
                    System.out.print(ANSI_RED+"-This "+animal.getClass().getSimpleName()+ " is old now."+ANSI_RESET);

            }
            System.out.print("\n---------------------------------------------\n" + ANSI_RESET);
            for (Animal animal : playerName.haveAnimal) {
                animal.checkHealth(animal.health);
            }
            removeDeadAnimals(playerName);

            for (Animal animal : playerName.haveAnimal) {
                animal.ageing();
            }
            removeDeadAnimals(playerName);

        } else
            System.out.println(ANSI_GREEN + playerName.name.toUpperCase() + "'s ANIMAL STORE is empty" +
                    "\n---------------------------------------------" + ANSI_RESET);
    }

    public static void removeDeadAnimals(Player playerName) {
        playerName.haveAnimal.removeIf(animal -> !animal.living);
    }

    static public void showFood(Player playerName) {
        if (playerName.haveFood.size() != 0) {
            System.out.println(ANSI_BLUE + playerName.name.toUpperCase() + "'s FOOD STORE" +
                    "\n---------------------------------------------" + ANSI_RESET);
            for (var food : playerName.haveFood.keySet()) {
                System.out.println(ANSI_BLUE + food + " quantity(kg): " + playerName.haveFood.get(food));
            }
            System.out.print("---------------------------------------------\n" + ANSI_RESET);

        } else
            System.out.println(ANSI_BLUE + playerName.name.toUpperCase() + "'s food STORE is empty" +
                    "\n---------------------------------------------" + ANSI_RESET);
    }

    static public String prompt(String question) {
        Scanner scan = new Scanner(System.in);
        System.out.println(question);
        return scan.nextLine();
    }

    static public int promptInt(String question, int min, int max) {
        var num = min - 1;
        try {
            num = Integer.parseInt(prompt(question));
        } catch (Exception ignore) {
        }
        return num < min || num > max ?
                promptInt(question, min, max) : num;
    }

    public void finalRound() {
        System.out.println("THAT WAS THE FINAL ROUND !!!" +
                " The system is working very HARD to find a winner. Be patient");
        delay();
        System.out.println("Still working...");
        delay();
        System.out.println("Almost there...");
        delay();
        clear();

    }

    public void findWinner() {
        HashMap<String, Integer> winnersMoney = new HashMap<>();
        System.out.print(ANSI_GREEN+"""
                    |@@@@|     |####|
                    |@@@@|     |####|
                    |@@@@|     |####|
                    \\@@@@|     |####/
                     \\@@@|     |###/
                      `@@|_____|##'
                           (O)
                        .-'''''-.
                      .'  * * *  `.
                     :  *       *  :
                    : ~  G A M E  ~ :
                    :  W I N N E R  :
                     :  *       *  :
                      `.  * * *  .'
                        `-.....-'
                """+ANSI_RESET);
        System.out.println("----------------------------------------------\n" + ANSI_GREEN +
                "===== FINAL RESULT =======  \n==== LIST OF PLAYERS =====" + ANSI_RESET +
                "\n---------------------------------------------");
        for (Player player : playerList) {
            var sum = 0;
            for (Animal animal : player.haveAnimal) {
                sum += (animal.getCurrentPrice());
            }
            sum += player.money;
            winnersMoney.put(player.name, sum);
            shortDelay();
            System.out.println("-" + player.name.toUpperCase() + "   -    TOTAL : $" + sum);
        }
        int playerWithMoreMoney = Collections.max(winnersMoney.values());
        for (var name : winnersMoney.keySet()) {
            if (winnersMoney.get(name) == playerWithMoreMoney) {
                delay();
                System.out.println("----------------------------------------------------");
                System.out.println(ANSI_GREEN + "The winner is : " + ANSI_RESET + ANSI_BLUE + name.toUpperCase() +
                        ANSI_RESET + ANSI_GREEN + " !!! with an amount of: $" + winnersMoney.get(name) + ANSI_RESET);
            }
        }

    }
    public boolean checkPlayerNoMoney(Player player){
        var noMoney=false;
        if(player.money<Dog.price && player.haveAnimal.size()==0){
            clear();
            System.out.println(ANSI_RED+"Player: '"+ player.name.toUpperCase() +"' can't continue playing. Not ENOUGH MONEY and has NO ANIMALS.");
            delay();
            System.out.println("""
                                  ___         \s
                                /   \\\\       \s
                           /\\\\ | . . \\\\      \s
                         ////\\\\|     ||      \s
                       ////   \\\\ ___//\\      \s
                      ///      \\\\      \\     \s
                     ///       |\\\\      |    \s
                    //         | \\\\  \\   \\   \s
                    /          |  \\\\  \\   \\  \s
                               |   \\\\ /   /  \s
                               |    \\/   /   \s
                               |     \\\\/|    \s
                               |      \\\\|    \s
                               |       \\\\    \s
                               |        |    \s
                               |_________\\   \s""");
            System.out.println("'"+player.name.toUpperCase()+ "' has been REMOVED from the game."+ANSI_RESET);
            delay();
            noMoney=true;
        }
        return noMoney;
    }
    public void gameLogo(){
        System.out.println(ANSI_YELLOW + """
                 =================  WELCOME TO THE  ===================
                 ___         _     _____                   _            \s
                | _ \\  ___  | |_  |_   _|  _ _   __ _   __| |  ___   _ _\s
                |  _/ / -_) |  _|   | |   | '_| / _` | / _` | / -_) | '_|
                |_|   \\___|  \\__|   |_|   |_|   \\__,_| \\__,_| \\___| |_| \s
                                                                        \s
                ====================== G A M E ========================
                                                                        """+ANSI_RESET);
    }


    public void info(){

        System.out.println(ANSI_YELLOW+" --------------------------------------------------\n" +
                "| Invest in buying Animals and take care of them!  |\n" +
                "| Mate them to get more!                           |\n" +
                "| Sell them to profit!                             |\n" +
                "| Older animals give more profit, but NOT too old. |\n" +
                " ---------------------------------------------------\n"+ANSI_RESET);
    }
}
