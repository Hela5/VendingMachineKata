package main.com.VendingMachine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ProductDAOImpl implements ProductDAO {

    //private HashMap<String, Product> productInfo = new HashMap<>();
    ArrayList<String> list = new ArrayList<>();

    @Override
    public ArrayList getProductTypes() {
        list.add("chips");
        list.add("cola");
        list.add("candy");
        return list;
    }

    @Override
    public double getProductCost(String productType) {
        Product chip = new Product();
        chip.setProductCost(.50);
        return chip.getProductCost();
    }

}
