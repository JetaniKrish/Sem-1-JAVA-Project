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

// Admin class
class Admin {
    Furniture furnitureShop;

    // Constructor
    public Admin(Furniture furnitureShop) {
        this.furnitureShop = furnitureShop;
    }

    // Add a new product to the catalog
    public void addProduct(String category, String productName, double price) {
        switch (category) {
            case "sofa":
                if (furnitureShop.sofaNames.length < 15) {
                    String[] newSofaNames = new String[furnitureShop.sofaNames.length + 1];
                    double[] newSofaPrices = new double[furnitureShop.sofaPrices.length + 1];
                    for (int i = 0; i < furnitureShop.sofaNames.length; i++) {
                        newSofaNames[i] = furnitureShop.sofaNames[i];
                        newSofaPrices[i] = furnitureShop.sofaPrices[i];
                    }
                    newSofaNames[furnitureShop.sofaNames.length] = productName;
                    newSofaPrices[furnitureShop.sofaPrices.length] = price;
                    furnitureShop.sofaNames = newSofaNames;
                    furnitureShop.sofaPrices = newSofaPrices;
                    System.out.println("| Product added successfully!     |");
                } else {
                    System.out.println("| Maximum limit of 15 products reached for Sofa. |");
                }
                break;
            case "dinningTable":
                if (furnitureShop.dinningTableNames.length < 15) {
                    String[] newDinningTableNames = new String[furnitureShop.dinningTableNames.length + 1];
                    double[] newDinningTablePrices = new double[furnitureShop.dinningTablePrices.length + 1];
                    for (int i = 0; i < furnitureShop.dinningTableNames.length; i++) {
                        newDinningTableNames[i] = furnitureShop.dinningTableNames[i];
                        newDinningTablePrices[i] = furnitureShop.dinningTablePrices[i];
                    }
                    newDinningTableNames[furnitureShop.dinningTableNames.length] = productName;
                    newDinningTablePrices[furnitureShop.dinningTablePrices.length] = price;
                    furnitureShop.dinningTableNames = newDinningTableNames;
                    furnitureShop.dinningTablePrices = newDinningTablePrices;
                    System.out.println("| Product added successfully!     |");
                } else {
                    System.out.println("| Maximum limit of 15 products reached for Dining Table. |");
                }
                break;
            case "bed":
                if (furnitureShop.bedNames.length < 15) {
                    String[] newBedNames = new String[furnitureShop.bedNames.length + 1];
                    double[] newBedPrices = new double[furnitureShop.bedPrices.length + 1];
                    for (int i = 0; i < furnitureShop.bedNames.length; i++) {
                        newBedNames[i] = furnitureShop.bedNames[i];
                        newBedPrices[i] = furnitureShop.bedPrices[i];
                    }
                    newBedNames[furnitureShop.bedNames.length] = productName;
                    newBedPrices[furnitureShop.bedPrices.length] = price;
                    furnitureShop.bedNames = newBedNames;
                    furnitureShop.bedPrices = newBedPrices;
                    System.out.println("| Product added successfully!     |");
                } else {
                    System.out.println("| Maximum limit of 15 products reached for Bed. |");
                }
                break;
            case "wardrobe":
                if (furnitureShop.wardrobeNames.length < 15) {
                    String[] newWardrobeNames = new String[furnitureShop.wardrobeNames.length + 1];
                    double[] newWardrobePrices = new double[furnitureShop.wardrobePrices.length + 1];
                    for (int i = 0; i < furnitureShop.wardrobeNames.length; i++) {
                        newWardrobeNames[i] = furnitureShop.wardrobeNames[i];
                        newWardrobePrices[i] = furnitureShop.wardrobePrices[i];
                    }
                    newWardrobeNames[furnitureShop.wardrobeNames.length] = productName;
                    newWardrobePrices[furnitureShop.wardrobePrices.length] = price;
                    furnitureShop.wardrobeNames = newWardrobeNames;
                    furnitureShop.wardrobePrices = newWardrobePrices;
                    System.out.println("| Product added successfully!     |");
                } else {
                    System.out.println("| Maximum limit of 15 products reached for Wardrobe. |");
                }
                break;
            default:
                System.out.println("| Invalid category.               |");
        }
    }

