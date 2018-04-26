package com.VendingMachine;
import com.VendingMachine.ProductDAOImpl;

public class App {

    public static void main(String[] args){
        ProductDAOImpl prodDAO = new ProductDAOImpl();

        Controller controller = new Controller(prodDAO);
        controller.run();
    }


}
