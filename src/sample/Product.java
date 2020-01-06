package sample;

public class Product {
    private int productID;
    private String productName;
    private int productPrice;
    private int stock;
    private String productLocation;
    private String barcodeNumber;


    public Product(int productID, String productName, int productPrice, int stock, String productLocation, String barcodeNumber) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.stock = stock;
        this.productLocation = productLocation;
        this.barcodeNumber = barcodeNumber;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }
}

//package sample;
//
//public class Product {
//    private int productID;
//    private String productLocation;
//    private String productName;
//    private String barcodeNumber;
//    private int productPrice;
//    private int stock;
//
//    public Product(int productID, String productLocation, String productName, String barcodeNumber, int productPrice, int stock) {
//    }
//
//    public int getProductID() {
//        return productID;
//    }
//
//    public void setProductID(int productID) {
//        this.productID = productID;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public String getProductLocation() {
//        return productLocation;
//    }
//
//    public void setProductLocation(String productLocation) {
//        this.productLocation = productLocation;
//    }
//
//    public int getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(int productPrice) {
//        this.productPrice = productPrice;
//    }
//
//    public int getStock() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }
//
//    public String getBarcodeNumber() {
//        return barcodeNumber;
//    }
//
//    public void setBarcodeNumber(String barcodeNumber) {
//        this.barcodeNumber = barcodeNumber;
//    }
//}
