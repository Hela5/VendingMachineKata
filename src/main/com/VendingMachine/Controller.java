package main.com.VendingMachine;

import java.text.DecimalFormat;

public class Controller {

    ConsoleIO cons = new ConsoleIO();
    boolean keepRunning = true;
    ProductDAOImpl prodD = new ProductDAOImpl();
    CoinDAOImpl coinD = new CoinDAOImpl();
    DecimalFormat df = new DecimalFormat("#.00");

    double cost;
    double totalCost;

    double indivInput;
    double totalInput;

    public void run() {
        displaymenu();

        do {
            int userChoice;
            try {
                userChoice = cons.queryUserIntRange("Please type the number that best suits you from the Main Menu: ", 1, 3);

            switch (userChoice) {
                case 1:
                    cons.displayUserString("\n ___ Today's Selections are: _____\n");
                    displayAllProducts();
                    cons.displayUserString("\n _________________________________ \n ");
                    break;
                case 2:
                    displayProductAvailability();
                    purchaseAProduct();
                    cons.displayUserString("\n ------ We accept these methods of payment ------ \n");
                    methodsOfPayment();
                    selectPaymentMethod();
                    cons.displayUserString("\nThanks for shopping with us today!");
                    break;
                case 3:
                    cons.displayUserString("Thanks for stopping by! ");
                    keepRunning = false;
                    break;
                default:
                    cons.displayUserString("That appears to be an invalid option. \n Please try again. ");
            }
            } catch (Exception e) {
                cons.displayUserString("Exception caught, please try again at a later time.");
                keepRunning = false;
            }
        } while (keepRunning);


    }

    private void displaymenu() {
        cons.displayUserString("\n**********************************");
        cons.displayUserString("*         Welcome to the         *");
        cons.displayUserString("*        Vending Machine!        *");
        cons.displayUserString("*                                *");
        cons.displayUserString("*        -- Main Menu --         *");
        cons.displayUserString("*                                *");
        cons.displayUserString("*       Would you like to:       *");
        cons.displayUserString("*    1. View Product List        *");
        cons.displayUserString("*    2. Purchase a Product       *");
        cons.displayUserString("*    3. Exit                     *");
        cons.displayUserString("*                                *");
        cons.displayUserString("**********************************\n");

    }

    private void purchaseAProduct() {
        boolean onceAgain = true;
        do {
            String resp = cons.queryUserString("\nPlease type your selected product!  ");
            if (resp.equalsIgnoreCase("CHIPS")) {
                cost = prodD.getProductCost(Product.CHIPS);
                totalCost = cost + totalCost;
            } else if (resp.equalsIgnoreCase("COLA")) {
                cost = prodD.getProductCost(Product.COLA);
                totalCost = cost + totalCost;
            } else if (resp.equalsIgnoreCase("CANDY")) {
                cost = prodD.getProductCost(Product.CANDY);
                totalCost = cost + totalCost;
            } else {
                cons.displayUserString("I'm sorry, that seems to be an invalid option. Please try and type it again.");
            }

            cons.displayUserString("Your current total is: " + "$ " + totalCost);
            String keepGoing = cons.queryUserString("Would you like anything else today? Y or N ");
            if (keepGoing.equalsIgnoreCase("Y")) {
                onceAgain = true;
            } else if (keepGoing.equalsIgnoreCase("N")) {
                onceAgain = false;
            }
        } while (onceAgain);
    }

    private void displayAllProducts() {
        Product[] prodsAvail = prodD.getProductTypes();
        for (Product currentProd : prodsAvail) {
            cons.displayUserString( "\t" + currentProd.toString() + " --  COST : $ " + prodD.getProductCost(currentProd));
        }
    }

    private void displayProductAvailability() {
        Product[] prods = prodD.getProductTypes();
        for (Product currentProd : prods) {
            if (prodD.getProductInventory(currentProd) > 0) {
                cons.displayUserString("\t" + currentProd.toString() + "   is available");
            } else if (prodD.getProductInventory(currentProd) <= 0) {
                cons.displayUserString("\t" + currentProd.toString() + "is currently unavailable. Sorry!");
            }
        }
    }

    private void methodsOfPayment() {
        Coin[] coins = coinD.getCoinTypes();
        for (Coin currentCoin : coins) {
            cons.displayUserString("\t" + currentCoin.toString());
        }
        cons.displayUserString("\n  ");
    }

    public void selectPaymentMethod() {
        boolean needMoreChange = false;
        boolean validCoin;
        String coinType;
        Coin coinRealName = null;
        String returnCoins;

        cons.displayUserString("If you want your coins returned. Please say RETURN.");

        do {
           do {
               coinType = cons.queryUserString("\nWhich coin would you like to start adding in?");
               if (coinType.equalsIgnoreCase("QUARTER")) {
                   coinRealName = Coin.QUARTER;
                   validCoin = true;
               } else if (coinType.equalsIgnoreCase("DIME")) {
                   coinRealName = Coin.DIME;
                   validCoin = true;
               } else if (coinType.equalsIgnoreCase("NICKEL")) {
                   coinRealName = Coin.NICKEL;
                   validCoin = true;
               } else if(coinType.equalsIgnoreCase("RETURN")){
                   cons.displayUserString("We are sorry to hear that. All coins are being returned. ");
                   indivInput = 0;
                   break;
               }
               else  {
                   validCoin = false;
               }
               if(!validCoin) {
                   cons.displayUserString("Sorry, we can't accept that. Please try again!");
                   validCoin = false;
               }

           } while (!validCoin);

            indivInput = coinD.getCoinValue(coinRealName);

            int numOfCoinType = cons.queryUserInt("How many coins would you like to put in?");
            if (acceptableRangeForCoins(numOfCoinType)) {
                cons.displayUserString("We are returning your coins.");
                numOfCoinType = 0;
            }
            totalInput = (indivInput * numOfCoinType) + totalInput;

            cons.displayUserString("You inserted " + numOfCoinType + "  " + coinRealName + "S ... a total of $" + df.format(totalInput));
            cons.displayUserString("Your total cost is $" + totalCost);

            if (totalInput < totalCost) {
                cons.displayUserString("We need more change!");
                needMoreChange = true;
            } else if (totalInput > totalCost) {
                cons.displayUserString("Great! Generating your change. ");
                needMoreChange = false;
                makeChange();
                totalCost = 0;
                totalInput = 0;
            } else if (totalCost == totalInput) {
                needMoreChange = false;
                totalCost = 0;
                totalInput = 0;
            }
        }
        while (needMoreChange) ;
    }

        public void makeChange(){
            double remainder = totalInput - totalCost;
            remainder = remainder * 100;
                cons.displayUserString("You get $" + df.format(remainder / 100));
                double numQuarters = remainder / 25;

                remainder = remainder % 25;
                double numDimes = remainder / 10;
                remainder = remainder % 10;
                double numNickels = remainder / 5;
                DecimalFormat intStyle = new DecimalFormat("#0");
                cons.displayUserString( intStyle.format(numQuarters) + " Quarters back\n" + intStyle.format(numDimes) + " Dimes back \n" + intStyle.format(numNickels) + " Nickels back");
    }

        public boolean acceptableRangeForCoins(int coinsIn){
        boolean tooMany = false;
        if (coinsIn < 1 || coinsIn > 10){
            cons.displayUserString("We can't make that much change. Please lower your input to less than 10.");
            tooMany = true;
        }
        return tooMany;
        }

    }

