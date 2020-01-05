package sample;

public class Bill {

    private int billID;
    private String transactionTime;
    private String cashierName;
    private String StoreName;
    private String PaymentType;

    public Bill(int billID, String transactionTime, String cashierName, String storeName, String paymentType) {
        this.billID = billID;
        this.transactionTime = transactionTime;
        this.cashierName = cashierName;
        StoreName = storeName;
        PaymentType = paymentType;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }
}
