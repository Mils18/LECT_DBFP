
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageProductsController implements Initializable {

    String username;
    String role;

    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, String> productIDCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, String> priceCol;
    @FXML private TableColumn<Product, String> stockCol;
    @FXML private TableColumn<Product, String> storeLocationCol;
    @FXML private TableColumn<Product, String> barcodeNumberCol;
    private ObservableList<Product> productList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        storeLocationCol.setCellValueFactory(new PropertyValueFactory<>("productLocation"));
        barcodeNumberCol.setCellValueFactory(new PropertyValueFactory<>("barcodeNumber"));
        refresh();
    }

    public void passData(String username, String role){

        this.username = username;
        this.role = role;
    }

    @FXML
    public void refresh(){
        System.out.println("Refresh Products");
        productList.clear();
        productList.addAll(Database.getAllProducts());
        System.out.println("productList"+productList);
        productTableView.setItems(productList);

    }

    @FXML
    public void addProductButtonClicked(){
        System.out.print("AddProductButtonClicked");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddProductPage.fxml"));
            Parent AddProductParent = loader.load();

            Stage stage = new Stage(); // New stage (window)

            AddProductController controller = loader.getController();
            controller.passData(this);

            // Setting the stage up
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Add Product");
            stage.setScene(new Scene(AddProductParent));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    @FXML
    public void editProductButtonClicked(){
        System.out.println("editProductButtonClicked");
//        try{
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("EditProductPage.fxml"));
//            Parent EditProductParent = loader.load();
//
//            Stage stage = new Stage(); // New stage (window)
//
//            EditProductController controller = loader.getController();
//            controller.passData(productTableView.getSelectionModel().getSelectedItem(), this);
//
//            // Setting the stage up
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setResizable(false);
//            stage.setTitle("Edit Product");
//            stage.setScene(new Scene(EditProductParent));
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
    public void deleteProductButtonClicked(){
        System.out.println("deleteProductButtonClicked");

        try{
            Product selected = productTableView.getSelectionModel().getSelectedItem();
            Database.deleteProduct(selected.getProductID());
            refresh();

        } catch (NullPointerException e){
            System.out.println("no selection");
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
}