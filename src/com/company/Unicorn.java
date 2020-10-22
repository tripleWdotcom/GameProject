package com.company;

public class Unicorn extends Animal {
    public static int price = 1800;
    public static int vetPrice = price/2;
    public static int maxAge = 20;




    public Unicorn(String name,String  gender){
        super(name,gender, price,vetPrice,maxAge);
    }

}
