package VendingMachine;

import main.com.VendingMachine.Coin;
import main.com.VendingMachine.CoinDAO;
import main.com.VendingMachine.CoinDAOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinDAOImplTest {

    @Test
    public void getListOfValidCoins(){
        Coin[] testCoin;
        testCoin = Coin.values();
        CoinDAOImpl testList = new CoinDAOImpl();
        assertArrayEquals(testCoin, testList.getCoinTypes());
    }
}
