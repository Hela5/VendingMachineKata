package com.VendingMachine;

public enum Coin {

    QUARTER(.25),
    DIME(.10),
    NICKEL(.05);

    private int coinInventory = 50;
    private double coinValue;
    Coin (double coinValue){
        this.coinValue = coinValue;
    }

    public double getCoinValue() {
        return coinValue;
    }

    public int getCoinInventory() {
        return coinInventory;
    }

    public void setCoinInventory(int coinInv){
        this.coinInventory = coinInv;
    }

}