    // Remove a product from the catalog
    public void removeProduct(String category, int index) {
        switch (category) {
            case "sofa":
                if (index >= 0 && index < furnitureShop.sofaNames.length) {
                    String[] newSofaNames = new String[furnitureShop.sofaNames.length - 1];
                    double[] newSofaPrices = new double[furnitureShop.sofaPrices.length - 1];
                    for (int i = 0, j = 0; i < furnitureShop.sofaNames.length; i++) {
                        if (i != index) {
                            newSofaNames[j] = furnitureShop.sofaNames[i];
                            newSofaPrices[j] = furnitureShop.sofaPrices[i];
                            j++;
                        }
                    }
                    furnitureShop.sofaNames = newSofaNames;
                    furnitureShop.sofaPrices = newSofaPrices;
                    System.out.println("| Product removed successfully!    |");
                } else {
                    System.out.println("| Invalid index.                   |");
                }
                break;
            case "dinningTable":
                if (index >= 0 && index < furnitureShop.dinningTableNames.length) {
                    String[] newDinningTableNames = new String[furnitureShop.dinningTableNames.length - 1];
                    double[] newDinningTablePrices = new double[furnitureShop.dinningTablePrices.length - 1];
                    for (int i = 0, j = 0; i < furnitureShop.dinningTableNames.length; i++) {
                        if (i != index) {
                            newDinningTableNames[j] = furnitureShop.dinningTableNames[i];
                            newDinningTablePrices[j] = furnitureShop.dinningTablePrices[i];
                            j++;
                        }
                    }
                    furnitureShop.dinningTableNames = newDinningTableNames;
                    furnitureShop.dinningTablePrices = newDinningTablePrices;
                    System.out.println("| Product removed successfully!    |");
                } else {
                    System.out.println("| Invalid index.                   |");
                }
                break;
            case "bed":
                if (index >= 0 && index < furnitureShop.bedNames.length) {
                    String[] newBedNames = new String[furnitureShop.bedNames.length - 1];
                    double[] newBedPrices = new double[furnitureShop.bedPrices.length - 1];
                    for (int i = 0, j = 0; i < furnitureShop.bedNames.length; i++) {
                        if (i != index) {
                            newBedNames[j] = furnitureShop.bedNames[i];
                            newBedPrices[j] = furnitureShop.bedPrices[i];
                            j++;
                        }
                    }
                    furnitureShop.bedNames = newBedNames;
                    furnitureShop.bedPrices = newBedPrices;
                    System.out.println("| Product removed successfully!    |");
                } else {
                    System.out.println("| Invalid index.                   |");
                }
                break;
            case "wardrobe":
                if (index >= 0 && index < furnitureShop.wardrobeNames.length) {
                    String[] newWardrobeNames = new String[furnitureShop.wardrobeNames.length - 1];
                    double[] newWardrobePrices = new double[furnitureShop.wardrobePrices.length - 1];
                    for (int i = 0, j = 0; i < furnitureShop.wardrobeNames.length; i++) {
                        if (i != index) {
                            newWardrobeNames[j] = furnitureShop.wardrobeNames[i];
                            newWardrobePrices[j] = furnitureShop.wardrobePrices[i];
                            j++;
                        }
                    }
                    furnitureShop.wardrobeNames = newWardrobeNames;
                    furnitureShop.wardrobePrices = newWardrobePrices;
                    System.out.println("| Product removed successfully!    |");
                } else {
                    System.out.println("| Invalid index.                   |");
                }
                break;
            default:
                System.out.println("| Invalid category.               |");
        }
    }

