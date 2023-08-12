package lk.ijse.thogakadepos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakadepos.entity.Item;
import lk.ijse.thogakadepos.model.ItemModel;
import lk.ijse.thogakadepos.util.Navigation;
import lk.ijse.thogakadepos.util.Routes;

import java.io.IOException;
import java.util.ArrayList;

public class ItemFormController {
    public AnchorPane pane;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDis;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField txtSearch;


    private void clearFields() {
        txtCode.clear();
        txtDis.clear();
        txtPrice.clear();
        txtQty.clear();
    }


    @FXML
    public void AddOnAction(ActionEvent event) {
        try {
            int code = Integer.parseInt(txtCode.getText());
            String description = txtDis.getText();
            double unitPrice = Double.parseDouble(txtPrice.getText());
            int qtyOnHand = Integer.parseInt(txtQty.getText());

            Item item = new Item(code, description, unitPrice, qtyOnHand);

            // ADD
            if (btnAdd.getText().equals("ADD")) {
                boolean isAdded = ItemModel.add(item);
                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, "Added").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed").show();
                }
            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }

        clearFields();
    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        try {
            int code = Integer.parseInt(txtCode.getText());
            String description = txtDis.getText();
            double unitPrice = Double.parseDouble(txtPrice.getText());
            int qtyOnHand = Integer.parseInt(txtQty.getText());

            Item item = new Item(code, description, unitPrice, qtyOnHand);

            boolean isDeleted = ItemModel.delete(item);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed").show();
            }



        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }

        clearFields();
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        clearFields();
        btnAdd.setText("ADD");
    }

    public void UpdateOnAction(ActionEvent actionEvent) {
        ItemModel itemModel=new ItemModel();
        Item item=findItem();
        boolean isUpdated=itemModel.update(item);

        if(isUpdated){
            System.out.println("updated");
        }else{
            System.out.println("not update");
        }
    }

    private Item findItem() {
        Item item=new Item();
        item.setCode(Integer.parseInt(txtCode.getText()));
        item.setDescription(txtDis.getText());
        item.setQtyOnHand(Integer.parseInt(txtQty.getText()));
        item.setUnitPrice(Double.parseDouble(txtPrice.getText()));
        return item;
    }
}
