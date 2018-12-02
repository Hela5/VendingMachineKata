package VendingMachine;

import com.VendingMachine.*;
import junit.textui.TestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.text.DecimalFormat;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Test
    public void testRespondWithProdInventoryAndIterate() {
        Controller controller = mock(Controller.class);
        when(controller.respondWithProdInventoryAndIterate(false, 2, 5)).thenReturn(false);
        assertEquals(false, controller.respondWithProdInventoryAndIterate(false, 2, 5));
        verify(controller).respondWithProdInventoryAndIterate(false, 2, 5);
    }

    @Test
    public void testAcceptableRangeForCoins() {
        Controller controller = mock(Controller.class);
        when(controller.acceptableRangeForCoins(11)).thenReturn(false);
        assertEquals(false, controller.acceptableRangeForCoins(11));
        verify(controller).acceptableRangeForCoins(11);
    }

    @Test
    public void testCheckForProdInventory() {
        Product prodTest = Product.COLA;
        Controller controller = mock(Controller.class);
        when(controller.checkProdInventory(prodTest)).thenReturn(true);
        assertEquals(true, controller.checkProdInventory(prodTest));
    }

    @Test
    public void testDeterminePaymentTotal() {
        Coin coinTest = Coin.QUARTER;
        Controller controller = mock(Controller.class);
        doNothing().when(controller).determinePaymentTotal(isA(Double.class), isA(Coin.class), isA(Integer.class));
        controller.determinePaymentTotal(2.0, coinTest, 11);
        verify(controller, times(1)).determinePaymentTotal(2.0, coinTest, 11);
    }

    @Test
    public void testCheckCoinInventory() {
        Coin coinTest = Coin.QUARTER;
        Controller controller = mock(Controller.class);
        when(controller.checkCoinInventory(coinTest)).thenReturn(true);
        assertEquals(true, controller.checkCoinInventory(coinTest));
    }

    @Test
    public void testMakeChange() {
        Controller controller = mock(Controller.class);
        doNothing().when(controller).makeChange(isA(Integer.class),isA(Integer.class),isA(Integer.class) );
        controller.makeChange(8,8,8);
        verify(controller, times(1)).makeChange(8,8,8);
    }

    @Test
    public void testResetCoinInventory() {
        Controller controller = mock(Controller.class);
        doNothing().when(controller).resetCoinInv(isA(Integer.class),isA(Integer.class),isA(Integer.class),isA(Integer.class),isA(Integer.class),isA(Integer.class));
        controller.resetCoinInv(3,4,5,6,4,3);
        verify(controller, times(1)).resetCoinInv(3,4,5,6,4,3);
    }


}
