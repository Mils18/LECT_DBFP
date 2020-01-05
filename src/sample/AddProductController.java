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

public class AddProductController implements Initializable {

    private ArrayList<String> stores = new ArrayList<>();
    @FXML private ComboBox<String> storeCombo;
    @FXML private TextField productNameField;
    @FXML private TextField barcodeNumberField;
    @FXML private TextField productPriceField;
    @FXML private TextField stockField;
//    private ArrayList<String> stores = new ArrayList<>();

    ManageProductsController parentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Init");
        refreshStoreComboBox();
        storeCombo.setValue("");
    }
    @FXML
    private void refreshStoreComboBox(){
        stores.clear();
        stores = Database.getAllStores();
        stores.add("");
        storeCombo.setItems(FXCollections.observableArrayList(stores));

    }

    public void passData(ManageProductsController controller){
        this.parentController = controller;
    }

    public void addNewCashierButtonClicked(ActionEvent event){
        try{
            String productLocation = storeCombo.getValue();
            String productName = productNameField.getText();
            String barcodeNumber = barcodeNumberField.getText();
            int productPrice =Integer.parseInt(productPriceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            Database.addProduct(productLocation, productName, barcodeNumber, productPrice, stock);

            parentController.refresh();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        }catch (Exception e) {
            System.out.println("Please Make sure input is correct");
        }
    }

}
