package VendingMachine;

import static junit.framework.TestCase.assertEquals;

import main.com.VendingMachine.Product;
import org.junit.jupiter.api.Test;

public class OrderDAOImplTest {

    Product testPrd = Product.CHIPS;
    Order order1 = new Order();
    OrderDAOImpl testOrd = new OrderDAOImpl();

        @Test
    public void canAddOrder(){
            int orderIDT = 2;
            order1.setOrderID(orderIDT);
            order1.setProductType(testPrd);
            order1.setNumProductsPurchased(2);
            order1.setTotalCost(1.00);
        testOrd.addOrder(order1);
        Order result = testOrd.getOrder(2);
        assertEquals(order1, result);

    }

/*    @Test
    public void canRemoveOrder(){
        order1.setOrderID(1);
        order1.setProductType(testPrd);
        order1.setNumProductsPurchased(2);
        order1.setTotalCost(1.00);
    }*/


}
