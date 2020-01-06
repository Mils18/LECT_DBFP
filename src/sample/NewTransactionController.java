package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewTransactionController implements Initializable {

    String username;
    String role;
    private int currentBillNumber;

    @FXML private TableView<Product> inventoryTable;
    @FXML private TableColumn<Product, String> productIDInvenCol;
    @FXML private TableColumn<Product, String> productNameInvenCol;
    @FXML private TableColumn<Product, String> priceInvenCol;

    @FXML private ComboBox<Integer> qtyCombo;

    @FXML private TableView<ItemTransaction> cartTable;
    @FXML private TableColumn<ItemTransaction, String> productIDCartCol;
    @FXML private TableColumn<ItemTransaction, String> productNameCartCol;
    @FXML private TableColumn<ItemTransaction, String> priceCartCol;
    @FXML private TableColumn<ItemTransaction, String> qtyCartCol;
    @FXML private TableColumn<ItemTransaction, String> subtotalCartCol;

    private ObservableList<Product> inventoryList = FXCollections.observableArrayList();
    private ObservableList<ItemTransaction> cartList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(Database.getBillNumber());
        currentBillNumber = Database.getBillNumber();
        productIDInvenCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameInvenCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceInvenCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

//        -----------------------------------------------------------------------------------
        qtyCombo.setItems(FXCollections.observableArrayList(1,2,3,4,5));
        qtyCombo.setValue(1);
//        -----------------------------------------------------------------------------------

        productIDCartCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCartCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceCartCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        qtyCartCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        subtotalCartCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        refresh();
    }

    public void passData(String username, String role){
        this.username = username;
        this.role = role;
    }

    @FXML
    public void refresh(){
        inventoryList.clear();
        inventoryList.addAll(Database.getAllProducts());
        inventoryTable.setItems(inventoryList);

        cartList.clear();
        cartList.addAll(Database.getAllItemTransactionCurrBill(currentBillNumber));
        cartTable.setItems(cartList);

    }
    //
    @FXML
    public void addItemButtonClicked(){
        System.out.println("addItemButtonClicked");
        try {
            Product selected = inventoryTable.getSelectionModel().getSelectedItem();
//            Database.deleteCashier(selected.getCashierID());
            System.out.println("ID"+selected.getProductID());
            System.out.println("Name"+selected.getProductName());
            System.out.println("Price"+selected.getProductPrice());


//            INI NANTI HARUS DIGANTI
            int billID = currentBillNumber;
            int productID = selected.getProductID();
            String productName = selected.getProductName();
            int productPrice = selected.getProductPrice();
            int qty = qtyCombo.getValue();
            int subtotal = productPrice * qty;
            Database.addItemTransaction(billID, productID, productName, productPrice, qty, subtotal);
//            addToCartTable(selected.getProductID(),selected.getProductName(),selected.getProductPrice());
            refresh();

        } catch (NullPointerException e){
            System.out.println("no selection");
        }
    }

    @FXML
    public void deleteItemButtonClicked(){
        System.out.println("deleteItemButtonClicked");
//        try{
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("EditCashierPage.fxml"));
//            Parent EditCashierParent = loader.load();
//
//            Stage stage = new Stage(); // New stage (window)
//
//            EditCashierController controller = loader.getController();
//            controller.passData(this, cashierTable.getSelectionModel().getSelectedItem());
//
//            // Setting the stage up
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setResizable(false);
//            stage.setTitle("Edit Cashier");
//            stage.setScene(new Scene(EditCashierParent));
//            stage.showAndWait();
//        } catch (NullPointerException e){
//            System.out.println("No selection");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void refreshItemList(){
        System.out.println("refreshItemList");
    }

    @FXML
    public void checkOutButtonClicked(){
        System.out.println("checkOutButtonClicked");
//        try{
//            Cashier selected = cashierTable.getSelectionModel().getSelectedItem();
//            Database.deleteCashier(selected.getCashierID());
//            refresh();
//
//        } catch (NullPointerException e){
//            System.out.println("no selection");
//        }
//    }
//    @FXML
//    public void passData(String username, String role){
//        this.username = username;
//        this.role = role;
//        System.out.println("U "+this.username);
//        System.out.println("R "+this.role);

    }

    public void homeButtonClicked(ActionEvent event) throws IOException {
        System.out.println("HOME Btn Clicked");
        FXMLLoader loader = new FXMLLoader();

        String fxml;
        String title;

        System.out.println(role);

        if (role.equals("Cashier")){
            fxml = "CashierHomePage.fxml";
            title = "Cashier Home Page";
        } else{
            fxml = "AdminHomePage.fxml";
            title = "Admin Home Page";
        }


        loader.setLocation(getClass().getResource(fxml));
        Parent AdminHomePageParent = loader.load();
        Stage stage = new Stage();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        if (role.equals("Cashier")) {
            CashierHomeController controller = loader.getController();
            controller.passData(username, role);
        }else{
            AdminHomeController controller =loader.getController();
            controller.passData(username, role);
        }

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(AdminHomePageParent));
        stage.show();
    }
}
