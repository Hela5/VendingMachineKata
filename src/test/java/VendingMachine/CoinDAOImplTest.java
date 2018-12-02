package VendingMachine;

import com.VendingMachine.*;
import com.VendingMachine.Coin;
import com.VendingMachine.CoinDAOImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoFramework.*;

@RunWith(MockitoJUnitRunner.class)
public class CoinDAOImplTest {

        @Test
        public void testGetListOfValidCoins(){
            Coin[] testCoin = Coin.values();
            CoinDAOImpl testList = mock(CoinDAOImpl.class);
            when(testList.getCoinTypes()).thenReturn(testCoin);
            assertEquals(testCoin, testList.getCoinTypes());
            verify(testList, times(1)).getCoinTypes();
        }

        @Test
        public void testGetDimeValue() {
            CoinDAOImpl coinDTest = mock(CoinDAOImpl.class);
            when(coinDTest.getCoinValue()).thenReturn(.1);
            assertEquals(.1, coinDTest.getCoinValue());
            verify(coinDTest, times(1)).getCoinValue();

        }

        @Test
        public void testGetCoinInv() {
            Coin quarterInv = Coin.QUARTER;
            CoinDAOImpl coinDTest = mock(CoinDAOImpl.class);
            when(coinDTest.getCoinInventory(quarterInv)).thenReturn(50);
            assertEquals(50, coinDTest.getCoinInventory(quarterInv));
            verify(coinDTest, times(1)).getCoinInventory(quarterInv);
        }

        @Test
        public void testSetCoinInv() {
            CoinDAOImpl coinDTest = mock(CoinDAOImpl.class);
            doNothing().when(coinDTest).setCoinInventory(isA(Integer.class));
            coinDTest.setCoinInventory(15);
            verify(coinDTest, times(1)).setCoinInventory(15);
         }
    }
