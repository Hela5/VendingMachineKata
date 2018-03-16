package main.com.VendingMachine;

import java.util.ArrayList;

public interface ProductDAO {

    ArrayList getProductTypes();
    double getProductCost(String productType);

}
