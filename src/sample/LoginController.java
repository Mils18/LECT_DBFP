package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField uField;
    @FXML private PasswordField passField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loginButtonClicked(ActionEvent event) throws IOException {
        String username = uField.getText();
        String password = passField.getText();

        if(Database.login(username, password) == 1){
            System.out.println("success");


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CashierHomePage.fxml"));
            Parent CashierHomePageParent = loader.load();

            Stage stage = new Stage();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            CashierHomeController controller =loader.getController();
            String role = "Cashier";
            controller.passData(username,role);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Cashier");
            stage.setScene(new Scene(CashierHomePageParent));
            stage.show();

        } else if(Database.login(username, password) == 2){

            System.out.println("success");


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AdminHomePage.fxml"));
            Parent AdminHomePageParent = loader.load();

            Stage stage = new Stage();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            AdminHomeController controller =loader.getController();
            String role = "Admin";
            controller.passData(username,role);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Admin");
            stage.setScene(new Scene(AdminHomePageParent));
            stage.show();

        } else{
            System.out.println("Login Failed");
        }

    }

    @FXML
    private void testbuttonClicked(){
        Database.newItemTransaction(3,3, 1);
        Database.testconnect();
    }
}
