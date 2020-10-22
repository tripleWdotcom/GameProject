package com.company;

public class Crocodile extends Animal {
    public static int price = 760;
    public static int vetPrice = price/2;
    public static int maxAge = 12;




    public Crocodile(String name,String  gender){
        super(name,gender, price,vetPrice,maxAge);

    }
}
