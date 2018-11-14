package com.VendingMachine;
import java.text.DecimalFormat;

public class Controller {

    private ConsoleIO console;
    private CoinDAOImpl coinDAO;
    private ProductDAOImpl productDAO;
    private DecimalFormat decimalFormat;

    boolean keepRunning = true;
    double cost;
    double totalCost;
    double indivInput;
    double totalInput;
    Product productType;
    int numProdPurchased;
    int resetInventory;
    boolean needMoreChange = false;
    boolean validCoin;
    boolean sufficientCoins = true;
    String coinType;
    Coin coinRealName = null;
    int quarterCount = 0;
    int dimeCount = 0;
    int nickelCount = 0;
    int numOfCoinType =0;

    public Controller(ConsoleIO console, CoinDAOImpl coinDAO, ProductDAOImpl productDAO, DecimalFormat decimalFormat) {
        this.console = console;
        this.coinDAO = coinDAO;
        this.productDAO = productDAO;
        this.decimalFormat = decimalFormat;
    }


    public void run() {
        displayMenu();

        do {
            int userChoice;
            try {
                userChoice = console.queryUserIntRange("Please type the number that best suits you from the Main Menu: ", 1, 3);

            switch (userChoice) {
                case 1:
                    console.displayUserString("\n ___ Today's Selections are: _____\n");
                    displayAllProducts();
                    console.displayUserString("\n _________________________________ \n ");
                    break;
                case 2:
                    displayProductAvailability();
                    purchaseAProduct();
                    console.displayUserString("\n ------ We accept these methods of payment ------ \n");
                    methodsOfPayment();
                    selectPaymentMethod();
                    console.displayUserString("\nThanks for shopping with us today!\n");
                    numProdPurchased = 0;
                    break;
                case 3:
                    console.displayUserString("Thanks for stopping by! ");
                    keepRunning = false;
                    break;
                default:
                    console.displayUserString("That appears to be an invalid option. \n Please try again. ");
            }
            } catch (Exception e) {
                console.displayUserString("Exception caught, please try again at a later time.");
                keepRunning = false;
            }
        } while (keepRunning);


    }

    private void displayMenu() {
        console.displayUserString("\n**********************************");
        console.displayUserString("*         Welcome to the         *");
        console.displayUserString("*        Vending Machine!        *");
        console.displayUserString("*                                *");
        console.displayUserString("*        -- Main Menu --         *");
        console.displayUserString("*                                *");
        console.displayUserString("*       Would you like to:       *");
        console.displayUserString("*    1. View Product List        *");
        console.displayUserString("*    2. Purchase a Product       *");
        console.displayUserString("*    3. Exit                     *");
        console.displayUserString("*                                *");
        console.displayUserString("**********************************\n");

    }

    private void purchaseAProduct() {
        boolean sufficientProduct;
        boolean onceAgain = true;
        do {
            String resp = console.queryUserString("\nPlease type your selected product!  ");
            switch (resp.toUpperCase()) {
                case "CHIPS":
                    productType = Product.CHIPS;
                    cost = productType.getProductCost();
                    totalCost = cost + totalCost;
                    numProdPurchased++;
                    break;
                case "COLA":
                    productType = Product.COLA;
                    cost = productType.getProductCost();
                    totalCost = cost + totalCost;
                    numProdPurchased++;
                    break;
                case "CANDY":
                    productType = Product.CANDY;
                    cost = productType.getProductCost();
                    totalCost = cost + totalCost;
                    numProdPurchased++;
                    break;
                default:
                    console.displayUserString("I'm sorry, that seems to be an invalid option. Please try and type it again.");
            }
             sufficientProduct = checkProdInventory(productType);
             console.displayUserString("about to look through respondwithProdinventory");
             onceAgain = respondWithProdInventoryAndIterate(sufficientProduct, totalCost, numProdPurchased);
             console.displayUserString(onceAgain + " = onceAgain");

        } while (onceAgain);

    }
    private boolean respondWithProdInventoryAndIterate(boolean enoughProduct, double totalCost, int numProdPurchased){
        boolean continueOn = true;
        if (enoughProduct) {
           console.displayUserString("Your current total is: " + "$ " + totalCost + "\nTotal Items To Purchase " + numProdPurchased);
           String keepGoing = console.queryUserString("Would you like anything else today? Y or N ");
          if (keepGoing.equalsIgnoreCase("Y")) {
            continueOn = true;
          } else if (keepGoing.equalsIgnoreCase("N")) {
            continueOn = false;
        }}
        else {
          console.displayUserString("Product selected is SOLD OUT. \nPlease select something else today.");
          continueOn = true;
        }
     return continueOn;
    }

    private void displayAllProducts() {
        Product[] prodsAvail = productDAO.getProductTypes();
        for (Product currentProd : prodsAvail) {
            console.displayUserString( "\t" + currentProd.toString() + " --  COST : $ " + currentProd.getProductCost());
        }
    }

    private void displayProductAvailability() {
        Product[] prods = productDAO.getProductTypes();
        for (Product currentProd : prods) {
            if (currentProd.getProductInventory() > 0) {
                console.displayUserString("\t" + currentProd.toString() + "   is available");
            } else if (currentProd.getProductInventory() <= 0) {
                console.displayUserString("\t" + currentProd.toString() + " is currently unavailable. Sorry!");
            }
        }
    }

