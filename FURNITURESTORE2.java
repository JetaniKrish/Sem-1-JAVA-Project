import java.util.Scanner;

// User class (Parent class)
class User {
    String userName;
    String userID;
    String userContactNumber;

    // Constructor
    public User(String userName, String userContactNumber) {
        this.userName = userName;
        this.userContactNumber = userContactNumber;
        this.userID = generateUserID(userName);
    }

    // Generate user ID
    private String generateUserID(String name) {
        int randomNum = (int) (Math.random() * 9000) + 1000;
        return name.substring(0, Math.min(name.length(), 3)).toUpperCase() + randomNum;
    }

    // Display user details
    public void displayUserDetails() {
        System.out.println("\n====================================");
        System.out.println("|           USER DETAILS           |");
        System.out.println("====================================");
        System.out.println("| User ID: " + userID + "           |");
        System.out.println("| User Name: " + userName + "       |");
        System.out.println("| Contact Number: " + userContactNumber + " |");
        System.out.println("====================================");
    }
}

// Payment class (Parent class)
class Payment {
    double totalBill;

    // Constructor
    public Payment(double totalBill) {
        this.totalBill = totalBill;
    }

    // Process payment
    public void processPayment() {
        System.out.println("\n====================================");
        System.out.println("|   PROCESSING PAYMENT...          |");
        System.out.println("====================================");
        System.out.println("| Total Bill: " + totalBill + "    |");
        System.out.println("====================================");
    }
}

// CardPayment class (Child class of Payment)
class CardPayment extends Payment {
    String cardNumber;
    String expiryDate;
    String cvv;

