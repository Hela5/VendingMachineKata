package main.com.VendingMachine;

import main.com.VendingMachine.Coin;
import java.util.ArrayList;

public class CoinDAOImpl implements CoinDAO {

    @Override
    public Coin[] getCoinTypes() {
        return Coin.values();
    }

    @Override
    public double getCoinValue(Coin coinType) {
        double coinValue;
        switch (coinType){
            case DIME:
                coinValue = .10;
                break;
            case NICKEL:
                coinValue = .05;
                break;
            case QUARTER:
                coinValue = .25;
                break;
            default:
                coinValue = 0;
        }
        return coinValue;
    }
}


