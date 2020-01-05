package sample;

public class ActualBill {

    private int billID;
    private String transactionTime;
    private int cashierID;
    private int storeID;
    private int paymentTypeID;

    public ActualBill(int billID, String transactionTime, int cashierID, int storeID, int paymentTypeID) {
        this.billID = billID;
        this.transactionTime = transactionTime;
        this.cashierID = cashierID;
        this.storeID = storeID;
        this.paymentTypeID = paymentTypeID;
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

    public int getCashierID() {
        return cashierID;
    }

    public void setCashierID(int cashierID) {
        this.cashierID = cashierID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getPaymentTypeID() {
        return paymentTypeID;
    }

    public void setPaymentTypeID(int paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }
}
