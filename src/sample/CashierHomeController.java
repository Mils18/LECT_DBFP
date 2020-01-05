package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CashierHomeController implements Initializable {

    String username;
    String role;

    @FXML private Label WelcomeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void logoutButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Logout Btn Clicked");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginPage.fxml"));
        Parent LoginPageParent = loader.load();
        Stage stage = new Stage();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(new Scene(LoginPageParent));
        stage.show();
    }

    public void newBillButtonClicked(ActionEvent event) throws IOException {
        System.out.println("NewBill Btn Clicked");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewTransactionPage.fxml"));
        Parent LoginPageParent = loader.load();
        Stage stage = new Stage();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("CheckOutPage");
        stage.setScene(new Scene(LoginPageParent));
        stage.show();
    }
    @FXML
    public void passData(String username, String role){
        this.username = username;
        this.role = role;
        WelcomeLabel.setText("Welcome " + username);
    }
}
