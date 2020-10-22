package com.company;

public class Dog extends Animal {
    public static int price = 480;
    public static int vetPrice = price/2;
    public static int maxAge = 10;




    public Dog(String name,String  gender){
        super(name,gender, price,vetPrice,maxAge);
    }
}