    // Update a product in the catalog
    public void updateProduct(String category, int index, String newProductName, double newPrice) {
        switch (category) {
            case "sofa":
                if (index >= 0 && index < furnitureShop.sofaNames.length) {
                    furnitureShop.sofaNames[index] = newProductName;
                    furnitureShop.sofaPrices[index] = newPrice;
                    System.out.println("| Product updated successfully!    |");
                } else {
                    System.out.println("| Invalid index.                   |");
                }
                break;
            case "dinningTable":
                if (index >= 0 && index < furnitureShop.dinningTableNames.length) {
                    furnitureShop.dinningTableNames[index] = newProductName;
                    furnitureShop.dinningTablePrices[index] = newPrice;
                    System.out.println("| Product updated successfully!    |");
                } else {
                    System.out.println("| Invalid index.                   |");
                }
                break;
            case "bed":
                if (index >= 0 && index < furnitureShop.bedNames.length) {
                    furnitureShop.bedNames[index] = newProductName;
                    furnitureShop.bedPrices[index] = newPrice;
                    System.out.println("| Product updated successfully!    |");
                } else {
                    System.out.println("| Invalid index.                   |");
                }
                break;
            case "wardrobe":
                if (index >= 0 && index < furnitureShop.wardrobeNames.length) {
                    furnitureShop.wardrobeNames[index] = newProductName;
                    furnitureShop.wardrobePrices[index] = newPrice;
                    System.out.println("| Product updated successfully!    |");
                } else {
                    System.out.println("| Invalid index.                   |");
                }
                break;
            default:
                System.out.println("| Invalid category.               |");
        }
    }
}

// Furniture class
class Furniture {
    Scanner sc;
    User user;
    String[][] orderHistory; // Stores order history
    int orderCount; // Tracks the number of orders

    // Product arrays
    String[] sofaNames = {"Black Oak Lewis Bolstered Lounge Entryway Bench Three Seater Sofa", 
                          "Home Furniture Wooden Sofa For Living-Room", 
                          "Solid Wood Furniture Sheesham Wood Sofa", 
                          "Solimo Roasio 1 Seater Sofa", 
                          "Enchanting Design Accent Sofa Chair", 
                          "Innovate Reclainer & Sofa", 
                          "Sofa Crafter Solid Wood Super Soft Sofa"};
    double[] sofaPrices = {13999.00, 10799.00, 19998.00, 9799.00, 27899.00, 32869.00, 17499.00};

    String[] dinningTableNames = {"Wakefit Dining Table", 
                                  "Home Center Diana Beech Wood Veneer Finish", 
                                  "Drifting Wood Zig Zag Solid Sheesham Wood Dining Table", 
                                  "Solimo Indus Glass Round Dining Table", 
                                  "OAK Nest Supreme Oak Circular Dining Table"};
    double[] dinningTablePrices = {30710.00, 12999.00, 15969.00, 14599.00, 8500.00};

