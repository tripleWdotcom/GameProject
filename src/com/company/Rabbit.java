package com.company;

public class Rabbit extends Animal {
    public static int price = 300;
    public static int vetPrice = 150;
    public static int maxAge = 7;




    public Rabbit(String name,String  gender){
        super(name,gender, price,vetPrice,maxAge);
    }
}
