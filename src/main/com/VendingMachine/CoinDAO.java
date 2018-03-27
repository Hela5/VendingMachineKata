package main.com.VendingMachine;

import java.util.ArrayList;

public interface CoinDAO {

    Coin[] getCoinTypes();
    double getCoinValue();
    int getCoinInventory(Coin cointype);
    void setCoinInventory(int coinInv);

}
