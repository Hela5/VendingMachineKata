package com.VendingMachine;

import java.util.ArrayList;

public interface ProductDAO {

    Product[] getProductTypes();
    double getProductCost();
    int getProductInventory(Product productType);
    void setProductInventory(int productInventory);


}
