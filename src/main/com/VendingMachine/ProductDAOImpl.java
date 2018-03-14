package main.com.VendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ProductDAOImpl implements ProductDAO {

    private HashMap<String, Product> productInfo = new HashMap<>();


    @Override
    public String getProductTypes() {
        String chips = "chips";
        return chips;
    }

    @Override
    public double getProductCost(String productType) {
        Product chip = new Product();
        chip.setProductCost(.50);
        return chip.getProductCost();
    }

    @Override
    public double getTotalCost(String productType) {
        return 0;
    }
}
