package main.com.VendingMachine;


public class ProductDAOImpl implements ProductDAO {


    @Override
    public Product[] getProductTypes() {
        return Product.values();
    }

    @Override
    public double getProductCost(Product productType) {
/*        double pCost = Product.valueOf(productType);
        return pCost;*/
        double productCost;
        switch (productType){
            case CANDY:
                productCost = .65;
                break;
            case COLA:
                productCost = 1.00;
                break;
            case CHIPS:
                productCost = .50;
                break;
            default:
                productCost = 0;
                break;
        }
        return productCost;
    }

    @Override
    public int getProductInventory(Product productType) {
        int currentInventory;
        //int newInventory;
        switch (productType){
            case CHIPS:
                currentInventory = 10;
                break;
            case COLA:
                currentInventory = 10;
                break;
            case CANDY:
                currentInventory = 10;
                break;
                default:
                    currentInventory = 0;
                    break;
        }
        return currentInventory;

    }

}
