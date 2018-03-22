package main.com.VendingMachine;

public enum Coin {

    QUARTER, DIME, NICKEL;

    private double coinValue;

    public double getCoinValue(Coin coinType) {
        return coinValue;
    }

    public void setCoinValue(double coinValue) {
        this.coinValue = coinValue;
    }

}
