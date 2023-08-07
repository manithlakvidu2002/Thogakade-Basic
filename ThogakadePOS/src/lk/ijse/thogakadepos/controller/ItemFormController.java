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
    private TableView<Item> tblItem;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDis;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colQTY;

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

    public void initialize() {
        txtSearch.setText("");
        btnDelete.setDisable(true);

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDis.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        // get search bar typed text
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            //String newValue passed to loadAll() method
            loadAll(newValue);
        });

        // get selected item
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtCode.setText(String.valueOf(newValue.getCode()));
                txtDis.setText(newValue.getDescription());
                txtPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtQty.setText(String.valueOf(newValue.getQtyOnHand()));

                btnAdd.setText("UPDATE");
                btnDelete.setDisable(false);
            } else {
                btnAdd.setText("ADD");
                clearFields();
            }
        });

        loadAll("");
    }

    private void clearFields() {
        txtCode.clear();
        txtDis.clear();
        txtPrice.clear();
        txtQty.clear();
    }

    private void loadAll(String value) {
        ObservableList<Item> list = FXCollections.observableArrayList();

        try {
            ArrayList<Item> itemData = ItemModel.getAll();
            for (Item i : itemData) {
                if (i.getDescription().contains(value) || String.valueOf(i.getQtyOnHand()).contains(value) || String.valueOf(i.getCode()).contains(value) || String.valueOf(i.getUnitPrice()).contains(value)) {
                    Item item = new Item(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand());
                    list.add(item);
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.toString());
        }

        tblItem.setItems(list);
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
            // UPDATE
            else if (btnAdd.getText().equals("UPDATE")) {
                boolean isUpdated = ItemModel.update(item);
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed").show();
                }
            }

            loadAll("");

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

            loadAll("");

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
}
