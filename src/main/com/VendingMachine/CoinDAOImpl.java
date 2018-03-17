package main.com.VendingMachine;

import main.com.VendingMachine.Coin;
import java.util.ArrayList;

public class CoinDAOImpl implements CoinDAO {
    Coin coinInUse;

    @Override
    public Coin[] getCoinTypes() {
        return Coin.values();
    }

    @Override
    public double getCoinValue() {
        switch (coinInUse){
            case DIME:
                return .5;
            case NICKEL:
                return .10;
            case QUARTER:
                return .25;
        }
        return 0;
    }
}


