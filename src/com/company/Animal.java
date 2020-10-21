package com.company;

import javax.management.monitor.GaugeMonitor;
import javax.sound.midi.Soundbank;

public abstract class Animal {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public String animalName;
    public double health;
    public boolean living;
    public Gender gender;
    public int price;
    public int maxAge;
    public boolean sick;
    public int vetPrice;
    public int initialAge;

    enum Gender{
        MALE,
        FEMALE,
    }
    public Animal(String animalName,String  gender, int price,int vetPrice,int maxAge){
        this.price = price;
        this.health=100;
        this.animalName=animalName;
        this.living=true;
        this.gender= Gender.valueOf(gender.toUpperCase());
        this.sick=false;
        this.vetPrice=vetPrice;
        this.maxAge=maxAge;
        this.initialAge=0;

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

    public void veterinaryMiracle(){
        System.out.println("This might take a while. It wont be easy. FINGERS CROSSED!!!");
        Game.delay();
        System.out.println("The operation is still going...");
        Game.delay();
        if (Math.random() >= 0.5){
            System.out.println(this.animalName + " is saved!!!!");
        Game.delay();}
        else {
            System.out.println("\nUnfortunately, little "+this.animalName+ " was too sick and could not be saved.");
            this.die();
        }
    }
    public void ageing(){
        this.initialAge++;
        if(this.initialAge>=this.maxAge) {
            System.out.println(this.animalName+" is too old....");
            this.die();
        }
    }
    public void die(){
        System.out.println(ANSI_RED+ this.animalName.toUpperCase()+" is now dead"+ANSI_RESET);
        this.living=false;
        Game.delay();
    }

    public void animalGetSick(){
        this.sick= Math.random() < 0.2;
    }
}