    // Constructor
    public CardPayment(double totalBill, String cardNumber, String expiryDate, String cvv) {
        super(totalBill);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    // Validate card details
    private boolean isValidCard() {
        // Validate card number (must be 16 digits)
        if (cardNumber.length() != 16 || !isNumeric(cardNumber)) {
            return false;
        }

        // Validate expiry date (must be in MM/YY format)
        if (expiryDate.length() != 5 || expiryDate.charAt(2) != '/') {
            return false;
        }
        String monthStr = expiryDate.substring(0, 2);
        String yearStr = expiryDate.substring(3);
        if (!isNumeric(monthStr) || !isNumeric(yearStr)) {
            return false;
        }
        int month = Integer.parseInt(monthStr);
        int year = Integer.parseInt(yearStr);
        if (month < 1 || month > 12 || year < 23 || year > 99) {
            return false;
        }

        // Validate CVV (must be 3 digits)
        if (cvv.length() != 3 || !isNumeric(cvv)) {
            return false;
        }

        return true;
    }

    // Check if a string is numeric
    private boolean isNumeric(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Process card payment
    @Override
    public void processPayment() {
        System.out.println("\n====================================");
        System.out.println("|   PROCESSING CARD PAYMENT...     |");
        System.out.println("====================================");
        if (isValidCard()) {
            System.out.println("| Payment Successful!             |");
            System.out.println("| Amount Paid: " + totalBill + " |");
        } else {
            System.out.println("| Invalid Card Details.           |");
            System.out.println("| Payment Failed.                 |");
        }
        System.out.println("====================================");
    }
}

// UPI Payment class (Child class of Payment)
class UPIPayment extends Payment {
    String upiID;

    // Constructor
    public UPIPayment(double totalBill, String upiID) {
        super(totalBill);
        this.upiID = upiID;
    }

    // Validate UPI ID
    private boolean isValidUPI() {
        // UPI ID format: example@upi
        return upiID.matches("[a-zA-Z0-9]+@[a-zA-Z]+");
    }

    // Process UPI payment
    @Override
    public void processPayment() {
        System.out.println("\n====================================");
        System.out.println("|   PROCESSING UPI PAYMENT...      |");
        System.out.println("====================================");
        if (isValidUPI()) {
            System.out.println("| Payment Successful!             |");
            System.out.println("| Amount Paid: " + totalBill + " |");
        } else {
            System.out.println("| Invalid UPI ID.                 |");
            System.out.println("| Payment Failed.                 |");
        }
        System.out.println("====================================");
    }
}

// Furniture class
class Furniture {
    Scanner sc;
    User user;
    String[][] orderHistory; // Stores order history
    int orderCount; // Tracks the number of orders

    public Furniture() {
        sc = new Scanner(System.in);
        orderHistory = new String[100][3]; // Max 100 orders, each with product name, price, and date
        orderCount = 0;
    }

    // Initialize user with login/signup interface
    public void initializeUser() {
        System.out.println("\n====================================");
        System.out.println("|   WELCOME TO FURNITURE SHOP      |");
        System.out.println("====================================");
        System.out.println("| 1. Login                         |");
        System.out.println("| 2. Signup                        |");
        System.out.println("====================================");
        System.out.print("| Enter your choice: ");
        int choice = getValidChoice(1, 2);

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                signup();
                break;
            default:
                System.out.println("| Invalid choice. Exiting...       |");
                System.out.println("====================================");
                System.exit(0);
        }
    }

    // Login method
    private void login() {
        System.out.println("\n====================================");
        System.out.println("|           LOGIN PAGE             |");
        System.out.println("====================================");
        System.out.print("| Enter your User ID: ");
        String inputUserID = sc.nextLine();
        System.out.print("| Enter your name: ");
        String inputName = sc.nextLine();

        // Simulate login validation (in a real system, this would check a database)
        if (user != null && inputUserID.equals(user.userID) && inputName.equals(user.userName)) {
            System.out.println("====================================");
            System.out.println("| Login Successful!                |");
            System.out.println("| Welcome back, " + user.userName + "! |");
            System.out.println("====================================");
        } else {
            System.out.println("====================================");
            System.out.println("| Invalid User ID or name.         |");
            System.out.println("| Please sign up or try again.     |");
            System.out.println("====================================");
            initializeUser(); // Restart login/signup process
        }
    }

    // Signup method
    private void signup() {
        System.out.println("\n====================================");
        System.out.println("|           SIGNUP PAGE            |");
        System.out.println("====================================");
        System.out.print("| Enter your name: ");
        String userName = sc.nextLine();
        System.out.print("| Enter your contact number: ");
        String userContactNumber = getValidContactNumber();
        user = new User(userName, userContactNumber);
        System.out.println("====================================");
        System.out.println("| Signup Successful!               |");
        System.out.println("| Welcome, " + user.userName + "!       |");
        System.out.println("| Your User ID is: " + user.userID + " |");
        System.out.println("====================================");
    }

    // Validate contact number
    private String getValidContactNumber() {
        while (true) {
            String contactNumber = sc.nextLine();
            if (contactNumber.length() == 10 && isNumeric(contactNumber)) {
                return contactNumber;
            } else {
                System.out.println("====================================");
                System.out.println("| Invalid contact number.          |");
                System.out.println("| Please enter a 10-digit number.   |");
                System.out.println("====================================");
            }
        }
    }

    // Validate date
    public boolean isValidDate(int day, int month, int year) {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Leap year check for February
        if (month == 2) {
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            if (isLeapYear) {
                daysInMonth[1] = 29;
            }
        }

        return day <= daysInMonth[month - 1];
    }

    // Check if a string is numeric
    private boolean isNumeric(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Enter and validate date
    public String enterDate() {
        while (true) {
            System.out.print("| Enter date (DD/MM/YYYY): ");
            String date = sc.nextLine();
            String[] parts = date.split("/");

            if (parts.length != 3) {
                System.out.println("====================================");
                System.out.println("| Invalid format.                  |");
                System.out.println("| Please use DD/MM/YYYY.            |");
                System.out.println("====================================");
                continue;
            }

            String dayStr = parts[0];
            String monthStr = parts[1];
            String yearStr = parts[2];

            if (!isNumeric(dayStr) || !isNumeric(monthStr) || !isNumeric(yearStr)) {
                System.out.println("====================================");
                System.out.println("| Invalid input.                   |");
                System.out.println("| Please enter numbers only.       |");
                System.out.println("====================================");
                continue;
            }

            int day = Integer.parseInt(dayStr);
            int month = Integer.parseInt(monthStr);
            int year = Integer.parseInt(yearStr);

            if (isValidDate(day, month, year)) {
                return date;
            } else {
                System.out.println("====================================");
                System.out.println("| Invalid date.                   |");
                System.out.println("| Please enter a valid date.       |");
                System.out.println("====================================");
            }
        }
    }

    // Get valid choice from user
    private int getValidChoice(int min, int max) {
        while (true) {
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice >= min && choice <= max) {
                    sc.nextLine(); // Consume newline
                    return choice;
                } else {
                    System.out.println("====================================");
                    System.out.println("| Invalid choice.                  |");
                    System.out.println("| Please enter a number between " + min + " and " + max + ". |");
                    System.out.println("====================================");
                }
            } else {
                System.out.println("====================================");
                System.out.println("| Invalid input.                   |");
                System.out.println("| Please enter a number.           |");
                System.out.println("====================================");
                sc.next(); // Clear invalid input
            }
        }
    }

