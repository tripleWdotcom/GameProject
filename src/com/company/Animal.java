package com.company;

public abstract class Animal {
    public String animalName;
    public double health;
    public boolean living;
    public Gender gender;

    enum Gender{
        MALE,
        FEMALE,
    }


    public Animal(String animalName,String  gender){
        this.health=health;
        this.animalName=animalName;
        this.living=true;
        this.gender= Gender.valueOf(gender.toUpperCase());

    }

    public  int randomHealthDecrease(double health) {
        double random = (int)(Math.random() * 20) + 11;
        random=100-random;
        health*=(random/100);
        int hp =(int) health;
        return hp;
    }

    public  void checkHealth(int health){
        if(health<=2)
            System.out.println(this.animalName + " is in critical condition, it will die next round.");
        if(health<1)
            this.die();

    }

    public void die(){
        System.out.println(this.animalName+" is now dead");
        this.living=false;
    }

    public void mate(Animal animalToMate){
        if(!this.living)
            System.out.println(this.animalName+ " Cant do that. im dead");
        if(!animalToMate.living)
            System.out.println("That is Necrophilia. it is  illegal!!!");
        if(!this.getClass().equals(animalToMate.getClass()))
            System.out.println("Different species. that wont work out.Please select the same species");
        if(this.gender!=animalToMate.gender){

            //create new animal
        }


    }



}
