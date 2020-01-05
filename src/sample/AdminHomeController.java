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

public class AdminHomeController implements Initializable {

    String username;
    String role;

    @FXML private Label WelcomeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void manageItemsButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Manage Btn Clicked");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManageProductsPage.fxml"));
        Parent ManageItemsPageParent = loader.load();
        Stage stage = new Stage();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Manage Items");
        stage.setScene(new Scene(ManageItemsPageParent));
        stage.show();
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

    public void newTransactionButtonClicked(ActionEvent event) throws IOException {
        System.out.println("NewBill Btn Clicked");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewTransactionPage.fxml"));
        Parent LoginPageParent = loader.load();
        Stage stage = new Stage();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("New Transaction");
        stage.setScene(new Scene(LoginPageParent));
        stage.show();
    }
    public void manageTransactionButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Manage Transaction Btn Clicked");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManageTransactionPage.fxml"));
        Parent ManageTransactionPageParent = loader.load();
        Stage stage = new Stage();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        ManageTransactionController controller =loader.getController();
        controller.passData(username,role);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Manage Transaction");
        stage.setScene(new Scene(ManageTransactionPageParent));
        stage.show();
    }
    public void manageUserButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Manage User Btn Clicked");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManageUserPage.fxml"));
        Parent ManageUserPageParent = loader.load();
        Stage stage = new Stage();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        ManageUserController controller =loader.getController();
        controller.passData(username,role);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Manage User");
        stage.setScene(new Scene(ManageUserPageParent));
        stage.show();



    }

    @FXML
    public void passData(String username, String role){
        this.username = username;
        this.role = role;
        WelcomeLabel.setText("Welcome " + username);
    }
}