    // Get valid quantity from user
    private int getValidQuantity() {
        while (true) {
            if (sc.hasNextInt()) {
                int quantity = sc.nextInt();
                if (quantity > 0) {
                    sc.nextLine(); // Consume newline
                    return quantity;
                } else {
                    System.out.println("====================================");
                    System.out.println("| Invalid quantity.               |");
                    System.out.println("| Please enter a positive number.  |");
                    System.out.println("====================================");
                }
            } else {
                System.out.println("====================================");
                System.out.println("| Invalid input.                   |");
                System.out.println("| Please enter a number.           |");
                System.out.println("====================================");
                sc.next(); // Clear invalid input
            }
        }
    }

    // Show order confirmation
    private void showOrderConfirmation(String productName, String color, int quantity, double totalPrice) {
        System.out.println("\n====================================");
        System.out.println("|        ORDER CONFIRMATION        |");
        System.out.println("====================================");
        System.out.println("| Product: " + productName + " |");
        System.out.println("| Color: " + color + "           |");
        System.out.println("| Quantity: " + quantity + "     |");
        System.out.println("| Total Price: " + totalPrice + " |");
        System.out.println("====================================");
    }

    // Sofa
    public void sofa() {
        System.out.println("\n====================================");
        System.out.println("|           SOFA CATALOG           |");
        System.out.println("====================================");
        String[] sofaNames = {"Black Oak Lewis Bolstered Lounge Entryway Bench Three Seater Sofa", 
                              "Home Furniture Wooden Sofa For Living-Room", 
                              "Solid Wood Furniture Sheesham Wood Sofa", 
                              "Solimo Roasio 1 Seater Sofa", 
                              "Enchanting Design Accent Sofa Chair", 
                              "Innovate Reclainer & Sofa", 
                              "Sofa Crafter Solid Wood Super Soft Sofa"};
        double[] sofaPrices = {13999.00, 10799.00, 19998.00, 9799.00, 27899.00, 32869.00, 17499.00};
        
        System.out.println("| Available Sofas:                 |");
        for (int i = 0; i < sofaNames.length; i++) {
            System.out.println("| " + (i + 1) + ". " + sofaNames[i] + " - Price: " + sofaPrices[i] + " |");
        }
        System.out.println("====================================");
        System.out.print("| Select a sofa (1-" + sofaNames.length + "): ");
        int choice = getValidChoice(1, sofaNames.length);
        sc.nextLine(); // consume newline
        System.out.print("| Enter sofa color: ");
        String color = sc.nextLine();
        System.out.print("| Enter quantity: ");
        int quantity = getValidQuantity();

        double totalPrice = sofaPrices[choice - 1] * quantity;
        showOrderConfirmation(sofaNames[choice - 1], color, quantity, totalPrice);

        // Add to order history
        orderHistory[orderCount][0] = sofaNames[choice - 1];
        orderHistory[orderCount][1] = String.valueOf(totalPrice);
        orderHistory[orderCount][2] = enterDate();
        orderCount++;
    }

