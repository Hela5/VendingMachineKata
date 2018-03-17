package main.com.VendingMachine;

public enum Product {

    CHIPS, COLA, CANDY;

   // private String productType;
    private double productCost;
    private int productInventory;

/*
    public String getProductTypes() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
*/

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public int getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(int productInventory){
        this.productInventory = productInventory;
    }

}
