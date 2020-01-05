package sample;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/FoodHallDB";
    static final String USER = "root";
    static final String PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public static Connection connect(){
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean executeSQL(String sql){
        try {
            conn = connect();
            stmt = conn.createStatement();
            boolean result = stmt.execute(sql);
            conn.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


//    login function
    public static int login(String username, String password){
        if(!username.isEmpty() && !password.isEmpty()) {
            String sql = "SELECT * FROM cashier WHERE cashierName = '%s' AND password = '%s'";

            try {
                conn = connect();
                sql = String.format(sql, username, password);
                rs = conn.createStatement().executeQuery(sql);

                if (rs.next()) {
                    if(rs.getInt("admin") == 0){
                        return 1;
                    } else if(rs.getInt("admin") == 1){
                        return 2;
                    }

                } else {
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        } else{
            return 0;
        }
        return 0;
    }

//    bill related functions
    public static void newBill(int cashierID, int storeID, int paymentTypeID){

        String sql = "INSERT INTO bill (cashierID, storeID, paymentTypeID) values ('%d', '%d', '%d')";

        try {
            conn = connect();
            stmt = conn.createStatement();
            sql = String.format(sql, cashierID, storeID, paymentTypeID);
            stmt.execute(sql);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateBill(int billID, int cashierID, int storeID, int paymentTypeID){

        String sql = "UPDATE bill set cashierID = '%d', storeID = '%d', paymentTypeID = '%d' where billID = '%d'";
        sql = String.format(sql, cashierID, storeID, paymentTypeID, billID);

        executeSQL(sql);

    }

    public static ActualBill getABill(int billID){
        String sql = "SELECT * FROM bill WHERE billID = " + billID;
        ActualBill bill;

        try{
            conn = connect();
            rs = conn.createStatement().executeQuery(sql);

            rs.next();

            bill = new ActualBill(rs.getInt("billID"), rs.getString("transactionTime"),
                    rs.getInt("cashierID"), rs.getInt("storeID"), rs.getInt("paymentTypeID"));

            return bill;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ResultSet selectAllBill(String startDate, String endDate){

        String sql = "SELECT b.billID, b.transactionTime, c.cashierName, s.storeName, p.paymentName FROM bill b " +
                "INNER JOIN cashier c on b.cashierID = c.cashierID " +
                "INNER JOIN store s on b.storeID = s.StoreID " +
                "INNER JOIN paymenttype p ON b.paymentTypeID = p.paymentTypeID " +
                "WHERE b.transactionTime BETWEEN \'" + startDate + "\' and \'" + endDate +"\'";

        try{
            conn = connect();
            rs = conn.createStatement().executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return rs;

    }

    public static ResultSet selectBillFromStore(String startDate, String endDate, String storeName){

        String sql = "SELECT b.billID, b.transactionTime, c.cashierName, s.storeName, p.paymentName FROM bill b " +
                "INNER JOIN cashier c on b.cashierID = c.cashierID " +
                "INNER JOIN store s on b.storeID = s.StoreID " +
                "INNER JOIN paymenttype p ON b.paymentTypeID = p.paymentTypeID " +
                "WHERE b.transactionTime BETWEEN \'" + startDate + "\' and \'" + endDate +"\' AND s.StoreName = \'" + storeName + "\'";

        try{
            conn = connect();
            rs = conn.createStatement().executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return rs;
    }

    public static void deleteBill(int billID){
        String sql = "DELETE FROM bill WHERE billID = '%d'";
        sql = String.format(sql, billID);

        executeSQL(sql);

    }

//    itemTransaction related functions
    public static void newItemTransaction(int billID, int productID, int qty){

        String sql = "INSERT INTO itemTransaction (billID, productID, qty) VALUES ( '%d', '%d', '%d')";
        sql = String.format(sql, billID, productID, qty);

        executeSQL(sql);

    }

    public static void updateItemTransaction(int transactionID, int billID, int productID, int qty){

        String sql = "UPDATE itemTransaction set billID = '%d', productID = '%d', qty where transactionID = '%d'";
        sql = String.format(sql, billID, productID, qty, transactionID);

        executeSQL(sql);

    }

    public static ResultSet getItemTransaction(int billID){

        String sql = "SELECT t.transactionID, t.qty, p.productName, p.productPrice FROM itemtransaction t INNER JOIN products p ON t.productID = p.productID WHERE billID = '%d'";
        sql = String.format(sql, billID);

        try{
            conn = connect();
            rs = conn.createStatement().executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return rs;

    }

//    store related fucntions
    public static ArrayList<String> getAllStores() throws NullPointerException {
        ArrayList<String> listofTypes = new ArrayList<>();
        try {
            conn = connect();
            String sql = "SELECT * FROM store";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                listofTypes.add(rs.getString("StoreID") + " "+ rs.getString("StoreName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofTypes;
    }

//    cashier related functions
    public static ArrayList<Cashier> getAllCashier(){
        ArrayList<Cashier> cashierList = new ArrayList<>();
        try {
            conn = connect();
            String sql = "SELECT cashierID, cashierName, password, admin, CASE WHEN admin = '0' THEN \"NO\" ELSE \"YES\" END AS adminStatus From cashier";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                cashierList.add(new Cashier(rs.getInt("cashierID"), rs.getString("cashierName"),
                        rs.getString("password"), rs.getString("adminStatus")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cashierList;
    }

    public static ArrayList<String> getCashierNames(){
        ArrayList<String> cashierNames = new ArrayList<>();
        try {
            conn = connect();
            String sql = "SELECT cashierID, cashierName FROM cashier";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                cashierNames.add(rs.getString("cashierID") + " " + rs.getString("cashierName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cashierNames;

    }

    public static void addCashier(String name, String pass, int admin){

        String sql = "INSERT INTO cashier (cashierName, password, admin) VALUES ('%s','%s','%d')";
        sql = String.format(sql, name, pass, admin);

        executeSQL(sql);

    }


    public static void deleteCashier(int cashierID){

        String sql = "DELETE FROM cashier WHERE cashierID = '%d'";
        sql = String.format(sql, cashierID);

        executeSQL(sql);

    }

    public static void updateCashier(int cashierID, String cashierName, String cashierPass, int admin){

        String sql = "UPDATE cashier SET cashierName = '%s', password = '%s', admin = '%d' WHERE cashierID = '%d'";
        sql = String.format(sql, cashierName, cashierPass, admin, cashierID);

        executeSQL(sql);

    }

//    product related function
    public static ArrayList<Product> getAllProducts(){
        ArrayList<Product> productList = new ArrayList<>();
        try {
            conn = connect();
            String sql = "SELECT productID, productLocation, productName, barcodeNumber, productPrice, stock From products";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                productList.add(new Product(rs.getInt("productID"), rs.getString("productLocation"),
                        rs.getString("productName"), rs.getString("barcodeNumber"),
                        rs.getInt("productPrice"), rs.getInt("stock")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    public static void addProduct(String productLocation, String productName, String barcodeNumber, int productPrice, int stock){

        String sql = "INSERT INTO products (productLocation, productName, barcodeNumber, productPrice, stock) VALUES ('%s','%s','%s','%d','%d')";
        sql = String.format(sql, productLocation, productName, barcodeNumber, productPrice, stock);

        executeSQL(sql);

    }

    public static void deleteProduct(int productID){

        String sql = "DELETE FROM products WHERE productID = '%d'";
        sql = String.format(sql, productID);

        executeSQL(sql);

    }
//    payment related functions

    public static ArrayList<String> getPaymentMethods(){
        ArrayList<String> paymentNames = new ArrayList<>();

        try {
            conn = connect();
            String sql = "SELECT * FROM paymentType";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                paymentNames.add(rs.getString("paymentTypeID") + " " + rs.getString("paymentName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentNames;

    }

    public static void testconnect(){

        String sql = "Select * from itemTransaction";

        try {
            conn = connect();
            rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("transactionID"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    }