package VendingMachine;

import main.com.VendingMachine.ProductDAOImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


public class ProductDAOTest {
    @Test
    public void canGetAProduct() {
        String chips = "chips";
        ProductDAOImpl chipTest = new ProductDAOImpl();
        assertEquals(chips, chipTest.getProductTypes());
    }

}
