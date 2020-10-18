package com.company;

import javax.naming.MalformedLinkException;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Store {
    Scanner scan=new Scanner(System.in);

    public Animal buyAnimal(Player PlayerName, int opt){
        Animal animalToReturn=null;
        switch (opt){
            case 1 -> animalToReturn= new Dog(nameToAnimal(),genderToAnimal());
            case 2 -> animalToReturn=new Crocodile(nameToAnimal(),genderToAnimal());
            case 3 -> animalToReturn=new Condor(nameToAnimal(),genderToAnimal());
            case 4 -> animalToReturn=new Unicorn(nameToAnimal(),genderToAnimal());
            case 5 -> animalToReturn=new Rabbit(nameToAnimal(),genderToAnimal());
        }
        return animalToReturn;
    }

    public String nameToAnimal(){
        System.out.println("Name it: ");
        var nameToAnimal=scan.next();
        return nameToAnimal;
    }

    public String genderToAnimal(){
        System.out.println("Choose (1) MALE or (2) FEMALE");
        String chosenGender=null;
        int option=scan.nextInt();
        switch (option) {
            case 1-> chosenGender=Animal.Gender.MALE.toString();
            case 2-> chosenGender= Animal.Gender.FEMALE.toString();
            default -> genderToAnimal();
        }
        return chosenGender;
        }

    public Animal buyFood(Player PlayerName, int opt){
        Animal animalToReturn=null;
        switch (opt){
            case 1 -> new BigPizza();
            case 2 -> new Burger();
            case 3 -> new Salad();
        }
        return animalToReturn;
    }


    }



