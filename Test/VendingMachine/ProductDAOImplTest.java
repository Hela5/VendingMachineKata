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
        double chp = Product.CHIPS.getProductCost();
        double chipsCost = .50;
        assertEquals(chipsCost, chp);


    }

    @Test
    public void canGetInventory(){
        int candy = Product.CANDY.getProductInventory();
        int candyCount = 10;
        assertEquals(candyCount,candy);
    }

    @Test
    public void setInventory(){
        Product.COLA.setProductInventory(7);
        int diff = Product.COLA.getProductInventory();
        int newColaInv = 7;
        assertEquals(newColaInv, diff);
    }

}
