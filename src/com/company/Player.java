package com.company;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Collections;
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
    public  void feedAnimal(Animal animalToFeed,double kg, String feedingFood){
        if(animalToFeed instanceof Dog)
            switch (feedingFood){
                case "Big Pizza", "Burger" ->animalToFeed.health+=kg*10;
            }
        if(animalToFeed instanceof Crocodile)
            if(feedingFood.equalsIgnoreCase("Burger")){
                animalToFeed.health+=kg*5;
            }
        if(animalToFeed instanceof Condor)
            switch (feedingFood){
                case "Burger","Salad" ->animalToFeed.health+=kg*5;
            }
        if(animalToFeed instanceof Unicorn)
            if(feedingFood.equalsIgnoreCase("Big Pizza")){
                animalToFeed.health+=kg*15;
            }
        if(animalToFeed instanceof Rabbit)
            if(feedingFood.equalsIgnoreCase("Salad")) {
                animalToFeed.health += kg * 20;

            }
        if(animalToFeed.health>100)
            animalToFeed.health=125;
    }


    public  boolean toMate(Animal animalToMate1, Animal AnimalToMate2){
        var possible=false;
        if(!AnimalToMate2.getClass().equals(animalToMate1.getClass())) {
            System.out.println("Different species. that wont work out.Please select the same species");
            Game.delay();
            Game.clear();
        }
        if(AnimalToMate2.getClass().equals(animalToMate1.getClass())&&(AnimalToMate2.gender!=animalToMate1.gender)){
            possible=true;
        }
        if((AnimalToMate2.getClass().equals(animalToMate1.getClass())&&(AnimalToMate2.gender==animalToMate1.gender))){
            System.out.println("Pick different genders PLEASE");
            Game.delay();
            Game.clear();
        }
        return possible;
    }
}
