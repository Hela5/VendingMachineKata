package main.com.VendingMachine;

import main.com.VendingMachine.Coin;
import java.util.ArrayList;

public class CoinDAOImpl implements CoinDAO {

    @Override
    public Coin[] getCoinTypes() {
        return Coin.values();
    }

    @Override
    public double getCoinValue() {
        return this.getCoinValue();
    }

    @Override
    public int getCoinInventory(Coin cointype) {
        return this.getCoinInventory(cointype);
    }

    @Override
    public void setCoinInventory(int coinInv) {
        this.setCoinInventory(coinInv);
    }
}


