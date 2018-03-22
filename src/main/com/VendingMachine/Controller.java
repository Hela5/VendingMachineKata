package main.com.VendingMachine;

public class Controller {

    ConsoleIO cons = new ConsoleIO();
    Order order = new Order();
    boolean keepRunning = true;
    OrderDAOImpl orderD = new OrderDAOImpl();
    ProductDAOImpl prodD = new ProductDAOImpl();
    CoinDAOImpl coinD = new CoinDAOImpl();

    double cost;
    double totalCost;

    double indivInput;
    double totalInput;

    public void run() {

        do {
            displaymenu();
            int userChoice = cons.queryUserInt("Please choose from the listed options: ");
            switch (userChoice) {
                case 1:
                    cons.displayUserString("\n ___ Today's Selections are: _____");
                    displayAllProducts();
                    break;
                case 2:
                    displayProductAvailability();
                    purchaseAProduct();
                    cons.displayUserString("\n ------ We accept these methods of payment ------ \n");
                    methodsOfPayment();
                    selectPaymentMethod();
                    cons.displayUserString("Thanks for shopping with us today!");
                    break;
                case 3:
                    cons.displayUserString("Thanks for stopping by! ");
                    keepRunning = false;
                    break;
                default:
                    cons.displayUserString("That appears to be an invalid option. \n Please try again. ");
            }
        } while (keepRunning);


    }

    private void displaymenu() {
        cons.displayUserString("\n**********************************");
        cons.displayUserString("*         Welcome to the         *");
        cons.displayUserString("*        Vending Machine!        *");
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
            String resp = cons.queryUserString("Please select your product!  ");
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
                cons.displayUserString("I'm sorry, that seems to be an invalid option. Please try again.");
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
        int num;
        for (Product currentProd : prodsAvail) {
            num = currentProd.ordinal() + 1;
            cons.displayUserString(num + " " + currentProd.toString() + " --  COST : $ " + prodD.getProductCost(currentProd));
        }
    }

    private void displayProductAvailability() {
        Product[] prods = prodD.getProductTypes();
        for (Product currentProd : prods) {
            if (prodD.getProductInventory(currentProd) > 0) {
                cons.displayUserString(currentProd.toString() + "   is available");
            } else if (prodD.getProductInventory(currentProd) <= 0) {
                cons.displayUserString(currentProd.toString() + "is currently unavailable. Sorry!");
            }
        }
    }

    private void methodsOfPayment() {
        Coin[] coins = coinD.getCoinTypes();
        for (Coin currentCoin : coins) {
            cons.displayUserString(currentCoin.toString());
        }
    }

    public void selectPaymentMethod() {
        boolean needMoreChange = false;
        boolean validCoin;
        String coinType;
        Coin coinRealName = null;
        do {

           do {
               coinType = cons.queryUserString("Which coin would you like to start adding in?");
               if (coinType.equalsIgnoreCase("QUARTER")) {
                   coinRealName = Coin.QUARTER;
                   validCoin = true;
               } else if (coinType.equalsIgnoreCase("DIME")) {
                   coinRealName = Coin.DIME;
                   validCoin = true;
               } else if (coinType.equalsIgnoreCase("NICKEL")) {
                   coinRealName = Coin.NICKEL;
                   validCoin = true;
               } else {
                   validCoin = false;
               }
               if(!validCoin) {
                   cons.displayUserString("Sorry, we can't accept those. Please try again!");
               }

           } while (!validCoin);

            indivInput = coinD.getCoinValue(coinRealName);

            int numOfCoinType = cons.queryUserInt("How many would you like to put in?");
            totalInput = (indivInput * numOfCoinType) + totalInput;
            cons.displayUserString("We have " + numOfCoinType + " of  " + coinRealName + " ... a total of $" + totalInput);
            cons.displayUserString("Your total cost is $" + totalCost);

            if (totalInput < totalCost) {
                cons.displayUserString("Uhoh! We need more change!");
                needMoreChange = true;
            } else if (totalInput > totalCost) {
                cons.displayUserString("Great! We can make you change now. ");
                needMoreChange = false;
                makeChange();
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
            double changeBack;
            if (remainder % 25 == 0) {
                changeBack = remainder / 25;
                cons.displayUserString("You get " + changeBack + " Quarters back ");
            } else if (remainder % 10 == 0) {
                changeBack = remainder / 10;
                cons.displayUserString("You get " + changeBack + " Dimes back");
            } else if (remainder % 5 == 0) {
                changeBack = remainder / 5;
                cons.displayUserString("You get " + changeBack + " Dimes back");
            }
        }

    }