    // Dining table
    public void dinningTable() {
        System.out.println("\n====================================");
        System.out.println("|       DINING TABLE CATALOG       |");
        System.out.println("====================================");
        String[] dinningTableNames = {"Wakefit Dining Table", 
                                      "Home Center Diana Beech Wood Veneer Finish", 
                                      "Drifting Wood Zig Zag Solid Sheesham Wood Dining Table", 
                                      "Solimo Indus Glass Round Dining Table", 
                                      "OAK Nest Supreme Oak Circular Dining Table"};
        double[] dinningTablePrices = {30710.00, 12999.00, 15969.00, 14599.00, 8500.00};
        
        System.out.println("| Available Dining Tables:         |");
        for (int i = 0; i < dinningTableNames.length; i++) {
            System.out.println("| " + (i + 1) + ". " + dinningTableNames[i] + " - Price: " + dinningTablePrices[i] + " |");
        }
        System.out.println("====================================");
        System.out.print("| Select a dining table (1-" + dinningTableNames.length + "): ");
        int choice = getValidChoice(1, dinningTableNames.length);
        sc.nextLine(); // consume newline
        System.out.print("| Enter dining table color: ");
        String color = sc.nextLine();
        System.out.print("| Enter quantity: ");
        int quantity = getValidQuantity();

        double totalPrice = dinningTablePrices[choice - 1] * quantity;
        showOrderConfirmation(dinningTableNames[choice - 1], color, quantity, totalPrice);

        // Add to order history
        orderHistory[orderCount][0] = dinningTableNames[choice - 1];
        orderHistory[orderCount][1] = String.valueOf(totalPrice);
        orderHistory[orderCount][2] = enterDate();
        orderCount++;
    }

    // Bed
    public void bed() {
        System.out.println("\n====================================");
        System.out.println("|           BED CATALOG            |");
        System.out.println("====================================");
        String[] bedNames = {"Caspian Furniture Wood Textured Single Bed", 
                             "Studio Kook Tribe Leaf with Headboard Wood Single Bed", 
                             "Solimo Lincum Single Bed", 
                             "Wakefit Double Bed", 
                             "Solid Sheesham Wood Mayor Palang King Size Bed", 
                             "Geetanjali Decor Grace King Size Bed", 
                             "Vaidik Furniture Solid Sheesham Wood King Size Bed"};
        double[] bedPrices = {7000.00, 26487.00, 28099.00, 25410.00, 68999.00, 53999.00, 32599.00};
        
        System.out.println("| Available Beds:                  |");
        for (int i = 0; i < bedNames.length; i++) {
            System.out.println("| " + (i + 1) + ". " + bedNames[i] + " - Price: " + bedPrices[i] + " |");
        }
        System.out.println("====================================");
        System.out.print("| Select a bed (1-" + bedNames.length + "): ");
        int choice = getValidChoice(1, bedNames.length);
        sc.nextLine(); // consume newline
        System.out.print("| Enter bed color: ");
        String color = sc.nextLine();
        System.out.print("| Enter quantity: ");
        int quantity = getValidQuantity();

        double totalPrice = bedPrices[choice - 1] * quantity;
        showOrderConfirmation(bedNames[choice - 1], color, quantity, totalPrice);

        // Add to order history
        orderHistory[orderCount][0] = bedNames[choice - 1];
        orderHistory[orderCount][1] = String.valueOf(totalPrice);
        orderHistory[orderCount][2] = enterDate();
        orderCount++;
    }

