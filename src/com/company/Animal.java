package com.company;


public abstract class Animal {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";


    public String animalName;
    public int health;
    public boolean living;
    public Gender gender;
    public int price;
    public int maxAge;
    public boolean sick;
    public int vetPrice;
    public int initialAge;
    public int sickBefore;

    enum Gender {
        MALE,
        FEMALE,
    }

    public Animal(String animalName, String gender, int price, int vetPrice, int maxAge) {
        this.price = price;
        this.health = 100;
        this.animalName = animalName;
        this.living = true;
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.sick = false;
        this.vetPrice = vetPrice;
        this.maxAge = maxAge;
        this.initialAge = 1;
        this.sickBefore=0;

    }

    public static int randomHealthDecrease(int health) {
        double random = (Math.random() * 20) + 11;
        random = 100 - random;
        health *= (random / 100);
        return health;
    }

    public static String genderToNewAnimal() {
        var r = "";
        if (Math.random() >= 0.5)
            r = "MALE";
        else
            r = "FEMALE";
        return r;
    }

    public int getCurrentPrice() {
        return price * health / 100;
    }


    public void checkHealth(double health) {
        if ((int) health == 1)
            System.out.println(ANSI_RED + " --" + this.animalName.toUpperCase() + " is in CRITICAL condition, it will die next round." + ANSI_RESET);
        if ((int) health == 0)
            this.die();
    }
    public void veterinaryMiracle() {
        System.out.println("This might take a while. It wont be easy. FINGERS CROSSED!!!");
        Game.delay();
        System.out.println("The operation is still going...");
        Game.delay();
        if (Math.random() < 0.5) {
            System.out.println(ANSI_GREEN+ "'" + this.animalName.toUpperCase() + "'" + " is SAVED!!!"+ANSI_RESET);
            this.sickBefore=1;
            this.sick=false;
            Game.delay();
        } else {
            System.out.println("\nUnfortunately, little " + this.animalName.toUpperCase() + " was too sick and could not be saved.");
            this.die();
        }
    }

    public void ageing() {
        this.initialAge++;
        if (this.initialAge > this.maxAge) {
            System.out.println(this.animalName + " is too old....");
            this.die();
        }
    }

    public void die() {
        System.out.println(ANSI_RED + "'"+this.animalName.toUpperCase() + "' is now dead" + ANSI_RESET);
        this.living = false;
        Game.delay();
    }

    public void animalGetSick() {
        if(this.initialAge>3){
            if(Math.random() < 0.2){
                this.sick = true;
            }
        }
    }
}
