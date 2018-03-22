package main.com.VendingMachine;

public enum Product {

    CHIPS,
    COLA,
    CANDY;

   // private String productType;

    private int productInventory;

    private double productCost;
/*    Product(double productCost){
        this.productCost = productCost;
    }*/


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
