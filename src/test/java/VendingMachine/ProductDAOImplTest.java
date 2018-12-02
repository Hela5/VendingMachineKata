package VendingMachine;

import com.VendingMachine.Product;
import com.VendingMachine.ProductDAOImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductDAOImplTest {
    @Test
    public void testCanGetAProduct() {
        ProductDAOImpl productDAOTest = mock(ProductDAOImpl.class);
        Product[] testProduct = Product.values();
        when(productDAOTest.getProductTypes()).thenReturn(testProduct);
        assertArrayEquals(testProduct, productDAOTest.getProductTypes());
        verify(productDAOTest, times(1)).getProductTypes();
    }

    @Test
    public void testCanGetProductCost() {
        ProductDAOImpl productDAOTest = mock(ProductDAOImpl.class);
        when(productDAOTest.getProductCost()).thenReturn(.50);
        assertEquals(.50, productDAOTest.getProductCost());
        verify(productDAOTest, times(1)).getProductCost();

    }

    @Test
    public void testCanGetInventory(){
        ProductDAOImpl productDAOTest = mock(ProductDAOImpl.class);
        Product candy = Product.CANDY;
        when(productDAOTest.getProductInventory(candy)).thenReturn(10);
        assertEquals(10,productDAOTest.getProductInventory(candy));
        verify(productDAOTest, times(1)).getProductInventory(candy);
    }

    @Test
    public void testSetInventory(){
        ProductDAOImpl productDAOTest = mock(ProductDAOImpl.class);
        doNothing().when(productDAOTest).setProductInventory(9);
        productDAOTest.setProductInventory(9);
        verify(productDAOTest, times(1 )).setProductInventory(9);
    }

}
