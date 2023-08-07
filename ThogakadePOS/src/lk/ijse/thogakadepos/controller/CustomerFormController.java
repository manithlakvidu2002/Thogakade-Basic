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
import lk.ijse.thogakadepos.entity.Customer;
import lk.ijse.thogakadepos.model.CustomerModel;
import lk.ijse.thogakadepos.util.Navigation;
import lk.ijse.thogakadepos.util.Routes;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerFormController {
    public AnchorPane pane;
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    public void initialize() {
        txtSearch.setText("");
        btnDelete.setDisable(true);

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        // get search bar typed text
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            //String newValue passed to loadAll() method
            loadAll(newValue);
        });

        // get selected item
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtID.setText(String.valueOf(newValue.getId()));
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());

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
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
    }

    private void loadAll(String value) {
        ObservableList<Customer> list = FXCollections.observableArrayList();

        try {
            ArrayList<Customer> customerData = CustomerModel.getAll();
            for (Customer c : customerData) {
                if (c.getName().contains(value) || c.getAddress().contains(value) || String.valueOf(c.getId()).contains(value)) {
                    Customer customer = new Customer(c.getId(), c.getName(), c.getAddress());
                    list.add(customer);
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.toString());
        }

        tblCustomer.setItems(list);
    }

    @FXML
    public void AddOnAction(ActionEvent event) {
        try {

            String name = txtName.getText();
            String address = txtAddress.getText();
            int id = Integer.parseInt(txtID.getText());

            Customer customer = new Customer(id, name, address);

            // ADD
            if (btnAdd.getText().equals("ADD")) {
                boolean isAdded = CustomerModel.add(customer);
                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, "Added").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed").show();
                }
            }
            // UPDATE
            else if (btnAdd.getText().equals("UPDATE")) {
                boolean isUpdated = CustomerModel.update(customer);
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
            String name = txtName.getText();
            String address = txtAddress.getText();
            int id = Integer.parseInt(txtID.getText());

            Customer customer = new Customer(id, name, address);
            boolean isDeleted = CustomerModel.delete(customer);
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
