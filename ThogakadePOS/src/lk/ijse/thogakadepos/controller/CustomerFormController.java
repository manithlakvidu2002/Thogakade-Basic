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
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
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
