package VendingMachine;

import main.com.VendingMachine.Product;
import main.com.VendingMachine.ProductDAO;
import main.com.VendingMachine.ProductDAOImpl;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProductDAOImplTest {
    @Test
    public void canGetAProduct() {
        Product[] testProduct;
        testProduct = Product.values();
        ProductDAOImpl chipTest = new ProductDAOImpl();
        assertArrayEquals(testProduct, chipTest.getProductTypes());
    }

    @Test
    public void canGetProductCost() {
        Product chp = Product.CHIPS;
        ProductDAOImpl prodCostTest = new ProductDAOImpl();
        double chipsCost = .50;
        assertEquals(chipsCost, prodCostTest.getProductCost(chp));


    }

    @Test
    public void canGetInventory(){
        Product candy = Product.CANDY;
        ProductDAOImpl candyInvTest = new ProductDAOImpl();
        int candyCount = 10;
        assertEquals(candyCount, candyInvTest.getProductInventory(candy));
    }

}
