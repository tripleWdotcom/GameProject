package com.company;

public class Unicorn extends Animal {
    public static int price = 1800;
    public static int vetPrice = 900;
    public static int maxAge = 30;




    public Unicorn(String name,String  gender){
        super(name,gender, price,vetPrice,maxAge);
    }

}
