package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    public String name;
    public int money;
    public ArrayList<Animal> haveAnimal = new ArrayList<Animal>();
    //public ArrayList<Food> haveFood = new ArrayList<Food>();
    public HashMap<String,Integer> haveFood= new HashMap<String, Integer>();

    public Player(String name){
        this.name = name;
        this.money= 10000;

    }


    public static void feedAnimal(Player player){

    }







}
