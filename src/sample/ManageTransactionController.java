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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageTransactionController implements Initializable {

    private String username;
    private String role;
    private ArrayList<String> stores = new ArrayList<>();

    @FXML private Label WelcomeAdmin;
    @FXML private TableView<Bill> BillTable;
    @FXML private TableColumn<Bill, Integer> BillIDCol;
    @FXML private TableColumn<Bill, String> TTCol;
    @FXML private TableColumn<Bill, String> CashierNameCol;
    @FXML private TableColumn<Bill, String> StrNameCol;
    @FXML private TableColumn<Bill, String> PMCol;
    ObservableList<Bill> BillList = FXCollections.observableArrayList();

    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private ComboBox<String> StoreCombo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshStoreComboBox();
        StoreCombo.setValue("");
        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());
        refreshBillList();
    }


    @FXML
    public void refreshBillList(){
        BillList.clear();
        String storename = StoreCombo.getValue();
        String startDate = startDatePicker.getValue().toString();
        String endDate = endDatePicker.getValue().toString();
        ResultSet rs;
        if(storename.isEmpty()) {
            rs = Database.selectAllBill(startDate,endDate);
        } else{
            rs = Database.selectBillFromStore(startDate,endDate,storename.substring(2));
        }

        try {
            while (rs.next()) {
                BillList.add(new Bill(rs.getInt("billID"), rs.getString("transactionTime"), rs.getString("cashierName")
                        , rs.getString("storeName"), rs.getString("paymentName")));
            }

            rs.close();

            BillIDCol.setCellValueFactory(new PropertyValueFactory<>("billID"));
            TTCol.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
            CashierNameCol.setCellValueFactory(new PropertyValueFactory<>("cashierName"));
            StrNameCol.setCellValueFactory(new PropertyValueFactory<>("storeName"));
            PMCol.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
            BillTable.setItems(BillList);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("no data");
        }
    }

    @FXML
    private void refreshStoreComboBox(){
        stores.clear();
        stores = Database.getAllStores();
        stores.add("");
        StoreCombo.setItems(FXCollections.observableArrayList(stores));

    }

    @FXML
    public void seeItemsTransactionButtonClicked() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ItemsTransaction.fxml"));
            Parent ItemsTransactionParent = loader.load();

            Stage stage = new Stage(); // New stage (window)

            // Passing data to CustomerFormController
            ItemsTransactionController controller = loader.getController();
            Bill selectedBill = BillTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedBill.getBillID());
            controller.passData(selectedBill.getBillID());

            // Setting the stage up
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Items Transaction for bill " + selectedBill.getBillID());
            stage.setScene(new Scene(ItemsTransactionParent));
            stage.showAndWait();
        }catch (NullPointerException e){
            System.out.println("no bill selected");
        }
    }

    @FXML
    public void editBillButtonClicked(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditBillPage.fxml"));
            Parent EditPageParent = loader.load();

            Stage stage = new Stage(); // New stage (window)

            // Passing data to CustomerFormController
            EditBillPageController controller = loader.getController();
            Bill selectedBill = BillTable.getSelectionModel().getSelectedItem();
            ActualBill actualBill = Database.getABill(selectedBill.getBillID());
            controller.passData(actualBill, this);

            // Setting the stage up
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Edit Bill");
            stage.setScene(new Scene(EditPageParent));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("no selection");
        }
    }

    @FXML
    public void deleteButtonClicked(){
        Bill selectedBill = BillTable.getSelectionModel().getSelectedItem();

        int billID = selectedBill.getBillID();

        Database.deleteBill(billID);

        refreshBillList();
    }

    @FXML
    public void manageCashierButtonClicked(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageUserPage.fxml"));
            Parent ManageCashierParent = loader.load();

            Stage stage = new Stage(); // New stage (window)

            // Setting the stage up
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Manage Cashiers");
            stage.setScene(new Scene(ManageCashierParent));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    public void passData(String username, String role){
        this.username = username;
        this.role = role;
        System.out.println("U "+this.username);
        System.out.println("R "+this.role);

    }

}
