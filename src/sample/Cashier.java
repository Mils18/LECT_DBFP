package sample;

public class Cashier {

    private int cashierID;
    private String cashierName;
    private String cashierPass;
    private String adminStatus;

    public Cashier(int cashierID, String cashierName, String cashierPass, String adminStatus) {
        this.cashierID = cashierID;
        this.cashierName = cashierName;
        this.cashierPass = cashierPass;
        this.adminStatus = adminStatus;
    }

    public int getCashierID() {
        return cashierID;
    }

    public void setCashierID(int cashierID) {
        this.cashierID = cashierID;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getCashierPass() {
        return cashierPass;
    }

    public void setCashierPass(String cashierPass) {
        this.cashierPass = cashierPass;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }
}
