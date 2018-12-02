package com.VendingMachine;
import java.text.DecimalFormat;

public class App {

    public static void main(String[] args){
        ProductDAOImpl prodDAO = new ProductDAOImpl();
        CoinDAOImpl coinDAO = new CoinDAOImpl();
        DecimalFormat df = new DecimalFormat();
        ConsoleIO cons = new ConsoleIO();

        Controller controller = new Controller(cons, coinDAO, prodDAO, df);
        controller.run();
    }


}
