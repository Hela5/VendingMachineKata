package main.com.VendingMachine;

import java.util.ArrayList;

public interface ProductDAO {

    String getProductTypes();
    double getProductCost(String productType);
    double getTotalCost(String productType);
}
