package com.company;

public class Condor extends Animal {
    public static int price = 1300;
    public static int vetPrice = price/2;
    public static int maxAge = 20;




    public Condor(String name,String  gender){
        super(name,gender, price,vetPrice,maxAge);
    }
}
