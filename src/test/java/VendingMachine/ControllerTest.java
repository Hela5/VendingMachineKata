package VendingMachine;

import com.VendingMachine.CoinDAOImpl;
import com.VendingMachine.ConsoleIO;
import com.VendingMachine.Controller;
import com.VendingMachine.ProductDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.DecimalFormat;

public class ControllerTest {

    private ProductDAOImpl prodDAOT;
    private CoinDAOImpl coinDAOT;
    private DecimalFormat dfT;
    private ConsoleIO consT;


    @Mock
    private Controller controller = new Controller(consT, coinDAOT, prodDAOT, dfT );


    @Before
    public void setup() {
        prodDAOT = new ProductDAOImpl();
    }

    @Test
    public void displayProductsTest() {
        Mockito.when(controller.displayAllProducts(););
        }

    @Test
    public void displayMethodsOfPaymentTest() {

    }

    @Test
    public void selectPaymentMethodTest() {

    }

}
