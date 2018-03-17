package main.com.VendingMachine;

import java.util.ArrayList;

public interface ProductDAO {

    Product[] getProductTypes();
    double getProductCost(Product productType);
    int getProductInventory(Product productType);

}
