package VendingMachine;

import main.com.VendingMachine.Product;
import main.com.VendingMachine.ProductDAO;
import main.com.VendingMachine.ProductDAOImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProductDAOImplTest {
    @Test
    public void canGetAProduct() {
        ArrayList<String> sampleList = new ArrayList<>();
        sampleList.add("chips");
        sampleList.add("cola");
        sampleList.add("candy");
        ProductDAOImpl chipTest = new ProductDAOImpl();
        assertArrayEquals(sampleList.toArray(), chipTest.getProductTypes().toArray());
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