    // Wardrobe
    public void wardrobe() {
        System.out.println("\n====================================");
        System.out.println("|         WARDROBE CATALOG         |");
        System.out.println("====================================");
        String[] wardrobeNames = {"Solimo 2-Door Foldable Wardrobe", 
                                  "Solimo 3-Door Foldable Wardrobe", 
                                  "Lifewit 6-Tiers Multipurpose Foldable Wardrobe", 
                                  "CITRODA Multipurpose Shoe Wardrobe", 
                                  "AYSIS DIY Shoe Rack Organizer", 
                                  "Solimo Multipurpose Rack for Shoes", 
                                  "FLIPZON Baby Wardrobe Plastic Multipurpose 6 Shelve Wardrobe", 
                                  "BucketList Baby Wardrobe Door Plastic Sheet Kids Wardrobe", 
                                  "Nilkamal Freedome Big 1 (FB1) Plastic Cabinet", 
                                  "Sharan Almirah Multi Purpose Wardrobe", 
                                  "HEKAMI Interiors 4 Doors Wardrobe"};
        double[] wardrobePrices = {2069.00, 2349.00, 1399.00, 2149.00, 2147.00, 1369.00, 1095.00, 3969.00, 9999.00, 15507.00, 36969.00};
        
        System.out.println("| Available Wardrobes:             |");
        for (int i = 0; i < wardrobeNames.length; i++) {
            System.out.println("| " + (i + 1) + ". " + wardrobeNames[i] + " - Price: " + wardrobePrices[i] + " |");
        }
        System.out.println("====================================");
        System.out.print("| Select a wardrobe (1-" + wardrobeNames.length + "): ");
        int choice = getValidChoice(1, wardrobeNames.length);
        sc.nextLine(); // consume newline
        System.out.print("| Enter wardrobe color: ");
        String color = sc.nextLine();
        System.out.print("| Enter quantity: ");
        int quantity = getValidQuantity();

        double totalPrice = wardrobePrices[choice - 1] * quantity;
        showOrderConfirmation(wardrobeNames[choice - 1], color, quantity, totalPrice);

        // Add to order history
        orderHistory[orderCount][0] = wardrobeNames[choice - 1];
        orderHistory[orderCount][1] = String.valueOf(totalPrice);
        orderHistory[orderCount][2] = enterDate();
        orderCount++;
    }

    // Display order history
    public void displayOrderHistory() {
        if (orderCount == 0) {
            System.out.println("\n====================================");
            System.out.println("| No orders placed yet.           |");
            System.out.println("====================================");
            return;
        }
        System.out.println("\n====================================");
        System.out.println("|         ORDER HISTORY            |");
        System.out.println("====================================");
        for (int i = 0; i < orderCount; i++) {
            System.out.println("| Product: " + orderHistory[i][0] + " |");
            System.out.println("| Total Price: " + orderHistory[i][1] + " |");
            System.out.println("| Delivery Date: " + orderHistory[i][2] + " |");
            System.out.println("====================================");
        }
    }

    // Billing
    public void billing() {
        if (orderCount == 0) {
            System.out.println("\n====================================");
            System.out.println("| No items in the cart.           |");
            System.out.println("| Please add items first.         |");
            System.out.println("====================================");
            return;
        }

        double totalBill = 0;
        for (int i = 0; i < orderCount; i++) {
            totalBill += Double.parseDouble(orderHistory[i][1]);
        }

        // Apply discount if total bill is above 50000
        if (totalBill > 50000) {
            double discount = totalBill * 0.1; // 10% discount
            totalBill -= discount;
            System.out.println("\n====================================");
            System.out.println("| Congratulations!                |");
            System.out.println("| You have received a 10% discount.|");
            System.out.println("====================================");
        }

        System.out.println("\n====================================");
        System.out.println("|           BILLING                |");
        System.out.println("====================================");
        System.out.println("| Total Bill: " + totalBill + "    |");
        System.out.println("====================================");
        System.out.print("| Enter Address: ");
        sc.nextLine(); // Consume newline
        String address = sc.nextLine();

        String deliveryDate = enterDate();
        System.out.println("====================================");
        System.out.println("| Delivery Date: " + deliveryDate + " |");
        System.out.println("====================================");

        // Choose payment method
        System.out.println("\n====================================");
        System.out.println("|       CHOOSE PAYMENT METHOD      |");
        System.out.println("====================================");
        System.out.println("| 1. Card Payment                  |");
        System.out.println("| 2. UPI Payment                   |");
        System.out.println("====================================");
        System.out.print("| Enter your choice: ");
        int paymentChoice = getValidChoice(1, 2);

        switch (paymentChoice) {
            case 1:
                // Card payment details
                System.out.print("| Enter card number (16 digits): ");
                String cardNumber = getValidCardNumber();
                System.out.print("| Enter expiry date (MM/YY): ");
                String expiryDate = getValidExpiryDate();
                System.out.print("| Enter CVV (3 digits): ");
                String cvv = getValidCVV();

                // Process card payment
                CardPayment cardPayment = new CardPayment(totalBill, cardNumber, expiryDate, cvv);
                cardPayment.processPayment();
                break;

            case 2:
                // UPI payment details
                System.out.print("| Enter UPI ID (e.g., example@upi): ");
                String upiID = sc.nextLine();

                // Process UPI payment
                UPIPayment upiPayment = new UPIPayment(totalBill, upiID);
                upiPayment.processPayment();
                break;

            default:
                System.out.println("| Invalid choice.                  |");
                System.out.println("====================================");
        }
    }

