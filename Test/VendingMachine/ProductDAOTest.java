package VendingMachine;

import main.com.VendingMachine.Product;
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

    @Test
    public void canGetProductCost() {
        String chips = "chips";
        Product chp = new Product();
        chp.setProductType(chips);
        chp.setProductCost(.50);
        ProductDAOImpl prodCostTest = new ProductDAOImpl();
        assertEquals(chp.getProductCost(), prodCostTest.getProductCost(chips));


    }

}
