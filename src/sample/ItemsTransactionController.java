package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemsTransactionController implements Initializable {

    int billID;
    int total = 0;

    @FXML private TableView<ItemTransaction> transactionTable;
    @FXML private TableColumn<ItemTransaction, Integer> transactionIDCol;
    @FXML private TableColumn<ItemTransaction, String> productNameCol;
    @FXML private TableColumn<ItemTransaction, Integer> qtyCol;
    @FXML private TableColumn<ItemTransaction, Integer> subTotCol;
    ObservableList<ItemTransaction> transactionList = FXCollections.observableArrayList();

    @FXML private Label totalPay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void passData(int billID){
        this.billID = billID;
        getTransactions();
    }

    public void getTransactions(){
        ResultSet rs = Database.getItemTransaction(this.billID);
        System.out.println("here");
        System.out.println("selected bill is" + this.billID);

        try {
            while (rs.next()){
                System.out.println("now here");
                int qty = rs.getInt("qty");
                int price = rs.getInt("productPrice");
                int subtotal = price*qty;
                transactionList.add(new ItemTransaction(rs.getInt("transactionID"), rs.getString("productName"), qty, subtotal));
                total += subtotal;
            }

            transactionIDCol.setCellValueFactory(new PropertyValueFactory<>("itemID"));
            productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
            qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
            subTotCol.setCellValueFactory(new PropertyValueFactory<>("sub_total"));
            transactionTable.setItems(transactionList);

            totalPay.setText("Rp." + total);

        } catch (NullPointerException e){
            System.out.println("no data");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeButtonClicked(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
