package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditBillPageController implements Initializable {

    ActualBill bill;

    private ManageTransactionController parentController;

    private ArrayList<String> cashierList = new ArrayList<>();
    private ArrayList<String> storeList = new ArrayList<>();
    private ArrayList<String> paymentList = new ArrayList<>();

    @FXML private ComboBox<String> cashierCombo;
    @FXML private ComboBox<String> storeCombo;
    @FXML private ComboBox<String> paymentCombo;

    @FXML private Label editBillLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        refreshCashierCombo();
        refreshStoreComboBox();
        refreshPaymentComboBox();
    }

    @FXML
    public void passData(ActualBill bill, ManageTransactionController parentController){
        this.bill = bill;
        this.parentController = parentController;

        cashierCombo.setValue(String.valueOf(bill.getCashierID()));
        storeCombo.setValue(String.valueOf(bill.getStoreID()));
        paymentCombo.setValue(String.valueOf(bill.getPaymentTypeID()));

        editBillLabel.setText("Edit Bill: " + bill.getBillID());
    }

    @FXML
    private void refreshCashierCombo(){
        cashierList.clear();
        cashierList = Database.getCashierNames();
        cashierCombo.setItems(FXCollections.observableArrayList(cashierList));

    }

    @FXML
    private void refreshStoreComboBox(){
        storeList.clear();
        storeList = Database.getAllStores();
        storeCombo.setItems(FXCollections.observableArrayList(storeList));

    }

    @FXML
    private void refreshPaymentComboBox(){
        paymentList.clear();
        paymentList = Database.getPaymentMethods();
        paymentCombo.setItems(FXCollections.observableArrayList(paymentList));

    }

    @FXML
    private void updateButtonClicked(ActionEvent event){
        int cashierNew = Integer.parseInt(cashierCombo.getValue().substring(0,1));
        int storeNew = Integer.parseInt(storeCombo.getValue().substring(0,1));
        int paymentNew = Integer.parseInt(paymentCombo.getValue().substring(0,1));

        System.out.println(cashierNew);
        System.out.println(storeNew);
        System.out.println(paymentNew);


        Database.updateBill(bill.getBillID(), cashierNew, storeNew, paymentNew);

        parentController.refreshBillList();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }



}
