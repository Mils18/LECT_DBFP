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
import java.util.ResourceBundle;

public class EditCashierController implements Initializable {

    Cashier cashier;

    @FXML private TextField nameField;
    @FXML private TextField passField;
    @FXML private ComboBox<String> adminCombo;

    ManageUserController parentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminCombo.setItems(FXCollections.observableArrayList("NO", "YES"));
    }

    public void passData(ManageUserController controller, Cashier cashier){
        this.parentController = controller;
        this.cashier = cashier;

        nameField.setText(cashier.getCashierName());
        passField.setText(cashier.getCashierPass());

        adminCombo.setValue(cashier.getAdminStatus());
    }

    @FXML
    public void updateCashierButtonClicked(ActionEvent event){
        String name = nameField.getText();
        String pass = passField.getText();
        int admin = 0;
        if(adminCombo.getValue().equals("YES")){
            admin = 1;
        }

        Database.updateCashier(cashier.getCashierID(), name, pass, admin);

        parentController.refresh();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

}
