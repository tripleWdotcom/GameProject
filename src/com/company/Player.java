package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    public String name;
    public int money;
    public ArrayList<Animal> haveAnimal = new ArrayList<>();
    //public ArrayList<Food> haveFood = new ArrayList<Food>();
    public HashMap<String,Integer> haveFood= new HashMap<>();

    public Player(String name){
        this.name = name;
        this.money= 10000;

    }
    public  void feedAnimal(Animal animalToFeed,int kg, String feedingFood){
        animalToFeed.health+=kg*animalToFeed.getCurrentHealth();
        haveFood.keySet();

    }
    public  boolean toMate(Animal animalToMate1, Animal AnimalToMate2){
        var possible=false;
        if(!AnimalToMate2.getClass().equals(animalToMate1.getClass())) {
            System.out.println("Different species. that wont work out.Please select the same species");
            Game.delay();
            possible=false;
        }
        if(AnimalToMate2.gender!=animalToMate1.gender){
            var newAnimal=animalToMate1.getClass().getSimpleName();
            possible=true;
        }
        return possible;
    }
}