    String[] bedNames = {"Caspian Furniture Wood Textured Single Bed", 
                         "Studio Kook Tribe Leaf with Headboard Wood Single Bed", 
                         "Solimo Lincum Single Bed", 
                         "Wakefit Double Bed", 
                         "Solid Sheesham Wood Mayor Palang King Size Bed", 
                         "Geetanjali Decor Grace King Size Bed", 
                         "Vaidik Furniture Solid Sheesham Wood King Size Bed"};
    double[] bedPrices = {7000.00, 26487.00, 28099.00, 25410.00, 68999.00, 53999.00, 32599.00};

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
        System.out.println("| 3. Admin Login                   |");
        System.out.println("====================================");
        System.out.print("| Enter your choice: ");
        int choice = getValidChoice(1, 3);

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                signup();
                break;
            case 3:
                adminLogin();
                break;
            default:
                System.out.println("| Invalid choice. Exiting...       |");
                System.out.println("====================================");
                System.exit(0);
        }
    }

    // Admin login method
    private void adminLogin() {
        System.out.println("\n====================================");
        System.out.println("|           ADMIN LOGIN           |");
        System.out.println("====================================");
        System.out.print("| Enter admin username: ");
        String username = sc.nextLine();
        System.out.print("| Enter admin password: ");
        String password = sc.nextLine();

        // Simulate admin login validation
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("====================================");
            System.out.println("| Admin login successful!          |");
            System.out.println("====================================");
            adminMenu();
        } else {
            System.out.println("====================================");
            System.out.println("| Invalid admin credentials.        |");
            System.out.println("====================================");
            initializeUser(); // Restart login/signup process
        }
    }

    // Admin menu
    private void adminMenu() {
        Admin admin = new Admin(this);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n====================================");
            System.out.println("|           ADMIN MENU            |");
            System.out.println("====================================");
            System.out.println("| 1. Manage Sofas                 |");
            System.out.println("| 2. Manage Dining Tables         |");
            System.out.println("| 3. Manage Beds                  |");
            System.out.println("| 4. Manage Wardrobes             |");
            System.out.println("| 5. Exit                         |");
            System.out.println("====================================");
            System.out.print("| Enter your choice: ");
            int choice = getValidChoice(1, 5);

            switch (choice) {
                case 1:
                    manageProducts("sofa", admin);
                    break;
                case 2:
                    manageProducts("dinningTable", admin);
                    break;
                case 3:
                    manageProducts("bed", admin);
                    break;
                case 4:
                    manageProducts("wardrobe", admin);
                    break;
                case 5:
                    exit = true;
                    System.out.println("\n====================================");
                    System.out.println("| Exiting admin menu.              |");
                    System.out.println("====================================");
                    break;
                default:
                    System.out.println("\n====================================");
                    System.out.println("| Invalid choice.                  |");
                    System.out.println("====================================");
            }
        }
    }

    // Manage products for a category
    private void manageProducts(String category, Admin admin) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n====================================");
            System.out.println("|       MANAGE " + category.toUpperCase() + "       |");
            System.out.println("====================================");
            System.out.println("| 1. Add Product                  |");
            System.out.println("| 2. Remove Product               |");
            System.out.println("| 3. Update Product               |");
            System.out.println("| 4. View Products                |");
            System.out.println("| 5. Exit                         |");
            System.out.println("====================================");
            System.out.print("| Enter your choice: ");
            int choice = getValidChoice(1, 5);

            switch (choice) {
                case 1:
                    System.out.print("| Enter product name: ");
                    String productName = sc.nextLine();
                    System.out.print("| Enter product price: ");
                    double price = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    admin.addProduct(category, productName, price);
                    break;
                case 2:
                    System.out.print("| Enter product index to remove: ");
                    int removeIndex = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    admin.removeProduct(category, removeIndex - 1);
                    break;
                case 3:
                    System.out.print("| Enter product index to update: ");
                    int updateIndex = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("| Enter new product name: ");
                    String newProductName = sc.nextLine();
                    System.out.print("| Enter new product price: ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    admin.updateProduct(category, updateIndex - 1, newProductName, newPrice);
                    break;
                case 4:
                    System.out.println("\n====================================");
                    System.out.println("|       " + category.toUpperCase() + " CATALOG       |");
                    System.out.println("====================================");
                    String[] productNames;
                    double[] productPrices;
                    switch (category) {
                        case "sofa":
                            productNames = sofaNames;
                            productPrices = sofaPrices;
                            break;
                        case "dinningTable":
                            productNames = dinningTableNames;
                            productPrices = dinningTablePrices;
                            break;
                        case "bed":
                            productNames = bedNames;
                            productPrices = bedPrices;
                            break;
                        case "wardrobe":
                            productNames = wardrobeNames;
                            productPrices = wardrobePrices;
                            break;
                        default:
                            productNames = new String[0];
                            productPrices = new double[0];
                            break;
                    }
                    for (int i = 0; i < productNames.length; i++) {
                        System.out.println("| " + (i + 1) + ". " + productNames[i] + " - Price: " + productPrices[i] + " |");
                    }
                    System.out.println("====================================");
                    break;
                case 5:
                    exit = true;
                    System.out.println("\n====================================");
                    System.out.println("| Exiting " + category.toUpperCase() + " management. |");
                    System.out.println("====================================");
                    break;
                default:
                    System.out.println("\n====================================");
                    System.out.println("| Invalid choice.                  |");
                    System.out.println("====================================");
            }
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
