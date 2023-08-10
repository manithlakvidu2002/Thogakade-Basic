package lk.ijse.thogakadepos.controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.thogakadepos.embedded.OrderDetails;
import lk.ijse.thogakadepos.entity.OrderTM;
import lk.ijse.thogakadepos.entity.Orders;
import lk.ijse.thogakadepos.model.OrderModel;
import lk.ijse.thogakadepos.util.Navigation;
import lk.ijse.thogakadepos.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderFormController {
    @FXML
    public AnchorPane root;
    @FXML
    public TableView <OrderTM>tblOrder;
    @FXML
    private TableColumn tblOrderId;
    @FXML
    private TableColumn tblCusId;
    @FXML
    private TableColumn tblItemcode;
    @FXML
    private TableColumn tblqty;
    @FXML
    private TableColumn tblUnitPrice;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtCustomer;
    @FXML
    private TextField txtItem;
    @FXML
    private TextField txtOrder;

    private OrderModel orderModel;

    public void initialize() throws SQLException, ClassNotFoundException {
        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cusId"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        TableColumn<OrderTM, Button> lastCol = (TableColumn<OrderTM, Button>) tblOrder.getColumns().get(5);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(event -> {
                tblOrder.getItems().remove(param.getValue());
                tblOrder.getSelectionModel().clearSelection();
            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });
    }
    @FXML
    private void navigateToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("../view/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    @FXML
    private void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtQty.getText().isEmpty()  && !txtPrice.getText().isEmpty()  && !txtCustomer.getText().isEmpty() && !txtItem.getText().isEmpty() && !txtOrder.getText().isEmpty()) {

            List<OrderDetails> orderDetail = tblOrder.getItems().stream().map(tm -> new OrderDetails(tm.getQty(), tm.getUnitPrice(), tm.getItemId())).collect(Collectors.toList());
//            int orderId = tblOrder.getSelectionModel().getSelectedItem().getOrderId();
//            int cusId = tblOrder.getSelectionModel().getSelectedItem().getCusId();

            int orderId = Integer.parseInt(txtOrder.getText());
            int cusId = Integer.parseInt(txtCustomer.getText());

            Orders orders = new Orders(orderId, cusId, orderDetail);
            orderModel = new OrderModel();
            int saved = orderModel.saveOrder(orders);
            if (saved > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Saved!!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error while saving Order!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please Fill Details!").show();
        }
    }

    @FXML
    private void btnClearOnAction(ActionEvent actionEvent) {
        txtOrder.setText("");
        txtCustomer.setText("");
        txtItem.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        tblOrder.getItems().clear();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (!txtQty.getText().isEmpty()  && !txtPrice.getText().isEmpty()  && !txtCustomer.getText().isEmpty() && !txtItem.getText().isEmpty() && !txtOrder.getText().isEmpty()) {

            int orderId = Integer.parseInt(txtOrder.getText());
            int cutId = Integer.parseInt(txtCustomer.getText());
            int itemId = Integer.parseInt(txtItem.getText());
            int qty = Integer.parseInt(txtQty.getText());
            double price = Double.parseDouble(txtPrice.getText());

            tblOrder.getItems().add(new OrderTM(orderId, cutId, itemId, qty, price));
        }else {
            new Alert(Alert.AlertType.ERROR, "Please Fill Details!").show();
        }
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, root);
    }
}
