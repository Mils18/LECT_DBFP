package sample;

public class ItemTransaction {

    private int itemID;
    private String productName;
    private int qty;
    private int subtotal;

    public ItemTransaction(int itemID, String productName, int qty, int subtotal){
        this.itemID = itemID;
        this.productName = productName;
        this.qty = qty;
        this.subtotal = subtotal;
    }


    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}

