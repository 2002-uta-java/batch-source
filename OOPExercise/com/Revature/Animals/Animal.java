package com.Revature.Animals;

public abstract class Animal{

    public Animal(){
        super();
    }

    private boolean living;

    public boolean isAlive(){
        return living;
    }

    abstract void makeNoise();

}