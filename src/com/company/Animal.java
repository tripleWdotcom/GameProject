package com.company;

public abstract class Animal {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public String animalName;
    public double health;
    public boolean living;
    public Gender gender;
    public int price;

    enum Gender{
        MALE,
        FEMALE,
    }
    public Animal(String animalName,String  gender, int price){
        this.price = price;
        this.health=100;
        this.animalName=animalName;
        this.living=true;
        this.gender= Gender.valueOf(gender.toUpperCase());

    }
    public static int randomHealthDecrease(double health) {
        double random = (int)(Math.random() * 20) + 11;
        random=100-random;
        health*=(random/100);
        return (int)health;
    }

    public static String genderToNewAnimal() {
        var r="";
        if (Math.random() >= 0.5)
            r="MALE";
        else
            r="FEMALE";
        return r;
    }

    public double getCurrentPrice(){
        return price * health/100.0;
    }


    public  void checkHealth(double health){
        if((int)health==1)
            System.out.println(ANSI_RED+" --"+this.animalName.toUpperCase() + " is in CRITICAL condition, it will die next round."+ANSI_RESET);
        if((int)health==0)
            this.die();
    }

    public void die(){
        System.out.println(this.animalName.toUpperCase()+" is now dead");
        this.living=false;
    }



}
