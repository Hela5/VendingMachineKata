package VendingMachine;

import main.com.VendingMachine.Coin;
import main.com.VendingMachine.CoinDAO;
import main.com.VendingMachine.CoinDAOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sun.misc.Version.print;

public class CoinDAOImplTest {

    @Test
    public void getListOfValidCoins(){
        Coin[] testCoin;
        testCoin = Coin.values();
        CoinDAOImpl testList = new CoinDAOImpl();
        assertArrayEquals(testCoin, testList.getCoinTypes());
    }

    @Test
    public void getDimeValue() {
        Coin dime = Coin.DIME;
        CoinDAOImpl dimeTest = new CoinDAOImpl();
        double dimeValue = .1;
        assertEquals(dimeValue, dimeTest.getCoinValue(dime));

    }
}
