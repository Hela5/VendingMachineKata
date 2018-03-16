package main.com.VendingMachine;

public class Product {

    private String productType;
    private double productCost;
    private double totalCost;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

}
