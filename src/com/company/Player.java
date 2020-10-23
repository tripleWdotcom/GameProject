package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    public String name;
    public int money;
    public ArrayList<Animal> haveAnimal = new ArrayList<>();
    public HashMap<String,Integer> haveFood= new HashMap<>();

    public Player(String name){
        this.name = name;
        this.money= 10000;

    }
    public  boolean feedAnimal(Animal animalToFeed,double kg, String feedingFood){
        var correctFood=false;
        if(animalToFeed instanceof Dog)
            switch (feedingFood){
                case "Big Pizza", "Burger" ->{animalToFeed.health+=kg*10;
                    correctFood=true;
                }
            }
        if(animalToFeed instanceof Crocodile)
            if(feedingFood.equalsIgnoreCase("Burger")){
                animalToFeed.health+=kg*5;
                correctFood=true;

            }
        if(animalToFeed instanceof Condor)
            switch (feedingFood){
                case "Burger","Salad" ->{animalToFeed.health+=kg*5;
                    correctFood=true;
                }
            }
        if(animalToFeed instanceof Unicorn)
            if(feedingFood.equalsIgnoreCase("Big Pizza")){
                animalToFeed.health+=kg*15;
                correctFood=true;
            }
        if(animalToFeed instanceof Rabbit)
            if(feedingFood.equalsIgnoreCase("Salad")) {
                animalToFeed.health += kg * 20;
                correctFood=true;
            }
        if(animalToFeed.health>100)
            animalToFeed.health=125; // it gives close to 100% (animals lose 10-30% each round)
        return correctFood;
    }


    public  boolean toMate(Animal animalToMate1, Animal animalToMate2){
        var possible=false;
        if(!animalToMate2.getClass().equals(animalToMate1.getClass())) {
            System.out.println("Different species. that wont work out.Please select the same species");
            Game.delay();
            Game.clear();
        }
        if(animalToMate2.getClass().equals(animalToMate1.getClass())&&(animalToMate2.gender!=animalToMate1.gender)){
            if(animalToMate2.initialAge>3 && animalToMate1.initialAge>3)
                possible=true;
            else{
                System.out.println("Animals too YOUNG to mate. Wait 1-2 years");
                Game.delay();
                Game.clear();
            }
        }
        if((animalToMate2.getClass().equals(animalToMate1.getClass())&&(animalToMate2.gender==animalToMate1.gender))){
            System.out.println("Pick different genders PLEASE");
            Game.delay();
            Game.clear();
        }
        return possible;
    }
}
