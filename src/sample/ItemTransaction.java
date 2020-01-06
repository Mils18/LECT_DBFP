package sample;

public class ItemTransaction {

    private int itemID;
    private int billID;
    private int productID;
    private String productName;
    private int productPrice;
    private int qty;
    private int subtotal;


    public ItemTransaction(int itemID, int billID, int productID, String productName, int productPrice, int qty, int subtotal) {
        this.itemID = itemID;
        this.billID = billID;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}

////    private String productName;
//    private String productName;
//    private int price;
//    private int qty;
//    private int subtotal;
//
//    public ItemTransaction(int itemID, String productName, int price, int qty,  int subtotal){
//        this.itemID = itemID;
//        this.productName = productName;
//        this.price = price;
//        this.qty = qty;
//        this.subtotal = subtotal;
//    }
//
//
//    public int getItemID() {
//        return itemID;
//    }
//
//    public void setItemID(int itemID) {
//        this.itemID = itemID;
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
//    public int getQty() {
//        return qty;
//    }
//
//    public void setQty(int qty) {
//        this.qty = qty;
//    }
//
//    public int getSubtotal() {
//        return subtotal;
//    }
//
//    public void setSubtotal(int subtotal) {
//        this.subtotal = subtotal;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//}

