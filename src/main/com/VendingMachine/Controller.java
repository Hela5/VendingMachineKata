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

    Product prodType;
    int numProdPurchased;
    int resetInv;

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
                    numProdPurchased = 0;
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
        boolean suffProd;
        boolean onceAgain = true;
         do {
            String resp = cons.queryUserString("\nPlease type your selected product!  ");
            switch (resp.toUpperCase()) {
                case "CHIPS":
                    prodType = Product.CHIPS;
                    cost = prodType.getProductCost();
                    totalCost = cost + totalCost;
                    numProdPurchased++;
                    break;
                case "COLA":
                    prodType = Product.COLA;
                    cost = prodType.getProductCost();
                    totalCost = cost + totalCost;
                    numProdPurchased++;
                     break;
                case "CANDY":
                    prodType = Product.CANDY;
                    cost = prodType.getProductCost();
                    totalCost = cost + totalCost;
                    numProdPurchased++;
                    break;
                default:
                    cons.displayUserString("I'm sorry, that seems to be an invalid option. Please try and type it again.");
            }
            suffProd = checkProdInventory(prodType);
            if (suffProd) {
            cons.displayUserString("Your current total is: " + "$ " + totalCost + "\nTotal Items To Purchase " + numProdPurchased);
            String keepGoing = cons.queryUserString("Would you like anything else today? Y or N ");
            if (keepGoing.equalsIgnoreCase("Y")) {
                onceAgain = true;
            } else if (keepGoing.equalsIgnoreCase("N")) {
                onceAgain = false;
            }}
            else {
                cons.displayUserString("Product selected is SOLD OUT. Please select something else today.");
                onceAgain = true;
            }
        } while (onceAgain);
    }

    private void displayAllProducts() {
        Product[] prodsAvail = prodD.getProductTypes();
        for (Product currentProd : prodsAvail) {
            cons.displayUserString( "\t" + currentProd.toString() + " --  COST : $ " + currentProd.getProductCost());
        }
    }

    private void displayProductAvailability() {
        Product[] prods = prodD.getProductTypes();
        for (Product currentProd : prods) {
            if (currentProd.getProductInventory() > 0) {
                cons.displayUserString("\t" + currentProd.toString() + "   is available");
            } else if (currentProd.getProductInventory() <= 0) {
                cons.displayUserString("\t" + currentProd.toString() + " is currently unavailable. Sorry!");
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
        int qCount = 0;
        int dCount = 0;
        int nCount = 0;
        boolean needMoreChange = false;
        boolean validCoin;
        boolean suffCoin = true;
        String coinType;
        Coin coinRealName = null;

        cons.displayUserString("If you want your coins returned. Please say RETURN.");

        do {
            do {
                coinType = cons.queryUserString("\nWhich coin would you like to start adding in?");
                switch (coinType.toUpperCase()) {
                    case "QUARTER":
                        coinRealName = Coin.QUARTER;
                        validCoin = true;
                        break;
                    case "DIME":
                        coinRealName = Coin.DIME;
                        validCoin = true;
                        break;
                    case "NICKEL":
                        coinRealName = Coin.NICKEL;
                        validCoin = true;
                        break;
                    case "RETURN":
                        cons.displayUserString("We are sorry to hear that. All coins are being returned. ");
                        indivInput = 0;
                        validCoin = false;
                        break;
                    default:
                        validCoin = false;
                }

                if (!validCoin) {
                    cons.displayUserString("Sorry, we can't accept that. Please try again!");
                    validCoin = false;
                }

            } while (!validCoin);

            indivInput = coinRealName.getCoinValue();
            suffCoin = checkCoinInventory(coinRealName);
            if (suffCoin) {

                int numOfCoinType = cons.queryUserInt("How many coins would you like to put in?");
                if (acceptableRangeForCoins(numOfCoinType)) {
                    cons.displayUserString("We are returning your coins.");
                    numOfCoinType = 0;
                }

                switch (coinRealName){
                    case QUARTER:
                        qCount = numOfCoinType;
                        break;
                    case NICKEL:
                        nCount = numOfCoinType;
                        break;
                    case DIME:
                        dCount = numOfCoinType;
                        break;
                }
                double thisInput = indivInput * numOfCoinType;
                totalInput = thisInput + totalInput;

                cons.displayUserString("You inserted " + numOfCoinType + "  " + coinRealName + "S ... a total of : $" + df.format(thisInput));
                cons.displayUserString("Input total is : $" + df.format(totalInput) + "\nYour total cost is : $" + df.format(totalCost));

                if (totalInput < totalCost) {
                    cons.displayUserString("We need more change!");
                    needMoreChange = true;
                } else if (totalInput > totalCost) {
                    cons.displayUserString("Great! Generating your change. ");
                    needMoreChange = false;
                    makeChange(qCount, dCount, nCount);
                    totalCost = 0;
                    totalInput = 0;
                } else if (totalCost == totalInput) {
                    needMoreChange = false;
                    totalCost = 0;
                    totalInput = 0;
                }
            } else {
                cons.displayUserString("Please enter in EXACT CHANGE ONLY");
                needMoreChange = true;
            }
        }while (needMoreChange) ;
    }

        public void makeChange(int qCount, int dCount, int nCount){
            double remainder = totalInput - totalCost;
            remainder = remainder * 100;
                cons.displayUserString("You get $" + df.format(remainder / 100));
                double numQrtrsRtrnd = remainder / 25;

                remainder = remainder % 25;
                double numDmsRtrnd = remainder / 10;
                remainder = remainder % 10;
                double numNklsRtrnd = remainder / 5;
                DecimalFormat intStyle = new DecimalFormat("#0");
                cons.displayUserString( intStyle.format(numQrtrsRtrnd) + " Quarters back\n" + intStyle.format(numDmsRtrnd) + " Dimes back \n" + intStyle.format(numNklsRtrnd) + " Nickels back");
            resetCoinInv(qCount, dCount, nCount, ((int) numQrtrsRtrnd), ((int) numDmsRtrnd), ((int) numNklsRtrnd));
    }

        public boolean acceptableRangeForCoins(int coinsIn){
        boolean tooMany = false;
        if (coinsIn < 1 || coinsIn > 10){
            cons.displayUserString("Please lower your input to less than 10.");
            tooMany = true;
        }
        return tooMany;
        }

        public boolean checkProdInventory(Product prodT) {
            boolean sufficientProd = true;
            int initialInv = prodT.getProductInventory();
            if (initialInv <= 0) {
                sufficientProd = false;
        }
             resetInv = initialInv - 1;
             prodT.setProductInventory(resetInv);
             return sufficientProd;
        }

        public boolean checkCoinInventory(Coin coinT){
          boolean sufficientCoin = true;
          int initialCoins = coinT.getCoinInventory();
          if(initialCoins <= 4){
              sufficientCoin = false;
          }
          return sufficientCoin;
        }

        public void resetCoinInv(int numQGiven, int numDGiven, int numNGiven, int numQRtrn, int numDRtrn, int numNRtrn){
            int qReset;
            int dReset;
            int nReset;
            int qInitialInv = Coin.QUARTER.getCoinInventory();
            int nInitialInv = Coin.NICKEL.getCoinInventory();
            int dInitialInv = Coin.DIME.getCoinInventory();

            qReset = qInitialInv + numQGiven - numQRtrn;
            Coin.QUARTER.setCoinInventory(qReset);

            dReset = dInitialInv + numDGiven - numDRtrn;
            Coin.DIME.setCoinInventory(dReset);

            nReset = nInitialInv + numNGiven - numNRtrn;
            Coin.NICKEL.setCoinInventory(nReset);
        }

    }

