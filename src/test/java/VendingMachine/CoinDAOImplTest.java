package VendingMachine;

import com.VendingMachine.Coin;
import com.VendingMachine.CoinDAOImpl;
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

        @Test
        public void getDimeValue() {
            double dime = Coin.DIME.getCoinValue();
            double dimeValue = .1;
            assertEquals(dimeValue, dime);

        }

        @Test
        public void getCoinInv() {
            int quarterInv = Coin.QUARTER.getCoinInventory();
            int numQuarters = 50;
            assertEquals(numQuarters, quarterInv);
        }

        @Test
        public void setCoinInv() {
            Coin.DIME.setCoinInventory(15);
            int diffInv = Coin.DIME.getCoinInventory();
            int newInvResults = 15;
            assertEquals(newInvResults, diffInv);
        }
    }
