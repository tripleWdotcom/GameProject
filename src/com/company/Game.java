package com.company;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    Scanner scan = new Scanner(System.in);
    ArrayList<Player> playerList = new ArrayList<Player>();
    Store store = new Store();

    boolean win = false;


    public Game() {
        System.out.println("=============== WELCOME ==============");
        System.out.println("How many players? (1-4)");
        while (!scan.hasNextInt()) {
            System.out.println("Insert valid number 1-4");
            scan.nextLine();
        }
        var nPlayers = scan.nextInt();
        if (nPlayers < 1)
            System.out.println("No one will play? then BYE BYE \n EXITING GAME...");
        if (nPlayers > 4)
            System.out.println("I said up to just 4 players, but ok Lets go bananas");
        for (int i = 0; i < nPlayers; i++) {
            System.out.printf("Type Name of Player %d : ", i + 1);
            var name = scan.next();
            playerList.add(new Player(name));
            System.out.printf(playerList.get(i).name.toUpperCase() + " - will be Player %d.%n", i + 1);
        }

        System.out.println("How many rounds do you want to play? (5-30)");
        while (!scan.hasNextInt()) {
            System.out.println("Insert valid number 5-30");
            scan.nextLine();
        }
        var nRounds = scan.nextInt();
        System.out.println("Processing...");
        delay();
        clear();

        var roundCounter = 0;
        while (!win && roundCounter < nRounds) {
            for (int i = 0; i < playerList.size(); i++) {
                clear();
                System.out.println("ROUND " + (roundCounter + 1));
                System.out.println("=== " + playerList.get(i).name.toUpperCase() + " === is your turn to play----------------");
                roundOptions(playerList.get(i));
                //decrease heath animals of player method
            }
            roundCounter++;
        }

    }

    public void roundOptions(Player playerName) {
        //System.out.println("=== "+playerName.name + " === is playing----------------");
        System.out.println("You have this amount of money available: $" + playerName.money);
        showAnimals(playerName);
        decreaseAnimalsHealth(playerName);
        showFood(playerName);
        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n1: Buy \n2: Sell \n3: Feed \n4: Mate Animals \n5: something else" +
                "\n---------------------------------------------");
        int opt = scan.nextInt();
        System.out.println("Option " + opt + " was selected \nProcessing...");
        clear();
        switch (opt) {
            case 1 -> buyOptions(playerName);
            case 2 -> sellOptions(playerName);
            //case 3->buyOptions(playerName);
            // case 4->sellOptions(playerName);
        }


    }

    public void buyOptions(Player playerName) {
        System.out.println("=== " + playerName.name + " === is playing----------------");

        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n1: Buy Animal \n2: Buy food \n3: something else" +
                "\n---------------------------------------------");
        int opt = scan.nextInt();
        System.out.println("Option " + opt + " was selected \nProcessing...");
        //delay();
        clear();

        switch (opt) {
            case 1 -> buyAnimalsOptions(playerName);
            case 2 -> buyFoodOptions(playerName);

        }
    }

    public void buyAnimalsOptions(Player playerName) {
        System.out.println("=== " + playerName.name.toUpperCase() + " === is playing----------------");
        System.out.println("You have this amount of money available: $" + playerName.money);
        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n1: Dog  $480 \n2: Crocodile $760 \n3: Condor $1300 \n4: Unicorn $1800 \n5: Rabbit $3000 \n6: Stop Buying animals" +
                "\n---------------------------------------------");
        int opt = scan.nextInt();

        try {
            if (opt >= 1 && opt <= 6) {
                System.out.println("Option " + opt + " was selected \nProcessing...");
                //delay();
                clear();
                switch (opt) {
                    case 1 -> {
                        if (playerName.money >= Dog.price) {
                            playerName.haveAnimal.add(store.buyAnimal(playerName, opt));
                            playerName.money -= Dog.price;
                        } else {
                            System.out.println("Not enough money. You have:" + playerName.money +
                                    "  You need: " + Dog.price + " to buy it.");
                            roundOptions(playerName);
                        }
                    }
                    case 2 -> {
                        if (playerName.money >= Crocodile.price) {
                            playerName.haveAnimal.add(store.buyAnimal(playerName, opt));
                            playerName.money -= Crocodile.price;
                        } else {
                            System.out.println("Not enough money. You have:" + playerName.money +
                                    "  You need: " + Crocodile.price + " to buy it.");
                            roundOptions(playerName);
                        }
                    }
                    case 3 -> {
                        if (playerName.money >= Condor.price) {
                            playerName.haveAnimal.add(store.buyAnimal(playerName, opt));
                            playerName.money -= Condor.price;
                        } else {
                            System.out.println("Not enough money. You have:" + playerName.money +
                                    "  You need: " + Condor.price + " to buy it.");
                            roundOptions(playerName);
                        }
                    }
                    case 4 -> {
                        if (playerName.money >= Unicorn.price) {
                            playerName.haveAnimal.add(store.buyAnimal(playerName, opt));
                            playerName.money -= Unicorn.price;
                        } else {
                            System.out.println("Not enough money. You have:" + playerName.money +
                                    "  You need: " + Unicorn.price + " to buy it.");
                            roundOptions(playerName);
                        }
                    }
                    case 5 -> {
                        if (playerName.money >= Rabbit.price) {
                            playerName.haveAnimal.add(store.buyAnimal(playerName, opt));
                            playerName.money -= Rabbit.price;
                        } else {
                            System.out.println("Not enough money. You have:" + playerName.money +
                                    "  You need: " + Rabbit.price + " to buy it.");
                            roundOptions(playerName);
                        }
                    }
                }
                if (opt == 6)
                    return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Insert valid option (1-6)");
        }
        buyAnimalsOptions(playerName);
    }

    public void buyFoodOptions(Player playerName) {
        System.out.println("=== " + playerName.name.toUpperCase() + " === is playing----------------");
        System.out.println("You have this amount of money available: $" + playerName.money);
        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n1: Big Pizza  " + BigPizza.pricePerK + "\n2: Burger " + Burger.pricePerK +
                " \n3: Salad  " + Salad.pricePerK + " \n4: Stop Buying Food" +
                "\n---------------------------------------------");
        int opt = scan.nextInt();
        System.out.println("Option " + opt + " was selected \nProcessing...");
        //delay();
        clear();

        try {
            switch (opt) {
                case 1 -> {
                    if (playerName.money >= BigPizza.pricePerK) {
                        System.out.println("How many Kg?");
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
                        }
                    } else {
                        System.out.println("Not enough money. You have:" + playerName.money +
                                "  You need: " + BigPizza.pricePerK + " to buy it.");
                        roundOptions(playerName);
                    }
                }
                case 2 -> {
                    if (playerName.money >= Burger.pricePerK) {
                        System.out.println("How many Kg?");
                        var amount = scan.nextInt();
                        var totalAmount = amount * Burger.pricePerK;
                        Burger.quantity += amount;
                        if (totalAmount < playerName.money) {
                            if (playerName.haveFood.containsKey(Burger.name)) {
                                playerName.haveFood.replace(Burger.name, Burger.quantity);
                            } else {
                                playerName.haveFood.put(Burger.name, Burger.quantity);

                            }
                            playerName.money -= totalAmount;
                        }
                    } else {
                        System.out.println("Not enough money. You have:" + playerName.money +
                                "  You need: " + Burger.pricePerK + " to buy it.");
                        roundOptions(playerName);
                    }
                }
                case 3 -> {
                    if (playerName.money >= Salad.pricePerK) {
                        System.out.println("How many Kg?");
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
                        }
                    } else {
                        System.out.println("Not enough money. You have:" + playerName.money +
                                "  You need: " + BigPizza.pricePerK + " to buy it.");
                        roundOptions(playerName);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Insert valid option (1-4)");
        }
        if (opt == 4)
            return;
        buyFoodOptions(playerName);
    }

    public void sellOptions(Player playerName) {
        System.out.println("=== " + playerName.name.toUpperCase() + " === is playing----------------");

        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n1: Sell animal \n2: something else" +
                "\n---------------------------------------------");
        int opt = scan.nextInt();
        System.out.println("Option " + opt + " was selected \nProcessing...");
        //delay();
        clear();

        switch (opt) {
            //  case 1->buyOptions(playerName);
            case 2 -> sellOptions(playerName);

        }

    }

    public void feedOptions() {
        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n---------------------------------------------");

    }

    public void mateOptions() {
        System.out.println("Type option" +
                "\n---------------------------------------------" +
                "\n---------------------------------------------");

    }

    static public void clear() {

        System.out.println("\n".repeat(50));

    }

    static public void delay() {
        try {
            TimeUnit.SECONDS.sleep(3);
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

    static public void showAnimals(Player playerName) {
        if (playerName.haveAnimal.size() != 0) {
            System.out.println(playerName.name.toUpperCase() + "'s ANIMAL STORE" +
                    "\n---------------------------------------------");
            for (int i = 0; i < playerName.haveAnimal.size(); i++) {
                System.out.print("-" + playerName.haveAnimal.get(i).getClass().getSimpleName());
                System.out.print(" " + " '" + playerName.haveAnimal.get(i).animalName.toUpperCase() + "' ");
                System.out.print(" " + "(" + (int) playerName.haveAnimal.get(i).health + "% hp)");
                System.out.print("  (" + playerName.haveAnimal.get(i).gender + ")\n");

            }
            System.out.print("---------------------------------------------\n");
            for (int i = 0; i < playerName.haveAnimal.size(); i++) {
                Animal.randomHealthDecrease(playerName.haveAnimal.get(i).health);
            }


        } else
            System.out.println("Your STORE is empty" +
                    "\n---------------------------------------------");
    }

    static public void showFood(Player playerName) {
        if (playerName.haveFood.size() != 0) {
            System.out.println(playerName.name.toUpperCase() + "'s FOOD STORE" +
                    "\n---------------------------------------------");
            for (var food : playerName.haveFood.keySet()) {
                System.out.println(food + " quantity(kg): " + playerName.haveFood.get(food));
            }
            System.out.print("---------------------------------------------\n");

        } else
            System.out.println(playerName.name.toUpperCase() + "'s food STORE is empty" +
                    "\n---------------------------------------------");
    }


}
