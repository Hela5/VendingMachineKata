package main.com.VendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ProductDAOImpl implements ProductDAO {


    @Override
    public String getProductTypes() {
        String chips = "chips";
        return chips;
    }

    @Override
    public double getProductCost(String productType) {
        return 0;
    }

    @Override
    public double getTotalCost(String productType) {
        return 0;
    }
}