    private void methodsOfPayment() {
        Coin[] coins = coinDAO.getCoinTypes();
        for (Coin currentCoin : coins) {
            console.displayUserString("\t" + currentCoin.toString());
        }
        console.displayUserString("\n  ");
    }

    private void selectPaymentMethod() {
        console.displayUserString("\nIf at any point you want your coins returned. Please say RETURN.");
        do {
            setCustomerCoinSelection();
            indivInput = coinRealName.getCoinValue();
            sufficientCoins = checkCoinInventory(coinRealName);
            if (sufficientCoins) {
                numOfCoinType = console.queryUserInt("How many coins would you like to put in?");
                if (acceptableRangeForCoins(numOfCoinType)) {
                    console.displayUserString("We are returning your coins.");
                    numOfCoinType = 0;
                }
               determinePaymentTotal(indivInput, coinRealName, numOfCoinType);
            } else {
                console.displayUserString("Currently experiencing low coin volume. Please enter in EXACT CHANGE ONLY");
                needMoreChange = true;
            }
        }while (needMoreChange);
    }

    private void makeChange(int qCount, int dCount, int nCount){
        double remainder = totalInput - totalCost;
           remainder = remainder * 100;
                console.displayUserString("You get $" + decimalFormat.format(remainder / 100));
                double numQrtrsReturned = remainder / 25;

                remainder = remainder % 25;
                double numDimesReturned = remainder / 10;
                remainder = remainder % 10;
                double numNickelsReturned = remainder / 5;
                DecimalFormat intStyle = new DecimalFormat("#0");
                console.displayUserString( intStyle.format(numQrtrsReturned) + " Quarters back\n" + intStyle.format(numDimesReturned) + " Dimes back \n" + intStyle.format(numNickelsReturned) + " Nickels back");
            resetCoinInv(qCount, dCount, nCount, ((int) numQrtrsReturned), ((int) numDimesReturned), ((int) numNickelsReturned));
    }

    private boolean acceptableRangeForCoins(int coinsIn){
        boolean tooMany = false;
        if (coinsIn < 1 || coinsIn > 10){
            console.displayUserString("Please lower your input to less than 10.");
            tooMany = true;
        }
        return tooMany;
        }

     private boolean checkProdInventory(Product prodT) {
        boolean sufficientProd = true;
        int initialInv = prodT.getProductInventory();
        if (initialInv <= 0) {
            sufficientProd = false;
        }
        resetInventory = initialInv - 1;
        prodT.setProductInventory(resetInventory);
        return sufficientProd;
        }

     private boolean checkCoinInventory(Coin coinT){
        boolean sufficientCoin = true;
        int initialCoins = coinT.getCoinInventory();
        if(initialCoins <= 4){
           sufficientCoin = false;
          }
        return sufficientCoin;
        }

     private void resetCoinInv(int numQGiven, int numDGiven, int numNGiven, int numQRtrn, int numDRtrn, int numNRtrn){
        int quarterReset;
        int dimeReset;
        int nickelReset;
        int qInitialInv = Coin.QUARTER.getCoinInventory();
        int nInitialInv = Coin.NICKEL.getCoinInventory();
        int dInitialInv = Coin.DIME.getCoinInventory();
        quarterReset = qInitialInv + numQGiven - numQRtrn;
        Coin.QUARTER.setCoinInventory(quarterReset);

        dimeReset = dInitialInv + numDGiven - numDRtrn;
        Coin.DIME.setCoinInventory(dimeReset);

        nickelReset = nInitialInv + numNGiven - numNRtrn;
        Coin.NICKEL.setCoinInventory(nickelReset);
        }


     private void setCustomerCoinSelection() {
        do {
           coinType = console.queryUserString("\nWhich coin would you like to start adding in?");
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
                 console.displayUserString("We are sorry to hear that. All coins are being returned. ");
                 indivInput = 0;
                 validCoin = false;
                 break;
            default:
                 validCoin = false;
            }

         if (!validCoin) {
             console.displayUserString("Sorry, we can't accept that. Please try again!");
             validCoin = false;
          }
         }while(!validCoin);
        }

     private void determinePaymentTotal(double customerInput, Coin coinToUse, int numOfCoinsSelected) {
         switch (coinToUse) {
             case QUARTER:
                 quarterCount = numOfCoinsSelected;
                 break;
             case NICKEL:
                 nickelCount = numOfCoinsSelected;
                 break;
             case DIME:
                 dimeCount = numOfCoinsSelected;
                 break;
         }
         double thisInput = customerInput * numOfCoinsSelected;
         totalInput = thisInput + totalInput;

         console.displayUserString("You inserted " + numOfCoinsSelected + "  " + coinToUse + "S ... for a total of : $" + decimalFormat.format(thisInput));
         console.displayUserString("Total Customer Input is : $" + decimalFormat.format(totalInput) + "\nYour total cost is : $" + decimalFormat.format(totalCost));


         if (totalInput < totalCost) {
             console.displayUserString("We need more change!");
             needMoreChange = true;
         } else if (totalInput > totalCost) {
             console.displayUserString("Great! Generating your change. ");
             needMoreChange = false;
             makeChange(quarterCount, dimeCount, nickelCount);
             totalCost = 0;
            //returnTotalInput= 0;
         } else if (totalCost == totalInput) {
             needMoreChange = false;
             totalCost = 0;
            //returnTotalInput = 0;
        }
        //return returnTotalInput;
       }
    }

