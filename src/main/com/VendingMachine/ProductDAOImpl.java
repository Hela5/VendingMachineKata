package main.com.VendingMachine;


public class ProductDAOImpl implements ProductDAO {

    @Override
    public Product[] getProductTypes() {
        return Product.values();
    }

    @Override
    public double getProductCost() {
        return this.getProductCost();
    }

    @Override
    public int getProductInventory(Product productType) {
       return this.getProductInventory(productType);
    }

    @Override
    public void setProductInventory(int productInventory) {
        this.setProductInventory(productInventory);
    }

}