    // Get valid card number
    private String getValidCardNumber() {
        while (true) {
            String cardNumber = sc.nextLine();
            if (cardNumber.length() == 16 && isNumeric(cardNumber)) {
                return cardNumber;
            } else {
                System.out.println("====================================");
                System.out.println("| Invalid card number.             |");
                System.out.println("| Please enter a 16-digit number. |");
                System.out.println("====================================");
            }
        }
    }

    // Get valid expiry date
    private String getValidExpiryDate() {
        while (true) {
            String expiryDate = sc.nextLine();
            if (expiryDate.length() == 5 && expiryDate.charAt(2) == '/') {
                String monthStr = expiryDate.substring(0, 2);
                String yearStr = expiryDate.substring(3);
                if (isNumeric(monthStr) && isNumeric(yearStr)) {
                    int month = Integer.parseInt(monthStr);
                    int year = Integer.parseInt(yearStr);
                    if (month >= 1 && month <= 12 && year >= 23 && year <= 99) {
                        return expiryDate;
                    }
                }
            }
            System.out.println("====================================");
            System.out.println("| Invalid expiry date.             |");
            System.out.println("| Please enter in MM/YY format.    |");
            System.out.println("====================================");
        }
    }

    // Get valid CVV
    private String getValidCVV() {
        while (true) {
            String cvv = sc.nextLine();
            if (cvv.length() == 3 && isNumeric(cvv)) {
                return cvv;
            } else {
                System.out.println("====================================");
                System.out.println("| Invalid CVV.                     |");
                System.out.println("| Please enter a 3-digit number.   |");
                System.out.println("====================================");
            }
        }
    }

    // Feedback
    public void feedback() {
        System.out.println("\n====================================");
        System.out.println("|           FEEDBACK              |");
        System.out.println("====================================");
        System.out.print("| Please provide your feedback: ");
        sc.nextLine(); // Consume newline
        String feedback = sc.nextLine();
        System.out.println("====================================");
        System.out.println("| Thank you for your feedback!     |");
        System.out.println("====================================");
    }

    public static void main(String[] args) {
        Furniture furnitureShop = new Furniture();
        furnitureShop.initializeUser(); // Start with login/signup interface

        boolean exit = false;
        while (!exit) {
            System.out.println("\n====================================");
            System.out.println("|       FURNITURE SHOP MENU        |");
            System.out.println("====================================");
            System.out.println("| 1. Add Sofa                      |");
            System.out.println("| 2. Add Dining Table              |");
            System.out.println("| 3. Add Bed                       |");
            System.out.println("| 4. Add Wardrobe                  |");
            System.out.println("| 5. Display Order History         |");
            System.out.println("| 6. Proceed to Billing            |");
            System.out.println("| 7. Provide Feedback              |");
            System.out.println("| 8. Display User Details          |");
            System.out.println("| 9. Exit                          |");
            System.out.println("====================================");
            System.out.print("| Enter your choice: ");
            int choice = furnitureShop.getValidChoice(1, 9);

            switch (choice) {
                case 1:
                    furnitureShop.sofa();
                    break;
                case 2:
                    furnitureShop.dinningTable();
                    break;
                case 3:
                    furnitureShop.bed();
                    break;
                case 4:
                    furnitureShop.wardrobe();
                    break;
                case 5:
                    furnitureShop.displayOrderHistory();
                    break;
                case 6:
                    furnitureShop.billing();
                    break;
                case 7:
                    furnitureShop.feedback();
                    break;
                case 8:
                    furnitureShop.user.displayUserDetails();
                    break;
                case 9:
                    exit = true;
                    System.out.println("\n====================================");
                    System.out.println("| Thank you for shopping with us!  |");
                    System.out.println("====================================");
                    break;
                default:
                    System.out.println("\n====================================");
                    System.out.println("| Invalid choice.                  |");
                    System.out.println("| Please choose a valid option.    |");
                    System.out.println("====================================");
            }
        }
    }
}