package lk.ijse.thogakadepos.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakadepos.util.Navigation;
import lk.ijse.thogakadepos.util.Routes;

import java.io.IOException;

public class DashboardController {
    public AnchorPane pane;

    public void CustomersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, pane);
    }

    public void ItemsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM, pane);
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACE_ORDER, pane);
    }
}
