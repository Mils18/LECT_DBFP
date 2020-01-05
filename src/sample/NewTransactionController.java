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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewTransactionController implements Initializable {

    String username;
    String role;

    @FXML private TableView<Cashier> cashierTable;
    @FXML private TableColumn<Cashier, Integer> cashierIDCol;
    @FXML private TableColumn<Cashier, String> cashierNameCol;
    @FXML private TableColumn<Cashier, String> cashierPassCol;
    @FXML private TableColumn<Cashier, String> adminStatusCol;
    ObservableList<Cashier> cashierList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        cashierIDCol.setCellValueFactory(new PropertyValueFactory<>("cashierID"));
//        cashierNameCol.setCellValueFactory(new PropertyValueFactory<>("cashierName"));
//        cashierPassCol.setCellValueFactory(new PropertyValueFactory<>("cashierPass"));
//        adminStatusCol.setCellValueFactory(new PropertyValueFactory<>("adminStatus"));

//        refresh();
    }

    @FXML
    public void refresh(){
        cashierList.clear();
        cashierList.addAll(Database.getAllCashier());
        cashierTable.setItems(cashierList);

    }
    //
    @FXML
    public void addItemButtonClicked(){
        System.out.print("addItemButtonClicked");
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("NewCashierPage.fxml"));
//            Parent NewCashierParent = loader.load();
//
//            Stage stage = new Stage(); // New stage (window)
//
//            NewCashierController controller = loader.getController();
//            controller.passData(this);
//
//            // Setting the stage up
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setResizable(false);
//            stage.setTitle("New Cashier");
//            stage.setScene(new Scene(NewCashierParent));
//            stage.showAndWait();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    //
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
        loader.setLocation(getClass().getResource("AdminHomePage.fxml"));
        Parent AdminHomePageParent = loader.load();
        Stage stage = new Stage();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        AdminHomeController controller =loader.getController();
        controller.passData(username,role);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Admin Home Page");
        stage.setScene(new Scene(AdminHomePageParent));
        stage.show();
    }
}
