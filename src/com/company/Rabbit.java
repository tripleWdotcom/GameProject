package com.company;

public class Rabbit extends Animal {
    public static int price = 300;
    public static int vetPrice = price/2;
    public static int maxAge = 9;




    public Rabbit(String name,String  gender){
        super(name,gender, price,vetPrice,maxAge);
    }
}
