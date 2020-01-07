package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckOutController implements Initializable {

    @FXML private TextField nameField;
    @FXML private TextField passField;
    @FXML private ComboBox<String> storeCombo;
    @FXML private ComboBox<String> paymentTypeCombo;

    NewTransactionController parentController;

    private int billID;
    private String transactionTime;
    private int cashierID;
    private ArrayList<String> stores = new ArrayList<>();
    private ArrayList<String> paymentTypeList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshStoreComboBox();
        storeCombo.setValue("Select Store");
        refreshPaymentTypeComboBox();
        paymentTypeCombo.setValue("Select Payment");
    }


    public void passData(NewTransactionController controller, int billID, int cashierID){
        this.parentController = controller;
        this.billID = billID;
        this.cashierID = cashierID;
    }

    @FXML
    private void refreshStoreComboBox(){
        stores.clear();
        stores = Database.getAllStores();
        storeCombo.setItems(FXCollections.observableArrayList(stores));

    }
    @FXML
    private void refreshPaymentTypeComboBox(){
        paymentTypeList.clear();
        paymentTypeList = Database.getAllPaymentTypes();
        paymentTypeCombo.setItems(FXCollections.observableArrayList(paymentTypeList));

    }

    public void checkOutButtonClicked(ActionEvent event){
        int storeID =  Database.getStoreID(storeCombo.getValue());
        System.out.println("storeID"+storeID);
        int paymentTypeID = Database.getPaymentID(paymentTypeCombo.getValue());
        System.out.println("paymentTypeID"+paymentTypeID);
        Database.updateBill(billID, cashierID, storeID, paymentTypeID);
        parentController.refresh();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

    }

}
